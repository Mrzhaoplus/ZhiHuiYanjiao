package www.diandianxing.com.diandianxing.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

import www.diandianxing.com.diandianxing.MainActivity;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.Loginsuccess;
import www.diandianxing.com.diandianxing.bean.Successbean;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;


public class AgreementActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_callback;
    private WebView webView;
    private TextView tv_to_top,tv_to_next;
    private String contact;
    private String password;
    private String register_or_bind;
    private String type;
    private String openid;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        initView();
        initWeb();
    }


    private void initView() {
        //注册接口需要的手机号密码
        contact = getIntent().getStringExtra("contact");
        password = getIntent().getStringExtra("password");
        //判断是注册还是绑定的字段
        register_or_bind = getIntent().getStringExtra("register_or_bind");
        //绑定接口需要的数据
        type = getIntent().getStringExtra("type");
        openid = getIntent().getStringExtra("openid");
        name = getIntent().getStringExtra("name");

        iv_callback= (ImageView) findViewById(R.id.iv_callback);
        webView= (WebView) findViewById(R.id.webView);
        tv_to_top= (TextView) findViewById(R.id.tv_to_top);
        tv_to_next= (TextView) findViewById(R.id.tv_to_next);
        iv_callback.setOnClickListener(this);
        tv_to_top.setOnClickListener(this);
        tv_to_next.setOnClickListener(this);
    }

  

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//判断是否点击了BACK键
        if(keyCode==KeyEvent.KEYCODE_BACK){
//判断是否可以返回
            if(webView.canGoBack()){
                webView.goBack();
                return true;
            }else{
//如果已经到顶端了，我们可以将界面摧毁掉
               finish();
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_callback:
                finish();
                break;
            case R.id.tv_to_top:
                finish();
                break;
            case R.id.tv_to_next:
                if (register_or_bind.equals("register")){
                    register();
                }else if (register_or_bind.equals("bind")){
                    bind();
                }
                break;
        }
    }
    private void register() {
        Map<String,String> map=new ArrayMap<>();
        map.put("contact",contact);
        map.put("password",password);
        RetrofitManager.post(MyContants.BASEURL + "s=Login/register", map, new BaseObserver1<Successbean>("") {
            @Override
            public void onSuccess(Successbean result, String tag) {
                if(result.getCode()==200){
                    ToastUtils.show(AgreementActivity.this,"注册成功",1);
                    finish();
                    removeAllActivitys();
                    startActivity(new Intent(AgreementActivity.this,LoginActivity.class));
                }
                else if(result.getCode()==404){
                    ToastUtils.show(AgreementActivity.this,"手机号已注册",1);
                }
            }

            @Override
            public void onFailed(int code,String data) {

                ToastUtils.show(AgreementActivity.this,"手机号或密码错误",1);
            }
        });

    }
    private void bind() {

        Map<String,String> map=new ArrayMap<>();
        map.put("contact",contact);
        map.put("type",type);
        map.put("openid",openid);
        map.put("name",name);
        RetrofitManager.post(MyContants.BASEURL +"s=Login/threeRegister", map, new BaseObserver1<Loginsuccess>("") {

            @Override
            public void onSuccess(Loginsuccess result, String tag) {
                if(result.getCode()==200){
                    SpUtils.putString(AgreementActivity.this,"userid",result.getDatas().getId());
                    SpUtils.putString(AgreementActivity.this,"token",result.getDatas().getToken());
                    SpUtils.putInt(AgreementActivity.this, "guid", 2);
                    Intent gb = new Intent();
                    gb.setAction(GlobalParams.LOGING_SX);
                    sendBroadcast(gb);
                    //绑定成功跳登录
                    Intent intent=new Intent(AgreementActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(result.getCode()==404){
                    ToastUtils.show(AgreementActivity.this,"手机号已注册",1);
                }

            }

            @Override
            public void onFailed(int code,String data) {

                ToastUtils.show(AgreementActivity.this,data,1);
            }
        });

    }
    private void initWeb() {
        webView.loadUrl("http://47.93.45.38/server/html5/admin_agreement.html");

        WebSettings setTtings= webView.getSettings();
//设置可以加载JavaScript的代码
        setTtings.setJavaScriptEnabled(true);
//优先加载缓存
        setTtings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//不加载缓存
        setTtings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//是否支持缩放，true是支持 false是不支持
        setTtings.setSupportZoom(true);

//这个方法使用后，网页就会在自己浏览器中显示出来
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//super.onProgressChanged(view, newProgress);
                if(newProgress==100){
//数据加载完毕

//这里面我们可以将进度条或者对话框dismiss掉
                }else{
//数据正在加载
//这里面我们将进度条或者对话框show出来

                }
            }


        });


    }
}
