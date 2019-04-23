package com.example.zul_mizini.bangunpc1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class newsinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsinfo);

        WebView web = (WebView) findViewById(R.id.wvnewsinfo);
        web.loadUrl("https://news.google.com/search?q=komponen%20komputer&hl=id&gl=ID&ceid=ID%3Aid");
        web.setWebViewClient(new WebViewClient());
    }
}
