package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.luck.picture.lib.decoration.RecycleViewDivider;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.TopRuleActivity;
import www.diandianxing.com.diandianxing.adapter.TieziAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.StateClickListener;

/**
 * Created by Administrator on 2018/5/5.
 */

public class MyTieZiFragment extends BaseFragment implements View.OnClickListener{


    private RecyclerView recy_card;
    private SpringView sv_tz;
    ArrayList<GuanzhuJD> mList=new ArrayList<>();
    private int pageNo=1;
    private TieziAdapter tieziAdapter;
    private String title;
    Dialog DDdialog;
    private String pic="";
    private String userLevel="1";
    private TextView tv_jfzd,tv_sc,tv_close;
    private String uid;
    @Override
    protected int setContentView() {
        return R.layout.fragment_my_tzdt;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        recy_card=contentView.findViewById(R.id.recy_card);
        sv_tz=contentView.findViewById(R.id.sv_tz);
        Bundle arguments = getArguments();

        uid = arguments.getString("uid");
        title=arguments.getString("title");
          /*
           设置监听
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recy_card.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recy_card.setNestedScrollingEnabled(false);
        finishFreshAndLoad();
    }

    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        sv_tz.setType(SpringView.Type.FOLLOW);
        sv_tz.setFooter(new DefaultFooter(getActivity()));
        sv_tz.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(mContext,"下拉刷新中",Toast.LENGTH_SHORT).show();
                // list.clear();
                // 网络请求;
                // mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法

                mList.clear();
                pageNo=1;
                if(NetUtil.checkNet(getActivity())){

                    finishFreshAndLoad();
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                pageNo++;
                if(NetUtil.checkNet(getActivity())){
                    finishFreshAndLoad();
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

                    networkTiezi();

                sv_tz.onFinishFreshAndLoad();
            }
        }, 0);
    }

    private void networkTiezi() {
        HttpParams params = new HttpParams();

        params.put("userId", uid);
        params.put("pageNo", pageNo);
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
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<GuanzhuJD> guanzhuJDs = new ArrayList<GuanzhuJD>();
                            if (code == 200) {
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
                                    JSONArray ja=jo.getJSONArray("imagesList");

                                    guanzhu.imagesList = new ArrayList<String>();
                                    for(int j=0;j<ja.length();j++){
                                        guanzhu.imagesList.add(ja.getString(j));
                                    }

                                    guanzhuJDs.add(guanzhu);


                                }
                                if(pageNo>1){

                                    if(guanzhuJDs.size()<=0){

                                        Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        mList.addAll(guanzhuJDs);
                                    }

                                }else{


                                    mList.clear();

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
//                                    tieziAdapter.SetOnItemClickListener(onItemClickListener);
                                }else{
                                    if("我的主页".equals(title)){
                                        tieziAdapter.setXS(true);
                                    }
                                    tieziAdapter.notifyDataSetChanged();
                                }

                                Log.e("TAG","帖子数据：：："+mList.size());

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
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                if(operation_type==0){
                                    mList.get(pos).is_zan="1";
                                    mList.get(pos).dianZanCount=Integer.parseInt(mList.get(pos).dianZanCount)+1+"";
                                }else{
                                    mList.get(pos).is_collect="1";
                                    mList.get(pos).collectCount=Integer.parseInt(mList.get(pos).collectCount)+1+"";
                                }
                                if(tieziAdapter==null){
                                    tieziAdapter =new TieziAdapter(R.layout.tz_item_view,mList,title,stateClickListener);
                                    recy_card.setAdapter(tieziAdapter);
                                    if("我的主页".equals(title)){
                                        tieziAdapter.setXS(true);
                                        tieziAdapter.notifyDataSetChanged();
                                    }
                                    tieziAdapter.setOnDianClickListener(dianClickListener);
//                                    tieziAdapter.SetOnItemClickListener(onItemClickListener);
                                }else{
                                    if("我的主页".equals(title)){
                                        tieziAdapter.setXS(true);
                                    }
                                    tieziAdapter.notifyDataSetChanged();
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
    private void QXnetwork(int objId , int obj_type, final int operation_type, final int pos) {

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
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

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
                                        tieziAdapter.notifyDataSetChanged();
                                    }
                                    tieziAdapter.setOnDianClickListener(dianClickListener);
//                                    tieziAdapter.SetOnItemClickListener(onItemClickListener);
                                }else{
                                    if("我的主页".equals(title)){
                                        tieziAdapter.setXS(true);
                                    }
                                    tieziAdapter.notifyDataSetChanged();
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

//    private TieziAdapter.OnItemClickListener onItemClickListener = new TieziAdapter.OnItemClickListener() {
//        @Override
//        public void ItemClick(View view, int position) {
//
//
//            Intent intent=new Intent(getActivity(),JiaoDetailActivity.class);
//
//            mList.get(position).pic=pic;
//            mList.get(position).userLevel=userLevel;
//
//            intent.putExtra("guanzhu",mList.get(position));
//
//
//            startActivity(intent);
//
//        }
//
//        @Override
//        public void FxClickListener(int pos) {
////            setShowPop(sharingPop,liner_tiezi);
//            showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
//
//        }
//
//        @Override
//        public void ScClickListener(int pos) {
//        }
//
//        @Override
//        public void DzClickListener(int pos) {
//
//        }
//    };
    private String postId;

    private TieziAdapter.DianClickListener dianClickListener = new TieziAdapter.DianClickListener() {
        @Override
        public void onDianClickListener(int pos) {

//            setShowPop(zdPop,liner_tiezi);
//            zdPop.showAtLocation(liner_tiezi, Gravity.BOTTOM, 0, 0);


            postId=mList.get(pos).id;

            showDDDDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion,postId);

        }
    };

    private void showDDDDialog(int grary, int animationStyle, final String postId) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
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

    private String timelong,bonus;

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
                            JSONObject datas = jsonobj.getJSONObject("datas");
                            if (code == 200) {


                                timelong=datas.getString("timelong");
                                bonus=datas.getString("bonus");
                                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion,postId);


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

    private TextView text_sure;
    private void shumaDialog(int grary, int animationStyle, final String pId) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
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
        tv_dt_content.setText("本次顶帖需要花费"+bonus+"积分("+ MyUtils.stampsToDate(timelong)+"后开始新的计费周期)，是否继续？");
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
                Intent intent = new Intent(getActivity(), TopRuleActivity.class);
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
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private void cgDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_close:

                DDdialog.dismiss();

                break;
            case R.id.tv_sc:
                if(NetUtil.checkNet(getActivity())){
                    networkdele(postId);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
                DDdialog.dismiss();
                break;
            case R.id.quxiao:
                dialog_fx.dismiss();
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


        }
    }


    protected  void SharebyQQ(Activity context) {
        UMWeb web = new UMWeb("http://iphone.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
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
        UMWeb web = new UMWeb("http://iphone.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
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
        UMWeb web = new UMWeb("http://iphone.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
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
    protected void SharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb("http://iphone.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
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
                                pageNo=1;
                                mList.clear();
                                finishFreshAndLoad();


                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.DONGTAI_SX);
                                getActivity().sendBroadcast(intent);
                                Intent GZ = new Intent();
                                GZ.setAction(GlobalParams.GZ);
                                getActivity().sendBroadcast(GZ);

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
    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;
    Dialog dialog_fx;
    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
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
}
