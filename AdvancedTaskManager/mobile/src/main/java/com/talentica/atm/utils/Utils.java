package com.talentica.atm.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ashutosht on 03/04/16.
 */
public class Utils {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Context mContext = null;


    /**
     * Public constructor that takes mContext for later use
     */
    public Utils(Context con) {
        mContext = con;
    }

    public static String getFormattedTime(long timeInMs)
    {
        Date date = new Date(timeInMs);

        return SIMPLE_DATE_FORMAT.format(date);
    }
}
