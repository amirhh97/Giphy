package com.example.aebrahimi.firstmvp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.aebrahimi.firstmvp.Model.Item;
import com.example.aebrahimi.firstmvp.ShowContract.ShowView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aebrahimi on 8/13/2018 AD.
 */

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.Holder> {

    ArrayList<Item> items;
    Context c;

    public TrendingAdapter(Context c, ArrayList<Item> items) {
        this.c = c;
        this.items = items;
    }

    @Override
    public Holder onCreateViewHolder(final ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(c).inflate(R.layout.list_items, parent, false), new ItemClickListener() {
            @Override
            public void OnItemClick(View view, int Position) {
                Intent intent = new Intent(parent.getContext(), ShowView.class);
                intent.putExtra("item", items.get(Position));
                parent.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.user.setText(items.get(position).getTitle());
        Glide.with(c)
                .load(items.get(position)
                        .getUrl()).into(holder.gif);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView gif;
        TextView user;
        ItemClickListener listener;

        public Holder(View itemView, ItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            gif = itemView.findViewById(R.id.gif);
            user = itemView.findViewById(R.id.usertxt);
            gif.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v, this.getLayoutPosition());
        }
    }

    public interface ItemClickListener {
        void OnItemClick(View view, int Position);
    }

    public List<Item> getItems() {
        return items;
    }

    public void Additems(List<Item> newItems) {
        for (int i = 0; i < newItems.size(); i++) {
            if (newItems.get(i).getTitle() != null)
                items.add(newItems.get(i));
        }
        //  items.addAll(newItems);
    }
}
