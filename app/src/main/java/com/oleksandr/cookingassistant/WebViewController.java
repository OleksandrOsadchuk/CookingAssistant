package com.oleksandr.cookingassistant;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by pear on 16-10-11.
 */
public class WebViewController extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


}
