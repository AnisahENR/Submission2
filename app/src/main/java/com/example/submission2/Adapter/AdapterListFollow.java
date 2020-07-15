package com.example.submission2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.submission2.Model.FollowModel;
import com.example.submission2.R;

import java.util.ArrayList;

public class AdapterListFollow extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<FollowModel> items = new ArrayList<>();

    private Context ctx;
    private AdapterListFollow.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, FollowModel obj, int position);
    }

    public void setOnItemClickListener(final AdapterListFollow.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListFollow(Context context, ArrayList<FollowModel> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.profil_follow);
            name = (TextView) v.findViewById(R.id.nama_follow);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_follow, parent, false);
        vh = new AdapterListFollow.OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof AdapterListFollow.OriginalViewHolder) {
            AdapterListFollow.OriginalViewHolder view = (AdapterListFollow.OriginalViewHolder) holder;

            final FollowModel obj = items.get(position);
            view.name.setText(obj.login);
            Glide.with(ctx)
                    .load(obj.getAvatar_url())
                    .fitCenter() // menyesuaikan ukuran imageview
                    .crossFade() // animasi
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view.image);
//            Tools.displayImageRound(ctx, view.image, p.image);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
