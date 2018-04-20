package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.adapter.JiaoLiuyanAdapter;
import www.diandianxing.com.diandianxing.adapter.Jiaodianadapter;
import www.diandianxing.com.diandianxing.adapter.TPAdapter1;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.CustomReplayList;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.Info;
import www.diandianxing.com.diandianxing.bean.PingLunInfo;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class JiaoDetailActivity extends BaseActivity implements View.OnClickListener {


    private ImageView tv_back;
    private ImageView img_collect;
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
    private TextView text_share;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_jiaodetail);
        initView();

    }


    private void initView() {
        tv_back = (ImageView) findViewById(R.id.tv_back);
        img_collect = (ImageView) findViewById(R.id.img_collect);
        real = (RelativeLayout) findViewById(R.id.real);
        text_title = (TextView) findViewById(R.id.text_title);
        img_tou = (ImageView) findViewById(R.id.img_tou);
        text_name = (TextView) findViewById(R.id.text_name);
        da_address = (TextView) findViewById(R.id.da_address);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text_dengji = (TextView) findViewById(R.id.text_dengji);
        rela_guanzhu = (RelativeLayout) findViewById(R.id.rela_guanzhu);
        text_count = (TextView) findViewById(R.id.text_count);
        jiao_Recycler = (RecyclerView) findViewById(R.id.jiao_Recycler);
        text_share = (TextView) findViewById(R.id.text_share);
        text_zan = (TextView) findViewById(R.id.text_zan);
        jiao_pinglun = (RecyclerView) findViewById(R.id.jiao_pinglun);
        liuyan = (TextView) findViewById(R.id.text_liuyan);
        ed_text = (EditText) findViewById(R.id.ed_text);
        tv_jdxq_gz= (TextView) findViewById(R.id.tv_jdxq_gz);
        button_fabu = (Button) findViewById(R.id.button_fabu);

        jiao_Recycler.setLayoutManager(new GridLayoutManager(this,3));
        jiao_Recycler.setNestedScrollingEnabled(false);

        guanzhuJD = (GuanzhuJD) getIntent().getSerializableExtra("guanzhu");
        text_title.setText(guanzhuJD.postTitle);
        Glide.with(JiaoDetailActivity.this).load(guanzhuJD.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img_tou);
        text_name.setText(guanzhuJD.userName);
        da_address.setText(MyUtils.stampToDate(guanzhuJD.updateTime)+" "+guanzhuJD.address);
        text_dengji.setText(guanzhuJD.userLevel);
        if(Integer.parseInt(guanzhuJD.is_focus)==0){
            rela_guanzhu.setVisibility(View.VISIBLE);
        }else{
            rela_guanzhu.setVisibility(View.INVISIBLE);
        }
        text_count.setText(guanzhuJD.postContent);

        if(Integer.parseInt(guanzhuJD.is_collect)==0){
            img_collect.setImageResource(R.drawable.icon_collect);
        }else{
            img_collect.setImageResource(R.drawable.shouchang_xz_icon_3x);
        }
        text_zan.setText(guanzhuJD.dianZanCount);
        if(Integer.parseInt(guanzhuJD.is_zan)==0){
            Drawable nav_up=getResources().getDrawable(R.drawable.icon_dianzan);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            text_zan.setCompoundDrawables(nav_up, null, null, null);
        }else{
            Drawable nav_up=getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            text_zan.setCompoundDrawables(nav_up, null, null, null);
        }

        TPAdapter1 tpAdapter1 = new TPAdapter1(this,guanzhuJD.imagesList);
        jiao_Recycler.setAdapter(tpAdapter1);
        jiao_pinglun.setLayoutManager(new LinearLayoutManager(this));
        jiao_pinglun.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        jiao_pinglun.setNestedScrollingEnabled(false);
        jiaoLiuyanAdapter = new JiaoLiuyanAdapter(this);
        jiao_pinglun.setAdapter(jiaoLiuyanAdapter);
        /**
         *点击事件
         */
        tv_back.setOnClickListener(this);
        img_tou.setOnClickListener(this);
        tv_jdxq_gz.setOnClickListener(this);
        text_share.setOnClickListener(this);
        img_collect.setOnClickListener(this);
        text_zan.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.img_tou:
                Intent intent = new Intent(JiaoDetailActivity.this, MydynamicActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_jdxq_gz:
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
            case R.id.text_share:
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.img_collect:

                if(Integer.parseInt(guanzhuJD.is_collect)==0){

                    network(Integer.parseInt(guanzhuJD.id),0,1);

                }else{
                    QXnetwork(Integer.parseInt(guanzhuJD.id),0,1);
                }

                break;
            case R.id.text_zan:

                if(Integer.parseInt(guanzhuJD.is_zan)==0){

                    network(Integer.parseInt(guanzhuJD.id),0,0);

                }else{
                    QXnetwork(Integer.parseInt(guanzhuJD.id),0,0);
                }

                break;
        }
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

                                if(operation_type==0){
                                    guanzhuJD.is_zan="1";
                                    guanzhuJD.dianZanCount=Integer.parseInt(guanzhuJD.dianZanCount)+1+"";
                                }else{
                                    guanzhuJD.is_collect="1";
                                    guanzhuJD.collectCount=Integer.parseInt(guanzhuJD.collectCount)+1+"";
                                }
                                if(Integer.parseInt(guanzhuJD.is_collect)==0){
                                    img_collect.setImageResource(R.drawable.icon_collect);
                                }else{
                                    img_collect.setImageResource(R.drawable.shouchang_xz_icon_3x);
                                }

                                text_zan.setText(guanzhuJD.dianZanCount);
                                if(Integer.parseInt(guanzhuJD.is_zan)==0){
                                    Drawable nav_up=getResources().getDrawable(R.drawable.icon_dianzan);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    text_zan.setCompoundDrawables(nav_up, null, null, null);
                                }else{
                                    Drawable nav_up=getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    text_zan.setCompoundDrawables(nav_up, null, null, null);
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

                                if(operation_type==0){
                                    guanzhuJD.is_zan="0";
                                    guanzhuJD.dianZanCount=Integer.parseInt(guanzhuJD.dianZanCount)-1+"";
                                }else{
                                    guanzhuJD.is_collect="0";
                                    guanzhuJD.collectCount=Integer.parseInt(guanzhuJD.collectCount)-1+"";
                                }

                                if(Integer.parseInt(guanzhuJD.is_collect)==0){
                                    img_collect.setImageResource(R.drawable.icon_collect);
                                }else{
                                    img_collect.setImageResource(R.drawable.shouchang_xz_icon_3x);
                                }

                                text_zan.setText(guanzhuJD.dianZanCount);
                                if(Integer.parseInt(guanzhuJD.is_zan)==0){
                                    Drawable nav_up=getResources().getDrawable(R.drawable.icon_dianzan);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    text_zan.setCompoundDrawables(nav_up, null, null, null);
                                }else{
                                    Drawable nav_up=getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
                                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                                    text_zan.setCompoundDrawables(nav_up, null, null, null);
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


//    private void networklist() {
//
//        HttpParams params = new HttpParams();
//        params.put("pageNo", pageNo);
//        params.put("token", Api.token);
//        params.put("objId", guanzhuJD.id);
//        params.put("objType", 0);
//        OkGo.<String>post(Api.BASE_URL +"app/home/getCommentList")
//                .tag(this)
//                .params(params)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//
//                        String body = response.body();
//                        Log.d("TAG", "评论内容：" + body);
//                        JSONObject jsonobj = null;
//                        try {
//                            jsonobj = new JSONObject(body);
//                            int code = jsonobj.getInt("code");
//                            JSONArray datas = jsonobj.getJSONArray("datas");
//                            List<PingLunInfo> pingLunInfos = new ArrayList<PingLunInfo>();
//                            if (code == 200) {
//
//                                String allCount=jsonobj.getString("allCount");
//
//                                for(int i=0;i<datas.length();i++){
//
//                                    JSONObject jo = datas.getJSONObject(i);
//
//                                    PingLunInfo pingLunInfo = new PingLunInfo();
//                                    pingLunInfo.id=jo.getString("");
//                                    pingLunInfo.userId=jo.getString("userId");
//                                    pingLunInfo.userName=jo.getString("userName");
//                                    pingLunInfo.objId=jo.getString("objId");
//                                    pingLunInfo.content=jo.getString("content");
//                                    pingLunInfo.createTime=jo.getString("createTime");
//                                    pingLunInfo.isDeleted=jo.getString("isDeleted");
//                                    pingLunInfo.objType=jo.getString("objType");
//                                    pingLunInfo.nickName=jo.getString("nickName");
//                                    pingLunInfo.pic=jo.getString("pic");
//
//                                    pingLunInfo.customReplayLists = new ArrayList<CustomReplayList>();
//                                    JSONArray ja=jo.getJSONArray("customReplayList");
//                                    for(int j=0;j<ja.length();j++){
//
//                                        JSONObject jo1 = ja.getJSONObject(j);
//                                        CustomReplayList crl = new CustomReplayList();
//                                        crl.id=jo1.getString("");
//                                        crl.commentFatherId=jo1.getString("commentFatherId");
//                                        crl.replyId=jo1.getString("replyId");
//                                        crl.beReturnedId=jo1.getString("beReturnedId");
//                                        crl.replyContent=jo1.getString("replyContent");
//                                        crl.createTime=jo1.getString("createTime");
//                                        crl.isDelete=jo1.getString("isDelete");
//                                        crl.replName=jo1.getString("replName");
//                                        crl.beReturnedName=jo1.getString("beReturnedName");
//                                        crl.postId=jo1.getString("postId");
//                                        crl.nickName=jo1.getString("nickName");
//                                        crl.userId=jo1.getString("userId");
//                                        crl.pic=jo1.getString("pic");
//                                        pingLunInfo.customReplayLists.add(crl);
//                                    }
//
//
//                                    pingLunInfos.add(pingLunInfo);
//
//                                }
//                                if(pageNo>1){
//
//                                    if(pingLunInfos.size()<=0){
//
//                                        Toast.makeText(JiaoDetailActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();
//
//                                    }else{
//                                        lists.addAll(guanzhuJDs);
//                                    }
//
//                                }else{
//
//                                    lists.addAll(guanzhuJDs);
//
//                                }
//
//                                if(jiaodianadapter==null){
//                                    jiaodianadapter=new Jiaodianadapter(getActivity(),lists,shareListener,stateClickListener);
//                                    jiao_list.setAdapter(jiaodianadapter);
//                                }else{
//
//                                    jiaodianadapter.notifyDataSetChanged();
//                                }
//
//
//                            } else {
//                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Log.e("TAG","解析失败了！！！");
//                        }
//                    }
//                });
//    }


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
            }
        });
        ll_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharebyWeixincenter(JiaoDetailActivity.this);
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharebyQQ(JiaoDetailActivity.this);
            }
        });
        ll_kj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharebyQzon(JiaoDetailActivity.this);
            }
        });
        ll_wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

}
