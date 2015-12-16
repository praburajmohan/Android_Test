package com.cts.androidtest.servicehandler;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/*FactsServiceHandler class used to make HttpsURLConnection(Facts URL)*/
public class FactsServiceHandler {

    private static final String TAG = "Facts";

    public static String readInputStreamToString(String requestedURL) {

        HttpsURLConnection httpURLConnection = getRequestedHttpUrlConnection(requestedURL);

        String result = getResponse(httpURLConnection);

        return result;
    }

    private static HttpsURLConnection getRequestedHttpUrlConnection(String requestedURL){
        URL getStocksURL = null;
        HttpsURLConnection httpURLConnection = null;
        try {
            getStocksURL = new URL(requestedURL);
            httpURLConnection = (HttpsURLConnection) getStocksURL.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpURLConnection;
    }

    private static String getResponse(HttpsURLConnection httpURLConnection){
        String result = null;
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;

        try {
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            result = stringBuffer.toString();
        }
        catch (Exception e) {
            Log.i(TAG, "Error reading InputStream");
            result = null;
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    Log.i(TAG, "Error closing InputStream");
                }
            }
        }

        return result;
    }
}
