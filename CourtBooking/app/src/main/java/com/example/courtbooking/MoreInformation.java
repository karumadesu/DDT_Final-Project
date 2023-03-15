package com.example.courtbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MoreInformation extends AppCompatActivity {

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);

        webview = (WebView) findViewById(R.id.wv_moreInfo);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.facebook.com/JSPgraphicsd");

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

}