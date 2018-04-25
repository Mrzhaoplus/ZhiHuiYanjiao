package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.adapter.MsgitmeAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.MessInfo;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.LoadingDialog;
import www.diandianxing.com.diandianxing.util.MyContants;

/**
 * Created by Administrator on 2018/4/8.
 */

public class ReleaseShootoffVidoActivity extends BaseActivity {


    private ImageView include_back;

    private LinearLayout ll_szwz;

    private VideoView videoView;
    private ImageView iv_bf;
    private TextView textView2;
    private TextView tv_fb;
    private String address="";
    private EditText et_xcl;
    private File file;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_release_shootoff_vido);
        EventBus.getDefault().register(ReleaseShootoffVidoActivity.this);
        include_back= (ImageView) findViewById(R.id.include_back);
        ll_szwz= (LinearLayout) findViewById(R.id.ll_szwz);
        videoView= (VideoView) findViewById(R.id.videoView);
        iv_bf= (ImageView) findViewById(R.id.iv_bf);
        textView2= (TextView) findViewById(R.id.textView2);
        tv_fb= (TextView) findViewById(R.id.tv_fb);
        et_xcl= (EditText) findViewById(R.id.et_xcl);
        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String url=getIntent().getStringExtra("url");
        file = new File(url);
        Uri uri = Uri.parse( url );

        MediaController mediaController = new MediaController(this);
        mediaController.setVisibility(View.INVISIBLE);
        //设置视频控制器
        videoView.setMediaController(mediaController);

        //播放完成回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());

        //设置视频路径
        videoView.setVideoURI(uri);

        //开始播放视频
        videoView.start();

        ll_szwz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ReleaseShootoffVidoActivity.this,LocationActivity.class);
                startActivity(intent);

            }
        });

        iv_bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //开始播放视频
                videoView.start();
                iv_bf.setVisibility(View.INVISIBLE);
            }
        });
        tv_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String trim = et_xcl.getText().toString().trim();

                if(trim!=null&&trim.length()>0){
//                    if(loadingDialog==null) {
//                        loadingDialog = new LoadingDialog(ReleaseShootoffVidoActivity.this, R.layout.view_tips_loading);
//                        loadingDialog.setCancelable(false);
//                        loadingDialog.setCanceledOnTouchOutside(false);
//                        loadingDialog.show();
//                        loadingDialog.setTextMessage("发布中...");
                        shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                        network(trim, address);
//                    }
                }else{
                    Toast.makeText(ReleaseShootoffVidoActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    BaseDialog dialog;
    /*
* 对话框
* */
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        dialog = builder.setViewId(R.layout.view_tips_loading)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tips_loading_msg = dialog.getView(R.id.tips_loading_msg);
        tips_loading_msg.setText("发布中...");
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(MsgBus messageEvent) {
        address=messageEvent.msg;
        textView2.setText(messageEvent.msg);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ReleaseShootoffVidoActivity.this);
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
//            Toast.makeText( LocalVideoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();

            iv_bf.setVisibility(View.VISIBLE);

        }
    }

    private void network(String content,String address) {

        HttpParams params = new HttpParams();
        params.put("content", content);
        params.put("address", address);
        params.put("file", file);
        params.put("token", Api.token);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/paike/uppaikesource")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dialog.dismiss();
                        String body = response.body();
                        Log.d("TAG", "上传成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                MsgBus msgBus = new MsgBus();
                                msgBus.tiaozhuan="首页";
                                EventBus.getDefault().postSticky(msgBus);

                                finish();
                            } else {
                                Toast.makeText(ReleaseShootoffVidoActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


}
