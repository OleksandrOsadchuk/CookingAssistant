package com.oleksandr.cookingassistant;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by Oleksandr on 16-10-11.
 */
public class WebViewActivity extends AppCompatActivity { //AppCompatActivity

    private WebView webView;
    private String url="http://www.google.com";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewController());

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            url = (String) extras.get("link");
        }

        webView.loadUrl(url);


    }
}


