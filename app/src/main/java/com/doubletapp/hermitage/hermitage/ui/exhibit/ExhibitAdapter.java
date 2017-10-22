package com.doubletapp.hermitage.hermitage.ui.exhibit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.Exhibit;
import com.doubletapp.hermitage.hermitage.ui.nav.NavAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by onthecrow on 22.10.2017.
 */

public class ExhibitAdapter extends RecyclerView.Adapter<ExhibitAdapter.ExhibitViewHolder> {

    List<Exhibit> mItems;

    @Override
    public ExhibitAdapter.ExhibitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exhibit, parent, false);
        return new ExhibitViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExhibitAdapter.ExhibitViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    void swap(@NonNull List<Exhibit> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    class ExhibitViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.exhibit_item_image)
        ImageView mImage;
        @BindView(R.id.item_exhibit_name)
        TextView mTitle;
        @BindView(R.id.item_exhibit_subscription)
        TextView mDescription;

        ExhibitViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull Exhibit item) {
            mImage.setImageResource(item.getImageRes());
            mTitle.setText(item.getName());
            mDescription.setText(item.getSubscription());
        }
    }
}
