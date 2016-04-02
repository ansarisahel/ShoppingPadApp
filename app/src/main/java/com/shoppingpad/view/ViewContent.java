package com.shoppingpad.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.shoppingpad.R;
import com.shoppingpad.databinding.ActivityViewContentBinding;
import com.shoppingpad.viewModelHandel.ViewContentHandler;
import com.shoppingpad.viewModelHandel.ViewContentViewModel;

import java.util.ArrayList;
import java.util.List;

// This is java class for view content activity.
public class ViewContent extends AppCompatActivity {

    ViewContentHandler mViewContentHandler;
//    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_content);

        Bundle bundle = getIntent().getExtras();
        String contentId = bundle.getString("mContentId");
        mViewContentHandler = new ViewContentHandler(this);
        new ViewContentAsyncTask().execute(contentId);
//        viewPager = (ViewPager) findViewById(R.id.viewContentViewPager);
//        viewPager.setAdapter(new ViewContentImagePagerAdapter(getSupportFragmentManager()));

    }

    public void onPageBtnClicked(View view)
    {

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
        ImageButton reverseBtn,forwardBtn,fastReverseBtn,fastForwardBtn,pageImageBtn;
        TextView txtPageNumber;

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
            reverseBtn = (ImageButton) findViewById(R.id.reverseImageBtn);
            forwardBtn = (ImageButton) findViewById(R.id.forwardImageBtn);
            fastReverseBtn = (ImageButton) findViewById(R.id.fastReverseimageBtn);
            fastForwardBtn = (ImageButton) findViewById(R.id.fastForwardImageBtn);
            pageImageBtn = (ImageButton) findViewById(R.id.pageImageBtn);
            viewPager = (ViewPager) findViewById(R.id.viewContentViewPager);
            txtPageNumber = (TextView) findViewById(R.id.txtPageNum);
            final List<Fragment> fragments = getFragments();
            viewPager.setAdapter(new ViewContentImagePagerAdapter(getSupportFragmentManager(),fragments));
            txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+(viewPager.getChildCount()+1));

            pageImageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setAdapter(new ViewContentPagePagerAdapter(getSupportFragmentManager()));
                }
            });


            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                int lastPage;
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                   txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+(viewPager.getChildCount()));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });



            // on reverseBtnClick
            reverseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(viewPager.getCurrentItem() > 0) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                        int count = viewPager.getChildCount()+1;
                        Log.e("count",""+count);
                        txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+(fragments.size()));
                    }
                }
            });

            // on forwardBtnClick
            forwardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (viewPager.getCurrentItem() < viewPager.getChildCount()) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        int count = viewPager.getChildCount()+1;
                        Log.e("count",""+count);
                        txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+(viewPager.getChildCount()+1));
                    }
                }
            });

            // onFastReverseBtnClick
            fastReverseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(0);
                    int count2 = viewPager.getChildCount()+1;
                    Log.e("count",""+count2);
                    txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+(viewPager.getChildCount()+1));
                }
            });

            //
            fastForwardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(viewPager.getChildCount());
                    int count3 = viewPager.getChildCount()+1;
                    Log.e("count",""+count3);
                    txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+(viewPager.getChildCount()+1));
                }
            });
        }

        private List<Fragment> getFragments() {
            List<Fragment> fragments = new ArrayList<>();
            fragments.add(ViewContentImageFragment.getFragments(R.drawable.facebook_f_logo));
            fragments.add(ViewContentImageFragment.getFragments(R.drawable.message));
            fragments.add(ViewContentImageFragment.getFragments(R.drawable.fast_forward));
            return fragments;
        }

        public void onPageBtnClicked1()
        {
            Toast.makeText(ViewContent.this, "page Btn Clicked", Toast.LENGTH_SHORT).show();
        }

    }
}
