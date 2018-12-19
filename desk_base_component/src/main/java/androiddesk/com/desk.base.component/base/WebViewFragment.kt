package androiddesk.com.desk.base.component.base

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androiddesk.com.desk.base.component.R
import kotlinx.android.synthetic.main.fragment_web_view.*
import me.yokeyword.fragmentation.SupportFragment

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/13
 */
class WebViewFragment : SupportFragment() {

    private var mRootView: ViewGroup? = null

    companion object {

        private const val ADDRESS = "address"

        fun newInstance(address: String): WebViewFragment {
            val args = Bundle()
            val fragment = WebViewFragment()
            args.putString(ADDRESS, address)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = View.inflate(context, R.layout.fragment_web_view, null) as ViewGroup
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAndData()
    }

    fun loadUrl(url: String) {
        if (TextUtils.isEmpty(url) || webView == null) return
        webView.loadUrl(url)
    }

    protected fun initViewAndData() {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        settings.builtInZoomControls = true
        settings.loadWithOverviewMode = true
        settings.domStorageEnabled = true
        settings.pluginState = WebSettings.PluginState.ON
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressWheel.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progressWheel.visibility = View.GONE
            }
        }

        val address = arguments!!.getString(ADDRESS)
        if (!TextUtils.isEmpty(address)) {
            webView.loadUrl(address)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mRootView?.removeView(webView)
        webView?.destroy()
    }

    fun webViewGoBack(): Boolean {
        if (webView.canGoBack()) {
            webView.goBack()// 返回前一个页面
            return true
        }
        return false
    }
}