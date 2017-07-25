package com.example.android.currencyconverter;


/**
 * Created by pahuj on 11-07-2017.
 */


public class Country {
    private String mCurrencyName;
    int mflag;

    public Country(String CurrencyName,int flag){
        mCurrencyName=CurrencyName;
        mflag=flag;

    }

    public String getmCurrencyName() {
        return mCurrencyName;
    }

    public int getMflag() {
        return mflag;
    }
}