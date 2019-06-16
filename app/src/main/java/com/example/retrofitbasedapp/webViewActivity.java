package com.example.retrofitbasedapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class webViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);

        WebView myWebView = new WebView(getApplicationContext());
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);

        setContentView(myWebView);
        String url= "https://www.google.com";
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) url=bundle.getString("url");

        myWebView.loadUrl(url);

    }
}