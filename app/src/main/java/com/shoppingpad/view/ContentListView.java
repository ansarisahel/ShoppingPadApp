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
import com.shoppingpad.viewModelHandel.ContentViewModel;

import java.util.List;

// purpose:
// 1:This is the view class whenever user perform any action on the UI,
// the respective method of view gets called here in this class,
// 2:It passes to the view model for further processing.
// 3: This will also take the data returned by the view model and populate
//    the data in the respective view

public class ContentListView extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<ContentViewModel> mContentViewModelList;
    ContentListViewModel mContentListViewModelInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // making instance of ContentListViewModel class
       // mContentListViewModelInstance = new ContentListViewModel(this);

        // getting the list in the contentListViewModel class
       // mContentViewModelList = mContentListViewModelInstance.mContentListViewList;

        // getting reference of recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
       // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setAdapter(new ContentListViewAdapter(mContentListViewModelInstance));

        new MyNewThread().execute();

    }


    class MyNewThread extends AsyncTask<String,String,String>
    {
        ProgressDialog progressDialog = new ProgressDialog(ContentListView.this);
        @Override
        protected String doInBackground(String... strings) {

            // making instance of ContentListViewModel class
             mContentListViewModelInstance = new ContentListViewModel(ContentListView.this);

            // getting the list in the contentListViewModel class
            // mContentViewModelList = mContentListViewModelInstance.mContentListViewList;
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
            mRecyclerView.setLayoutManager(new LinearLayoutManager(ContentListView.this));
            mRecyclerView.setAdapter(new ContentListViewAdapter(mContentListViewModelInstance,getLayoutInflater()));

        }
    }
}
