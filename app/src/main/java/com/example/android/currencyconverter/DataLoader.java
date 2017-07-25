package com.example.android.currencyconverter;

import android.content.Context;
/*
 * Created by pahuj on 06-07-2017.
 */

public class DataLoader extends android.content.AsyncTaskLoader<Double> {
    String mUrl;
    private String mtoo;
    // ArrayList<Country> countries;
    private String mfrom;
    public DataLoader(Context context,String too,String From,String Url) {
        super(context);
        mtoo=too;
        mfrom=From;
        mUrl=Url;
    }

    @Override
    protected void onStartLoading() {
        /*super.onStartLoading();*/
        forceLoad();
    }

    @Override
    public Double loadInBackground() {

        return QueryUtils.fetchConversion( mUrl,mfrom,mtoo);
    }

}