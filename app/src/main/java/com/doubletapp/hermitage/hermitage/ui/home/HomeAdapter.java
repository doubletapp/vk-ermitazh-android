package com.doubletapp.hermitage.hermitage.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by onthecrow on 21.10.2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    List<HomeItem> mItems;

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    public void swap(@NonNull List<HomeItem> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_feed_title)
        TextView mTitle;
        @BindView(R.id.item_feed_description)
        TextView mDescription;
        @BindView(R.id.item_feed_image)
        ImageView mImage;

        HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull HomeItem item) {
            mTitle.setText(item.mTitle);
            mDescription.setText(item.mDescription);
            mImage.setImageResource(item.mImage);
        }
    }
}
