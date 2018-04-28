package www.diandianxing.com.diandianxing.fragment.paikefragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnTouch;
import www.diandianxing.com.diandianxing.adapter.JiaoLiuyanAdapter;
import www.diandianxing.com.diandianxing.adapter.Jiaodianadapter;
import www.diandianxing.com.diandianxing.adapter.MyPagerAdapter;
import www.diandianxing.com.diandianxing.adapter.Tuijiantieadapter;
import www.diandianxing.com.diandianxing.adapter.Video_pinglun_Adapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.CustomReplayList;
import www.diandianxing.com.diandianxing.bean.PaiKeDRInfo;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.bean.PingLunInfo;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.interfase.HuiFuClickListener;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Mr赵 on 2018/4/8.
 */

public class VideoFragment extends BaseFragment implements View.OnClickListener ,HuiFuClickListener{

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

        Bundle arguments = getArguments();

        pk= (PaiKeInfo) arguments.getSerializable("pk");

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
            params.put("token", Api.token);
            params.put("pkId", pk.id);

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
                                JSONObject jo1 = jsonobj.getJSONObject("datas");
                                if (code == 200) {

                                    JSONObject jo=jo1.getJSONObject("paike");
                                    pk = new PaiKeInfo();
                                    pk.id=jo.getString("id");
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

                                    pk.paths = new ArrayList<String>();
                                    if(pk.imagesUrl.length()>0){

                                        JSONArray ja = jo.getJSONArray("paths");
                                        for(int j=0;j<ja.length();j++){

                                            pk.paths.add(ja.getString(j));

                                        }

                                    }


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


//            banlist.add("http://h.hiphotos.baidu.com/zhidao/pic/item/c2fdfc039245d688bb61de94a2c27d1ed21b249a.jpg");
//            banlist.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1301/25/c0/17712320_1359096063354.jpg");
//            banlist.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1212/04/c1/16339958_1354613158701.jpg");
                                        for(int i=0;i<pk.paths.size();i++){
                                            ImageView view = new ImageView(getActivity());
                                            Glide.with(mContext).load(pk.paths.get(i))
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

                                        //设置视频路径
                                        vv_sp.setVideoURI(uri);

                                        //开始播放视频
                                        vv_sp.start();
                                        handler.postDelayed(runnable,1000);
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
    }


    private void networklist() {

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", Api.token);
        params.put("objId", pk.id);
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
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<PingLunInfo> pingLunInfos = new ArrayList<PingLunInfo>();
                            if (code == 200) {

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

                                    jiaoLiuyanAdapter = new JiaoLiuyanAdapter(getActivity(),list,VideoFragment.this,pk.userId,pk.userName);
                                    recy_view.setAdapter(jiaoLiuyanAdapter);

                                }else{
                                    jiaoLiuyanAdapter.notifyDataSetChanged();
                                }

                                pinglun.setText(allCount+"评论");


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

        params.put("token", Api.token);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ding.getBackground().mutate().setAlpha(0);
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
                if(NetUtil.checkNet(getActivity())){
                    networkGZ(Integer.parseInt(pk.userId));
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
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
            case R.id.video_zan:
                if(NetUtil.checkNet(getActivity())){
                    if(Integer.parseInt(pk.iszan)==0){
                        Log.e("TAG","点击点赞");
                        network(Integer.parseInt(pk.id),0,0);
                    }else{
                        QXnetwork(Integer.parseInt(pk.id),0,0);
                    }
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.button_fabu:

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

                break;

        }
    }
    private String content="";

    private void networkFB() {

        HttpParams params = new HttpParams();
        params.put("objId", pk.id);
//
        params.put("content", content);

        params.put("objType",1);

        params.put("token", Api.token);
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

        params.put("token", Api.token);
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
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                    pk.iszan="1";
                                    pk.zannum=Integer.parseInt(pk.zannum)+1+"";

                                zan.setImageResource(R.drawable.sc_xz_3x);
                                text_zan.setText(pk.zannum);

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

        params.put("token", Api.token);
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
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                    pk.iszan="0";
                                    pk.zannum=Integer.parseInt(pk.zannum)-1+"";
                                    zan.setImageResource(R.drawable.wdz);
                                text_zan.setText(pk.zannum);
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

        params.put("token", Api.token);
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
        if(pk!=null){
            if(pk.imagesUrl.length()>0) {//轮播图
            }else{
                handler.removeCallbacks(runnable);
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
