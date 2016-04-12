package com.talentica.atm.ui;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.talentica.atm.R;
import com.talentica.atm.model.ItemModel;
import com.talentica.atm.model.ListModel;
import com.talentica.atm.utils.Constants;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by ashutosht on 01/04/16.
 */
public class AddNewItemDialog extends DialogFragment {

    EditText mEditTextItemName;
    String mListId;

    public static AddNewItemDialog newInstance() {

        AddNewItemDialog addnewItemDialog = new AddNewItemDialog();
        Bundle bundle = new Bundle();
        addnewItemDialog.setArguments(bundle);
        return addnewItemDialog;
    }

    public void setListId(String listId)
    {
        this.mListId = listId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_item, null);
        mEditTextItemName = (EditText) rootView.findViewById(R.id.edit_text_item_name);



        mEditTextItemName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    addNewItem();
                }
                return true;
            }
        });

        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout*/
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton(R.string.positive_button_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addNewItem();
                    }
                });

        return builder.create();


    }

    public void addNewItem() {
        // Get the string that the user entered into the EditText and make an object with it
        // We'll use "Anonymous Owner" for the owner because we don't have user accounts yet
        String itemName = mEditTextItemName.getText().toString();
        String itemStatus = "Pending";

        /**
         * If EditText input is not empty
         */
        if (!itemName.equals("")) {

            /**
             * Create Firebase references
             */
            Firebase itemsRef = new Firebase(Constants.FIREBASE_URL_LIST_ITEMS).child(mListId);
            Firebase newItemRef = itemsRef.push();

            /* Save listsRef.push() to maintain same random Id */
            final String itemId = newItemRef.getKey();

            /**
             * Set raw version of date to the ServerValue.TIMESTAMP value and save into
             * timestampCreatedMap
             */
            HashMap<String, Object> timestampCreated = new HashMap<>();
            timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

            /* Build the new list */

            ItemModel newItem = new ItemModel(itemName,itemStatus,timestampCreated);

            /* Add the shopping list */
            newItemRef.setValue(newItem);

            /* Close the dialog fragment */
            AddNewItemDialog.this.getDialog().cancel();
        }

    }
}
