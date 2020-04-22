package com.dev.dormotory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapFloorActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_floor);
        findViewById(R.id.back).setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        SimpleWebViewClient webViewClient = new SimpleWebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(getResources().getString(R.string.urlETAJI));

    }
    public class SimpleWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
    }
}
