package com.shoppingpad.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.shoppingpad.R;
import com.shoppingpad.viewModelHandel.ContentListViewModel;

// purpose:
// 1:This is the view class whenever user perform any action on the UI,
// the respective method of view gets called here in this class,
// 2:It passes to the view model for further processing.
// 3: This will also take the data returned by the view model and populate
//    the data in the respective view

public class ContentListView extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ContentListViewModel mContentListViewModelInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.viewContentToolbar);
        setSupportActionBar(toolbar);

        // getting reference of recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);

        // making instance of ContentListViewModel class
        mContentListViewModelInstance = new ContentListViewModel(ContentListView.this);

        // calling Async Task so that all the work happens in another thread
        // i.e calling JSON data from REST.
        new ContentListViewTask().execute();

    }

    class ContentListViewTask extends AsyncTask<String,String,String>
    {
        ProgressDialog progressDialog = new ProgressDialog(ContentListView.this);
        @Override
        protected String doInBackground(String... strings) {

             mContentListViewModelInstance.getRequiredDataForAdapter();
            
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("loading");
            progressDialog.setMessage("Downloading contents");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            // setting the layout for the recyclerView
            mRecyclerView.setLayoutManager(new LinearLayoutManager(ContentListView.this));

            // setting the adapter for the recyclerView
            mRecyclerView.setAdapter(new ContentListViewAdapter
                                (mContentListViewModelInstance,ContentListView.this));

        }
    }
}
