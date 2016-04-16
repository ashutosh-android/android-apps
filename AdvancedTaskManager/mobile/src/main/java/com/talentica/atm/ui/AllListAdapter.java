package com.talentica.atm.ui;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.talentica.atm.R;
import com.talentica.atm.model.ListModel;
import com.talentica.atm.utils.Utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ashutosht on 25/03/16.
 */
public class AllListAdapter extends FirebaseListAdapter<ListModel> {

    public AllListAdapter(Activity activity, Class<ListModel> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);

        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, ListModel listModel, int i) {

        TextView textViewListName = (TextView) view.findViewById(R.id.text_view_list_name);
//        TextView textViewCreatedByUser = (TextView) view.findViewById(R.id.text_view_created_by_user);
//        TextView textViewListType = (TextView) view.findViewById(R.id.text_view_list_type);
        TextView textViewLastEdited = (TextView) view.findViewById(R.id.text_view_edit_time);
        ImageView imageViewListType = (ImageView) view.findViewById(R.id.image_view_list_type);

        textViewListName.setText(listModel.getListName());
//        textViewCreatedByUser.setText(listModel.getOwner());
//        textViewListType.setText(listModel.getListType());
        textViewLastEdited.setText(Utils.getFormattedTime(listModel.getTimestampCreatedLong()));

        if(listModel.getListType().equalsIgnoreCase("Personal"))
        {
            imageViewListType.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.icon_personal));
        }



    }
}
