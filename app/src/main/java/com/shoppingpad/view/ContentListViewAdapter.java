package com.shoppingpad.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingpad.R;
import com.shoppingpad.viewmodelHandel.ContentListViewModel;
import com.shoppingpad.viewmodelHandel.ContentViewModel;

import java.util.List;

/**
 * Created by bridgelabz on 13/3/16.
    This is the adapter class created for the recycler view in the ContentListView */

public class ContentListViewAdapter extends RecyclerView.Adapter
                    <ContentListViewAdapter.ContentListViewAdapterHolder> {

    ContentListViewModel mContentListViewModelInstance;

    // Constructor of this class which initializes the list so that it can be
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
        holder.dp.setImageResource(contentViewModelInstance.mImage);
        holder.title.setText(contentViewModelInstance.mTitle);
        holder.lastSeen.setText(contentViewModelInstance.mLastSeen);
        holder.noOfViews.setText(""+ contentViewModelInstance.mNoOfViews);
        holder.participants.setText(""+ contentViewModelInstance.mNoOfParticipants);
        holder.status.setText(contentViewModelInstance.mStatus);
    }

    @Override
    public int getItemCount() {
       return mContentListViewModelInstance.getListSize();
    }

    public class ContentListViewAdapterHolder extends RecyclerView.ViewHolder {
        TextView title,status,lastSeen,noOfViews,participants;
        ImageView dp;
        public ContentListViewAdapterHolder(View view) {
            super(view);
            dp = (ImageView) view.findViewById(R.id.imageViewDP);
            title = (TextView) view.findViewById(R.id.txtTitle);
            status = (TextView) view.findViewById(R.id.txtStatus);
            lastSeen = (TextView) view.findViewById(R.id.txtLastSeen);
            noOfViews = (TextView) view.findViewById(R.id.textNoOfViews);
            participants = (TextView) view.findViewById(R.id.txtParticipants);
        }
    }
}
