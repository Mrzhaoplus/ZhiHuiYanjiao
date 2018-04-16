package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.MsgBus;
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
        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String url=getIntent().getStringExtra("url");
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

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(MsgBus messageEvent) {

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


}
