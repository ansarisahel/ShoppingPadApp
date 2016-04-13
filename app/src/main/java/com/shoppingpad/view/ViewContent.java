package com.shoppingpad.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cocosw.bottomsheet.BottomSheet;
import com.shoppingpad.R;
import com.shoppingpad.databinding.ActivityViewContentBinding;
import com.shoppingpad.viewModelHandel.ViewContentHandler;
import com.shoppingpad.viewModelHandel.ViewContentViewModel;

import java.util.ArrayList;
import java.util.List;

// This is java class for view content activity.
public class ViewContent extends AppCompatActivity {

    ViewContentHandler mViewContentHandler;
    String mContentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        mContentId = bundle.getString("mContentId");
        mViewContentHandler = new ViewContentHandler(this);
        new ViewContentAsyncTask().execute(mContentId);

    }


    public void onBackBtnClicked(View view)
    {
        startActivity(new Intent(this,ContentListView.class));
    }

    public void onToolbarImageClicked(View view)
    {
        Toast.makeText(ViewContent.this,"Image Cliked", Toast.LENGTH_SHORT).show();
    }


    class ViewContentAsyncTask extends AsyncTask<String,String,String>
    {
        ViewPager viewPager;
        ViewContentViewModel viewContentViewModelInstance;

        ActivityViewContentBinding binding;
        ProgressDialog progressDialog;
        ImageButton reverseBtn,forwardBtn,fastReverseBtn,fastForwardBtn,pageImageBtn,
                mediaImageBtn,shareImageBtn;
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
            mediaImageBtn = (ImageButton) findViewById(R.id.mediaImageBtn);
            shareImageBtn = (ImageButton) findViewById(R.id.shareImageBtn);
            viewPager = (ViewPager) findViewById(R.id.viewContentViewPager);
            txtPageNumber = (TextView) findViewById(R.id.txtPageNum);
            final List<Fragment> svgImageFragments = getFragments(viewContentViewModelInstance.getmSvgImages());
            final List<Fragment> pngImageFragments = getFragments(viewContentViewModelInstance.getmPngImages());
            viewPager.setAdapter(new ViewContentImagePagerAdapter(getSupportFragmentManager(),svgImageFragments));
            txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+(svgImageFragments.size()));


            pageImageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setAdapter(new ViewContentPagePagerAdapter(getSupportFragmentManager(),viewPager,viewContentViewModelInstance.getmSvgImages(), mContentId, svgImageFragments,txtPageNumber));
                }
            });


            mediaImageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setAdapter(new ViewContentPagePagerAdapter(getSupportFragmentManager(),viewPager,viewContentViewModelInstance.getmPngImages(),mContentId,pngImageFragments,txtPageNumber));
                }
            });

            // share the image of the view pager.
            shareImageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // bottom sheet code
                    new BottomSheet.Builder(ViewContent.this).title("Share On").sheet(R.menu.bottom_sheet).listener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // user has clicked on whatsapp
                            if (which == R.id.whatsapp)
                            {
                                // check if the app is installed or not
                                boolean whatsAppInstalled = appInstalledOrNot("com.whatsapp.android");

                                // if app is installed then just start the app
                                if (whatsAppInstalled) {
                                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.whatsapp.android");
                                    startActivity(intent);
                                }

                                // toast that app is not installed
                                else
                                    Toast.makeText(ViewContent.this, "Whatsapp is not installed", Toast.LENGTH_SHORT).show();
                            }

                            // user clicked on whatsapp
                            if(which == R.id.facebook) {

                                // check if the app is installed or not
                                boolean fbInstalled = appInstalledOrNot("com.facebook.katana");

                                // if app is installed then just start the app
                                if(fbInstalled) {
                                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.android");
                                    startActivity(intent);
                                }

                                // toast that app is not installed
                                else
                                    Toast.makeText(ViewContent.this, "facebook in not installed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).show();
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
                        txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+svgImageFragments.size());
                    }
                }
            });

            // on forwardBtnClick
            forwardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (viewPager.getCurrentItem() < viewPager.getChildCount()) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+svgImageFragments.size());
                    }
                }
            });

            // onFastReverseBtnClick
            fastReverseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(0);
                    txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+svgImageFragments.size());
                }
            });

            // onFastForwardBtnClick
            fastForwardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(viewPager.getChildCount());
                    txtPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+svgImageFragments.size());
                }
            });
        }

        // getting the fragment to display in viewpager
        private List<Fragment> getFragments(String[] images) {
            List<Fragment> fragments = new ArrayList<>();
            for(int i = 0; i < images.length; i++) {
                fragments.add(ViewContentImageFragment.getFragments(images[i], mContentId));
            }
            return fragments;
        }

        // to check if the app is installed or not
        public boolean appInstalledOrNot(String uri)
        {
            boolean appInstalled = false;
            PackageManager pm = getPackageManager();
            try {
                pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES);
                appInstalled = true;
            } catch (PackageManager.NameNotFoundException e) {
                appInstalled = false;
            }
            return appInstalled;
        }

    }

}
