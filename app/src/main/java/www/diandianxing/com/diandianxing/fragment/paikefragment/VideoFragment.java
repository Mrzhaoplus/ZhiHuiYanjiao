package www.diandianxing.com.diandianxing.fragment.paikefragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.danikula.videocache.HttpProxyCacheServer;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import butterknife.OnTouch;
import www.diandianxing.com.diandianxing.GuidePageActivity;
import www.diandianxing.com.diandianxing.LancherActivity;
import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.MainActivity;
import www.diandianxing.com.diandianxing.adapter.JiaoLiuyanAdapter;
import www.diandianxing.com.diandianxing.adapter.Jiaodianadapter;
import www.diandianxing.com.diandianxing.adapter.MyPagerAdapter;
import www.diandianxing.com.diandianxing.adapter.Tuijiantieadapter;
import www.diandianxing.com.diandianxing.adapter.Video_pinglun_Adapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.CustomReplayList;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.bean.PKdetailsTable;
import www.diandianxing.com.diandianxing.bean.PaiKeDRInfo;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.bean.PingLunInfo;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.interfase.HuiFuClickListener;
import www.diandianxing.com.diandianxing.interfase.PinLunFJListener;
import www.diandianxing.com.diandianxing.interfase.PinLunZJListener;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.MyVideoView;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Mr赵 on 2018/4/8.
 */

public class VideoFragment extends BaseFragment implements View.OnClickListener ,HuiFuClickListener,PinLunFJListener,PinLunZJListener{

    private ImageView video;
    private RecyclerView recy_view;
    private LinearLayout img_back;
    private RelativeLayout guanzhu;
    private ImageView tou;
    private ImageView frnxiang;
    private ImageView zan;
    private TextView text_time;
    private TextView pinglun;
    private TextView text_zan;
    private MyVideoView vv_sp;

    private ImageView iv_xq_bf;
    private RelativeLayout rl_sp;
    private boolean isjs;
    private ProgressBar sp_jd;
    private Handler handler;

    private RelativeLayout rl_video,rl_img;

    private ViewPager vp_img_banner;
    private LinearLayout ll_dian;
    private NestedScrollView nsc_pm;
    private LinearLayout lines;


    private List<View> views;
    private RelativeLayout ding;
    private RelativeLayout kongjian;
    private int scrollY1;
    private int height;
    private PaiKeInfo pk;
    private TextView video_title;
    private int pageNo=1;
    private List<PingLunInfo> list = new ArrayList<>();
    JiaoLiuyanAdapter jiaoLiuyanAdapter;
    private SpringView sv_pk;
    private EditText ed_text;
    private Button button_fabu;

    private boolean ispk;
    HttpProxyCacheServer proxy;
    private RelativeLayout rl_bs;

    boolean isTQ;


    private int pmWidth,pmHeight;

    private int fbHeight;
    private int KeyboardHeight;
    private boolean isdy;

    @Override
    protected int setContentView() {
        return R.layout.fragment_video;
    }

    @Override
    protected void lazyLoad() {

        View contentView = getContentView();
        ding = contentView.findViewById(R.id.ding);
        kongjian = contentView.findViewById(R.id.kongjian);
        video = contentView.findViewById(R.id.video_view);
        recy_view = contentView.findViewById(R.id.rcy_view);
        img_back = contentView.findViewById(R.id.img_back);
        guanzhu = contentView.findViewById(R.id.guanzhu);
        iv_xq_bf=contentView.findViewById(R.id.iv_xq_bf);
        tou = contentView.findViewById(R.id.video_tou);
        sp_jd=contentView.findViewById(R.id.sp_jd);
        sv_pk=contentView.findViewById(R.id.sv_pk);
        zan = contentView.findViewById(R.id.video_zan);
        rl_video=contentView.findViewById(R.id.rl_video);
        rl_img=contentView.findViewById(R.id.rl_img);
        nsc_pm=contentView.findViewById(R.id.nsc_pm);
        rl_sp=contentView.findViewById(R.id.rl_sp);
        lines=contentView.findViewById(R.id.lines);
        rl_bs=contentView.findViewById(R.id.rl_bs);
        ed_text = (EditText) contentView.findViewById(R.id.ed_text);
        button_fabu = (Button) contentView.findViewById(R.id.button_fabu);
        frnxiang = contentView.findViewById(R.id.video_fenxiang);
        text_time = contentView.findViewById(R.id.text_time);
        pinglun = contentView.findViewById(R.id.text_pinglun);
        text_zan = contentView.findViewById(R.id.text_zan);
        video_title=contentView.findViewById(R.id.video_title);
        vp_img_banner=contentView.findViewById(R.id.vp_img_banner);
        ll_dian=contentView.findViewById(R.id.ll_dian);
        vv_sp=findViewById(R.id.vv_sp);
        ding.getBackground().mutate().setAlpha(0);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        pmHeight = dm.heightPixels;       // 屏幕高度（像素）

        pmWidth=dm.widthPixels;
        ViewTreeObserver fbview = lines.getViewTreeObserver();
        fbview.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                lines.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                fbHeight=lines.getHeight();

            }
        });

        Bundle arguments = getArguments();
        if(proxy==null){
            proxy = new HttpProxyCacheServer(getActivity());
        }

        pk= (PaiKeInfo) arguments.getSerializable("pk");
        ispk=arguments.getBoolean("ispk");



        ViewTreeObserver vto = kongjian.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                kongjian.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = kongjian.getHeight();
            }
        });
        if(NetUtil.checkNet(getActivity())){
            networkxq();
            finishFreshAndLoad();
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();


            List<PKdetailsTable> pKdetailsTables = DataSupport.where("pkid = ?", pk.pkid).find(PKdetailsTable.class);

            if(pKdetailsTables.size()>0){
                video_title.setText(pKdetailsTables.get(0).pkContent);
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String end = sf.format(date);
                String fb = MyUtils.stampToDate(pKdetailsTables.get(0).createTime);

                String time = MyUtils.dateDiff(fb, end, "yyyy-MM-dd HH:mm:ss",pKdetailsTables.get(0).createTime);
                text_time.setText(time);

                text_zan.setText(pKdetailsTables.get(0).zannum);

                if(pKdetailsTables.get(0).pic.length()>0){
                    Glide.with(getActivity()).load(pKdetailsTables.get(0).pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(tou);

                }

                if(Integer.parseInt(pKdetailsTables.get(0).iszan)==0){
                    zan.setImageResource(R.drawable.wdz);
                }else{
                    zan.setImageResource(R.drawable.sc_xz_3x);
                }

                if(Integer.parseInt(pKdetailsTables.get(0).isgz)==0){
                    guanzhu.setVisibility(View.VISIBLE);
                }else{
                    guanzhu.setVisibility(View.GONE);
                }



                if(pKdetailsTables.get(0).imagesUrl.length()>0){//轮播图
                    rl_video.setVisibility(View.GONE);
                    rl_img.setVisibility(View.VISIBLE);
                    views = new ArrayList<>();
                    ViewGroup.LayoutParams layoutParams = rl_img.getLayoutParams();

                    Log.e("TAG","长度：：："+(pmHeight-fbHeight));
                    Log.e("TAG","长度sssss：：：pmHeight="+pmHeight+"，fbHeight=="+fbHeight);
                    layoutParams.height=pmHeight-fbHeight;
                    layoutParams.width= RelativeLayout.LayoutParams.MATCH_PARENT;


                    rl_img.setLayoutParams(layoutParams);
                    if(pKdetailsTables.get(0).paths!=null&&pKdetailsTables.get(0).paths.length()>0){

                        String[] split = pKdetailsTables.get(0).paths.split(",");

                        for(int i=0;i<split.length;i++){
                            final ImageView view = new ImageView(getActivity());

                            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                            view.setScaleType(ImageView.ScaleType.FIT_XY);
                            RequestOptions options = new RequestOptions()
                                    .priority(Priority.LOW )
                                    .diskCacheStrategy(DiskCacheStrategy.ALL);
                            Glide.with(mContext).load(split[i]).apply(options)
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                                            Log.e("TAG","数值：：："+resource.getIntrinsicWidth()+"宽：："+resource.getIntrinsicHeight());
                                            if (view == null) {
                                                return false;
                                            }
                                            if((pmWidth/pmHeight)<(resource.getIntrinsicWidth()/resource.getIntrinsicHeight())){

                                            }else{
                                                if (view.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                                                }
                                            }




                                            ViewGroup.LayoutParams params = view.getLayoutParams();
                                            int vw = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();
                                            float scale = (float) vw / (float) resource.getIntrinsicWidth();
                                            int vh = Math.round(resource.getIntrinsicHeight() * scale);
                                            params.height = vh + view.getPaddingTop() + view.getPaddingBottom();
                                            view.setLayoutParams(params);
                                            return false;
                                        }
                                    })
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
                    }

                }else{//视频
                    rl_video.setVisibility(View.VISIBLE);
                    rl_img.setVisibility(View.GONE);
                    handler=new Handler();
                    Uri uri = Uri.parse( pk.mvUrl );
                    MediaController mediaController = new MediaController(getActivity());
                    mediaController.setVisibility(View.INVISIBLE);
                    //设置视频控制器
                    vv_sp.setMediaController(mediaController);

                    //播放完成回调
                    vv_sp.setOnCompletionListener( new MyPlayerOnCompletionListener());
                                        String proxyUrl = proxy.getProxyUrl(pk.mvUrl);
                                        vv_sp.setVideoPath(proxyUrl);
                    //设置视频路径
//                    vv_sp.setVideoURI(uri);

                    vv_sp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {





                            mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                @Override
                                public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                                    int mVideoHeight = mediaPlayer.getVideoHeight();
                                    int mVideoWidth = mediaPlayer.getVideoWidth();
//                                    Log.e("TAG","视频有效高度：：："+mVideoHeight);


                                    if(mVideoWidth>mVideoHeight){
                                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_video.getLayoutParams();
                                        layoutParams.height=mVideoHeight;
                                        rl_video.setLayoutParams(layoutParams);

                                        RelativeLayout.LayoutParams sp = (RelativeLayout.LayoutParams) vv_sp.getLayoutParams();
                                        layoutParams.width=RelativeLayout.LayoutParams.MATCH_PARENT;
                                        vv_sp.setLayoutParams(layoutParams);
                                    }else{
                                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_video.getLayoutParams();
                                        layoutParams.width=RelativeLayout.LayoutParams.MATCH_PARENT;
                                        vv_sp.setLayoutParams(layoutParams);
                                    }
//                                                        vv_sp.setVisibility(View.VISIBLE);



                                }
                            });
                        }
                    });
                    //开始播放视频
                    vv_sp.start();


                    handler.postDelayed(runnable,1000);
            }
        }else{

                guanzhu.setVisibility(View.INVISIBLE);
                tou.setVisibility(View.INVISIBLE);

                video_title.setText(pk.pkContent);
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String end = sf.format(date);
                String fb = MyUtils.stampToDate(pk.createTime);

                String time = MyUtils.dateDiff(fb, end, "yyyy-MM-dd HH:mm:ss",pk.createTime);
                text_time.setText(time);

                text_zan.setText(pk.zannum);

                if(pk.pic.length()>0){
                    Glide.with(getActivity()).load(pk.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(tou);
                }

                if(Integer.parseInt(pk.isZan)==0){
                    zan.setImageResource(R.drawable.wdz);
                }else{
                    zan.setImageResource(R.drawable.sc_xz_3x);
                }

//                if(Integer.parseInt(pk.isgz)==0){
//                    guanzhu.setVisibility(View.VISIBLE);
//                }else{
//                    guanzhu.setVisibility(View.GONE);
//                }



                if(pk.imagesUrl.length()>0){//轮播图
                    rl_video.setVisibility(View.GONE);
                    rl_img.setVisibility(View.VISIBLE);
                    views = new ArrayList<>();
                    ViewGroup.LayoutParams layoutParams = rl_img.getLayoutParams();

                    Log.e("TAG","长度：：："+(pmHeight-fbHeight));
                    Log.e("TAG","长度sssss：：：pmHeight="+pmHeight+"，fbHeight=="+fbHeight);
                    layoutParams.height=pmHeight-fbHeight;
                    layoutParams.width= RelativeLayout.LayoutParams.MATCH_PARENT;
                    rl_img.setLayoutParams(layoutParams);
                    for(int i=0;i<pk.paths.size();i++){
                        final ImageView view = new ImageView(getActivity());
                        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                        view.setScaleType(ImageView.ScaleType.FIT_XY);

                        RequestOptions options = new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL);

                        Glide.with(mContext).load(pk.paths.get(i)).
                                apply(options)
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                                        Log.e("TAG","数值：：："+resource.getIntrinsicWidth()+"宽：："+resource.getIntrinsicHeight());
                                        if (view == null) {
                                            return false;
                                        }
                                        if((pmWidth/pmHeight)<(resource.getIntrinsicWidth()/resource.getIntrinsicHeight())){

                                        }else{
                                            if (view.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                                view.setScaleType(ImageView.ScaleType.FIT_XY);
                                            }
                                        }




                                        ViewGroup.LayoutParams params = view.getLayoutParams();
                                        int vw = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();
                                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                                        params.height = vh + view.getPaddingTop() + view.getPaddingBottom();
                                        view.setLayoutParams(params);
                                        return false;
                                    }
                                })
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

                }else{//视频
                    rl_video.setVisibility(View.VISIBLE);
                    rl_img.setVisibility(View.GONE);
                    handler=new Handler();
                    Uri uri = Uri.parse( pk.mvUrl );
                    MediaController mediaController = new MediaController(getActivity());
                    mediaController.setVisibility(View.INVISIBLE);
                    //设置视频控制器
                    vv_sp.setMediaController(mediaController);

                    //播放完成回调
                    vv_sp.setOnCompletionListener( new MyPlayerOnCompletionListener());
                    String proxyUrl = proxy.getProxyUrl(pk.mvUrl);
                    vv_sp.setVideoPath(proxyUrl);
                    //设置视频路径
//                    vv_sp.setVideoURI(uri);

                    vv_sp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                @Override
                                public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                                    int mVideoHeight = mediaPlayer.getVideoHeight();
                                    int mVideoWidth = mediaPlayer.getVideoWidth();
//                                    Log.e("TAG","视频有效高度：：："+mVideoHeight);


                                    if(mVideoWidth>mVideoHeight){
                                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_video.getLayoutParams();
                                        layoutParams.height=mVideoHeight;
                                        rl_video.setLayoutParams(layoutParams);

                                        RelativeLayout.LayoutParams sp = (RelativeLayout.LayoutParams) vv_sp.getLayoutParams();
                                        layoutParams.width=RelativeLayout.LayoutParams.MATCH_PARENT;
                                        vv_sp.setLayoutParams(layoutParams);
                                    }else{
                                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_video.getLayoutParams();
                                        layoutParams.width=RelativeLayout.LayoutParams.MATCH_PARENT;
                                        vv_sp.setLayoutParams(layoutParams);
                                    }
//                                                        vv_sp.setVisibility(View.VISIBLE);



                                }
                            });
                        }
                    });
                    //开始播放视频
                    vv_sp.start();


                    handler.postDelayed(runnable,1000);
                }




            }





        }
        initRefresh();

        img_back.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        tou.setOnClickListener(this);
        frnxiang.setOnClickListener(this);
        rl_sp.setOnClickListener(this);
        zan.setOnClickListener(this);
        button_fabu.setOnClickListener(this);

        nsc_pm.setOnScrollChangeListener(scrollChangeListener);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recy_view.setNestedScrollingEnabled(false);
       //recy_view.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
//        Video_pinglun_Adapter video_pinglun_adapter = new Video_pinglun_Adapter(getActivity());
//        recy_view.setAdapter(video_pinglun_adapter);

//        META_SYM_ON
        vv_sp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                Log.e("TAG","视频开始执行======="+keyEvent.getKeyCode());

                if(KeyEvent.KEYCODE_MEDIA_PLAY==keyEvent.getKeyCode()){
                    Log.e("TAG","视频播放=======");


                }


                return false;
            }
        });

//        lines.setFocusable(true);
//        lines.setFocusableInTouchMode(true);

        final View myLayout = getActivity().getWindow().getDecorView();
        lines.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private int statusBarHeight;

            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                // 使用最外层布局填充，进行测算计算
                lines.getWindowVisibleDisplayFrame(r);
                int screenHeight = myLayout.getRootView().getHeight();
                int heightDiff = screenHeight - (r.bottom - r.top);
                if (heightDiff > 100) {
                    // 如果超过100个像素，它可能是一个键盘。获取状态栏的高度
                    statusBarHeight = 0;
                }
                try {
                    Class<?> c = Class.forName("com.android.internal.R$dimen");
                    Object obj = c.newInstance();
                    Field field = c.getField("status_bar_height");
                    int x = Integer.parseInt(field.get(obj).toString());
                    statusBarHeight = getResources().getDimensionPixelSize(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int realKeyboardHeight = heightDiff - statusBarHeight;
//                Log.e("TAG", "keyboard height(单位像素) = " + realKeyboardHeight);

                LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) lines.getLayoutParams();

//                if(realKeyboardHeight!=0){
//
//                    params.bottomMargin=realKeyboardHeight;
//                }else{
//                    params.bottomMargin=0;
//                }



                if(!isdy){//第一次
                    KeyboardHeight=realKeyboardHeight;

                    params.bottomMargin=0;

                }else{

//                    if(realKeyboardHeight+112==0){//是否有
//                        params.bottomMargin=0;
//                    }else{//
                    if(realKeyboardHeight>KeyboardHeight){

                        params.bottomMargin=realKeyboardHeight-KeyboardHeight;//realKeyboardHeight

                    }else{//收回
                        params.bottomMargin=0;//0
                    }
//                    }
//                    KeyboardHeight=realKeyboardHeight+112;
                }




                lines.setLayoutParams(params);
//                if(!isTQ){
//                    //不管软键盘是否打开都关闭软键盘
//                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    if (null != imm) {
//                        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
//                        if (isOpen) {
////                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                            if (getActivity().getCurrentFocus() != null) {//强制关闭软键盘
//                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
//                                        InputMethodManager.HIDE_NOT_ALWAYS);
//                                isTQ=true;
//                            }
//                        }
//                    }
//                }
                isdy=true;
            }
        });


        //不管软键盘是否打开都关闭软键盘
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm) {
            boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
            if (isOpen) {
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                if (getActivity().getCurrentFocus() != null) {//强制关闭软键盘
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }


    }

    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        sv_pk.setType(SpringView.Type.FOLLOW);
        sv_pk.setHeader(new DefaultHeader(getActivity()));
        sv_pk.setFooter(new DefaultFooter(getActivity()));
        sv_pk.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(mContext,"下拉刷新中",Toast.LENGTH_SHORT).show();
                // list.clear();
                // 网络请求;
                // mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法

                list.clear();
                pageNo=1;
                if(NetUtil.checkNet(getActivity())){
                    if(pk!=null){
                        finishFreshAndLoad();
                    }

                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                pageNo++;
                if(NetUtil.checkNet(getActivity())){
                    if(pk!=null){
                        finishFreshAndLoad();
                    }
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void stopVideo(){
        Log.e("TAG","开始关闭播放了");
        vv_sp.pause();
        iv_xq_bf.setVisibility(View.VISIBLE);
        getActivity().finish();
    }





    /**
     * 关闭加载提示
     */
    private void finishFreshAndLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                networklist();
                sv_pk.onFinishFreshAndLoad();
            }
        }, 0);
    }

    private NestedScrollView.OnScrollChangeListener scrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            Log.i("TAG1","scrollX="+scrollX+",scrollY="+scrollY+",oldScrollX="+oldScrollX+",oldScrollY="+oldScrollY);
            if(scrollY>=height){
                ding.getBackground().mutate().setAlpha(230);
            }else{
                ding.getBackground().mutate().setAlpha(0);
            }
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

    private void networkxq(){
        {

            HttpParams params = new HttpParams();
            params.put("token", SpUtils.getString(getActivity(),"token",null));
            params.put("pkId", pk.pkid);

            OkGo.<String>post(Api.BASE_URL +"app/paike/paikeinfo")
                    .tag(this)
                    .params(params)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {

                            String body = response.body();
                            Log.d("TAG", "拍客详情：" + body);
                            JSONObject jsonobj = null;
                            try {
                                jsonobj = new JSONObject(body);
                                int code = jsonobj.getInt("code");
                                if (code == 200) {
                                    JSONObject jo1 = jsonobj.getJSONObject("datas");

                                    JSONObject jo=jo1.getJSONObject("paike");
                                    pk = new PaiKeInfo();

                                    PKdetailsTable pKdetailsTable = new PKdetailsTable();

                                    pk.pkid=jo.getString("id");
                                    pk.imageUrl=jo.getString("imageUrl");
                                    pk.mvUrl=jo.getString("mvUrl");
                                    pk.userId=jo.getString("userId");
                                    pk.address=jo.getString("address");
                                    pk.userName=jo.getString("userName");
                                    pk.userLevel=jo.getString("userLevel");
                                    pk.isRecommend=jo.getString("isRecommend");
                                    pk.pkOperation=jo.getString("pkOperation");
                                    pk.createTime=jo.getString("createTime");
                                    pk.isDeleted=jo.getString("isDeleted");
                                    pk.pkTitle=jo.getString("pkTitle");
                                    pk.imagesUrl=jo.getString("imagesUrl");
                                    pk.pkContent=jo.getString("pkContent");
                                    pk.commentCount=jo.getString("commentCount");
                                    pk.dianZanCount=jo.getString("dianZanCount");
                                    pk.collectCount=jo.getString("collectCount");
                                    pk.relayCount=jo.getString("relayCount");
                                    pk.nickName=jo.getString("nickName");
                                    pk.pic=jo.getString("pic");
                                    pk.nickName=jo.getString("nickName");
                                    pk.iszan=jo1.getString("iszan");
                                    pk.zannum=jo1.getString("zannum");
                                    pk.plnum=jo1.getString("plnum");
                                    pk.isgz=jo1.getString("isgz");



                                    pKdetailsTable.pkid=jo.getString("id");
                                    pKdetailsTable.imageUrl=jo.getString("imageUrl");
                                    pKdetailsTable.mvUrl=jo.getString("mvUrl");
                                    pKdetailsTable.userId=jo.getString("userId");
                                    pKdetailsTable.address=jo.getString("address");
                                    pKdetailsTable.userName=jo.getString("userName");
                                    pKdetailsTable.userLevel=jo.getString("userLevel");
                                    pKdetailsTable.isRecommend=jo.getString("isRecommend");
                                    pKdetailsTable.pkOperation=jo.getString("pkOperation");
                                    pKdetailsTable.createTime=jo.getString("createTime");
                                    pKdetailsTable.isDeleted=jo.getString("isDeleted");
                                    pKdetailsTable.pkTitle=jo.getString("pkTitle");
                                    pKdetailsTable.imagesUrl=jo.getString("imagesUrl");
                                    pKdetailsTable.pkContent=jo.getString("pkContent");
                                    pKdetailsTable.commentCount=jo.getString("commentCount");
                                    pKdetailsTable.dianZanCount=jo.getString("dianZanCount");
                                    pKdetailsTable.collectCount=jo.getString("collectCount");
                                    pKdetailsTable.relayCount=jo.getString("relayCount");
                                    pKdetailsTable.nickName=jo.getString("nickName");
                                    pKdetailsTable.pic=jo.getString("pic");
                                    pKdetailsTable.nickName=jo.getString("nickName");
                                    pKdetailsTable.iszan=jo1.getString("iszan");
                                    pKdetailsTable.zannum=jo1.getString("zannum");
                                    pKdetailsTable.plnum=jo1.getString("plnum");
                                    pKdetailsTable.isgz=jo1.getString("isgz");



                                    pk.paths = new ArrayList<String>();
                                    if(pk.imagesUrl.length()>0){

                                        JSONArray ja = jo.getJSONArray("paths");
                                        for(int j=0;j<ja.length();j++){

                                            pk.paths.add(ja.getString(j));
                                            if(j==0){
                                                pKdetailsTable.paths=ja.getString(j);
                                            }else{
                                                pKdetailsTable.paths=pKdetailsTable.paths+","+ja.getString(j);
                                            }
                                        }

                                    }

                                    pKdetailsTable.save();

                                    video_title.setText(pk.pkContent);
                                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date date = new Date();
                                    String end = sf.format(date);
                                    String fb = MyUtils.stampToDate(pk.createTime);

                                    String time = MyUtils.dateDiff(fb, end, "yyyy-MM-dd HH:mm:ss",pk.createTime);
                                    text_time.setText(time);

                                    text_zan.setText(pk.zannum);

                                    if(pk.pic.length()>0){
                                        Glide.with(getActivity()).load(pk.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(tou);

                                    }

                                    if(Integer.parseInt(pk.iszan)==0){
                                        zan.setImageResource(R.drawable.wdz);
                                    }else{
                                        zan.setImageResource(R.drawable.sc_xz_3x);
                                    }

                                    if(Integer.parseInt(pk.isgz)==0){
                                        guanzhu.setVisibility(View.VISIBLE);
                                    }else{
                                        guanzhu.setVisibility(View.GONE);
                                    }



                                    if(pk.imagesUrl.length()>0){//轮播图
                                        rl_video.setVisibility(View.GONE);
                                        rl_img.setVisibility(View.VISIBLE);
                                        views = new ArrayList<>();

                                        ViewGroup.LayoutParams layoutParams = rl_img.getLayoutParams();

                                        Log.e("TAG","长度：：："+(pmHeight-fbHeight));
                                        Log.e("TAG","长度sssss：：：pmHeight="+pmHeight+"，fbHeight=="+fbHeight);
                                        layoutParams.height=pmHeight-fbHeight;
                                        layoutParams.width= RelativeLayout.LayoutParams.MATCH_PARENT;

                                        rl_img.setLayoutParams(layoutParams);


//            banlist.add("http://h.hiphotos.baidu.com/zhidao/pic/item/c2fdfc039245d688bb61de94a2c27d1ed21b249a.jpg");
//            banlist.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1301/25/c0/17712320_1359096063354.jpg");
//            banlist.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1212/04/c1/16339958_1354613158701.jpg");

                                        jz();

                                        for(int i=0;i<pk.paths.size();i++){
                                            final ImageView view = new ImageView(getActivity());
                                            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                                            RequestOptions options = new RequestOptions()
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL);

                                            Glide.with(mContext).load(pk.paths.get(i)).
                                            apply(options)
                                                    .listener(new RequestListener<Drawable>() {
                                                        @Override
                                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                            return false;
                                                        }

                                                        @Override
                                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                                                            Log.e("TAG","数值：：："+resource.getIntrinsicWidth()+"宽：："+resource.getIntrinsicHeight());
                                                            if (view == null) {
                                                                return false;
                                                            }
                                                            if((pmWidth/pmHeight)<(resource.getIntrinsicWidth()/resource.getIntrinsicHeight())){

                                                            }else{
                                                                if (view.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                                                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                                                                }
                                                            }




                                                            ViewGroup.LayoutParams params = view.getLayoutParams();
                                                            int vw = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();
                                                            float scale = (float) vw / (float) resource.getIntrinsicWidth();
                                                            int vh = Math.round(resource.getIntrinsicHeight() * scale);
                                                            params.height = vh + view.getPaddingTop() + view.getPaddingBottom();
                                                            view.setLayoutParams(params);
                                                            return false;
                                                        }
                                                    })
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
//

                                    }else{//视频
                                        rl_video.setVisibility(View.VISIBLE);
                                        rl_img.setVisibility(View.GONE);
                                        handler=new Handler();
                                        Uri uri = Uri.parse( pk.mvUrl );
                                        MediaController mediaController = new MediaController(getActivity());
                                        mediaController.setVisibility(View.INVISIBLE);
                                        //设置视频控制器
                                        vv_sp.setMediaController(mediaController);

                                        //播放完成回调
                                        vv_sp.setOnCompletionListener( new MyPlayerOnCompletionListener());


                                        if( proxy.isCached(pk.mvUrl)){

                                            Log.d("TAG","是否缓存了#####################");

                                        }else {
                                            Log.d("TAG","是否缓存了&^&&&&&&&&&&&&&&&&&&&&&&&&&");
                                        }
                                        String proxyUrl = proxy.getProxyUrl(pk.mvUrl);
                                        vv_sp.setVideoPath(proxyUrl);
                                        //设置视频路径
//                                        vv_sp.setVideoURI(uri);

                                        vv_sp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                            @Override
                                            public void onPrepared(MediaPlayer mediaPlayer) {

                                                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                                    @Override
                                                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                                                        int mVideoHeight = mediaPlayer.getVideoHeight();
                                                        int mVideoWidth = mediaPlayer.getVideoWidth();
//                                                        Log.e("TAG","视频有效高度：：："+mVideoHeight);


                                                        if(mVideoWidth>mVideoHeight){
                                                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_video.getLayoutParams();
                                                            layoutParams.height=mVideoHeight;
                                                            rl_video.setLayoutParams(layoutParams);

                                                            RelativeLayout.LayoutParams sp = (RelativeLayout.LayoutParams) vv_sp.getLayoutParams();
                                                            layoutParams.width=RelativeLayout.LayoutParams.MATCH_PARENT;
                                                            vv_sp.setLayoutParams(layoutParams);
                                                        }else{
                                                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_video.getLayoutParams();
                                                            layoutParams.width=RelativeLayout.LayoutParams.MATCH_PARENT;
                                                            vv_sp.setLayoutParams(layoutParams);
                                                        }




                                                    }
                                                });
                                            }
                                        });
//                                        vv_sp.setVisibility(View.VISIBLE);



                                        //开始播放视频
                                        vv_sp.start();

                                        handler.postDelayed(runnable,1000);
                                    }

                                } else if(code==201){

                                    startActivity(new Intent(getActivity(),LoginActivity.class));
                                    SpUtils.putInt(getActivity(), "guid", 1);
                                    getActivity().finish();
                                }else {
                                    Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("TAG","解析失败了！！！");
                            }
                        }
                    });
        }
    }



    class MyRun implements Runnable{
        public MyRun(){
        }

        @Override
        public void run() {

            for(int i=0;i<pk.paths.size();i++){

                FutureTarget<File> future = Glide.with(getActivity())
                        .load(pk.paths.get(i))
                        .downloadOnly(500, 500);
                File cacheFile = null;
                try {
                    cacheFile = future.get();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }



        }
    }

    //缓存线程
    public void jz(){

        new Thread(new MyRun()).start();

    }
    private void networklist() {

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", SpUtils.getString(getActivity(),"token",null));
        params.put("objId", pk.pkid);
        params.put("objType", 1);
        OkGo.<String>post(Api.BASE_URL +"app/home/getCommentList")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "评论内容：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                JSONArray datas = jsonobj.getJSONArray("datas");
                                List<PingLunInfo> pingLunInfos = new ArrayList<PingLunInfo>();

                                String allCount=jsonobj.getString("allCount");

                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);

                                    PingLunInfo pingLunInfo = new PingLunInfo();
                                    pingLunInfo.id=jo.getString("id");
                                    pingLunInfo.userId=jo.getString("userId");
                                    pingLunInfo.userName=jo.getString("userName");
                                    pingLunInfo.objId=jo.getString("objId");
                                    pingLunInfo.content=jo.getString("content");
                                    pingLunInfo.createTime=jo.getString("createTime");
                                    pingLunInfo.isDeleted=jo.getString("isDeleted");
                                    pingLunInfo.objType=jo.getString("objType");
                                    pingLunInfo.nickName=jo.getString("nickName");
                                    pingLunInfo.pic=jo.getString("pic");
                                    pingLunInfo.userLevel=jo.getString("userLevel");
                                    pingLunInfo.customReplayLists = new ArrayList<CustomReplayList>();
                                    JSONArray ja=jo.getJSONArray("customReplayList");
                                    for(int j=0;j<ja.length();j++){

                                        JSONObject jo1 = ja.getJSONObject(j);
                                        CustomReplayList crl = new CustomReplayList();
                                        crl.id=jo1.getString("id");
                                        crl.commentFatherId=jo1.getString("commentFatherId");
                                        crl.replyId=jo1.getString("replyId");
                                        crl.beReturnedId=jo1.getString("beReturnedId");
                                        crl.replyContent=jo1.getString("replyContent");
                                        crl.createTime=jo1.getString("createTime");
                                        crl.isDelete=jo1.getString("isDelete");
                                        crl.replName=jo1.getString("replName");
                                        crl.beReturnedName=jo1.getString("beReturnedName");
                                        crl.postId=jo1.getString("postId");
                                        crl.nickName=jo1.getString("nickName");
                                        crl.userId=jo1.getString("userId");
                                        crl.pic=jo1.getString("pic");
                                        pingLunInfo.customReplayLists.add(crl);
                                    }


                                    pingLunInfos.add(pingLunInfo);

                                }
                                if(pageNo>1){

                                    if(pingLunInfos.size()<=0){

                                        Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{

                                        list.addAll(pingLunInfos);

                                    }

                                }else{

                                    list.addAll(pingLunInfos);

                                }



                                if(jiaoLiuyanAdapter==null){

                                    jiaoLiuyanAdapter = new JiaoLiuyanAdapter(getActivity(),list,VideoFragment.this,pk.userId,pk.userName,VideoFragment.this,VideoFragment.this);
                                    recy_view.setAdapter(jiaoLiuyanAdapter);

                                }else{
                                    jiaoLiuyanAdapter.notifyDataSetChanged();
                                }

                                pinglun.setText(allCount+"评论");


                            } else if(code==201){

                                startActivity(new Intent(getActivity(),LoginActivity.class));
                                SpUtils.putInt(getActivity(), "guid", 1);
                                getActivity().finish();
                            }else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private boolean isHf=false;
    private String commentFatherId,beReturnedId;

    @Override
    public void OnHuiFuClickListener(String commentFatherId, String beReturnedId,String name) {
        ed_text.setFocusable(true);
        ed_text.setFocusableInTouchMode(true);
        ed_text.requestFocus();
        InputMethodManager imm = (InputMethodManager)  getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        ed_text.setHint("回复"+name);
        this.commentFatherId=commentFatherId;
        this.beReturnedId=beReturnedId;
        isHf=true;


    }

    @Override
    public void OnHYDataClickListener() {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(ed_text.getWindowToken(), 0);
        }
        ed_text.setText("");
        ed_text.setHint("请说说您的看法...");
        isHf=false;

    }

    private void networkZJPL(String commentFatherId,String beReturnedId) {

        HttpParams params = new HttpParams();
        params.put("commentFatherId", commentFatherId);
//
        params.put("replyContent", content);

        params.put("beReturnedId",beReturnedId);

        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/isertReplay")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                if (imm != null) {
                                    imm.hideSoftInputFromWindow(ed_text.getWindowToken(), 0);
                                }
                                ed_text.setText("");
                                ed_text.setHint("请说说您的看法...");
                                isHf=false;

                                list.clear();
                                pageNo=1;
                                if(pk!=null){
                                    finishFreshAndLoad();
                                }
                            } else if(code==201){

                                startActivity(new Intent(getActivity(),LoginActivity.class));
                                SpUtils.putInt(getActivity(), "guid", 1);
                                getActivity().finish();

                            }else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    @Override
    public void onPinLunFJListener(String id) {
        tuichuDialog(Gravity.CENTER,R.style.Alpah_aniamtion,id,"0");
    }

    @Override
    public void onPinLunZJListener(String id) {
        tuichuDialog(Gravity.CENTER,R.style.Alpah_aniamtion,id,"1");
    }


    //退出登录
    private void tuichuDialog(int grary, int animationStyle, final String id, final String type) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_pl_sc)
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
        TextView tv_clean= dialog.getView(R.id.tv_clean);
        TextView tv_content = dialog.getView(R.id.tv_content);
        TextView tv_canel = dialog.getView(R.id.tv_canel);
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                networkSCPL(id,type);
                //关闭dialog
                dialog.close();
            }
        });
    }

    private void networkSCPL(String id,String type) {

        HttpParams params = new HttpParams();

        params.put("id",id);

        params.put("type",type);

//        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","回复完成了------===========");
        OkGo.<String>post(Api.BASE_URL +"app/home/deleteComment")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                list.clear();
                                pageNo=1;
                                if(pk!=null){
                                    finishFreshAndLoad();
                                }

                            }else if(code==201){

                                startActivity(new Intent(getActivity(),LoginActivity.class));
                                SpUtils.putInt(getActivity(), "guid", 1);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
//            Toast.makeText( LocalVideoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();

            iv_xq_bf.setVisibility(View.VISIBLE);
            isjs=true;

        }
    }

//    @Override
//    public void onPause() {
//        try{
//            if(vv_sp.isPlaying()){
//                vv_sp.pause();
//            }
//            vv_sp.stopPlayback();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        super.onPause();
//    }
//
//    @Override
//    public void onStop() {
//        vv_sp = null;
//        super.onStop();
//    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ding.getBackground().mutate().setAlpha(0);

        try{
            if(vv_sp.isPlaying()){
                vv_sp.pause();
            }
            vv_sp.stopPlayback();
            vv_sp=null;
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            Log.e("TAG","当前进度：：："+vv_sp.getCurrentPosition()+",总进度：：：："+vv_sp.getDuration());

            if(vv_sp.getDuration()!=-1){
                sp_jd.setMax(vv_sp.getDuration());
                rl_bs.setVisibility(View.GONE);
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
        int guid = SpUtils.getInt(getActivity(), "guid", 0);
        switch(view.getId()){
            //点击返回
            case R.id.img_back:

                if(ispk){
                    MsgBus msgBus = new MsgBus();
                    msgBus.tiaozhuan="首页";
                    EventBus.getDefault().postSticky(msgBus);
                }
                getActivity().finish();
                break;
            case R.id.guanzhu:
                if(guid!=2){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }else{
                    if(NetUtil.checkNet(getActivity())){
                        networkGZ(Integer.parseInt(pk.userId));
                    }else{
                        Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.video_tou:
                Intent intent = new Intent(getActivity(), MydynamicActivity.class);
                intent.putExtra("uid",pk.userId);
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
                networkFxJF();
                networkFxTj("2");
                break;
            case R.id.ll_pyq:
                SharebyWeixincenter(getActivity());
                networkFxJF();
                networkFxTj("3");
                break;
            case R.id.ll_qq:
                SharebyQQ(getActivity());
                networkFxJF();
                networkFxTj("0");
                break;
            case R.id.ll_kj:
                SharebyQzon(getActivity());
                networkFxJF();
                networkFxTj("1");
                break;
            case R.id.ll_wb:
                if(isWxInstall(getActivity())){
                    SharebyWeiBo(getActivity());
                    networkFxJF();
                    networkFxTj("4");
                }else {
                    Toast.makeText(getActivity(),"请先安装微博",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.quxiao:
                dialog.dismiss();
                break;
            case R.id.video_zan:
                if(guid!=2){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }else{
                    if(NetUtil.checkNet(getActivity())){
                        zan.setEnabled(false);
                        if(Integer.parseInt(pk.iszan)==0){
                            Log.e("TAG","点击点赞");
                            network(Integer.parseInt(pk.pkid),2,0);
                        }else{
                            QXnetwork(Integer.parseInt(pk.pkid),2,0);
                        }
                    }else{
                        Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
            case R.id.button_fabu:

                if(guid!=2){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }else{
                    content=ed_text.getText().toString().trim();

                    if(content!=null&&content.length()>0){

                        if(NetUtil.checkNet(getActivity())){
                            if(isHf){
                                networkZJPL(commentFatherId,beReturnedId);
                            }else{
                                networkFB();
                            }
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }


                    }else {
                        Toast.makeText(getActivity(),"请输入评论内容",Toast.LENGTH_SHORT).show();
                    }

                }


                break;

        }
    }

    /**
     * 检测是否安装微信
     *
     * @param context
     * @return
     */
    public static boolean isWxInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.sina.weibo")) {
                    return true;
                }
            }
        }

        return false;
    }

    private void networkFxTj(String share_status) {

        HttpParams params = new HttpParams();
        Log.d("TAG","数据内容"+params.toString());
        params.put("objId",pk.pkid);
        params.put("obj_type",2);
        params.put("operation_type",2);
        params.put("share_status",share_status);
        params.put("token", SpUtils.getString(getActivity(),"token",null));
        OkGo.<String>post(Api.BASE_URL +"app/home/userOperation")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "分享" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                            }else {
//                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private void networkFxJF() {

        HttpParams params = new HttpParams();
        Log.d("TAG","数据内容"+params.toString());
        params.put("token", SpUtils.getString(getActivity(),"token",null));
        OkGo.<String>post(Api.BASE_URL +"app/home/shareAddMemberPoint")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                            }else {
//                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }
    private String content="";

    private void networkFB() {

        HttpParams params = new HttpParams();
        params.put("objId", pk.pkid);
//
        params.put("content", content);

        params.put("objType",1);

        params.put("token",SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/isertCommentFather")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                if (imm != null) {
                                    imm.hideSoftInputFromWindow(ed_text.getWindowToken(), 0);
                                }

                                ed_text.setText("");
                                ed_text.setHint("请说说您的看法...");

                                list.clear();
                                pageNo=1;
                                if(pk!=null){
                                    finishFreshAndLoad();
                                }

                            } else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }
    private void network(int objId , int obj_type, final int operation_type) {

        HttpParams params = new HttpParams();
        params.put("objId", objId);

        params.put("obj_type", obj_type);

        params.put("operation_type",operation_type);

        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/userOperation")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "数据" + body);
                        JSONObject jsonobj = null;
                        zan.setEnabled(true);
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                    pk.iszan="1";
                                    pk.zannum=Integer.parseInt(pk.zannum)+1+"";

                                zan.setImageResource(R.drawable.sc_xz_3x);
                                text_zan.setText(pk.zannum);

                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.LOGING_SX);
                                getActivity().sendBroadcast(intent);


                            } else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }
    private void QXnetwork(int objId , int obj_type, final int operation_type) {

        HttpParams params = new HttpParams();
        params.put("objId", objId);

        params.put("obj_type", obj_type);

        params.put("operation_type",operation_type);

        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/userCancelOperation")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "取消数据" + body);
                        JSONObject jsonobj = null;
                        zan.setEnabled(true);
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                    pk.iszan="0";
                                    pk.zannum=Integer.parseInt(pk.zannum)-1+"";
                                    zan.setImageResource(R.drawable.wdz);
                                text_zan.setText(pk.zannum);
                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.LOGING_SX);
                                getActivity().sendBroadcast(intent);
                            } else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }
    private void networkGZ(int concernedId) {

        HttpParams params = new HttpParams();

        params.put("concernedId",concernedId);

        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/insertFollowUser")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {


                                pk.isgz="1";
                                guanzhu.setVisibility(View.GONE);

                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.GZ);
                                getActivity().sendBroadcast(intent);

                            }else if(code==201){

                                startActivity(new Intent(getActivity(),LoginActivity.class));
                                SpUtils.putInt(getActivity(), "guid", 1);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    protected  void SharebyQQ(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"pkxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+pk.pkid);
        web.setTitle(pk.pkContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription("拍客");//描述

        new ShareAction(context)
                .withMedia(web)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyQzon(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"pkxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+pk.pkid);
        web.setTitle(pk.pkContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription("拍客");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeixin(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"pkxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+pk.pkid);
        web.setTitle(pk.pkContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription("拍客");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void SharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"pkxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+pk.pkid);
        web.setTitle(pk.pkContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription("拍客");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeiBo(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"pkxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+pk.pkid);
        web.setTitle(pk.pkContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription("拍客");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.SINA)//传入平台
                .withMedia(web)//分享内容
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



        if(pk!=null){
            if(pk.imagesUrl.length()>0) {//轮播图
            }else{
                if(handler!=null&&runnable!=null){
                    handler.removeCallbacks(runnable);
                }
            }
        }
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
