package com.talentica.atm.ui;

import com.talentica.atm.R;
import com.talentica.atm.common.ItemModelCommon;

import android.content.Context;
import android.content.Intent;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.CardFrame;
import android.support.wearable.view.CardScrollView;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ashutosht on 15/04/16.
 */
public class ListItemsGridPagerAdapter extends GridPagerAdapter implements GridViewPager.OnPageChangeListener{

    private ItemModelCommon[] mDataset;
    private final Context mContext;
    private final LayoutInflater mInflater;

    public ListItemsGridPagerAdapter(Context context , ItemModelCommon[] dataset) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataset = dataset;
    }

    public void updateData(ItemModelCommon[] dataset)
    {
        mDataset = dataset;
    }



    @Override
    public int getRowCount() {

        return (mDataset!=null && mDataset.length>0)?mDataset.length:1;
    }

    @Override
    public int getColumnCount(int i) {
        return 1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int row, int i1) {

        final ItemModelCommon item = mDataset[row];
        CardScrollView cardScrollView = (CardScrollView) mInflater.inflate(
                R.layout.gridpager_card, container, false);

        TextView itemNameTextView = (TextView) cardScrollView.findViewById(R.id.item_name_card);
        itemNameTextView.setText(item.getItemName());

        TextView itemStatusTextView = (TextView) cardScrollView.findViewById(R.id.item_status_card);
        itemStatusTextView.setText(item.getItemStatus());

        Button itemActionButton = (Button) cardScrollView.findViewById(R.id.item_button_card);
        itemActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Perform Required Action

                Intent intent = new Intent(mContext, ConfirmationActivity.class);
                intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                        ConfirmationActivity.SUCCESS_ANIMATION);
                intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,"Status Updated");
                mContext.startActivity(intent);

            }
        });
        cardScrollView.setCardGravity(Gravity.BOTTOM);
        container.addView(cardScrollView);
        return cardScrollView;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int i, int i1, Object o) {

        viewGroup.removeView((View)o);

    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }

    @Override
    public void onPageScrolled(int i, int i1, float v, float v1, int i2, int i3) {

    }

    @Override
    public void onPageSelected(int i, int i1) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
