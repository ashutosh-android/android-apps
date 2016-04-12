package com.talentica.atm.ui;

import com.talentica.atm.R;
import com.talentica.atm.common.ItemModelCommon;
import com.talentica.atm.common.ListModelCommon;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ashutosht on 12/04/16.
 */
public class ListItemsAdapter extends WearableListView.Adapter {
    private ItemModelCommon[] mDataset;
    private final Context mContext;
    private final LayoutInflater mInflater;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListItemsAdapter(Context context, ItemModelCommon[] dataset) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataset = dataset;
    }


    public void updateData(ItemModelCommon[] dataset)
    {
        mDataset = dataset;
    }

    // Provide a reference to the type of views you're using
    public static class ItemViewHolder extends WearableListView.ViewHolder {
        private TextView textViewItemName;
        private TextView textViewItemStatus;
        private TextView textViewItemId;
        public ItemViewHolder(View itemView) {
            super(itemView);
            // find the text view within the custom item's layout
            textViewItemName = (TextView) itemView.findViewById(R.id.item_name);
            textViewItemStatus = (TextView) itemView.findViewById(R.id.item_status);
            textViewItemId = (TextView) itemView.findViewById(R.id.item_key);
        }
    }

    // Create new views for list items
    // (invoked by the WearableListView's layout manager)
    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // Inflate our custom layout for list items
        return new ItemViewHolder(mInflater.inflate(R.layout.single_item, null));
    }

    // Replace the contents of a list item
    // Instead of creating new views, the list tries to recycle existing ones
    // (invoked by the WearableListView's layout manager)
    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder,
                                 int position) {
        // retrieve the text view
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        TextView itemName = itemHolder.textViewItemName;
        TextView itemStatus = itemHolder.textViewItemStatus;
        TextView itemId = itemHolder.textViewItemId;

        // replace text contents
        itemName.setText(mDataset[position].getItemName());
        itemStatus.setText(mDataset[position].getItemStatus());
        itemId.setText(mDataset[position].getItemId());
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
