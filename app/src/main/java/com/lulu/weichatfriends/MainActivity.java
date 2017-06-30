package com.lulu.weichatfriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lulu.weichatfriends.model.FriendsZone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.main_web_view);
        //解决点击链接跳转浏览器问题
        //mWebView.setWebViewClient(new WebViewClient());
        //解决弹窗问题
        //设置响应js 的prompt函数
        mWebView.setWebChromeClient(new WebChromeClient() {
            //设置响应js 的Prompt()函数
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
                final View v = View.inflate(MainActivity.this, R.layout.prompt_dialog, null);
                ((TextView) v.findViewById(R.id.prompt_message_text)).setText(message);
                ((EditText) v.findViewById(R.id.prompt_input_field)).setText(defaultValue);
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("O(∩_∩)O~~");
                b.setView(v);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String value = ((EditText) v.findViewById(R.id.prompt_input_field)).getText().toString();
                        result.confirm(value);
                    }
                });
                b.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
                b.create().show();
                return true;
            }
        });
        //js支持
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //允许访问assets目录
        settings.setAllowFileAccess(true);
        //设置WebView排版算法, 实现单列显示, 不允许横向移动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //assets文件路径
        String path = "file:///android_asset/index.html";
        //添加Json数据
        addJson();
        //加载Html页面
        mWebView.loadUrl(path);

    }

    private void addJson() {
        JsSupport jsSupport = new JsSupport(this);
        List<FriendsZone> zones = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            zones.add(new FriendsZone("鹿鹿" + i, "images/icon.png", "这里是Html测试数据, 这里是Html测试数据, 这里是Html测试数据" + i));
//        }
        zones.add(new FriendsZone("DogZhou" , "images/icon.png", "今天学习了3小时没有玩手机。。。。。。","images/pic2.png" ));
        zones.add(new FriendsZone("俊伟" , "images/icon.png", "当你遇遭不幸，向邻居诉说时，你正将自己心灵的一部分托付给他。倘若他胸怀宽阔，他会感谢你；倘若他气量狭小，他会鄙视你。——纪伯伦","images/pic1.png"));
        zones.add(new FriendsZone("鹿鹿" , "images/icon.png", "来说说我这条腿吧，太惨了!5555555","images/pic1.png"));

        Gson gson = new Gson();
        String json = gson.toJson(zones);
        Log.d(TAG, "addJson: json => " + json);
        jsSupport.setJson(json);
        //添加js交互接口, 并指明js中对象的调用名称
        mWebView.addJavascriptInterface(jsSupport, "weichat");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
