package www.diandianxing.com.diandianxing.my;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.Login.UmshareActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.SpUtils;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ShareActivity extends UmshareActivity implements View.OnClickListener {

    private ImageView iv_callback;
    private TextView zhong;
    private TextView you;
    private TextView textView;
    private TextView share_wei;
    private TextView share_friend;
    private TextView share_qq;
    private TextView share_kongjian;
    private TextView look_xiangqing;
    private LinearLayout linearLayout2;
    private ImageView iv_fx_bg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_sharefriend);
        initView();
    }

    private void initView() {
        iv_callback = (ImageView) findViewById(R.id.iv_callback);
        zhong = (TextView) findViewById(R.id.zhong);
        iv_fx_bg= (ImageView) findViewById(R.id.iv_fx_bg);
        you = (TextView) findViewById(R.id.you);
        textView = (TextView) findViewById(R.id.textView);
        share_wei = (TextView) findViewById(R.id.share_wei);
        share_friend = (TextView) findViewById(R.id.share_friend);
        share_qq = (TextView) findViewById(R.id.share_qq);
        share_kongjian = (TextView) findViewById(R.id.share_kongjian);
        look_xiangqing = (TextView) findViewById(R.id.look_xiangqing);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        networkImg();
        iv_callback.setOnClickListener(this);
        share_wei.setOnClickListener(this);
        share_qq.setOnClickListener(this);
        share_kongjian.setOnClickListener(this);
        look_xiangqing.setOnClickListener(this);
        share_friend.setOnClickListener(this);
        zhong.setText("邀请好友");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_callback:
                finish();
                break;
            case R.id.share_friend:
                SharebyWeixincenter(this);
                break;
            case R.id.share_kongjian:
                SharebyQzon(this);
                break;
            case R.id.share_wei:
                SharebyWeixin(this);
                break;
            case R.id.share_qq:
                SharebyQQ(this);
                break;
            case R.id.look_xiangqing:
                Intent intent=new Intent(this,XiangxiActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void networkImg() {
        HttpParams params = new HttpParams();
        params.put("imageType", 1);
        params.put("token", SpUtils.getString(ShareActivity.this,"token",null));
        OkGo.<String>post(Api.BASE_URL +"app/home/carouselFigure")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG","关于"+body);
                        try {
                            JSONObject jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");

                            JSONArray datas = jsonobj.getJSONArray("datas");
                            if (code == 200) {

                                Glide.with(ShareActivity.this).load(datas.getJSONObject(0).getString("imageUrl")).into(iv_fx_bg);


                            } else if(code==201){

                            }else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }


}
