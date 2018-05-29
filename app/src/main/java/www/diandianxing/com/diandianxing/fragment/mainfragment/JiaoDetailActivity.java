package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.LiveActivity;
import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.MyCollectionActivity;
import www.diandianxing.com.diandianxing.adapter.JiaoLiuyanAdapter;
import www.diandianxing.com.diandianxing.adapter.Jiaodianadapter;
import www.diandianxing.com.diandianxing.adapter.TPAdapter1;
import www.diandianxing.com.diandianxing.adapter.Tuijiantieadapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.CustomReplayList;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.Info;
import www.diandianxing.com.diandianxing.bean.PingLunInfo;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.interfase.HuiFuClickListener;
import www.diandianxing.com.diandianxing.interfase.PinLunFJListener;
import www.diandianxing.com.diandianxing.interfase.PinLunZJListener;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.set.SetActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.ConUtils;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.util.EventMessage;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class JiaoDetailActivity extends BaseActivity implements View.OnClickListener ,HuiFuClickListener,PinLunZJListener,PinLunFJListener{


    private ImageView tv_back;
    private TextView img_collect;
    private RelativeLayout real;
    private TextView text_title;
    private ImageView img_tou;
    private TextView text_name;
    private TextView da_address;
    private ImageView imageView2;
    private TextView text_dengji;
    private RelativeLayout rela_guanzhu;
    private TextView text_count;
    private RecyclerView jiao_Recycler;
    private ImageView text_share;
    private TextView text_zan;
    private RecyclerView jiao_pinglun;
    private TextView liuyan;
    private EditText ed_text;
    private Button button_fabu;
    private TextView tv_jdxq_gz;
    private TextView text_sure;
    GuanzhuJD guanzhuJD;
    private int pageNo=1;
    JiaoLiuyanAdapter jiaoLiuyanAdapter;
    private List<PingLunInfo> list = new ArrayList<>();

    private SpringView sv_pj;
    private String id;

    private int type;
    private TextView tv_xxjd;
    private int pos;

    private LinearLayout lines;

    private int KeyboardHeight;
    private boolean isdy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_jiaodetail);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.XQ);
        registerReceiver(broadcastReceiver,intentFilter);
        initView();
    }

    private void initView() {
        tv_back = (ImageView) findViewById(R.id.tv_back);
        img_collect = (TextView) findViewById(R.id.img_collect);
        real = (RelativeLayout) findViewById(R.id.real);
        text_title = (TextView) findViewById(R.id.text_title);
        img_tou = (ImageView) findViewById(R.id.img_tou);
        lines= (LinearLayout) findViewById(R.id.lines);
        text_name = (TextView) findViewById(R.id.text_name);
        da_address = (TextView) findViewById(R.id.da_address);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text_dengji = (TextView) findViewById(R.id.text_dengji);
        rela_guanzhu = (RelativeLayout) findViewById(R.id.rela_guanzhu);
        text_count = (TextView) findViewById(R.id.text_count);
        jiao_Recycler = (RecyclerView) findViewById(R.id.jiao_Recycler);
        text_share = (ImageView) findViewById(R.id.text_share);
        text_zan = (TextView) findViewById(R.id.text_zan);
        tv_xxjd= (TextView) findViewById(R.id.tv_xxjd);
        jiao_pinglun = (RecyclerView) findViewById(R.id.jiao_pinglun);
        liuyan = (TextView) findViewById(R.id.text_liuyan);
        ed_text = (EditText) findViewById(R.id.ed_text);
        tv_jdxq_gz= (TextView) findViewById(R.id.tv_jdxq_gz);
        button_fabu = (Button) findViewById(R.id.button_fabu);
        sv_pj= (SpringView) findViewById(R.id.sv_pj);


        guanzhuJD = (GuanzhuJD) getIntent().getSerializableExtra("guanzhu");
        String title= getIntent().getStringExtra("title");

        if(title!=null){

            tv_xxjd.setText(title);

        }

        id=getIntent().getStringExtra("id");
        pos=getIntent().getIntExtra("pos",0);
        type=getIntent().getIntExtra("type",-1);

        Log.e("TAG","帖子Id==="+id);


        if(guanzhuJD!=null){
            text_title.setText(guanzhuJD.postTitle);
            Glide.with(JiaoDetailActivity.this).load(guanzhuJD.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img_tou);
            text_name.setText(guanzhuJD.userName);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String end = sf.format(date);
            String fb = MyUtils.stampToDate(guanzhuJD.updateTime);

            String time = MyUtils.dateDiff(fb, end, "yyyy-MM-dd HH:mm:ss",guanzhuJD.updateTime);

            da_address.setText(time+" "+guanzhuJD.address);
            text_dengji.setText(guanzhuJD.userLevel);
            Log.e("TAG","关注是否：：："+guanzhuJD.is_focus);
            if(Integer.parseInt(guanzhuJD.is_focus)==0){
                rela_guanzhu.setVisibility(View.VISIBLE);
            }else{
                rela_guanzhu.setVisibility(View.INVISIBLE);
            }
            text_count.setText(guanzhuJD.postContent);

            if(Integer.parseInt(guanzhuJD.collectCount)==0){
                img_collect.setText("收藏");
            }else{
                img_collect.setText(guanzhuJD.collectCount);
            }

            if(Integer.parseInt(guanzhuJD.dianZanCount)==0){
                text_zan.setText("点赞");
            }else{
                text_zan.setText(guanzhuJD.dianZanCount);
            }

            if(Integer.parseInt(guanzhuJD.is_collect)==0){
                Drawable nav_up=getResources().getDrawable(R.drawable.icon_collect);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                img_collect.setCompoundDrawables(nav_up, null, null, null);
            }else{
                Drawable nav_up=getResources().getDrawable(R.drawable.shouchang_xz_icon_3x);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                img_collect.setCompoundDrawables(nav_up, null, null, null);
            }
            if(Integer.parseInt(guanzhuJD.is_zan)==0){
                Drawable nav_up=getResources().getDrawable(R.drawable.icon_dianzan);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                text_zan.setCompoundDrawables(nav_up, null, null, null);
            }else{
                Drawable nav_up=getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                text_zan.setCompoundDrawables(nav_up, null, null, null);
            }

            if(guanzhuJD.imagesList.size()>3){
                jiao_Recycler.setLayoutManager(new GridLayoutManager(this,3));
            }else{
                jiao_Recycler.setLayoutManager(new GridLayoutManager(this,guanzhuJD.imagesList.size()));
            }

            jiao_Recycler.setNestedScrollingEnabled(false);
            TPAdapter1 tpAdapter1 = new TPAdapter1(this,guanzhuJD.imagesList);
            jiao_Recycler.setAdapter(tpAdapter1);
        }else if(id!=null){
            Log.e("TAG","帖子详情#############################");
            if(NetUtil.checkNet(JiaoDetailActivity.this)){
                networkXX();
            }else{
                Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            }
        }


        jiao_pinglun.setLayoutManager(new LinearLayoutManager(this));
        jiao_pinglun.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        jiao_pinglun.setNestedScrollingEnabled(false);
        initRefresh();
        /**
         *点击事件
         */
        tv_back.setOnClickListener(this);
        img_tou.setOnClickListener(this);
        tv_jdxq_gz.setOnClickListener(this);
        text_share.setOnClickListener(this);
        img_collect.setOnClickListener(this);
        text_zan.setOnClickListener(this);
        button_fabu.setOnClickListener(this);
        if(guanzhuJD!=null){
            id=guanzhuJD.id;
            jiaoLiuyanAdapter = new JiaoLiuyanAdapter(JiaoDetailActivity.this,list,JiaoDetailActivity.this,guanzhuJD.userId,guanzhuJD.userName,this,this);
            jiao_pinglun.setAdapter(jiaoLiuyanAdapter);
            if(NetUtil.checkNet(JiaoDetailActivity.this)){
                finishFreshAndLoad();
            }else{
                Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            }
        }


        final View myLayout = getWindow().getDecorView();
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
                Log.e("TAG", "keyboard height(单位像素) = " + realKeyboardHeight);

                LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) lines.getLayoutParams();



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



//                if(realKeyboardHeight!=0){
//                    params.bottomMargin=realKeyboardHeight;
//                }else{
//                    params.bottomMargin=0;
//                }
                lines.setLayoutParams(params);
                isdy=true;
            }
        });
        //不管软键盘是否打开都关闭软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm) {
            boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
            if (isOpen) {
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                if (getCurrentFocus() != null) {//强制关闭软键盘
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }

    }

    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        sv_pj.setType(SpringView.Type.FOLLOW);
        sv_pj.setHeader(new DefaultHeader(JiaoDetailActivity.this));
        sv_pj.setFooter(new DefaultFooter(JiaoDetailActivity.this));
        sv_pj.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(mContext,"下拉刷新中",Toast.LENGTH_SHORT).show();
                // list.clear();
                // 网络请求;
                // mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法

                list.clear();
                pageNo=1;
                if(NetUtil.checkNet(JiaoDetailActivity.this)){
                    if(guanzhuJD!=null){
                        finishFreshAndLoad();
                    }
                }else{
                    Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                pageNo++;
                if(guanzhuJD!=null){
                    if(NetUtil.checkNet(JiaoDetailActivity.this)){
                        finishFreshAndLoad();
                    }else{
                        Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                    }
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
                sv_pj.onFinishFreshAndLoad();
            }
        }, 0);
    }

    @Override
    public void onClick(View view) {
        int guid = SpUtils.getInt(JiaoDetailActivity.this, "guid", 0);
        switch(view.getId()){
            case R.id.tv_back:
                Intent gb;
                if(type==0){
                    Intent intent = new Intent(JiaoDetailActivity.this,JiaodianActivity.class);

                    gb = new Intent();
                    gb.setAction(GlobalParams.MRJD);
                    gb.putExtra("tianzhuan",true);
                    sendBroadcast(gb);

                    intent.putExtra("tianzhuan",true);

                                    startActivity(intent);
                }else if(type==1){
                    Intent intent = new Intent(JiaoDetailActivity.this,LiveActivity.class);

                    gb = new Intent();
                    gb.setAction(GlobalParams.SHFW);
                    gb.putExtra("pos",pos);
                    sendBroadcast(gb);
                    intent.putExtra("pos",pos);
                    startActivity(intent);
                }
                finish();
                break;
            case R.id.img_tou:
                Intent intent = new Intent(JiaoDetailActivity.this, MydynamicActivity.class);
                intent.putExtra("uid",guanzhuJD.userId);
                startActivity(intent);
                break;
            case R.id.tv_jdxq_gz:

                if(guid!=2){
                    startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));
                }else{
                    networkGZ();
                    shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                }
                break;
            case R.id.text_share:
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.img_collect:

                if(guid!=2){
                    startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));
                }else{
                    img_collect.setEnabled(false);
                    if(NetUtil.checkNet(JiaoDetailActivity.this)){
                        if(Integer.parseInt(guanzhuJD.is_collect)==0){
                            network(Integer.parseInt(guanzhuJD.id),0,1);
                        }else{
                            QXnetwork(Integer.parseInt(guanzhuJD.id),0,1);
                        }
                    }else{
                        Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.text_zan:
                if(guid!=2){
                    startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));
                }else{
                    text_zan.setEnabled(false);
                    if(NetUtil.checkNet(JiaoDetailActivity.this)){
                        if(Integer.parseInt(guanzhuJD.is_zan)==0){

                            network(Integer.parseInt(guanzhuJD.id),0,0);

                        }else{
                            QXnetwork(Integer.parseInt(guanzhuJD.id),0,0);
                        }

                    }else{
                        Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
            case R.id.button_fabu:

                if(guid!=2){
                    startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));
                }else{
                    content=ed_text.getText().toString().trim();
                    if(content!=null&&content.length()>0){
                        if(NetUtil.checkNet(JiaoDetailActivity.this)){
                            if(isHf){
                                networkZJPL(commentFatherId,beReturnedId);
                            }else{
                                networkFB();
                            }
                        }else{
                            Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(JiaoDetailActivity.this,"请输入评论内容",Toast.LENGTH_SHORT).show();
                    }
                }



                break;
        }
    }


    private String content="";



    private void networkXX() {

        HttpParams params = new HttpParams();
        params.put("postId", id);
        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/getPostById")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "关注焦点" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                JSONObject jo = jsonobj.getJSONObject("datas");

                                    guanzhuJD= new GuanzhuJD();
                                guanzhuJD.id=jo.getString("id");
                                guanzhuJD.userId=jo.getString("userId");
                                guanzhuJD.postType=jo.getString("postType");
                                guanzhuJD.postTitle=jo.getString("postTitle");
                                guanzhuJD.postContent=jo.getString("postContent");
                                guanzhuJD.createTime=jo.getString("createTime");
                                guanzhuJD.updateTime=jo.getString("updateTime");
                                guanzhuJD.address=jo.getString("address");
                                guanzhuJD.postFlage=jo.getString("postFlage");
                                guanzhuJD.postStatus=jo.getString("postStatus");
                                guanzhuJD.isDeleted=jo.getString("isDeleted");
                                guanzhuJD.userName=jo.getString("userName");
                                guanzhuJD.userLevel=jo.getString("userLevel");
                                guanzhuJD.postImge=jo.getString("postImge");
                                guanzhuJD.commentCount=jo.getString("commentCount");
                                guanzhuJD.dianZanCount=jo.getString("dianZanCount");
                                guanzhuJD.collectCount=jo.getString("collectCount");
                                guanzhuJD.relayCount=jo.getString("relayCount");
                                guanzhuJD.pic=jo.getString("pic");
                                guanzhuJD.is_collect=jo.getString("is_collect");
                                guanzhuJD.is_zan=jo.getString("is_zan");
                                guanzhuJD.is_fx=jo.getString("is_fx");
                                guanzhuJD.is_focus=jo.getString("is_focus");
                                    JSONArray ja=jo.getJSONArray("imagesList");

                                guanzhuJD.imagesList = new ArrayList<String>();
                                    for(int j=0;j<ja.length();j++){
                                        guanzhuJD.imagesList.add(ja.getString(j));
                                    }

                                if(guanzhuJD!=null){
                                    text_title.setText(guanzhuJD.postTitle);
                                    Glide.with(JiaoDetailActivity.this).load(guanzhuJD.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img_tou);
                                    text_name.setText(guanzhuJD.userName);

                                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date date = new Date();
                                    String end = sf.format(date);
                                    String fb = MyUtils.stampToDate(guanzhuJD.updateTime);

                                    String time = MyUtils.dateDiff(fb, end, "yyyy-MM-dd HH:mm:ss",guanzhuJD.updateTime);
                                    da_address.setText(time+" "+guanzhuJD.address);
                                    text_dengji.setText(guanzhuJD.userLevel);
                                    Log.e("TAG","关注是否11111111111：：："+guanzhuJD.is_focus);
                                    if(Integer.parseInt(guanzhuJD.is_focus)==0){

                                        Log.e("TAG","关注按钮打开======================================");

                                        rela_guanzhu.setVisibility(View.VISIBLE);
                                    }else{
                                        rela_guanzhu.setVisibility(View.INVISIBLE);
                                    }
                                    text_count.setText(guanzhuJD.postContent);

                                    if(Integer.parseInt(guanzhuJD.collectCount)==0){
                                        img_collect.setText("收藏");
                                    }else{
                                        img_collect.setText(guanzhuJD.collectCount);
                                    }

                                    if(Integer.parseInt(guanzhuJD.dianZanCount)==0){
                                        text_zan.setText("点赞");
                                    }else{
                                        text_zan.setText(guanzhuJD.dianZanCount);
                                    }

                                    if(Integer.parseInt(guanzhuJD.is_collect)==0){
                                        Drawable nav_up=getResources().getDrawable(R.drawable.icon_collect);
                                        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                        img_collect.setCompoundDrawables(nav_up, null, null, null);
                                    }else{
                                        Drawable nav_up=getResources().getDrawable(R.drawable.shouchang_xz_icon_3x);
                                        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                        img_collect.setCompoundDrawables(nav_up, null, null, null);
                                    }
                                    if(Integer.parseInt(guanzhuJD.is_zan)==0){
                                        Drawable nav_up=getResources().getDrawable(R.drawable.icon_dianzan);
                                        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                        text_zan.setCompoundDrawables(nav_up, null, null, null);
                                    }else{
                                        Drawable nav_up=getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
                                        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                        text_zan.setCompoundDrawables(nav_up, null, null, null);
                                    }

                                    Log.e("TAG","数据问题--++++++++++--"+guanzhuJD.imagesList.size());
                                    if(guanzhuJD.imagesList.size()>0){

                                        if(guanzhuJD.imagesList.size()>3){
                                            jiao_Recycler.setLayoutManager(new GridLayoutManager(JiaoDetailActivity.this,3));
                                        }else{
                                            jiao_Recycler.setLayoutManager(new GridLayoutManager(JiaoDetailActivity.this,guanzhuJD.imagesList.size()));
                                        }
                                        jiao_Recycler.setNestedScrollingEnabled(false);
                                        TPAdapter1 tpAdapter1 = new TPAdapter1(JiaoDetailActivity.this,guanzhuJD.imagesList);
                                        jiao_Recycler.setAdapter(tpAdapter1);
                                    }
                                }
                                jiaoLiuyanAdapter = new JiaoLiuyanAdapter(JiaoDetailActivity.this,list,JiaoDetailActivity.this,guanzhuJD.userId,guanzhuJD.userName,JiaoDetailActivity.this,JiaoDetailActivity.this);
                                jiao_pinglun.setAdapter(jiaoLiuyanAdapter);
                                if(NetUtil.checkNet(JiaoDetailActivity.this)){
                                    finishFreshAndLoad();
                                }else{
                                    Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                                }
                            } else if(code==201){

                                startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));

                                SpUtils.putInt(JiaoDetailActivity.this, "guid", 1);

                            }else {
                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }
    private void networkFB() {

        HttpParams params = new HttpParams();
        params.put("objId", guanzhuJD.id);
//
        params.put("content", content);

        params.put("objType",0);

        params.put("token", SpUtils.getString(this,"token",null));
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
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                if (imm != null) {
                                    imm.hideSoftInputFromWindow(ed_text.getWindowToken(), 0);
                                }

                                ed_text.setText("");
                                ed_text.setHint("请说说您的看法...");

                                list.clear();
                                pageNo=1;
                                if(guanzhuJD!=null){
                                    finishFreshAndLoad();
                                }

                            } else if(code==201){

                                startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));
                                SpUtils.putInt(JiaoDetailActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

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

        params.put("token", SpUtils.getString(this,"token",null));
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

                                JSONObject datas = jsonobj.getJSONObject("datas");

                                if(operation_type==0){
                                    guanzhuJD.is_zan="1";
                                    guanzhuJD.dianZanCount=datas.getString("zanCount");
                                }else{
                                    guanzhuJD.is_collect="1";
                                    guanzhuJD.collectCount=datas.getString("zanCount");
                                }
                                if(Integer.parseInt(guanzhuJD.collectCount)==0){
                                    img_collect.setText("收藏");
                                }else{
                                    img_collect.setText(guanzhuJD.collectCount);
                                }

                                img_collect.setEnabled(true);
                                text_zan.setEnabled(true);
                                if(Integer.parseInt(guanzhuJD.dianZanCount)==0){
                                    text_zan.setText("点赞");
                                }else{
                                    text_zan.setText(guanzhuJD.dianZanCount);
                                }

                                if(Integer.parseInt(guanzhuJD.is_collect)==0){
                                    Drawable nav_up=getResources().getDrawable(R.drawable.icon_collect);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    img_collect.setCompoundDrawables(nav_up, null, null, null);
                                }else{
                                    Drawable nav_up=getResources().getDrawable(R.drawable.shouchang_xz_icon_3x);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    img_collect.setCompoundDrawables(nav_up, null, null, null);
                                }
                                if(Integer.parseInt(guanzhuJD.is_zan)==0){
                                    Drawable nav_up=getResources().getDrawable(R.drawable.icon_dianzan);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    text_zan.setCompoundDrawables(nav_up, null, null, null);
                                }else{
                                    Drawable nav_up=getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    text_zan.setCompoundDrawables(nav_up, null, null, null);
                                }

                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.JDSX);
                                sendBroadcast(intent);

                                Intent gz = new Intent();
                                gz.setAction(GlobalParams.GZ);
                                sendBroadcast(gz);


                            } else if(code==201){

                                startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));
                                SpUtils.putInt(JiaoDetailActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                            img_collect.setEnabled(true);
                            text_zan.setEnabled(true);
                        }
                    }
                });
    }


    private void QXnetwork(int objId , int obj_type, final int operation_type) {

        HttpParams params = new HttpParams();
        params.put("objId", objId);

        params.put("obj_type", obj_type);

        params.put("operation_type",operation_type);

        params.put("token", SpUtils.getString(this,"token",null));
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

                                JSONObject datas = jsonobj.getJSONObject("datas");
                                if(operation_type==0){
                                    guanzhuJD.is_zan="0";
                                    guanzhuJD.dianZanCount=datas.getString("zanCount");
                                }else{
                                    guanzhuJD.is_collect="0";
                                    guanzhuJD.collectCount=datas.getString("zanCount");
                                }
                                img_collect.setEnabled(true);
                                text_zan.setEnabled(true);
                                if(Integer.parseInt(guanzhuJD.collectCount)==0){
                                    img_collect.setText("收藏");
                                }else{
                                    img_collect.setText(guanzhuJD.collectCount);
                                }
                                    Log.e("TAG","收藏数量：：："+guanzhuJD.collectCount);
                                if(Integer.parseInt(guanzhuJD.dianZanCount)==0){
                                    text_zan.setText("点赞");
                                }else{
                                    text_zan.setText(guanzhuJD.dianZanCount);
                                }

                                if(Integer.parseInt(guanzhuJD.is_collect)==0){
                                    Drawable nav_up=getResources().getDrawable(R.drawable.icon_collect);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    img_collect.setCompoundDrawables(nav_up, null, null, null);
                                }else{
                                    Drawable nav_up=getResources().getDrawable(R.drawable.shouchang_xz_icon_3x);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    img_collect.setCompoundDrawables(nav_up, null, null, null);
                                }
                                if(Integer.parseInt(guanzhuJD.is_zan)==0){
                                    Drawable nav_up=getResources().getDrawable(R.drawable.icon_dianzan);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    text_zan.setCompoundDrawables(nav_up, null, null, null);
                                }else{
                                    Drawable nav_up=getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    text_zan.setCompoundDrawables(nav_up, null, null, null);
                                }
                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.JDSX);
                                sendBroadcast(intent);

                                Intent gz = new Intent();
                                gz.setAction(GlobalParams.GZ);
                                sendBroadcast(gz);

                            } else if(code==201){

                                startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));
                                SpUtils.putInt(JiaoDetailActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                            img_collect.setEnabled(true);
                            text_zan.setEnabled(true);
                        }
                    }
                });
    }


    private void networkFxTj(String share_status) {

        HttpParams params = new HttpParams();
        Log.d("TAG","数据内容"+params.toString());
        params.put("objId",guanzhuJD.id);
        params.put("obj_type",0);
        params.put("operation_type",2);
        params.put("share_status",share_status);
        params.put("token", SpUtils.getString(JiaoDetailActivity.this,"token",null));
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
//                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();
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
        params.put("token", SpUtils.getString(JiaoDetailActivity.this,"token",null));
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
//                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private void networklist() {

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", SpUtils.getString(this,"token",null));
        params.put("objId", id);
        params.put("objType", 0);
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

                                        Toast.makeText(JiaoDetailActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        list.addAll(pingLunInfos);
                                    }

                                }else{

                                    list.addAll(pingLunInfos);

                                }

                                if(jiaoLiuyanAdapter!=null){
                                    jiaoLiuyanAdapter.notifyDataSetChanged();

                                }

                                liuyan.setText("全部留言（"+allCount+"）");


                            } else if(code==201){

                                startActivity(new Intent(JiaoDetailActivity.this,LoginActivity.class));
                                SpUtils.putInt(JiaoDetailActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;
    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(JiaoDetailActivity.this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view)
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
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ll_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharebyWeixin(JiaoDetailActivity.this);
                networkFxJF();
                networkFxTj("2");
//                dialog.dismiss();
            }
        });
        ll_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharebyWeixincenter(JiaoDetailActivity.this);
                networkFxJF();
                networkFxTj("3");
//                dialog.dismiss();
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharebyQQ(JiaoDetailActivity.this);
                networkFxJF();
                networkFxTj("0");
//                dialog.dismiss();
            }
        });
        ll_kj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharebyQzon(JiaoDetailActivity.this);
                networkFxJF();
                networkFxTj("1");
//                dialog.dismiss();
            }
        });
        ll_wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isWxInstall(JiaoDetailActivity.this)){
                    SharebyWeiBo(JiaoDetailActivity.this);
                    networkFxJF();
                    networkFxTj("4");
                }else {
                    Toast.makeText(JiaoDetailActivity.this,"请先安装微博",Toast.LENGTH_SHORT).show();
                }
//                dialog.dismiss();

            }
        });
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



    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();


            if(GlobalParams.XQ.equals(action)){


                Log.e("TAG","刷新数据内容了@@@@@@@@");

                networkXX();

            }

        }
    };


    protected  void SharebyQQ(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(JiaoDetailActivity.this,"token",null)+"&id="+guanzhuJD.id);
        web.setTitle(guanzhuJD.postContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(guanzhuJD.objName);//描述

        new ShareAction(context)
                .withMedia(web)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyQzon(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(JiaoDetailActivity.this,"token",null)+"&id="+guanzhuJD.id);
        web.setTitle(guanzhuJD.postContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(guanzhuJD.objName);//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeixin(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(JiaoDetailActivity.this,"token",null)+"&id="+guanzhuJD.id);
        web.setTitle(guanzhuJD.postContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(guanzhuJD.objName);//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void SharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(JiaoDetailActivity.this,"token",null)+"&id="+guanzhuJD.id);
        web.setTitle(guanzhuJD.postContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(guanzhuJD.objName);//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeiBo(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(JiaoDetailActivity.this,"token",null)+"&id="+guanzhuJD.id);
        web.setTitle(guanzhuJD.postContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(guanzhuJD.objName);//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.SINA)//传入平台
                .withMedia(web)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    public UMShareListener umShareListener = new UMShareListener() {
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
            map.put("uid", SpUtils.getString(JiaoDetailActivity.this,"userid",null));
            map.put("type",type);
            map.put("token", SpUtils.getString(JiaoDetailActivity.this,"token",null));
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



    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(JiaoDetailActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_guanzhu)
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
        text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void networkGZ() {

        HttpParams params = new HttpParams();

        params.put("concernedId",guanzhuJD.userId);

        params.put("token", SpUtils.getString(this,"token",null));
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

                                rela_guanzhu.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.JDSX);
                                sendBroadcast(intent);


                            } else {
                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        unregisterReceiver(broadcastReceiver);
    }

    private boolean isHf=false;
    private String commentFatherId,beReturnedId;

    @Override
    public void OnHuiFuClickListener(String commentFatherId, String beReturnedId,String name) {
        ed_text.setFocusable(true);
        ed_text.setFocusableInTouchMode(true);
        ed_text.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        ed_text.setHint("回复"+name);
        this.commentFatherId=commentFatherId;
        this.beReturnedId=beReturnedId;
        isHf=true;


    }

    @Override
    public void OnHYDataClickListener() {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(ed_text.getWindowToken(), 0);
        }
        ed_text.setText("");
        ed_text.setHint("请说说您的看法...");
        isHf=false;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent gb;
        if(type==0){
            Intent intent = new Intent(JiaoDetailActivity.this,JiaodianActivity.class);

            gb = new Intent();
            gb.setAction(GlobalParams.MRJD);
            gb.putExtra("tianzhuan",true);
            sendBroadcast(gb);

            intent.putExtra("tianzhuan",true);

            startActivity(intent);
        }else if(type==1){
            Intent intent = new Intent(JiaoDetailActivity.this,LiveActivity.class);

            gb = new Intent();
            gb.setAction(GlobalParams.SHFW);
            gb.putExtra("pos",pos);
            sendBroadcast(gb);
            intent.putExtra("pos",pos);
            startActivity(intent);
        }
    }

    private void networkZJPL(String commentFatherId, String beReturnedId) {

        HttpParams params = new HttpParams();
        params.put("commentFatherId", commentFatherId);
//
        params.put("replyContent", content);

        params.put("beReturnedId",beReturnedId);

        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","回复完成了------===========");
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
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                if (imm != null) {
                                    imm.hideSoftInputFromWindow(ed_text.getWindowToken(), 0);
                                }
                                ed_text.setText("");
                                ed_text.setHint("请说说您的看法...");
                                isHf=false;

                                list.clear();
                                pageNo=1;
                                if(NetUtil.checkNet(JiaoDetailActivity.this)){
                                    if(guanzhuJD!=null){
                                        finishFreshAndLoad();
                                    }
                                }else{
                                    Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

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
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
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
                                if(NetUtil.checkNet(JiaoDetailActivity.this)){
                                    if(guanzhuJD!=null){
                                        finishFreshAndLoad();
                                    }
                                }else{
                                    Toast.makeText(JiaoDetailActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(JiaoDetailActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

}
