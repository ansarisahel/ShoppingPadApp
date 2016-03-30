package com.shoppingpad.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingpad.BR;
import com.shoppingpad.R;
import com.shoppingpad.databinding.RowViewBinding;
import com.shoppingpad.viewModelHandel.ContentListViewModel;
import com.shoppingpad.viewModelHandel.ContentModel;

import java.io.ByteArrayOutputStream;

/**
 * Created by bridgelabz on 13/3/16.
    This is the adapter class created for the recycler view in the ContentListView
    This adapter class is responsible for populating the data in the recyclerview*/

public class ContentListViewAdapter extends RecyclerView.Adapter
                    <ContentListViewAdapter.ContentListViewAdapterHolder> {

    private ContentListViewModel mContentListViewModelInstance;
    Context context;

    // Constructor of this class which initializes ContentListViewModel
    // so that data can be retrieved from the list of this class
    public ContentListViewAdapter(ContentListViewModel mContentListViewModelInstance,Context context)
    {
        this.mContentListViewModelInstance = mContentListViewModelInstance;
        this.context = context;
    }

    @Override
    public ContentListViewAdapterHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate
                                        (R.layout.row_view,parent,false);
        ContentListViewAdapterHolder holder = new ContentListViewAdapterHolder(rowView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContentListViewAdapterHolder holder, int position) {
        ContentModel contentModelInstance = mContentListViewModelInstance.getdata(position);
        holder.binding.setVariable(BR.data, contentModelInstance);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
       return mContentListViewModelInstance.getListSize();
    }


    // This is the view holder class for recycler view.
    public class ContentListViewAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        RowViewBinding binding;
        View view;
        public ContentListViewAdapterHolder(View view) {
            super(view);
            this.view = view;
            binding = RowViewBinding.bind(view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("mContentId",binding.txtLastSeen.getText().toString());
            context.startActivity(new Intent(context,ViewContent.class).putExtras(bundle));

        }
    }
}
