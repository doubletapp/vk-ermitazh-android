package com.doubletapp.hermitage.hermitage.ui.nav;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by onthecrow on 21.10.2017.
 */

public class NavAdapter extends RecyclerView.Adapter<NavAdapter.NavViewHolder> {

    private static final String TAG = "NavAdapter";

    List<NavItem> mItems;

    @NonNull
    OnItemClick mInterface;

    NavAdapter(@NonNull OnItemClick inter) {
        mInterface = inter;
    }

    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav, parent, false);
        return new NavViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NavViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    void swap(@NonNull List<NavItem> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    interface OnItemClick {
        void onClick(@NonNull NavItem item);
    }

    class NavViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_nav_description)
        TextView mDescription;
        @BindView(R.id.item_nav_title)
        TextView mTitle;
        @BindView(R.id.item_nav_image)
        ImageView mImage;

        NavViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull NavItem item) {
            mDescription.setText(item.getDescription());
            mTitle.setText(item.getTitle());
            mImage.setImageResource(item.getImage());
        }

        @OnClick(R.id.click_frame)
        void onItemClick() {
            mInterface.onClick(mItems.get(getAdapterPosition()));
        }
    }
}
