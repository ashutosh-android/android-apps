package com.talentica.atm.ui;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.talentica.atm.R;
import com.talentica.atm.model.ItemModel;
import com.talentica.atm.model.ListModel;
import com.talentica.atm.utils.Utils;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ashutosht on 01/04/16.
 */
public class ListItemAdapter extends FirebaseListAdapter<ItemModel> {

    private String mListId;
    private ListModel mListModel;

    public ListItemAdapter(Activity activity, Class<ItemModel> modelClass, int modelLayout, Query ref,String listId) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
        this.mListId = listId;
    }

    public void updateListModel(ListModel listModel)
    {
        this.mListModel = listModel;
        this.notifyDataSetChanged();
    }


    @Override
    protected void populateView(View view, ItemModel itemModel, int i) {

        TextView textViewItemName = (TextView) view.findViewById(R.id.text_view_item_name);
        TextView textViewItemStatus = (TextView) view.findViewById(R.id.text_view_work_item_status);
        TextView textViewTimeStamp = (TextView) view.findViewById(R.id.text_view_item_edit_time);

        textViewItemName.setText(itemModel.getItemName());
        textViewItemStatus.setText(itemModel.getItemStatus());
        textViewTimeStamp.setText(Utils.getFormattedTime(itemModel.getTimestampLastChangedLong()));


    }
}
