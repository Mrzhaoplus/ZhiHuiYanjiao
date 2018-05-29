package www.diandianxing.com.diandianxing;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Administrator on 2018/5/9.
 */

public class LunBoActivity extends BaseActivity {

    private ImageView img_back;

    private WebView wv_zx;
    private String url,title;
    private TextView tv_lb_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_zixun_ditails);
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        wv_zx= (WebView) findViewById(R.id.wv_zx);
        tv_lb_title= (TextView) findViewById(R.id.tv_lb_title);
        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");
        tv_lb_title.setText(title);
        wv_zx.getSettings().setJavaScriptEnabled(true);//允许使用js
        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        wv_zx.getSettings().setCacheMode(wv_zx.getSettings().LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        Log.e("TAG",url);

        wv_zx.loadUrl(url);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
