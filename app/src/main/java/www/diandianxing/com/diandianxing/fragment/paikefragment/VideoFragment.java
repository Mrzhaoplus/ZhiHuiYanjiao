package www.diandianxing.com.diandianxing.fragment.paikefragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
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
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.adapter.MyPagerAdapter;
import www.diandianxing.com.diandianxing.adapter.Video_pinglun_Adapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;

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
    private NestedScrollView nsc_pm;

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
        nsc_pm=contentView.findViewById(R.id.nsc_pm);
        rl_sp=contentView.findViewById(R.id.rl_sp);
        frnxiang = contentView.findViewById(R.id.video_fenxiang);
        time = contentView.findViewById(R.id.text_time);
        pinglun = contentView.findViewById(R.id.text_pinglun);
        text_zan = contentView.findViewById(R.id.text_zan);
        vp_img_banner=contentView.findViewById(R.id.vp_img_banner);
        ll_dian=contentView.findViewById(R.id.ll_dian);
        vv_sp=findViewById(R.id.vv_sp);
        //设置控件为半透明
        img_back.getBackground().setAlpha(80);
        guanzhu.getBackground().setAlpha(80);
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
        nsc_pm.setOnScrollChangeListener(scrollChangeListener);
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
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recy_view.setNestedScrollingEnabled(false);
       //recy_view.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        Video_pinglun_Adapter video_pinglun_adapter = new Video_pinglun_Adapter(getActivity());
        recy_view.setAdapter(video_pinglun_adapter);
    }

    private NestedScrollView.OnScrollChangeListener scrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            Log.e("TAG1","scrollX="+scrollX+",scrollY="+scrollY+",oldScrollX="+oldScrollX+",oldScrollY="+oldScrollY);

        }

    };

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
            case R.id.ll_wx:
                SharebyWeixin(getActivity());
                break;
            case R.id.ll_pyq:
                SharebyWeixincenter(getActivity());
                break;
            case R.id.ll_qq:
                Log.e("TAG","点击1111333333");
                SharebyQQ(getActivity());
                break;
            case R.id.ll_kj:
                SharebyQzon(getActivity());
                break;
            case R.id.ll_wb:
                break;
            case R.id.quxiao:
                dialog.dismiss();
                break;

        }
    }


    protected  void SharebyQQ(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context, R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述

        new ShareAction(context)
                .withMedia(web)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyQzon(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context,R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeixin(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context,R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void SharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context,R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    public  UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult( final  SHARE_MEDIA platform) {
            //            Log.d("plat", "platform" + platform);
             /*
               成功调用接口
              */
            String type = "";
            if (platform.equals(SHARE_MEDIA.WEIXIN)) {
                type = "1";

            } else if(platform.equals(SHARE_MEDIA.WEIXIN_CIRCLE)){
                type="2";

            }
            else if (platform.equals(SHARE_MEDIA.QQ)) {
                type = "3";
            } else if (platform.equals(SHARE_MEDIA.QZONE)) {
                type = "4";
            }
            //网络请求
            Map<String,String> map=new HashMap<>();
            map.put("uid", SpUtils.getString(getActivity(),"userid",null));
            map.put("type",type);
            map.put("token", SpUtils.getString(getActivity(),"token",null));
            map.put("platform",2+"");
            RetrofitManager.post(MyContants.BASEURL + "s=User/share", map, new BaseObserver1<Sharebean>("") {
                @Override
                public void onSuccess(Sharebean result, String tag) {
                    if(result.getCode()==200){
                        Toast.makeText(Myapplication.getGloableContext(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed(int code,String data) {

                }
            });

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            // Toast.makeText(Myapplication.getGloableContext(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                //                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            //    Toast.makeText(Myapplication.getGloableContext(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;
    Dialog dialog;

    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        //设置触摸dialog外围是否关闭
        //设置监听事件
        dialog = builder.setViewId(R.layout.sharing_pop_item_view)
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
        ll_wx=dialog.findViewById(R.id.ll_wx);
        ll_pyq=dialog.findViewById(R.id.ll_pyq);
        ll_qq=dialog.findViewById(R.id.ll_qq);
        ll_kj=dialog.findViewById(R.id.ll_kj);
        ll_wb=dialog.findViewById(R.id.ll_wb);
        quxiao=dialog.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(this);
        ll_wx.setOnClickListener(this);
        ll_pyq.setOnClickListener(this);
        ll_qq.setOnClickListener(this);
        ll_kj.setOnClickListener(this);
        ll_wb.setOnClickListener(this);
    }
}
