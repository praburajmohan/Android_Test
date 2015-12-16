package com.cts.androidtest.threads;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import com.cts.androidtest.adapter.FactsAdapter;
import com.cts.androidtest.model.FactsBean;
import com.cts.androidtest.servicehandler.FactsServiceHandler;
import com.cts.androidtest.util.JsonParserUtil;

import java.util.ArrayList;

/*
* Facts Asynchronous task to make standard service call of Http Request  */
public class FactsAsyncTask extends AsyncTask<String, String, ArrayList<FactsBean>> {

    private ProgressDialog progressDialog;
    private ActionBar actionBar = null;
    private Context context;
    private FactsAdapter mFactsAdapter;
    private static final String DOWNLOADING_STATIC_TEXT = "Downloading...";


    public FactsAsyncTask(Context context, ActionBar actionbar, FactsAdapter factsAdapter) {
        this.actionBar = actionbar;
        this.context = context;
        mFactsAdapter = factsAdapter;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(DOWNLOADING_STATIC_TEXT);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected ArrayList<FactsBean> doInBackground(String... params) {
        String resultJson = FactsServiceHandler.readInputStreamToString(params[0]);
        return JsonParserUtil.jsonParsing(resultJson);
    }

    @Override
    protected void onPostExecute(ArrayList<FactsBean> factsBeanListFromBackground) {
        progressDialog.dismiss();
        actionBar.setTitle(JsonParserUtil.title);
        actionBar.show();
        mFactsAdapter.setAdapter(factsBeanListFromBackground);
    }
}


