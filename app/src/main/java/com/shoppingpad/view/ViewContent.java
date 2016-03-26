package com.shoppingpad.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shoppingpad.R;
import com.shoppingpad.viewModelHandel.ViewContentHandler;

// This is java class for view content activity.
public class ViewContent extends ActionBarActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    ViewContentHandler mViewContentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_content);

      //  Intent intent = getIntent();
      //  Bitmap image = (Bitmap)intent.getParcelableExtra("image");
        viewPager = (ViewPager) findViewById(R.id.viewContentViewPager);
        Bundle bundle = getIntent().getExtras();
        byte[] image = bundle.getByteArray("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(image,0,image.length);
        String title = bundle.getString("title");
        String noOfViews = bundle.getString("noOfViews");
        String noOfParticipants = bundle.getString("noOfParticipants");
        String contentId = bundle.getString("mContentId");

        // getting reference of the toolbar in the view content activity
        toolbar = (Toolbar) findViewById(R.id.viewContentToolbar);
       // toolbar = (Toolbar) layout.findViewById(R.id.viewContentToolbar);

        // setting the toolbar in the view content activity
        setSupportActionBar(toolbar);

        // setting the title of the toolbar to the null
        getSupportActionBar().setTitle(null);

        // enable the home button which will navigate to its parent activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewContentHandler = new ViewContentHandler(this);
        ((ImageView) findViewById(R.id.viewContentToolbarImageView)).setImageBitmap(bmp);
        ((TextView) findViewById(R.id.viewContentToolbarTitle)).setText(title);
        ((TextView) findViewById(R.id.viewContentToolbarNoOfViews)).setText(noOfViews);
        ((TextView) findViewById(R.id.viewContentToolbarNoOfParticiapnts)).setText(noOfParticipants);
        mViewContentHandler.getRequiredDataForViewContent(contentId);
    }

    public void onToolbarImageClicked(View view)
    {
        Toast.makeText(ViewContent.this,"Image Cliked", Toast.LENGTH_SHORT).show();
    }

    public void onImageBtnClicked(View view)
    {
        Toast.makeText(ViewContent.this, Integer.toString(view.getId()) , Toast.LENGTH_SHORT).show();
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.selectedIcon)
            Toast.makeText(this,"icon",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }*/
}
