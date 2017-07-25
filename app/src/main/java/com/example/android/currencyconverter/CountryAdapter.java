package com.example.android.currencyconverter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by pahuj on 11-07-2017.
 */

public class CountryAdapter extends ArrayAdapter<Country> {

    public CountryAdapter(@NonNull Context context, @NonNull ArrayList<Country> countries) {
        super(context, 0, countries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Country currentcountry = getItem(position);
        TextView currency = (TextView) listItemView.findViewById(R.id.tv);
        currency.setText(" " + currentcountry.getmCurrencyName());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imag);
        imageView.setImageResource(currentcountry.getMflag());
        return listItemView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Country currentcountry = getItem(position);
        TextView currency = (TextView) listItemView.findViewById(R.id.tv);
        currency.setText("" + currentcountry.getmCurrencyName());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imag);
        imageView.setImageResource(currentcountry.getMflag());
        return listItemView;
    }
}