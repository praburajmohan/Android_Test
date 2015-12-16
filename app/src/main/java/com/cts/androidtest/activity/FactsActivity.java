package com.cts.androidtest.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.cts.androidtest.R;
import com.cts.androidtest.adapter.FactsAdapter;
import com.cts.androidtest.constants.FactsConstants;
import com.cts.androidtest.model.FactsBean;
import com.cts.androidtest.threads.FactsAsyncTask;
import com.cts.androidtest.util.NetworkUtil;

import java.util.ArrayList;

public class FactsActivity extends Activity {

    private ArrayList<FactsBean> mFactsList;

    private ListView mFactsListView;
    ActionBar mActionbar = null;
    public FactsAdapter mFactsAdapter;
    private Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        mActionbar = getActionBar();

        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        int actionBarColor = getResources().getColor(R.color.colorRedActionBar);
        mActionbar.setBackgroundDrawable(new ColorDrawable(actionBarColor));

        mFactsAdapter = new FactsAdapter(this, null);

        mFactsListView = (ListView) findViewById(R.id.factsListView);

        mFactsListView.setAdapter(mFactsAdapter);

        makeServiceCall(this, mActionbar, mFactsAdapter, FactsConstants.FACTS_URL);
    }

    /**
     * This method is used to call the facts webservice
     */
    private void makeServiceCall(Context context, ActionBar actionBar, FactsAdapter factsAdapter, String factsUrl){
        if(NetworkUtil.isOnline(FactsActivity.this)) {
            new FactsAsyncTask(context, actionBar, factsAdapter).execute(factsUrl);
        }else{
            Toast.makeText(FactsActivity.this, "No network connectivity", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRefresh:
                makeServiceCall(this, mActionbar, mFactsAdapter, FactsConstants.FACTS_URL);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
