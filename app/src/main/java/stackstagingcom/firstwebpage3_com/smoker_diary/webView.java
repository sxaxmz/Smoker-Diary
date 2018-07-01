package stackstagingcom.firstwebpage3_com.smoker_diary;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webView extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        android.support.v7.app.ActionBar bar =  getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d2f7242b")));

        webView = (WebView)findViewById(R.id.wv);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://github.com/sxaxmz");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

    }

    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
