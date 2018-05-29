package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.Info;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */
public class LancherActivity extends BaseActivity {

    private int time = 3;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        int guid = SpUtils.getInt(LancherActivity.this, "guid", 0);
        if (guid==1) {
            mHandler.postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LancherActivity.this,MainActivity.class));
                            finish();
                        }
                    }, 1500);
        }else if(guid==2){
            mHandler.postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LancherActivity.this,MainActivity.class));
                            finish();
                        }
                    }, 1500);
        }
        else if(guid==0){
            mHandler.postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            networkYDY();
                        }
                    }, 1500);
        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private List<String> list= new ArrayList<>();
    Info info = new Info();
    private void networkYDY() {
        HttpParams params = new HttpParams();
        params.put("imageType",5);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/carouselFigure")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "删除成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            info.imgs=new ArrayList<String>();
                            info.fileList = new ArrayList<File>();
                            if (code == 200) {

                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);

                                    info.imgs.add(jo.getString("imageUrl"));
                                }
                                jz();


                            } else {
                                Toast.makeText(LancherActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LancherActivity.this,MainActivity.class));
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                            startActivity(new Intent(LancherActivity.this,MainActivity.class));
                            finish();
                        }
                    }
                });
    }


    class MyRun implements Runnable{
        public MyRun(){
        }

        @Override
        public void run() {

            for(int i=0;i<info.imgs.size();i++){

                FutureTarget<File> future = Glide.with(LancherActivity.this)
                        .load(info.imgs.get(i))
                        .downloadOnly(500, 500);
                File cacheFile = null;
                try {
                    cacheFile = future.get();
                    info.fileList.add(cacheFile);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }

            if(info.imgs.size()>0){
                Intent intent = new Intent(LancherActivity.this, GuidePageActivity.class);

                intent.putExtra("imgs",  info);

                startActivity(intent);
            }else{

                startActivity(new Intent(LancherActivity.this,MainActivity.class));

            }
            finish();


        }
    }

//缓存线程
    public void jz(){

        new Thread(new MyRun()).start();

    }


}
