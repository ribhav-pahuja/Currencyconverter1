package com.example.android.currencyconverter;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by pahuj on 06-07-2017.
 */

public class QueryUtils {
    private static final String LOG_TAG=QueryUtils.class.getName();
    private QueryUtils() {

    }


    private static URL createUrlObject(String url) {
        URL url1 = null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException mfue) {
            mfue.printStackTrace();
        }
        return url1;
    }
    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse="";
        if(url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try{
            urlConnection=(HttpURLConnection) url.openConnection();

            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException  {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    public static Double fetchConversion(String requestUrl,String from,String too){
        URL url = createUrlObject(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("Queryutils ", "Error closing input stream", e);
        }
        return extractConversionRate(jsonResponse,from,too);

    }

    private static Double extractConversionRate(String jsonob,String from,String too) {
        if (TextUtils.isEmpty(jsonob)) {
            return 1.0;
        }
        //ArrayList<Double> ans=null ;
        double ans=1.0;
        try {
            JSONObject root = new JSONObject(jsonob);
            //ans=new ArrayList<>();
            JSONObject quotes= root.getJSONObject("quotes");
//            for(int i =0;i<countries.size();i++){
//                Double ans1 =quotes.getDouble(countries.get(i).getmCurrencyName().substring(0,3));
//                ans.add(ans1);
            double usdfrm=quotes.getDouble(from);
            double usdto=quotes.getDouble(too);
            ans=usdto/usdfrm;
//            }

            return ans;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
