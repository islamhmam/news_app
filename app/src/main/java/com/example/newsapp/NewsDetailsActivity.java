package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewsDetailsActivity extends AppCompatActivity {
String title , content ,desc , imageUrl,articleUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("body");
        desc = getIntent().getStringExtra("desc");
        imageUrl = getIntent().getStringExtra("imageUrl");
        articleUrl = getIntent().getStringExtra("articleUrl");
    }
}