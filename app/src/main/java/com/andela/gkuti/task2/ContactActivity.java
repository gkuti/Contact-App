package com.andela.gkuti.task2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import static com.andela.gkuti.task2.Constants.*;

public class ContactActivity extends AppCompatActivity {
    private TextView contactName;
    private TextView contactEmail;
    private TextView contactAddress;
    private TextView contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        String name = getIntent().getExtras().getString("name");
        ContactStore contactStore = new ContactStore(this);
        Cursor cursor = contactStore.getContactByDate(name);
        cursor.moveToFirst();
        contactName = (TextView) findViewById(R.id.contact_name);
        contactEmail = (TextView) findViewById(R.id.contact_email);
        contactAddress = (TextView) findViewById(R.id.contact_address);
        contactPhone = (TextView) findViewById(R.id.contact_phone);
        contactName.setText(cursor.getString(cursor.getColumnIndex(NAME_COLUMN.getValue())));
        contactAddress.setText(cursor.getString(cursor.getColumnIndex(ADDRESS_COLUMN.getValue())));
        contactEmail.setText(cursor.getString(cursor.getColumnIndex(EMAIL_COLUMN.getValue())));
        contactPhone.setText(cursor.getString(cursor.getColumnIndex(PHONE_COLUMN.getValue())));
    }
}
