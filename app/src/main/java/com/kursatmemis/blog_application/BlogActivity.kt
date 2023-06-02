package com.kursatmemis.blog_application

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.shashank.sony.fancytoastlib.FancyToast

class BlogActivity : AppCompatActivity() {
    private lateinit var githubWebView: WebView
    private lateinit var progressBar: ProgressBar
    private var isPageLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)

        githubWebView = findViewById(R.id.githubWebView)
        githubWebView.settings.javaScriptEnabled = true

        githubWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (!isPageLoaded) {
                    isPageLoaded = true
                    FancyToast.makeText(
                        this@BlogActivity,
                        "Sayfa başarıyla yüklendi!",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.INFO,
                        true
                    ).show()
                }
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                FancyToast.makeText(
                    this@BlogActivity,
                    "Sayfa yüklenmesi sırasında bir sorun oluştu!",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    true
                ).show()
            }
        }

        val url = "https://github.com/kursat-memis"
        githubWebView.loadUrl(url)
    }

    override fun onBackPressed() {
        if (githubWebView.canGoBack()) {
            githubWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
