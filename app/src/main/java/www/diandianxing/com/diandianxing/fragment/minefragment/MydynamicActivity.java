package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.umeng.message.UTrack;
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
import org.zackratos.ultimatebar.UltimateBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.Login.UmshareActivity;
import www.diandianxing.com.diandianxing.MyCollectionActivity;
import www.diandianxing.com.diandianxing.ReleaseShootoffActivity;
import www.diandianxing.com.diandianxing.TopRuleActivity;
import www.diandianxing.com.diandianxing.VideoActivity;
import www.diandianxing.com.diandianxing.adapter.DongTaiAdapter;
import www.diandianxing.com.diandianxing.adapter.JDAdapter;
import www.diandianxing.com.diandianxing.adapter.Jiaodianadapter;
import www.diandianxing.com.diandianxing.adapter.TieziAdapter;
import www.diandianxing.com.diandianxing.adapter.Tuijiantieadapter;
import www.diandianxing.com.diandianxing.adapter.Tujianadapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.bean.UserInfo;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.paikefragment.GuanzhuFragment;
import www.diandianxing.com.diandianxing.my.MyActivityActivity;
import www.diandianxing.com.diandianxing.my.PersonActivity;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.set.SetActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.ConUtils;
import www.diandianxing.com.diandianxing.util.EventMessage;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.PaiKeZZListener;
import www.diandianxing.com.diandianxing.util.SharingPop;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.StateClickListener;
import www.diandianxing.com.diandianxing.util.ZDPop;
import www.diandianxing.com.diandianxing.R;
/*
 * Created by ASUS on 2018/3/21.
 * 他人主页
 */

public class MydynamicActivity extends BaseActivity implements View.OnClickListener ,PaiKeZZListener,BaseQuickAdapter.OnItemLongClickListener {
    ImageView iv_gz,iv_zyfh;
    private ImageView img_tou;
    private TextView text_deng;
    private ImageView imageView2;
    private TextView text_name;
    private ImageView text_sex;
    private TextView guan_num;
    private LinearLayout text_guanzhu;
    private TextView fen_num;
    private LinearLayout text_fensi;
    private TextView collect_num;
    private LinearLayout text_collect;
    private TextView text_pin;
    private View v1;
    private LinearLayout liner_tiezi;
    private TextView text_zan;
    private View v2;
    private LinearLayout liner_dongtai;
    private SpringView sv_tz;
    private RecyclerView recy_card;
    private ImageView rl_bg;
    ArrayList<GuanzhuJD> mList=new ArrayList<>();
    ArrayList<GuanzhuJD> jdList=new ArrayList<>();
    private List<PaiKeInfo> list = new ArrayList<>();
    private ZDPop zdPop;
    private SharingPop sharingPop;
    private TextView tv_jfzd,tv_sc,tv_close,tv_sc_jd,tv_close_jd;
    Dialog DDdialog,jDDdialog;
    RecycleViewDivider recycleViewDivider;
    private TextView tv_dt_title;
    private BaseDialog dialog;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    private String title;

    private NestedScrollView sv_zy;
    private int height;
    private RelativeLayout rl_bt;
    private TextView tv_zy_xq;
    private UserInfo userInfo;
    private File file;
    private int pageJD=1,pageDT=1,pageTZ=1;
    private TieziAdapter tieziAdapter;
    private DongTaiAdapter tagAdapter;

    private JDAdapter jdAdapter;

    private int qh=1;
    private String uid;
    private TextView tv_qm;
    private LinearLayout liner_tz;
    private TextView text_tz;
    private View v3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        setContentView(R.layout.activity_minedynamic);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.TOU_SX);
        intentFilter.addAction(GlobalParams.LOGING_SX);
        intentFilter.addAction(GlobalParams.GZ);
        registerReceiver(broadcastReceiver,intentFilter);
        initView();
    }
    private void initView() {
        img_tou = (ImageView) findViewById(R.id.img_tou);
        iv_gz= (ImageView) findViewById(R.id.iv_gz);
        liner_tz= (LinearLayout) findViewById(R.id.liner_tz);
        text_tz= (TextView) findViewById(R.id.text_tz);
        v3=findViewById(R.id.v3);
        text_deng = (TextView) findViewById(R.id.text_deng);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text_name = (TextView) findViewById(R.id.text_name);
        text_sex = (ImageView) findViewById(R.id.text_sex);
        tv_dt_title= (TextView) findViewById(R.id.tv_dt_title);
        guan_num = (TextView) findViewById(R.id.guan_num);
        sv_zy= (NestedScrollView) findViewById(R.id.sv_zy);
        text_guanzhu = (LinearLayout) findViewById(R.id.text_guanzhu);
        fen_num = (TextView) findViewById(R.id.fen_num);
        iv_zyfh= (ImageView) findViewById(R.id.iv_zyfh);
        tv_qm= (TextView) findViewById(R.id.tv_qm);
        text_fensi = (LinearLayout) findViewById(R.id.text_fensi);
        collect_num = (TextView) findViewById(R.id.collect_num);
        text_collect = (LinearLayout) findViewById(R.id.text_collect);
        text_pin = (TextView) findViewById(R.id.text_pin);
        recy_card= (RecyclerView) findViewById(R.id.recy_card);
        sv_tz= (SpringView) findViewById(R.id.sv_tz);
        rl_bg= (ImageView) findViewById(R.id.rl_bg);
        v1 = (View) findViewById(R.id.v1);
        rl_bt= (RelativeLayout) findViewById(R.id.rl_bt);
        liner_tiezi = (LinearLayout) findViewById(R.id.liner_tiezi);
        text_zan = (TextView) findViewById(R.id.text_zan);
        tv_zy_xq= (TextView) findViewById(R.id.tv_zy_xq);
        v2 = (View) findViewById(R.id.v2);
        liner_dongtai = (LinearLayout) findViewById(R.id.liner_dongtai);

        userInfo= (UserInfo) getIntent().getSerializableExtra("userInfo");
        ViewTreeObserver vto = rl_bg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rl_bg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = rl_bg.getHeight();
            }
        });
        rl_bt.getBackground().mutate().setAlpha(0);
        title = getIntent().getStringExtra("title");
        if(title!=null){
            tv_dt_title.setText(title);
        }

        if("我的主页".equals(title)){
            iv_gz.setVisibility(View.VISIBLE);
            iv_gz.setImageResource(R.drawable.mdfx);
            iv_zyfh.setImageResource(R.drawable.bfh);
            tv_dt_title.setTextColor(getResources().getColor(R.color.white));

            img_tou.setOnClickListener(this);
            rl_bg.setOnClickListener(this);
            uid= SpUtils.getString(this,"userid",null);
        }else{

            uid=getIntent().getStringExtra("uid");

        }

        if(SpUtils.getString(this,"userid",null)!=null){
            if(SpUtils.getString(this,"userid",null).equals(uid)){

                title="我的主页";
                tv_dt_title.setText(title);
                iv_gz.setVisibility(View.VISIBLE);
                iv_gz.setImageResource(R.drawable.mdfx);
                iv_zyfh.setImageResource(R.drawable.bfh);
                tv_dt_title.setTextColor(getResources().getColor(R.color.white));



                img_tou.setOnClickListener(this);
                rl_bg.setOnClickListener(this);

            }
        }
        if(NetUtil.checkNet(MydynamicActivity.this)){
            networkGR();
            finishFreshAndLoad();
        }else{
            Toast.makeText(MydynamicActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }
        zdPop = new ZDPop(MydynamicActivity.this,R.layout.zd_pop_item_view);
        sharingPop = new SharingPop(MydynamicActivity.this,R.layout.sharing_pop_item_view);
        recycleViewDivider = new RecycleViewDivider(MydynamicActivity.this, GridLayoutManager.HORIZONTAL, 5, getResources().getColor(R.color.transparent));
          /*
           设置监听
         */
        text_zan.setTextColor(getResources().getColor(R.color.text_orage));
        v2.setVisibility(View.VISIBLE);
        text_pin.setTextColor(getResources().getColor(R.color.black_san));
        v1.setVisibility(View.INVISIBLE);

        recy_card.setLayoutManager(new GridLayoutManager(MydynamicActivity.this,2));
        recy_card.addItemDecoration(recycleViewDivider);
//                recy_card.addItemDecoration(new GridDivider(MydynamicActivity.this, 20, this.getResources().getColor(R.color.white)));
        recy_card.setNestedScrollingEnabled(false);

        initRefresh();
        liner_dongtai.setOnClickListener(this);
        liner_tiezi.setOnClickListener(this);
        iv_gz.setOnClickListener(this);
        iv_zyfh.setOnClickListener(this);
        text_guanzhu.setOnClickListener(this);
        text_fensi.setOnClickListener(this);
//        text_collect.setOnClickListener(this);
        liner_tz.setOnClickListener(this);
        zdPop.setOnDismissListener(onDismissListener);
        sharingPop.setOnDismissListener(onDismissListener);
        sv_zy.setOnScrollChangeListener(scrollChangeListener);
    }

    BaseDialog jzdialog;

    /*
   * 对话框
   * */
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(MydynamicActivity.this);
        jzdialog = builder.setViewId(R.layout.view_tips_loading)
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
        jzdialog.show();
        TextView tips_loading_msg = jzdialog.getView(R.id.tips_loading_msg);
        tips_loading_msg.setText("");
    }



    private NestedScrollView.OnScrollChangeListener scrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            Log.e("TAG","数据：：：："+(scrollY/height));
            if(scrollY>=height){
            rl_bt.getBackground().mutate().setAlpha(230);
            }else{
                rl_bt.getBackground().mutate().setAlpha(0);
            }
        }

    };
    private String pic="";
    private String userLevel="1";
    private TieziAdapter.OnItemClickListener onItemClickListener = new TieziAdapter.OnItemClickListener() {
        @Override
        public void ItemClick(View view, int position) {


            Intent intent=new Intent(MydynamicActivity.this,JiaoDetailActivity.class);

            mList.get(position).pic=pic;
            mList.get(position).userLevel=userLevel;
            mList.get(position).userName=nickname;

//            intent.putExtra("guanzhu",mList.get(position));
            intent.putExtra("id",mList.get(position).id);
            intent.putExtra("title",mList.get(position).objName);

            startActivity(intent);

        }





        @Override
        public void FxClickListener(int pos,String ms) {
//            setShowPop(sharingPop,liner_tiezi);
            showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion,false,ms,pos);

        }

        @Override
        public void ScClickListener(int pos) {

        }

        @Override
        public void DzClickListener(int pos) {

        }
    };


    private JDAdapter.OnItemClickListener onItemClickListenerjd = new JDAdapter.OnItemClickListener() {
        @Override
        public void ItemClick(View view, int position) {


            Intent intent=new Intent(MydynamicActivity.this,JiaoDetailActivity.class);

            jdList.get(position).pic=pic;
            jdList.get(position).userLevel=userLevel;
            jdList.get(position).userName=nickname;
//            intent.putExtra("guanzhu",jdList.get(position));

            intent.putExtra("id",jdList.get(position).id);

            startActivity(intent);

        }





        @Override
        public void FxClickListener(int pos,String ms) {
//            setShowPop(sharingPop,liner_tiezi);
            showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion,false,ms,pos);

        }

        @Override
        public void ScClickListener(int pos) {

        }

        @Override
        public void DzClickListener(int pos) {

        }
    };


    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;

    private String ms;
    private int pos_share;

    Dialog dialog_fx;
    private void showFXDialog(int grary, int animationStyle,boolean iszj,String ms,int pos) {
        pos_share=pos;
        this.ms=ms;
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        this.iszj=iszj;
        //设置触摸dialog外围是否关闭
        //设置监听事件
        dialog_fx = builder.setViewId(R.layout.sharing_pop_item_view)
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

        dialog_fx.show();
        ll_wx=dialog_fx.findViewById(R.id.ll_wx);
        ll_pyq=dialog_fx.findViewById(R.id.ll_pyq);
        ll_qq=dialog_fx.findViewById(R.id.ll_qq);
        ll_kj=dialog_fx.findViewById(R.id.ll_kj);
        ll_wb=dialog_fx.findViewById(R.id.ll_wb);
        quxiao=dialog_fx.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(this);
        ll_wx.setOnClickListener(this);
        ll_pyq.setOnClickListener(this);
        ll_qq.setOnClickListener(this);
        ll_kj.setOnClickListener(this);
        ll_wb.setOnClickListener(this);
    }

    private void showGZDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.gz_item_view)
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

        TextView tv_qxgz = dialog.findViewById(R.id.tv_qxgz);
        TextView tv_gz_close = dialog.findViewById(R.id.tv_gz_close);
        tv_qxgz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networkqg();
                dialog.dismiss();

            }
        });
        tv_gz_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void networkqg() {
        HttpParams params = new HttpParams();
        params.put("token", SpUtils.getString(this,"token",null));
        params.put("concernedId",uid);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/followuser/delete")
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

                            if (code == 200) {

                                iv_gz.setVisibility(View.INVISIBLE);


                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.GZ);
                                sendBroadcast(intent);

                                Intent xq = new Intent();
                                xq.setAction(GlobalParams.XQ);
                                sendBroadcast(xq);


                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private void showJDscDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        jDDdialog  = builder.setViewId(R.layout.jdsc_pop_item_view)
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
        jDDdialog.show();
        tv_sc_jd=jDDdialog.findViewById(R.id.tv_sc_jd);
        tv_close_jd=jDDdialog.findViewById(R.id.tv_close_jd);
        tv_sc_jd.setOnClickListener(this);
        tv_close_jd.setOnClickListener(this);

    }

    private void showDDDDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        DDdialog = builder.setViewId(R.layout.zd_pop_item_view)
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
        DDdialog.show();
        tv_jfzd=DDdialog.findViewById(R.id.tv_jfzd);
        tv_sc=DDdialog.findViewById(R.id.tv_sc);
        tv_close=DDdialog.findViewById(R.id.tv_close);
        tv_jfzd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        networksc();

                        DDdialog.dismiss();
            }
        });
        tv_sc.setOnClickListener(this);
        tv_close.setOnClickListener(this);

    }

    private void networksc() {
        HttpParams params = new HttpParams();
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/postbonuspoins/thestickyrules")
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
                            if (code == 200) {
                                JSONObject datas = jsonobj.getJSONObject("datas");


                                timelong=datas.getString("timelong");
                                bonus=datas.getString("bonus");
                                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion,postId);


                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private String postId;

    private TieziAdapter.DianClickListener dianClickListener = new TieziAdapter.DianClickListener() {
        @Override
        public void onDianClickListener(int pos) {

//            setShowPop(zdPop,liner_tiezi);
//            zdPop.showAtLocation(liner_tiezi, Gravity.BOTTOM, 0, 0);

            Log.e("TAG","点的位置：：："+pos);

            postId=mList.get(pos).id;

            showDDDDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion);

        }
    };



    private JDAdapter.DianClickListener dianClickListener1 = new JDAdapter.DianClickListener() {
        @Override
        public void onDianClickListener(int pos) {

//            setShowPop(zdPop,liner_tiezi);
//            zdPop.showAtLocation(liner_tiezi, Gravity.BOTTOM, 0, 0);

            postId=jdList.get(pos).id;

            showJDscDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion);

        }
    };



    private PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            setWindowTranslucence(1.0f);
        }
    };

    private boolean isdy,istz;






    private boolean iszj;

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.liner_tiezi://郊点
                text_pin.setTextColor(getResources().getColor(R.color.text_orage));
                v1.setVisibility(View.VISIBLE);
                text_zan.setTextColor(getResources().getColor(R.color.black_san));
                v2.setVisibility(View.INVISIBLE);
                text_tz.setTextColor(getResources().getColor(R.color.black_san));
                v3.setVisibility(View.INVISIBLE);
                recy_card.setLayoutManager(new LinearLayoutManager(MydynamicActivity.this));
                recy_card.removeItemDecoration(recycleViewDivider);
                recy_card.setNestedScrollingEnabled(false);

                qh=0;
                dateType=1;

                jdAdapter =new JDAdapter(R.layout.tz_item_view,jdList,title,stateClickListener);
                if("我的主页".equals(title)){
                    jdAdapter.setXS(true);
                }
                recy_card.setAdapter(jdAdapter);
                jdAdapter.setOnDianClickListener(dianClickListener1);
                jdAdapter.SetOnItemClickListener(onItemClickListenerjd);


                if(!isdy){
                    finishFreshAndLoad();
                    isdy=true;
                }

                break;
            case R.id.liner_tz://帖子
                text_tz.setTextColor(getResources().getColor(R.color.text_orage));
                v3.setVisibility(View.VISIBLE);
                text_zan.setTextColor(getResources().getColor(R.color.black_san));
                v2.setVisibility(View.INVISIBLE);
                text_pin.setTextColor(getResources().getColor(R.color.black_san));
                v1.setVisibility(View.INVISIBLE);
                recy_card.setLayoutManager(new LinearLayoutManager(MydynamicActivity.this));
//                recycleViewDivider = new RecycleViewDivider(MydynamicActivity.this, GridLayoutManager.VERTICAL, 5, getResources().getColor(R.color.white));
                recy_card.removeItemDecoration(recycleViewDivider);
//                recy_card.addItemDecoration(recycleViewDivider);
                recy_card.setNestedScrollingEnabled(false);
                qh=2;
                dateType=0;

                tieziAdapter =new TieziAdapter(R.layout.tz_item_view,mList,title,stateClickListener);
                recy_card.setAdapter(tieziAdapter);
                if("我的主页".equals(title)){
                    tieziAdapter.setXS(true);
                    tieziAdapter.notifyDataSetChanged();
                }
                tieziAdapter.setOnDianClickListener(dianClickListener);
                tieziAdapter.SetOnItemClickListener(onItemClickListener);

                if(!istz){
                    finishFreshAndLoad();
                    istz=true;
                }


                break;

            case R.id.liner_dongtai://拍客
                text_zan.setTextColor(getResources().getColor(R.color.text_orage));
                v2.setVisibility(View.VISIBLE);
                text_pin.setTextColor(getResources().getColor(R.color.black_san));
                v1.setVisibility(View.INVISIBLE);
                text_tz.setTextColor(getResources().getColor(R.color.black_san));
                v3.setVisibility(View.INVISIBLE);
                recy_card.setLayoutManager(new GridLayoutManager(MydynamicActivity.this,2));
                recycleViewDivider = new RecycleViewDivider(MydynamicActivity.this, GridLayoutManager.HORIZONTAL, 5, getResources().getColor(R.color.transparent));
                recy_card.addItemDecoration(recycleViewDivider);
//                recy_card.addItemDecoration(new GridDivider(MydynamicActivity.this, 20, this.getResources().getColor(R.color.white)));
                recy_card.setNestedScrollingEnabled(false);

                qh=1;
                    tagAdapter = new DongTaiAdapter(R.layout.tui_recyitem, list,MydynamicActivity.this);

                    if("我的主页".equals(title)){
                        tagAdapter.setXS(true);
                    }
                    recy_card.setAdapter(tagAdapter);

                    tagAdapter.setOnItemLongClickListener(this);

                break;
            case R.id.tv_sc:
                qh=2;
                if(NetUtil.checkNet(MydynamicActivity.this)){
                    networkdele(postId);
                }else{
                    Toast.makeText(MydynamicActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
                DDdialog.dismiss();
                break;
            case R.id.tv_close:

                DDdialog.dismiss();

                break;

            case R.id.tv_sc_jd:
                qh=0;
                if(NetUtil.checkNet(MydynamicActivity.this)){
                    networkdele(postId);
                }else{
                    Toast.makeText(MydynamicActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
                jDDdialog.dismiss();
                break;
            case R.id.tv_close_jd:

                jDDdialog.dismiss();

                break;

            case R.id.iv_gz:

                int guid = SpUtils.getInt(MydynamicActivity.this, "guid", 0);
                if(guid!=2){
                    startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                }else{
                    if("我的主页".equals(title)){
                        showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion,true,"",0);
                    }else{
                        showGZDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                    }
                }

                break;
            case R.id.iv_zyfh:
                finish();
                break;
            case R.id.text_guanzhu:

                intent=new Intent(MydynamicActivity.this, MyFllowActivity.class);


                intent.putExtra("title",title);

                intent.putExtra("uid",uid);

                startActivity(intent);

                break;
            case R.id.text_fensi://粉丝
                intent=new Intent(MydynamicActivity.this, MyFansiActivity.class);

                intent.putExtra("uid",uid);

                startActivity(intent);
                break;
            case R.id.text_collect:
                intent=new Intent(MydynamicActivity.this, MyCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.img_tou:
                Intent intent1=new Intent(this,PersonActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_bg:

                showphotoDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;

            case R.id.ll_wx:
                if(iszj){
                    mySharebyWeixin(MydynamicActivity.this);
                }else{
                    SharebyWeixin(MydynamicActivity.this);
                    networkFxTj("2");
                }
                networkFxJF();
                break;
            case R.id.ll_pyq:
                if(iszj){
                    mySharebyWeixincenter(MydynamicActivity.this);
                }else{
                    SharebyWeixincenter(MydynamicActivity.this);
                    networkFxTj("3");
                }
                networkFxJF();
                break;
            case R.id.ll_qq:
                if(iszj){
                    mySharebyQQ(MydynamicActivity.this);
                }else{
                    SharebyQQ(MydynamicActivity.this);
                    networkFxTj("0");
                }
                networkFxJF();
                break;
            case R.id.ll_kj:
                if(iszj){
                    mySharebyQzon(MydynamicActivity.this);
                }else{
                    SharebyQzon(MydynamicActivity.this);
                    networkFxTj("1");
                }
                networkFxJF();
                break;
            case R.id.ll_wb:

                if(isWxInstall(MydynamicActivity.this)){
                    if(iszj){
                        mSharebyWeiBo(MydynamicActivity.this);
                    }else{
                        SharebyWeiBo(MydynamicActivity.this);
                        networkFxTj("4");
                    }
                    networkFxJF();
                }else {
                    Toast.makeText(MydynamicActivity.this,"请先安装微博",Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.quxiao:
                dialog_fx.dismiss();
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
        if("每日郊点".equals(ms)){
            params.put("objId",jdList.get(pos_share).id);
        }else {
            params.put("objId",mList.get(pos_share).id);
        }
        params.put("obj_type",0);
        params.put("operation_type",2);
        params.put("share_status",share_status);
        params.put("token", SpUtils.getString(MydynamicActivity.this,"token",null));
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

//                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

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
        params.put("token", SpUtils.getString(MydynamicActivity.this,"token",null));
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

//                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    protected  void SharebyQQ(Activity context) {
        UMWeb web=null;
        if("每日郊点".equals(ms)){

            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+jdList.get(pos_share).id);

            web.setTitle(jdList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription("每日郊点");//描述
        }else {
            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+mList.get(pos_share).id);

            web.setTitle(mList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription(mList.get(pos_share).objName);//描述
        }

        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(ms);//描述

        new ShareAction(context)
                .withMedia(web)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyQzon(Activity context) {
        UMWeb web=null;
        if("每日郊点".equals(ms)){

            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+jdList.get(pos_share).id);

            web.setTitle(jdList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription("每日郊点");//描述
        }else {
            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+mList.get(pos_share).id);

            web.setTitle(mList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription(mList.get(pos_share).objName);//描述
        }

        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeixin(Activity context) {
        UMWeb web=null;
        if("每日郊点".equals(ms)){

            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+jdList.get(pos_share).id);

            web.setTitle(jdList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription("每日郊点");//描述
        }else {
            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+mList.get(pos_share).id);

            web.setTitle(mList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription(mList.get(pos_share).objName);//描述
        }

        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void SharebyWeixincenter(Activity context) {
        UMWeb web=null;
        if("每日郊点".equals(ms)){

            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+jdList.get(pos_share).id);

            web.setTitle(jdList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription("每日郊点");//描述
        }else {
            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+mList.get(pos_share).id);

            web.setTitle(mList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription(mList.get(pos_share).objName);//描述
        }

        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .withMedia(web)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeiBo(Activity context) {
        UMWeb web=null;
        if("每日郊点".equals(ms)){

            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+jdList.get(pos_share).id);

            web.setTitle(jdList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription("每日郊点");//描述
        }else {
            web= new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(MydynamicActivity.this,"token",null)+"&id="+mList.get(pos_share).id);

            web.setTitle(mList.get(pos_share).postContent);//标题
            web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
            web.setDescription(mList.get(pos_share).objName);//描述
        }
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.SINA)//传入平台
                .withMedia(web)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }


    protected  void mySharebyQQ(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"mine.html?id="+SpUtils.getString(context,"userid",null));
        web.setTitle("这是我的主页快来看看吧");//标题
        web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
        web.setDescription(name);//描述

        new ShareAction(MydynamicActivity.this)
                .withMedia(web)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void mySharebyQzon(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"mine.html?id="+SpUtils.getString(context,"userid",null));
        web.setTitle("这是我的主页快来看看吧");//标题
        web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
        web.setDescription(name);//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void mySharebyWeixin(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"mine.html?id="+SpUtils.getString(context,"userid",null));
        web.setTitle("这是我的主页快来看看吧");//标题
        web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
        web.setDescription(name);//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void mySharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"mine.html?id="+SpUtils.getString(context,"userid",null));
        web.setTitle("这是我的主页快来看看吧");//标题
        web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
        web.setDescription(name);//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void mSharebyWeiBo(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"mine.html?id="+SpUtils.getString(context,"userid",null));
        web.setTitle("这是我的主页快来看看吧");//标题
        web.setThumb(new UMImage(MydynamicActivity.this, R.drawable.icon_tu));  //缩略图
        web.setDescription(name);//描述
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
        public void onResult(final SHARE_MEDIA platform) {
            //            Log.d("plat", "platform" + platform);
             /*
               成功调用接口
              */
            String type = "";
            if (platform.equals(SHARE_MEDIA.WEIXIN)) {
                type = "1";

            } else if (platform.equals(SHARE_MEDIA.WEIXIN_CIRCLE)) {
                type = "2";

            } else if (platform.equals(SHARE_MEDIA.QQ)) {
                type = "3";
            } else if (platform.equals(SHARE_MEDIA.QZONE)) {
                type = "4";
            }
            //网络请求
            Map<String, String> map = new HashMap<>();
            map.put("uid", SpUtils.getString(MydynamicActivity.this, "userid", null));
            map.put("type", type);
            map.put("token", SpUtils.getString(MydynamicActivity.this, "token", null));
            map.put("platform", 2 + "");
            RetrofitManager.post(MyContants.BASEURL + "s=User/share", map, new BaseObserver1<Sharebean>("") {
                @Override
                public void onSuccess(Sharebean result, String tag) {
                    if (result.getCode() == 200) {
                        Toast.makeText(Myapplication.getGloableContext(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed(int code, String data) {

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
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(GlobalParams.TOU_SX.equals(action)){

                String tou = intent.getStringExtra("tou");

                String name = intent.getStringExtra("name");
                String sex = intent.getStringExtra("sex");
                String signature=intent.getStringExtra("signature");
                if(sex!=null&&sex.length()>0){
                    if(Integer.parseInt(sex)==0){//男

                        text_sex.setImageResource(R.drawable.icon_boy);

                    }else{
                        text_sex.setImageResource(R.drawable.icon_girl);
                    }

                }


                if(name!=null&&name.length()>0){
                    Log.e("TAG","name=="+name);
                    text_name.setText(name);
                }
                tv_qm.setText("简介："+signature);
                if((tou!=null&&tou.length()>0)){
                    Glide.with(MydynamicActivity.this).load(tou).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img_tou);
                }



            }else if(GlobalParams.LOGING_SX.equals(action)){

                networkGR();

            }else if(GlobalParams.GZ.equals(action)){

                networkGR();

                Log.e("TAG","开始根性*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

                if(qh==0){

                    pageJD=1;
                    jdList.clear();
                    finishFreshAndLoad();
                }else if(qh==2){
                    pageTZ=1;
                    mList.clear();
                    finishFreshAndLoad();
                }


            }

        }
    };

    private BaseQuickAdapter.OnItemClickListener onItemClickListener1 = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            Intent intent = new Intent(MydynamicActivity.this, VideoActivity.class);
            startActivity(intent);

        }
    };


    private void showphotoDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置dialogpadding
//设置显示位置
//设置动画
//设置dialog的宽高
//设置触摸dialog外围是否关闭
//设置监听事件
        dialog = builder.setViewId(R.layout.dialog_photo)
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
        //拍照
        dialog.getView(R.id.tv_paizhao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机选取
                requestCamera();
                dialog.dismiss();

            }
        });
        //相册
        dialog.getView(R.id.tv_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册选取
                requestPhoto();
                dialog.dismiss();

            }
        });
        //取消
        dialog.getView(R.id.tv_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /*
     调用相册
    */
    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style1)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
//                .selectionMedia(list)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }
    /*
      调用相机
     */
    private void requestCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .selectionMedia(list)// 是否传入已选图片
//                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    cutPath = selectList.get(0).getCutPath();
                    file = new File(cutPath);
                    Log.d("TAg",cutPath);

                    shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);

                    networkImg();
//                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    /*
                       质量压缩
                     */
//                    FileOutputStream baos = null;
//                    try {
//
//                        baos = new FileOutputStream(filess);
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 2, baos);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    list.clear();
//                    list.add(cutPath);
//                    photoworks();
                    break;
            }
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        sv_tz.setType(SpringView.Type.FOLLOW);
        sv_tz.setFooter(new DefaultFooter(MydynamicActivity.this));
        sv_tz.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(mContext,"下拉刷新中",Toast.LENGTH_SHORT).show();
                // list.clear();
                // 网络请求;
                // mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法


                if(qh==0){

                    mList.clear();

                    pageJD=1;

                }else if(qh==1){

                    list.clear();
                    pageDT=1;

                }
                if(NetUtil.checkNet(MydynamicActivity.this)){

                    finishFreshAndLoad();
                }else{
                    Toast.makeText(MydynamicActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                if(qh==0){
                    pageJD++;
                }else if(qh==1){
                    pageDT++;
                }else if(qh==2){
                    pageTZ++;
                }
                if(NetUtil.checkNet(MydynamicActivity.this)){
                    finishFreshAndLoad();
                }else{
                    Toast.makeText(MydynamicActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
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


                if(qh==0){
                    networkJD();

                }else if(qh==1){

                    networkPaiKe();

                }else if(qh==2){
                    networkTiezi();
                }


                sv_tz.onFinishFreshAndLoad();
            }
        }, 0);
    }
    private String timelong,bonus;
    private TextView text_sure;
    private void shumaDialog(int grary, int animationStyle, final String pId) {
        BaseDialog.Builder builder = new BaseDialog.Builder(MydynamicActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_zd)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        text_sure = dialog.getView(R.id.text_sure);
        TextView tv_xxgz = dialog.getView(R.id.tv_xxgz);
        TextView tv_dt_content=dialog.getView(R.id.tv_dt_content);
        tv_xxgz.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线





        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());


        Date date = null;
        try {
            date = simpleDateFormat.parse(format);
            long ts = date.getTime();
                    long timeStamp = System.currentTimeMillis();
            long fz = Long.parseLong(timelong) * 60*1000;
//            Log.e("TAG","数字：：："+);
            tv_dt_content.setText("本次顶帖需要花费"+bonus+"积分("+MyUtils.stampsToDate((ts+fz)+"")+"后开始新的计费周期)，是否继续？");
        } catch (ParseException e) {
            e.printStackTrace();
        }



        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networkzd(pId);
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
        tv_xxgz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MydynamicActivity.this, TopRuleActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    private void networkzd(String pId) {
        HttpParams params = new HttpParams();
        params.put("pId", pId);
        params.put("userId",uid);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/postbonuspoins/insertpostbonuspoins")
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

                            if (code == 200) {

                                cgDialog(Gravity.CENTER,R.style.Alpah_aniamtion);

                            } else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private void cgDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(MydynamicActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_dtcg)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
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

    private void networkdele(String postId) {
        HttpParams params = new HttpParams();
        params.put("postId", postId);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/postinfo/deletepostinfo")
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

                            if (code == 200) {
                                if(qh==2){
                                    pageTZ=1;
                                    mList.clear();
                                    if(tagAdapter!=null){
                                        tagAdapter.notifyDataSetChanged();
                                    }
                                }else if(qh==0){
                                    pageJD=1;
                                    jdList.clear();
                                    if(tieziAdapter!=null){
                                        tieziAdapter.notifyDataSetChanged();
                                    }
                                }

                                finishFreshAndLoad();
                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.DONGTAI_SX);
                                sendBroadcast(intent);
                                Intent GZ = new Intent();
                                GZ.setAction(GlobalParams.GZ);
                                sendBroadcast(GZ);
                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private void networkImg() {
        HttpParams params = new HttpParams();
        params.put("file", file);
        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/user/backimagebyuser")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        jzdialog.dismiss();
                        String body = response.body();
                        Log.d("TAG", "上传图片成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");

                            if (code == 200) {

                                Drawable fromPath = Drawable.createFromPath(cutPath);
                                rl_bg.setImageDrawable(fromPath);

                                EventMessage eventMessage = new EventMessage(cutPath);
                                EventBus.getDefault().postSticky(eventMessage);

                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private int dateType=0;


    private void networkJD() {
        HttpParams params = new HttpParams();

        params.put("isuserId",SpUtils.getString(MydynamicActivity.this,"userid",""));
        params.put("userId", uid);
        params.put("pageNo", pageJD);
        params.put("dateType",dateType);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/user/postbyuser")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "帖子成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                JSONArray datas = jsonobj.getJSONArray("datas");
                                List<GuanzhuJD> guanzhuJDs = new ArrayList<GuanzhuJD>();
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);

                                    GuanzhuJD guanzhu = new GuanzhuJD();
                                    guanzhu.id=jo.getString("id");
                                    guanzhu.userId=jo.getString("userId");
                                    guanzhu.postType=jo.getString("postType");
                                    guanzhu.postTitle=jo.getString("postTitle");
                                    guanzhu.postContent=jo.getString("postContent");
                                    guanzhu.createTime=jo.getString("createTime");
                                    guanzhu.updateTime=jo.getString("updateTime");
                                    guanzhu.address=jo.getString("address");
                                    guanzhu.postFlage=jo.getString("postFlage");
                                    guanzhu.postStatus=jo.getString("postStatus");
                                    guanzhu.isDeleted=jo.getString("isDeleted");
                                    guanzhu.userName=jo.getString("userName");
                                    guanzhu.userLevel=jo.getString("userLevel");
                                    guanzhu.postImge=jo.getString("postImge");
                                    guanzhu.commentCount=jo.getString("commentCount");
                                    guanzhu.dianZanCount=jo.getString("dianZanCount");
                                    guanzhu.collectCount=jo.getString("collectCount");
                                    guanzhu.relayCount=jo.getString("relayCount");
                                    guanzhu.pic=jo.getString("pic");
                                    guanzhu.is_collect=jo.getString("is_collect");
                                    guanzhu.is_zan=jo.getString("is_zan");
                                    guanzhu.is_fx=jo.getString("is_fx");
                                    guanzhu.is_focus=jo.getString("is_focus");
                                    guanzhu.objName=jo.getString("objName");
                                    JSONArray ja=jo.getJSONArray("imagesList");

                                    guanzhu.imagesList = new ArrayList<String>();
                                    for(int j=0;j<ja.length();j++){
                                        guanzhu.imagesList.add(ja.getString(j));
                                    }

                                    guanzhuJDs.add(guanzhu);


                                }
                                if(pageJD>1){

                                    if(guanzhuJDs.size()<=0){

                                        Toast.makeText(MydynamicActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{

                                        if(qh==0){

                                            jdList.addAll(guanzhuJDs);
                                        }else if(qh==2){
                                            mList.addAll(guanzhuJDs);
                                        }

                                    }

                                }else{

                                    if(qh==0){

                                        jdList.addAll(guanzhuJDs);
                                    }else if(qh==2){
                                        mList.addAll(guanzhuJDs);
                                    }

                                }

                                if(qh==0){
                                    if(jdAdapter ==null){
                                        jdAdapter = new JDAdapter(R.layout.tz_item_view,mList,title,stateClickListener);
                                        if("我的主页".equals(title)){
                                            jdAdapter.setXS(true);
                                        }
                                        recy_card.setAdapter(jdAdapter);
                                    }else{
                                        if("我的主页".equals(title)){
                                            jdAdapter.setXS(true);
                                        }
                                        jdAdapter.notifyDataSetChanged();
                                    }
                                }else if(qh==2){
                                    if(tieziAdapter==null){
                                        tieziAdapter =new TieziAdapter(R.layout.tz_item_view,mList,title,stateClickListener);
                                        recy_card.setAdapter(tieziAdapter);
                                        if("我的主页".equals(title)){
                                            tieziAdapter.setXS(true);
                                            tieziAdapter.notifyDataSetChanged();
                                        }
                                        tieziAdapter.setOnDianClickListener(dianClickListener);
                                        tieziAdapter.SetOnItemClickListener(onItemClickListener);
                                    }else{
                                        if("我的主页".equals(title)){
                                            tieziAdapter.setXS(true);
                                        }
                                        tieziAdapter.notifyDataSetChanged();
                                    }

                                }

                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private void networkTiezi() {
        HttpParams params = new HttpParams();
        params.put("isuserId",SpUtils.getString(MydynamicActivity.this,"userid",""));
        params.put("userId", uid);
        params.put("pageNo", pageTZ);
        params.put("dateType",dateType);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/user/postbyuser")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "帖子成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                JSONArray datas = jsonobj.getJSONArray("datas");
                                List<GuanzhuJD> guanzhuJDs = new ArrayList<GuanzhuJD>();
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);

                                    GuanzhuJD guanzhu = new GuanzhuJD();
                                    guanzhu.id=jo.getString("id");
                                    guanzhu.userId=jo.getString("userId");
                                    guanzhu.postType=jo.getString("postType");
                                    guanzhu.postTitle=jo.getString("postTitle");
                                    guanzhu.postContent=jo.getString("postContent");
                                    guanzhu.createTime=jo.getString("createTime");
                                    guanzhu.updateTime=jo.getString("updateTime");
                                    guanzhu.address=jo.getString("address");
                                    guanzhu.postFlage=jo.getString("postFlage");
                                    guanzhu.postStatus=jo.getString("postStatus");
                                    guanzhu.isDeleted=jo.getString("isDeleted");
                                    guanzhu.userName=jo.getString("userName");
                                    guanzhu.userLevel=jo.getString("userLevel");
                                    guanzhu.postImge=jo.getString("postImge");
                                    guanzhu.commentCount=jo.getString("commentCount");
                                    guanzhu.dianZanCount=jo.getString("dianZanCount");
                                    guanzhu.collectCount=jo.getString("collectCount");
                                    guanzhu.relayCount=jo.getString("relayCount");
                                    guanzhu.pic=jo.getString("pic");
                                    guanzhu.is_collect=jo.getString("is_collect");
                                    guanzhu.is_zan=jo.getString("is_zan");
                                    guanzhu.is_fx=jo.getString("is_fx");
                                    guanzhu.is_focus=jo.getString("is_focus");
                                    guanzhu.objName=jo.getString("objName");
                                    JSONArray ja=jo.getJSONArray("imagesList");

                                    guanzhu.imagesList = new ArrayList<String>();
                                    for(int j=0;j<ja.length();j++){
                                        guanzhu.imagesList.add(ja.getString(j));
                                    }

                                    guanzhuJDs.add(guanzhu);


                                }
                                if(pageTZ>1){

                                    if(guanzhuJDs.size()<=0){

                                        Toast.makeText(MydynamicActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{

                                            mList.addAll(guanzhuJDs);

                                    }

                                }else{

                                        mList.addAll(guanzhuJDs);

                                }

                                    if(tieziAdapter==null){
                                        tieziAdapter =new TieziAdapter(R.layout.tz_item_view,mList,title,stateClickListener);
                                        recy_card.setAdapter(tieziAdapter);
                                        if("我的主页".equals(title)){
                                            tieziAdapter.setXS(true);
                                            tieziAdapter.notifyDataSetChanged();
                                        }
                                        tieziAdapter.setOnDianClickListener(dianClickListener);
                                        tieziAdapter.SetOnItemClickListener(onItemClickListener);
                                    }else{
                                        if("我的主页".equals(title)){
                                            tieziAdapter.setXS(true);
                                        }
                                        tieziAdapter.notifyDataSetChanged();

                                }

                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }



    private StateClickListener stateClickListener = new StateClickListener() {
        @Override
        public void ShouCangClickListener(int objId, int obj_type, int operation_type, int fx,int  pos) {

            network(objId,obj_type,operation_type,pos);
        }

        @Override
        public void DianZanClickListener(int objId, int obj_type, int operation_type, int fx,int pos) {

            network(objId,obj_type,operation_type,pos);
        }

        @Override
        public void QuXiaoShouCangClickListener(int objId, int obj_type, int operation_type, int pos) {

            QXnetwork(objId,obj_type,operation_type,pos);

        }

        @Override
        public void QuXiaoDianZanClickListener(int objId, int obj_type, int operation_type, int pos) {
            QXnetwork(objId,obj_type,operation_type,pos);
        }
    };

    private void network(int objId , int obj_type, final int operation_type, final int pos) {

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


                                Intent gz = new Intent();
                                gz.setAction(GlobalParams.DONGTAI_SX);
                                sendBroadcast(gz);
                                JSONObject datas = jsonobj.getJSONObject("datas");
                                if(qh==0){

                                    if(operation_type==0){
                                        jdList.get(pos).is_zan="1";
                                        jdList.get(pos).dianZanCount=datas.getString("zanCount");
                                    }else{
                                        jdList.get(pos).is_collect="1";
                                        jdList.get(pos).collectCount=datas.getString("zanCount");
                                    }


                                    if(jdAdapter==null){
                                        jdAdapter =new JDAdapter(R.layout.tz_item_view,jdList,title,stateClickListener);
                                        if("我的主页".equals(title)){
                                            jdAdapter.setXS(true);
                                        }
                                        recy_card.setAdapter(jdAdapter);
                                    }else {
                                        jdAdapter.setEnable(true);
                                        if("我的主页".equals(title)){
                                            jdAdapter.setXS(true);
                                        }
                                        jdAdapter.notifyDataSetChanged();
                                    }


                                }else if(qh==2){
                                    if(operation_type==0){
                                        mList.get(pos).is_zan="1";
                                        mList.get(pos).dianZanCount=datas.getString("zanCount");
                                    }else{
                                        mList.get(pos).is_collect="1";
                                        mList.get(pos).collectCount=datas.getString("zanCount");
                                    }
                                    if(tieziAdapter==null){
                                        tieziAdapter =new TieziAdapter(R.layout.tz_item_view,mList,title,stateClickListener);
                                        recy_card.setAdapter(tieziAdapter);
                                        if("我的主页".equals(title)){
                                            tieziAdapter.setXS(true);
                                            tieziAdapter.setEnable(true);
                                            tieziAdapter.notifyDataSetChanged();
                                        }
                                        tieziAdapter.setOnDianClickListener(dianClickListener);
                                        tieziAdapter.SetOnItemClickListener(onItemClickListener);
                                    }else{
                                        if("我的主页".equals(title)){
                                            tieziAdapter.setXS(true);
                                        }
                                        tieziAdapter.setEnable(true);
                                        tieziAdapter.notifyDataSetChanged();
                                    }
                                }

                                networkGR();

                            }else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            } else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }
    private void QXnetwork(int objId , int obj_type, final int operation_type, final int pos) {

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

                                Intent gz = new Intent();
                                gz.setAction(GlobalParams.DONGTAI_SX);
                                sendBroadcast(gz);
                                JSONObject datas = jsonobj.getJSONObject("datas");
                                if(qh==0){
                                    if(operation_type==0){
                                        jdList.get(pos).is_zan="0";
                                        jdList.get(pos).dianZanCount=Integer.parseInt(jdList.get(pos).dianZanCount)-1+"";
                                    }else{
                                        jdList.get(pos).is_collect="0";
                                        jdList.get(pos).collectCount=Integer.parseInt(jdList.get(pos).collectCount)-1+"";
                                    }
                                    if(jdAdapter==null){
                                        jdAdapter =new JDAdapter(R.layout.tz_item_view,jdList,title,stateClickListener);
                                        if("我的主页".equals(title)){
                                            jdAdapter.setXS(true);
                                        }
                                        recy_card.setAdapter(jdAdapter);
                                    }else {
                                        jdAdapter.setEnable(false);
                                        if("我的主页".equals(title)){
                                            jdAdapter.setXS(true);
                                        }
                                        jdAdapter.notifyDataSetChanged();
                                    }
                                }else if(qh==2){
                                    if(operation_type==0){
                                        mList.get(pos).is_zan="0";
                                        mList.get(pos).dianZanCount=Integer.parseInt(mList.get(pos).dianZanCount)-1+"";
                                    }else{
                                        mList.get(pos).is_collect="0";
                                        mList.get(pos).collectCount=Integer.parseInt(mList.get(pos).collectCount)-1+"";
                                    }
                                    if(tieziAdapter==null){
                                        tieziAdapter =new TieziAdapter(R.layout.tz_item_view,mList,title,stateClickListener);
                                        recy_card.setAdapter(tieziAdapter);
                                        if("我的主页".equals(title)){
                                            tieziAdapter.setXS(true);
                                            tieziAdapter.setEnable(true);
                                            tieziAdapter.notifyDataSetChanged();
                                        }
                                        tieziAdapter.setOnDianClickListener(dianClickListener);
                                        tieziAdapter.SetOnItemClickListener(onItemClickListener);
                                    }else{
                                        if("我的主页".equals(title)){
                                            tieziAdapter.setXS(true);
                                        }
                                        tieziAdapter.setEnable(true);
                                        tieziAdapter.notifyDataSetChanged();
                                    }
                                }

                                networkGR();
                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private void networkPaiKe() {
        HttpParams params = new HttpParams();

            params.put("userId", uid);
        params.put("pageNo", pageDT);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/user/paikebyuser")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "拍客成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                JSONArray datas = jsonobj.getJSONArray("datas");
                                List<PaiKeInfo> paiKeInfos = new ArrayList<PaiKeInfo>();
                                for (int i = 0; i < datas.length(); i++) {

                                    JSONObject jo = datas.getJSONObject(i);
                                    PaiKeInfo paiKeInfo = new PaiKeInfo();
                                    paiKeInfo.pkid = jo.getString("id");
                                    paiKeInfo.imageUrl = jo.getString("imageUrl");
                                    paiKeInfo.mvUrl = jo.getString("mvUrl");
                                    paiKeInfo.userId = jo.getString("userId");
                                    paiKeInfo.address = jo.getString("address");
                                    paiKeInfo.userName = jo.getString("userName");
                                    paiKeInfo.userLevel = jo.getString("userLevel");
                                    paiKeInfo.isRecommend = jo.getString("isRecommend");
                                    paiKeInfo.pkOperation = jo.getString("pkOperation");
                                    paiKeInfo.createTime = jo.getString("createTime");
                                    paiKeInfo.isDeleted = jo.getString("isDeleted");
                                    paiKeInfo.pkTitle = jo.getString("pkTitle");
                                    paiKeInfo.imagesUrl = jo.getString("imagesUrl");
                                    paiKeInfo.pkContent = jo.getString("pkContent");
                                    paiKeInfo.commentCount = jo.getString("commentCount");
                                    paiKeInfo.dianZanCount = jo.getString("dianZanCount");
                                    paiKeInfo.collectCount = jo.getString("collectCount");
                                    paiKeInfo.relayCount = jo.getString("relayCount");
                                    paiKeInfo.nickName = jo.getString("nickName");
                                    paiKeInfo.pic = jo.getString("pic");
                                    paiKeInfo.nickName = jo.getString("nickName");
                                    paiKeInfo.isZan=jo.getString("isZan");

                                    paiKeInfo.paths = new ArrayList<String>();
                                    if (paiKeInfo.imagesUrl.length() > 0) {

                                        JSONArray ja = jo.getJSONArray("paths");
                                        for (int j = 0; j < ja.length(); j++) {

                                            paiKeInfo.paths.add(ja.getString(j));

                                        }

                                    }

                                    paiKeInfos.add(paiKeInfo);


                                }
                                if (pageDT > 1) {

                                    if (paiKeInfos.size() <= 0) {

                                        Toast.makeText(MydynamicActivity.this, Api.TOAST, Toast.LENGTH_SHORT).show();

                                    } else {
                                        list.addAll(paiKeInfos);
                                    }
                                } else {

                                    list.addAll(paiKeInfos);

                                }

                                Log.e("TAG", "推荐数据：：" + list.size());

                                if (tagAdapter == null) {
                                    tagAdapter = new DongTaiAdapter(R.layout.tui_recyitem, list,MydynamicActivity.this);
                                    if("我的主页".equals(title)){
                                        tagAdapter.setXS(true);
                                    }
                                    recy_card.setAdapter(tagAdapter);
                                    tagAdapter.setOnItemClickListener(onItemClickListener1);
                                    tagAdapter.setOnItemLongClickListener(MydynamicActivity.this);
                                } else {
                                    if("我的主页".equals(title)){
                                        tagAdapter.setXS(true);
                                    }
                                    tagAdapter.notifyDataSetChanged();
                                }
                            }else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private String name,nickname;

    private void networkGR() {
        HttpParams params = new HttpParams();

        params.put("userId", uid);
        params.put("myuserId", SpUtils.getString(this,"userid",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/user/personalcenter")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "个人成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {
                                JSONObject datas = jsonobj.getJSONObject("datas");

                                JSONObject jo1 = datas.getJSONObject("user");
                                JSONObject jo2 = datas.getJSONObject("userinfo");
                                name=jo1.getString("nickname");
                                text_name.setText(jo1.getString("nickname"));

                                if(jo2.getString("pic").length()>0){
                                    pic=jo2.getString("pic");
                                    Glide.with(MydynamicActivity.this).load(jo2.getString("pic")).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img_tou);
                                }
                                nickname=jo1.getString("nickname");
                                userLevel=jo2.getString("userLevel");
                                tv_zy_xq.setText(jo2.getString("userLevel"));
                                guan_num.setText(datas.getString("gznum"));
                                fen_num.setText(datas.getString("fsnum"));
                                collect_num.setText(datas.getString("dznum"));
                                tv_qm.setText("简介："+jo2.getString("signature"));

                                if(datas.getString("imageurl").length()>0){
                                    Glide.with(MydynamicActivity.this).load(datas.getString("imageurl")).into(rl_bg);
                                }



                                if(Integer.parseInt(jo2.getString("userSex"))==0){//男

                                    text_sex.setImageResource(R.drawable.icon_boy);


                                }else{

                                    text_sex.setImageResource(R.drawable.icon_girl);

                                }
                                text_pin.setText("郊点（"+datas.getString("jdnum")+"）");

                                text_zan.setText("拍客（"+datas.getString("pknum")+"）");

                                text_tz.setText("帖子（"+datas.getString("tznum")+"）");
                                if(!"我的主页".equals(title)){
                                    if(Integer.parseInt(datas.getString("isGz"))==0){
                                        iv_gz.setVisibility(View.INVISIBLE);
                                    }else{
                                        iv_gz.setVisibility(View.VISIBLE);
                                    }
                                }

                            }else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    int pos;
    @Override
    public void onPaiKeZZListener(int pos) {

        this.pos=pos;
        PaiKeInfo pk = list.get(pos);

        if(NetUtil.checkNet(MydynamicActivity.this)){
            if(Integer.parseInt(pk.isZan)==0){
                Log.e("TAG","点击点赞");
                network(Integer.parseInt(pk.pkid),2,0);
            }else{
                QXnetwork(Integer.parseInt(pk.pkid),2,0);
            }
        }else{
            Toast.makeText(MydynamicActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }

    }


    //退出登录
    private void tuichuDialog(int grary, int animationStyle, final int pos) {
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
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_clean= dialog.getView(R.id.tv_clean);
        TextView tv_content = dialog.getView(R.id.tv_content);
        tv_clean.setText("提示");
        tv_content.setText("是否删除？");
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

                qh=1;
                if(NetUtil.checkNet(MydynamicActivity.this)){
                    networkPaikeSC(list.get(pos).pkid);
                }else{
                    Toast.makeText(MydynamicActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

                //更新页面
                dialog.dismiss();


            }
        });
    }


    @Override
    public void onPaiKeSCListener(int pos) {



        tuichuDialog(Gravity.CENTER,R.style.Alpah_aniamtion,pos);

    }


    private void networkPaikeSC(String pkId) {

        HttpParams params = new HttpParams();
        params.put("pkId", pkId);
        params.put("token", SpUtils.getString(MydynamicActivity.this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/paike/deletepaike")
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

                                pageDT=1;
                                list.clear();
                                if(tagAdapter!=null){
                                    tagAdapter.notifyDataSetChanged();
                                }

                                finishFreshAndLoad();
                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.DONGTAI_SX);
                                sendBroadcast(intent);
                                Intent GZ = new Intent();
                                GZ.setAction(GlobalParams.GZ);
                                sendBroadcast(GZ);
                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

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

        params.put("token", SpUtils.getString(MydynamicActivity.this,"token",null));
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

                                list.get(pos).isZan="1";
                                list.get(pos).dianZanCount=Integer.parseInt(list.get(pos).dianZanCount)+1+"";

                                if (tagAdapter == null) {
                                    tagAdapter = new DongTaiAdapter(R.layout.tui_recyitem, list,MydynamicActivity.this);
                                    if("我的主页".equals(title)){
                                        tagAdapter.setXS(true);
                                    }
                                    recy_card.setAdapter(tagAdapter);
                                    tagAdapter.setOnItemClickListener(onItemClickListener1);
                                    tagAdapter.setOnItemLongClickListener(MydynamicActivity.this);
                                } else {
                                    if("我的主页".equals(title)){
                                        tagAdapter.setXS(true);
                                    }
                                    tagAdapter.notifyDataSetChanged();
                                }


                            } else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            }else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

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

        params.put("token", SpUtils.getString(MydynamicActivity.this,"token",null));
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

                                list.get(pos).isZan="0";
                                list.get(pos).dianZanCount=Integer.parseInt(list.get(pos).dianZanCount)-1+"";
                                if (tagAdapter == null) {
                                    tagAdapter = new DongTaiAdapter(R.layout.tui_recyitem, list,MydynamicActivity.this);
                                    if("我的主页".equals(title)){
                                        tagAdapter.setXS(true);
                                    }
                                    recy_card.setAdapter(tagAdapter);

                                    tagAdapter.setOnItemClickListener(onItemClickListener1);
                                    tagAdapter.setOnItemLongClickListener(MydynamicActivity.this);
                                } else {
                                    if("我的主页".equals(title)){
                                        tagAdapter.setXS(true);
                                    }
                                    tagAdapter.notifyDataSetChanged();
                                }
                            }else if(code==201){

                                startActivity(new Intent(MydynamicActivity.this,LoginActivity.class));
                                SpUtils.putInt(MydynamicActivity.this, "guid", 1);
                            } else {
                                Toast.makeText(MydynamicActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

        Log.d("TAG","长按————————————————————————————————————————");


        tuichuDialog(Gravity.CENTER,R.style.Alpah_aniamtion,position);

        return true;
    }
}
