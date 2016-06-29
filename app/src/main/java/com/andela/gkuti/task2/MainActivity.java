package com.andela.gkuti.task2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import static com.andela.gkuti.task2.Constants.ADDRESS_COLUMN;
import static com.andela.gkuti.task2.Constants.EMAIL_COLUMN;
import static com.andela.gkuti.task2.Constants.NAME_COLUMN;
import static com.andela.gkuti.task2.Constants.PHONE_COLUMN;

public class MainActivity extends AppCompatActivity implements ContactCallback {
    private ArrayList<Contact> contacts;
    private ContactStore contactStore;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contactStore = new ContactStore(this);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDialog addDialog = new AddDialog();
                addDialog.setCallback(MainActivity.this);
                addDialog.show(getSupportFragmentManager(), "Contact");
            }
        });
        contacts = new ArrayList<>();
        getContacts();
        contactAdapter = new ContactAdapter(contacts, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);
        recyclerView.addItemDecoration(new Decorator(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getContacts() {
        contacts.clear();
        Cursor cursor = contactStore.getAllData();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(NAME_COLUMN.getValue()));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL_COLUMN.getValue()));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS_COLUMN.getValue()));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE_COLUMN.getValue()));
            Contact contact = new Contact(name, address, phone, email);
            contacts.add(contact);
            cursor.moveToNext();
        }
    }

    @Override
    public void onContactAdded() {
        getContacts();
        contactAdapter.notifyDataSetChanged();
    }
}
