package com.example.android.currencyconverter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Double> {
    public static final String URL = "http://www.apilayer.net/api/live?access_key=c879c9205fb5bc64036a9ceeddb63720&format=1";
    int position1 = 0;
    int postion2 = 0;
    String[] arraySpinner = new String[]{
            "AED United Arab Emirates Dirham",
            "AFN Afghan Afghani",
            "ALL Albanian Lek",
            "AMD Armenian Dram",
            "ANG Netherlands Antillean Guilder",
            "AOA Angolan Kwanza",
            "ARS Argentine Peso",
            "AUD Australian Dollar",
            /*"AWG Aruban Florin",
            "AZN Azerbaijani Manat",
            "BAM Bosnia-Herzegovina Convertible Mark",
            "BBD Barbadian Dollar",*/
            "BDT Bangladeshi Taka",
//            "BGN Bulgarian Lev",
//            "BHD Bahraini Dinar",
//            "BIF Burundian Franc",
//            "BMD Bermudan Dollar",
//            "BND Brunei Dollar",
//            "BOB Bolivian Boliviano",
            "BRL Brazilian Real",
//            "BSD Bahamian Dollar",
            "BTC Bitcoin",
//            "BTN Bhutanese Ngultrum",
//            "BWP Botswanan Pula",
//            "BYN New Belarusian Ruble",
//            "BYR Belarusian Ruble",
//            "BZD Belize Dollar",
//            "CAD Canadian Dollar",
//            "CDF Congolese Franc",
            "CHF Swiss Franc",
//            "CLF Chilean Unit of Account (UF)",
//            "CLP Chilean Peso",
            "CNY Chinese Yuan",
//            "COP Colombian Peso",
//            "CRC Costa Rican Coln",
//            "CUC Cuban Convertible Peso",
//            "CUP Cuban Peso",
//            "CVE Cape Verdean Escudo",
//            "CZK Czech Republic Koruna",
//            "DJF Djiboutian Franc",
//            "DKK Danish Krone",
//            "DOP Dominican Peso",
//            "DZD Algerian Dinar",
//            "EEK Estonian Kroon",
//            "EGP Egyptian Pound",
//            "ERN Eritrean Nakfa",
//            "ETB Ethiopian Birr",
            "EUR Euro",
//            "FJD Fijian Dollar",
//            "FKP Falkland Islands Pound",
            "GBP British Pound Sterling",
//            "GEL Georgian Lari",
//            "GGP Guernsey Pound",
//            "GHS Ghanaian Cedi",
//            "GIP Gibraltar Pound",
//            "GMD Gambian Dalasi",
//            "GNF Guinean Franc",
//            "GTQ Guatemalan Quetzal",
//            "GYD Guyanaese Dollar",
//            "HKD Hong Kong Dollar",
//            "HNL Honduran Lempira",
//            "HRK Croatian Kuna",
//            "HTG Haitian Gourde",
//            "HUF Hungarian Forint",
            "IDR Indonesian Rupiah",
//            "ILS Israeli New Sheqel",
//            "IMP Manx pound",
            "INR Indian Rupee",
//            "IQD Iraqi Dinar",
            "IRR Iranian Rial",
//            "ISK Icelandic Krna",
//            "JEP Jersey Pound",
//            "JMD Jamaican Dollar",
//            "JOD Jordanian Dinar",
            "JPY Japanese Yen",
//            "KES Kenyan Shilling",
//            "KGS Kyrgystani Som",
//            "KHR Cambodian Riel",
//            "KMF Comorian Franc",
//            "KPW North Korean Won",
            "KRW South Korean Won",
//            "KWD Kuwaiti Dinar",
//            "KYD Cayman Islands Dollar",
//            "KZT Kazakhstani Tenge",
//            "LAK Laotian Kip",
//            "LBP Lebanese Pound",
            "LKR Sri Lankan Rupee",
//            "LRD Liberian Dollar",
//            "LSL Lesotho Loti",
//            "LTL Lithuanian Litas",
//            "LVL Latvian Lats",
//            "LYD Libyan Dinar",
//            "MAD Moroccan Dirham",
//            "MDL Moldovan Leu",
//            "MGA Malagasy Ariary",
//            "MKD Macedonian Denar",
//            "MMK Myanma Kyat",
//            "MNT Mongolian Tugrik",
//            "MOP Macanese Pataca",
//            "MRO Mauritanian Ouguiya",
//            "MUR Mauritian Rupee",
//            "MVR Maldivian Rufiyaa",
//            "MWK Malawian Kwacha",
            "MXN Mexican Peso",
//            "MYR Malaysian Ringgit",
//            "MZN Mozambican Metical",
//            "NAD Namibian Dollar",
            "NGN Nigerian Naira",
//            "NIO Nicaraguan Crdoba",
//            "NOK Norwegian Krone",
//            "NPR Nepalese Rupee",
            "NZD New Zealand Dollar",
//            "OMR Omani Rial",
//            "PAB Panamanian Balboa",
//            "PEN Peruvian Nuevo Sol",
//            "PGK Papua New Guinean Kina",
//            "PHP Philippine Peso",
//            "PKR Pakistani Rupee",
            "PLN Polish Zloty",
//            "PYG Paraguayan Guarani",
//            "QAR Qatari Rial",
//            "RON Romanian Leu",
//            "RSD Serbian Dinar",
            "RUB Russian Ruble",
//            "RWF Rwandan Franc",
//            "SAR Saudi Riyal",
//            "SBD Solomon Islands Dollar",
//            "SCR Seychellois Rupee",
//            "SDG Sudanese Pound",
            "SEK Swedish Krona",
//            "SGD Singapore Dollar",
//            "SHP Saint Helena Pound",
//            "SLL Sierra Leonean Leone",
//            "SOS Somali Shilling",
//            "SRD Surinamese Dollar",
//            "STD So Tom and Prncipe Dobra",
//            "SVC Salvadoran Coln",
//            "SYP Syrian Pound",
//            "SZL Swazi Lilangeni",
            "THB Thai Baht",
//            "TJS Tajikistani Somoni",
//            "TMT Turkmenistani Manat",
//            "TND Tunisian Dinar",
//            "TOP Tongan Paanga",
//            "TRL Turkish Lira",
//            "TTD Trinidad and Tobago Dollar",
            "TWD New Taiwan Dollar",
//            "TZS Tanzanian Shilling",
//            "UAH Ukrainian Hryvnia",
//            "UGX Ugandan Shilling",
            "USD United States Dollar",
//            "UYU Uruguayan Peso",
//            "UZS Uzbekistan Som",
//            "VEF Venezuelan Bolvar Fuerte",
//            "VND Vietnamese Dong",
//            "VUV Vanuatu Vatu",
//            "WST Samoan Tala",
//            "XAF CFA Franc BEAC",
//            "XAG Silver (troy ounce)",
//            "XAU Gold (troy ounce)",
//            "XCD East Caribbean Dollar",
//            "XDR Special Drawing Rights",
//            "XOF CFA Franc BCEAO",
//            "XPF CFP Franc",
//            "YER Yemeni Rial",
            "ZAR South African Rand",
//            "ZMK Zambian Kwacha (pre-2013)",
//            "ZMW Zambian Kwacha",
//            "ZWL Zimbabwean Dollar"
    };
    String frm = "USDAED";
    String too = "USDAED";
    Spinner to;
    Spinner from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        from = (Spinner) findViewById(R.id.spinner3);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpinner);*/
        final ArrayList<Country> countries = new ArrayList<>();
        for (int i = 0; i < arraySpinner.length; i++) {
            Country country = new Country(arraySpinner[i], getDrawable(this, arraySpinner[i].substring(0, 3).toLowerCase()));
            countries.add(country);
        }

        CountryAdapter countryAdapter = new CountryAdapter(MainActivity.this, countries);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from.setAdapter(countryAdapter);
        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country selection1 = (Country) parent.getItemAtPosition(position);
                String selection = selection1.getmCurrencyName();
                if (!TextUtils.isEmpty(selection)) {
                    frm = "USD" + selection.substring(0, 3);
                    Log.v("MainActivity.this", selection);
                }

            }


            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                frm = "USDAED";
            }
        });
        to = (Spinner) findViewById(R.id.spinner4);
        to.setAdapter(countryAdapter);
        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country selection1 = (Country) parent.getItemAtPosition(position);
                String selection = selection1.getmCurrencyName();
                if (!TextUtils.isEmpty(selection)) {
                    too = "USD" + selection.substring(0, 3);
                    Log.v("MainActivity.this", selection);

                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                too = "USDAED";
            }
        });
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, MainActivity.this);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int spinner1Index = from.getSelectedItemPosition();

                from.setSelection(to.getSelectedItemPosition());
                to.setSelection(spinner1Index);

            }
        });

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                EditText edt = (EditText) findViewById(R.id.editText);
                TextView tv = (TextView) findViewById(R.id.con);
                if (edt.getText().toString().equals("0") || edt.getText().toString().equals("0.0")) {
                    tv.setText("" + 0.0);
                } else {
                    if (networkInfo != null && networkInfo.isConnected()) {
                        LoaderManager loaderManager = getLoaderManager();
                        loaderManager.restartLoader(1, null, MainActivity.this);
                    } else {
                        Toast.makeText(MainActivity.this, "Please Check your Internet Connection.", Toast.LENGTH_LONG).show();
                        Log.v("MainActivity.this", "No internet Connection");
                    }
                }
            }
        });
    }

        public static int getDrawable(Context context, String name) {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);
        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }

    @Override
    public Loader<Double> onCreateLoader(int id, Bundle args) {
        Loader loader = new DataLoader(MainActivity.this, too, frm, URL);
        return loader;
    }


    @Override
    public void onLoadFinished(Loader<Double> loader, Double data) {
        TextView tv = (TextView) findViewById(R.id.con);
        EditText edt = (EditText) findViewById(R.id.editText);
        String a = edt.getText().toString();
        try {
            Double a1 = Double.parseDouble(a);
            double ans = a1 * data;
            tv.setText("" + ans);
        } catch (NumberFormatException e) {
            tv.setText("" + data);
        }

    }

    @Override
    public void onLoaderReset(Loader<Double> loader) {
        TextView tv = (TextView) findViewById(R.id.con);
        tv.setText("");
    }
}