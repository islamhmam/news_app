package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    ArrayList<Articles> articlesList;

    public NewsAdapter(Context context, ArrayList<Articles> articlesList) {
        this.context = context;
        this.articlesList = articlesList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,
                false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsAdapter.NewsViewHolder holder, int position) {
        holder.nTitle.setText(articlesList.get(position).getTitle());
        holder.nBody.setText(articlesList.get(position).getContent());

        Picasso.get().load(articlesList.get(position).getUrlToImage()).into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,NewsDetailsActivity.class);
                i.putExtra("title",articlesList.get(position).getTitle());
                i.putExtra("body",articlesList.get(position).getContent());
                i.putExtra("imageUrl",articlesList.get(position).getUrlToImage());
                i.putExtra("desc",articlesList.get(position).getDescription());
                i.putExtra("articleUrl",articlesList.get(position).getUrl());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
    ImageView iv;
    TextView nTitle,nBody;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            iv=itemView.findViewById(R.id.nitem_iv);
            nTitle=itemView.findViewById(R.id.nitem_tv_news_title);
            nBody=itemView.findViewById(R.id.nitem_tv_news_body);
        }
    }
}
