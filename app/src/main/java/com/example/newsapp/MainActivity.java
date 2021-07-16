package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickListener {
CategoryAdapter cA;
NewsAdapter nA;

    RecyclerView nRC;
    RecyclerView cRC;
ArrayList<Articles> articlesList;
ArrayList<CategoryRVModel> categoryList;
ProgressBar progressBar;

    private static final String TAG = "MainActivity";

    //f9c7eb07786e498e91cf4bd105cc4b3e
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nRC=findViewById(R.id.main_rv_news);
        cRC=findViewById(R.id.main_rv_categories);
        articlesList=new ArrayList<>();
        categoryList = new ArrayList<>();
        cA=new CategoryAdapter(this,categoryList,this);
        nA=new NewsAdapter(this,articlesList);
        nRC.setLayoutManager(new LinearLayoutManager(this));
        nRC.setAdapter(nA);
        cRC.setAdapter(cA);

    getCategory();

    getNews("All");


    }

    private void getCategory(){
        categoryList.add(new CategoryRVModel("All","https://images.pexels.com/photos/4057663/pexels-photo-4057663.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryList.add(new CategoryRVModel("Science","https://images.pexels.com/photos/3992943/pexels-photo-3992943.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryList.add(new CategoryRVModel("Technology","https://images.pexels.com/photos/3861964/pexels-photo-3861964.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryList.add(new CategoryRVModel("Business","https://images.pexels.com/photos/572056/pexels-photo-572056.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryList.add(new CategoryRVModel("Health","https://images.pexels.com/photos/48604/pexels-photo-48604.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryList.add(new CategoryRVModel("Sports","https://images.pexels.com/photos/3991976/pexels-photo-3991976.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"));
        categoryList.add(new CategoryRVModel("Entertainment","https://images.pexels.com/photos/1190297/pexels-photo-1190297.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"));
        categoryList.add(new CategoryRVModel("General","https://images.pexels.com/photos/3944454/pexels-photo-3944454.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
cA.notifyDataSetChanged();
    }

    private void getNews(String category){
//progressBar.setVisibility(View.VISIBLE);
articlesList.clear();

        Log.i(TAG, "getNews: 1");
        String catUrl="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=f9c7eb07786e498e91cf4bd105cc4b3e";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=f9c7eb07786e498e91cf4bd105cc4b3e";
        String baseUrl="https://newsapi.org/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(TAG, "getNews: 2");
        
        RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class);
        Log.i(TAG, "getNews: 3");
        
        Call<NewsModel> call;
        Log.i(TAG, "getNews: 4");
        if (category.equals("All")){
            Log.i(TAG, "getNews: 5");
            call=retrofitApi.allNews(url);
            Log.i(TAG, "getNews: 6");
        }
        else {
            Log.i(TAG, "getNews: 7");
            call=retrofitApi.categoryNews(catUrl);
            Log.i(TAG, "getNews: 8");
        }
        Log.i(TAG, "getNews: 9");
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel nm= response.body();
               // progressBar.setVisibility(View.GONE);

               // articlesList=nm.getArticles();

                ArrayList<Articles> articles =nm.getArticles();
                for (int i=0 ; i<articles.size();i++){

                    articlesList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),
                            articles.get(i).getUrl(),articles.get(i).getUrlToImage(),
                            articles.get(i).getContent()));
                }

                nA.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get news", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onCategoryClick(int position) {
    String cat = categoryList.get(position).getCategory();
        getNews(cat);
    }
}