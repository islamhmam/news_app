package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    ArrayList<CategoryRVModel> categoryList;
    private   CategoryClickListener categoryClickInterface;

    public CategoryAdapter(Context context, ArrayList<CategoryRVModel> categoryList, CategoryClickListener  categoryClickInterface) {
        this.context = context;
        this.categoryList = categoryList;
        this.categoryClickInterface=categoryClickInterface;

    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,
                false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.tv.setText(categoryList.get(position).category);
        Picasso.get().load(categoryList.get(position).getCategoryImageUrl()).into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tv=itemView.findViewById(R.id.citem_tv);
            iv=itemView.findViewById(R.id.citem_iv);

        }
    }


    public interface CategoryClickListener{
        void onCategoryClick(int position);


    }

}
