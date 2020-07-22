package com.example.submission2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.submission2.DetailUser;
import com.example.submission2.Model.UserModel;
import com.example.submission2.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class AdapterListUser extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<UserModel> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, UserModel obj, int position);
    }

    public AdapterListUser(Context context, ArrayList<UserModel> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public CircularImageView image;
        public TextView name;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (CircularImageView) v.findViewById(R.id.profil_user);
            name = (TextView) v.findViewById(R.id.nama_user);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            final UserModel obj = items.get(position);
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
//                    if (mOnItemClickListener != null) {
//                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    Intent in = new Intent(ctx, DetailUser.class);
                    in.putExtra("username",obj.login);
                    ctx.startActivity(in);

//                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
