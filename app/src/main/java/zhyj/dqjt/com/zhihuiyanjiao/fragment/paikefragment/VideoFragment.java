package zhyj.dqjt.com.zhihuiyanjiao.fragment.paikefragment;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.MyPagerAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.Video_pinglun_Adapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment.MydynamicActivity;
import zhyj.dqjt.com.zhihuiyanjiao.util.BaseDialog;
import zhyj.dqjt.com.zhihuiyanjiao.util.DividerItemDecoration;

/**
 * Created by Mr赵 on 2018/4/8.
 */

public class VideoFragment extends BaseFragment implements View.OnClickListener {

    private ImageView video;
    private RecyclerView recy_view;
    private LinearLayout img_back;
    private RelativeLayout guanzhu;
    private ImageView tou;
    private ImageView frnxiang;
    private RadioButton zan;
    private TextView time;
    private TextView pinglun;
    private TextView text_zan;
    private VideoView vv_sp;

    private ImageView iv_xq_bf;
    private RelativeLayout rl_sp;
    private boolean isjs;
    private ProgressBar sp_jd;
    private Handler handler;

    private RelativeLayout rl_video,rl_img;

    private ViewPager vp_img_banner;
    private LinearLayout ll_dian;


    private List<View> views;



    @Override
    protected int setContentView() {
        return R.layout.fragment_video;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        video = contentView.findViewById(R.id.video_view);
        recy_view = contentView.findViewById(R.id.rcy_view);
        img_back = contentView.findViewById(R.id.img_back);
        guanzhu = contentView.findViewById(R.id.guanzhu);
        iv_xq_bf=contentView.findViewById(R.id.iv_xq_bf);
        tou = contentView.findViewById(R.id.video_tou);
        sp_jd=contentView.findViewById(R.id.sp_jd);
        zan = contentView.findViewById(R.id.video_zan);
        rl_video=contentView.findViewById(R.id.rl_video);
        rl_img=contentView.findViewById(R.id.rl_img);
        rl_sp=contentView.findViewById(R.id.rl_sp);
        frnxiang = contentView.findViewById(R.id.video_fenxiang);
        time = contentView.findViewById(R.id.text_time);
        pinglun = contentView.findViewById(R.id.text_pinglun);
        text_zan = contentView.findViewById(R.id.text_zan);
        vp_img_banner=contentView.findViewById(R.id.vp_img_banner);
        ll_dian=contentView.findViewById(R.id.ll_dian);
        vv_sp=findViewById(R.id.vv_sp);
handler=new Handler();
        views = new ArrayList<>();
        Uri uri = Uri.parse( "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4" );
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setVisibility(View.INVISIBLE);
    //设置视频控制器
        vv_sp.setMediaController(mediaController);

        //播放完成回调
        vv_sp.setOnCompletionListener( new MyPlayerOnCompletionListener());

        //设置视频路径
        vv_sp.setVideoURI(uri);

        //开始播放视频
        vv_sp.start();
        handler.postDelayed(runnable,1000);


        List<String> banlist = new ArrayList<>();
        banlist.add("http://h.hiphotos.baidu.com/zhidao/pic/item/c2fdfc039245d688bb61de94a2c27d1ed21b249a.jpg");
        banlist.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1301/25/c0/17712320_1359096063354.jpg");
        banlist.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1212/04/c1/16339958_1354613158701.jpg");
        for(int i=0;i<banlist.size();i++){
            ImageView view = new ImageView(getActivity());
            Glide.with(mContext).load(R.drawable.icon_lu)
//                    .transform(new GlideUtils.GlideCircleTransform(mContext,0.5f,mContext.getResources().getColor(R.color.lianse)))
                    .into(view);

            views.add(view);

            View dian = new View(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            params.setMargins(10,0,10,0);
            dian.setLayoutParams(params);

            if(i==0){
                dian.setBackgroundResource(R.drawable.shape_xz_dian);
            }else{
                dian.setBackgroundResource(R.drawable.shape_wxz_dian);
            }

            ll_dian.addView(dian);


        }




        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(views,getActivity());
        vp_img_banner.setAdapter(myPagerAdapter);
        vp_img_banner.setOnPageChangeListener(pageChangeListener);
        img_back.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        tou.setOnClickListener(this);
        frnxiang.setOnClickListener(this);
        rl_sp.setOnClickListener(this);
        zan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String s = text_zan.getText().toString();
                int i = Integer.parseInt(s);
                if(b){
                    text_zan.setText((i+1)+"");
                }
            }
        });
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        recy_view.setNestedScrollingEnabled(false);
       //recy_view.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        Video_pinglun_Adapter video_pinglun_adapter = new Video_pinglun_Adapter(getActivity());
        recy_view.setAdapter(video_pinglun_adapter);
    }


    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            for(int i=0;i<views.size();i++){
                View dian = ll_dian.getChildAt(i);

                if(i==position){
                    dian.setBackgroundResource(R.drawable.shape_xz_dian);
                }else{
                    dian.setBackgroundResource(R.drawable.shape_wxz_dian);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
//            Toast.makeText( LocalVideoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();

            iv_xq_bf.setVisibility(View.VISIBLE);
            isjs=true;

        }
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e("TAG","当前进度：：："+vv_sp.getCurrentPosition()+",总进度：：：："+vv_sp.getDuration());

            if(vv_sp.getDuration()!=-1){
                sp_jd.setMax(vv_sp.getDuration());
            }

            sp_jd.setProgress(vv_sp.getCurrentPosition());
            handler.postDelayed(runnable,500);
        }
    };

    /*
    * 点击事件
    * */
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            //点击返回
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.guanzhu:
                   guanzhu.setVisibility(View.GONE);
                break;
            case R.id.video_tou:
                Intent intent = new Intent(getActivity(), MydynamicActivity.class);
                startActivity(intent);
                break;
            case R.id.video_fenxiang:
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.rl_sp:

                    if(isjs){
                        vv_sp.start();
                        iv_xq_bf.setVisibility(View.INVISIBLE);
                        isjs=false;
                    }else{//暂停

                        if(iv_xq_bf.getVisibility()==View.VISIBLE){
                            Log.e("TAG","显示出来@@@@@@");
                            vv_sp.start();
                            iv_xq_bf.setVisibility(View.INVISIBLE);
                        }else if(iv_xq_bf.getVisibility()==View.INVISIBLE){
                            Log.e("TAG","不显示出来=========");
                            vv_sp.pause();
                            iv_xq_bf.setVisibility(View.VISIBLE);
                        }

                    }





                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        //设置触摸dialog外围是否关闭
        //设置监听事件
        Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();

        dialog.show();
    }
}
