package zhyj.dqjt.com.zhihuiyanjiao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;

/**
 * Created by Administrator on 2018/4/8.
 */

public class VideoRecordingActivity extends BaseActivity {

    JCameraView jCameraView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recording);

        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }

        //1.1.1
        jCameraView = (JCameraView) findViewById(R.id.jcameraview);

//设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
//设置只能录像或只能拍照或两种都可以（默认两种都可以）
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);

//设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);

//JCameraView 监听
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //打开 Camera 失败回调
                Log.i("CJT", "open camera error");
            }

            @Override
            public void AudioPermissionError() {
                //没有录取权限回调
                Log.i("CJT", "AudioPermissionError");
            }
        });

        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取图片 bitmap

                File file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+"_img.png");

                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,new FileOutputStream(file));

                    Intent intent = new Intent(VideoRecordingActivity.this,ReleaseShootoffActivity.class);
                    intent.putExtra("img",file.getAbsolutePath());
                    startActivity(intent);
                    finish();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                //获取视频路径
                Log.i("TAG", "url = " + url);

                Intent intent = new Intent(VideoRecordingActivity.this,ReleaseShootoffVidoActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                finish();

            }
            //@Override
            //public void quit() {
            //    (1.1.9+后用左边按钮的点击事件替换)
            //}
        });
//左边按钮点击事件
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                VideoRecordingActivity.this.finish();
            }
        });
        //右边按钮点击事件
        jCameraView.setRightClickListener(new ClickListener(){

            @Override
            public void onClick() {
                Log.i("TAG", "点击右侧按钮" );
//                Toast.makeText(VideoRecordingActivity.this,"Right",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        jCameraView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }

}
