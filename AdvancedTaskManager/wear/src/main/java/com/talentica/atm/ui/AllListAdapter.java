package com.talentica.atm.ui;

import com.talentica.atm.R;
import com.talentica.atm.common.ListModelCommon;

import org.w3c.dom.Text;

import android.content.Context;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ashutosht on 04/04/16.
 */
public class AllListAdapter extends WearableListView.Adapter {
        private ListModelCommon[] mDataset;
        public final Context mContext;
        private final LayoutInflater mInflater;

        // Provide a suitable constructor (depends on the kind of dataset)
        public AllListAdapter(Context context, ListModelCommon[] dataset) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mDataset = dataset;
        }


        public void updateData(ListModelCommon[] dataset)
        {
            mDataset = dataset;
        }

        public static void createNotification(String message)
        {


        }

        // Provide a reference to the type of views you're using
        public static class ItemViewHolder extends WearableListView.ViewHolder {
            private TextView textViewListName;
            //private TextView textViewListType;
            private TextView textViewListId;
            private CircledImageView mCircledImageView;
            public ItemViewHolder(View itemView) {
                super(itemView);
                // find the text view within the custom item's layout
                textViewListName = (TextView) itemView.findViewById(R.id.list_name);
                //textViewListType = (TextView) itemView.findViewById(R.id.list_type);
                textViewListId = (TextView) itemView.findViewById(R.id.list_key);
                mCircledImageView = (CircledImageView)itemView.findViewById(R.id.imageView);
                mCircledImageView.setImageCirclePercentage(10);

            }
        }

        // Create new views for list items
        // (invoked by the WearableListView's layout manager)
        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
            // Inflate our custom layout for list items
            return new ItemViewHolder(mInflater.inflate(R.layout.single_list, null));
        }

        // Replace the contents of a list item
        // Instead of creating new views, the list tries to recycle existing ones
        // (invoked by the WearableListView's layout manager)
        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder,
                                     int position) {
            // retrieve the text view
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            TextView listName = itemHolder.textViewListName;
            //TextView listType = itemHolder.textViewListType;
            TextView listId = itemHolder.textViewListId;
            CircledImageView circledImageView = itemHolder.mCircledImageView;

            // replace text contents
            listName.setText(mDataset[position].getListName());
            //listType.setText(mDataset[position].getListType());
            listId.setText(mDataset[position].getListId());
            circledImageView.setImageCirclePercentage(10);
            if(mDataset[position].getListType().equalsIgnoreCase("Personal"))
            {
                circledImageView.setImageResource(R.drawable.home);
            }
            else
            {
                circledImageView.setImageResource(R.drawable.work);
            }

            //circledImageView.
            // replace list item's metadata
            holder.itemView.setTag(position);
        }

        // Return the size of your dataset
        // (invoked by the WearableListView's layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }

}
