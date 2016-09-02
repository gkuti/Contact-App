package com.andela.gkuti.task2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * AddDialog class
 */
public class AddDialog extends DialogFragment {
    private Dialog dialog;
    private Button cancelButton;
    private Button okButton;
    private TextView name;
    private TextView address;
    private TextView phone;
    private TextView email;
    private ContactCallback callback;

    public void setCallback(ContactCallback callback) {
        this.callback = callback;
    }

    /**
     * method called when the dialog is about to start
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.number_dialog_v21);
        initViews();
        return dialog;
    }

    /**
     * method for initializing views
     */
    private void initViews() {
        cancelButton = (Button) dialog.findViewById(R.id.cancel);
        okButton = (Button) dialog.findViewById(R.id.add);
        email = (TextView) dialog.findViewById(R.id.email);
        phone = (TextView) dialog.findViewById(R.id.phone);
        address = (TextView) dialog.findViewById(R.id.address);
        name = (TextView) dialog.findViewById(R.id.name);
        assignClickHandlers();
    }

    /**
     * method for setting listeners to buttons
     */
    private void assignClickHandlers() {
        cancelButton.setOnClickListener(cancelListener);
        okButton.setOnClickListener(addListener);
    }

    private View.OnClickListener cancelListener = new View.OnClickListener() {
        /**
         * method called when the cancel button is clicked
         */
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    };

    private View.OnClickListener addListener = new View.OnClickListener() {
        /**
         * method called when the ok button is clicked
         */
        @Override
        public void onClick(View v) {
            ContactStore contactStore = new ContactStore(getContext());
            contactStore.saveData(name.getText().toString(), phone.getText().toString(), address.getText().toString(), email.getText().toString());
            callback.onContactAdded();
            dialog.dismiss();
        }
    };

}
