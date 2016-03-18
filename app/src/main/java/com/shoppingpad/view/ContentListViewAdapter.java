package com.shoppingpad.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingpad.BR;
import com.shoppingpad.R;
import com.shoppingpad.databinding.RowViewBinding;
import com.shoppingpad.viewModelHandel.ContentListViewModel;
import com.shoppingpad.viewModelHandel.ContentViewModel;

/**
 * Created by bridgelabz on 13/3/16.
    This is the adapter class created for the recycler view in the ContentListView
    This adapter class is resposible for populating the data in the recyclerview*/

public class ContentListViewAdapter extends RecyclerView.Adapter
                    <ContentListViewAdapter.ContentListViewAdapterHolder> {

    ContentListViewModel mContentListViewModelInstance;

    // Constructor of this class which initializes ContentListViewModel
    // so that data can be retrieved from the list of this class
    public ContentListViewAdapter(ContentListViewModel mContentListViewModelInstance)
    {
        this.mContentListViewModelInstance = mContentListViewModelInstance;
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
        ContentViewModel contentViewModelInstance = mContentListViewModelInstance.getdata(position);
        holder.binding.setVariable(BR.data,contentViewModelInstance);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
       return mContentListViewModelInstance.getListSize();
    }

    // This is the view holder class for recycler view.
    public class ContentListViewAdapterHolder extends RecyclerView.ViewHolder {
        RowViewBinding binding;
        public ContentListViewAdapterHolder(View view) {
            super(view);
            binding = RowViewBinding.bind(view);
        }
    }
}
