package com.shoppingpad.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shoppingpad.R;
import com.shoppingpad.databinding.ActivityViewContentBinding;
import com.shoppingpad.viewModelHandel.ViewContentHandler;
import com.shoppingpad.viewModelHandel.ViewContentViewModel;

// This is java class for view content activity.
public class ViewContent extends ActionBarActivity {

    ViewContentHandler mViewContentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_view_content);

        Bundle bundle = getIntent().getExtras();
        String contentId = bundle.getString("mContentId");

        mViewContentHandler = new ViewContentHandler(this);
        new ViewContentAsyncTask().execute(contentId);

    }

    public void onBackBtnClicked(View view)
    {
        startActivity(new Intent(this,ContentListView.class));
    }

    public void onToolbarImageClicked(View view)
    {
        Toast.makeText(ViewContent.this,"Image Cliked", Toast.LENGTH_SHORT).show();
    }

    public void onImageBtnClicked(View view)
    {
        Toast.makeText(ViewContent.this, Integer.toString(view.getId()) , Toast.LENGTH_SHORT).show();
    }


    class ViewContentAsyncTask extends AsyncTask<String,String,String>
    {
        ViewPager viewPager;
        ViewContentViewModel viewContentViewModelInstance;
        ActivityViewContentBinding binding;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ViewContent.this);
            progressDialog.setTitle("Downloading");
            progressDialog.setMessage("Downloading Zip File");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            viewContentViewModelInstance = mViewContentHandler.getRequiredDataForViewContent(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            binding = DataBindingUtil.setContentView(ViewContent.this,R.layout.activity_view_content);
            binding.setViewContentData(viewContentViewModelInstance);
            binding.executePendingBindings();
            progressDialog.dismiss();
            viewPager = (ViewPager) findViewById(R.id.viewContentViewPager);
            viewPager.setAdapter(new ViewContentPagerAdapter(getSupportFragmentManager()));
        }
    }
}
