package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.MsgItmeActivity;
import www.diandianxing.com.diandianxing.ZixunDitailsActivity;
import www.diandianxing.com.diandianxing.adapter.Jiaodianadapter;
import www.diandianxing.com.diandianxing.adapter.MsgitmeAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.MessInfo;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.StateClickListener;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class JIaodianFragment extends BaseFragment {

    private ListView jiao_list;
    private SpringView jiao_spring;
    private List<GuanzhuJD> lists =new ArrayList<>();
    private int pageNo=1;
    Jiaodianadapter jiaodianadapter;
    private TextView tv_yw_content;
    @Override
    protected int setContentView() {
        return R.layout.fragment_jiaodian;
    }


    @Override
    protected void lazyLoad() {

        View contentView = getContentView();
        jiao_list = contentView.findViewById(R.id.jiaodan_list);
        jiao_spring = contentView.findViewById(R.id.jiao_springview);
        tv_yw_content=contentView.findViewById(R.id.tv_yw_content);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.JDSX);
        intentFilter.addAction(GlobalParams.GZ);
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
        if(NetUtil.checkNet(getActivity())){
            networklist();
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }

          jiao_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                  Intent intent=new Intent(getActivity(),JiaoDetailActivity.class);
                  startActivity(intent);
              }
          });
        jiao_spring.setType(SpringView.Type.FOLLOW);
        jiao_spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        lists.clear();
                        pageNo=1;
                        if(NetUtil.checkNet(getActivity())){
                            networklist();
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, 0);
                jiao_spring.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        if(NetUtil.checkNet(getActivity())){
                            networklist();
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 0);
                jiao_spring.onFinishFreshAndLoad();
            }
        });
        jiao_spring.setFooter(new DefaultFooter(getActivity()));
        jiao_spring.setHeader(new DefaultHeader(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    int bq;

    private ShareListener shareListener = new ShareListener() {
        @Override
        public void OnShareListener(int poss,int pos) {
            bq=pos;
            switch (poss){
                case 0:
                    SharebyWeixin(getActivity());
                    networkFxTj("2");
                    break;
                case 1:
                    SharebyWeixincenter(getActivity());
                    networkFxTj("3");
                    break;
                case 2:
                    SharebyQQ(getActivity());
                    networkFxTj("0");
                    break;
                case 3:
                    SharebyQzon(getActivity());
                    networkFxTj("1");
                    break;
                case 4:
                    if(isWxInstall(getActivity())){
                        SharebyWeiBo(getActivity());
                        networkFxTj("4");
                    }else {
                        Toast.makeText(getActivity(),"请先安装微博",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

            networkFxJF();

        }
    };

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
        params.put("objId",lists.get(bq).id);
        params.put("obj_type",0);
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

    
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(GlobalParams.JDSX.equals(action)){

                lists.clear();
                pageNo=1;
                if(NetUtil.checkNet(getActivity())){
                    networklist();
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }else if(GlobalParams.GZ.equals(action)){
                lists.clear();
                pageNo=1;
                if(NetUtil.checkNet(getActivity())){
                    networklist();
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
            }

        }
    };

    private void networklist() {

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", SpUtils.getString(getActivity(),"token",null));
        params.put("dataType", "1");
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/focusAttention")
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
                                if(pageNo>1){

                                    if(guanzhuJDs.size()<=0){

                                        Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        lists.addAll(guanzhuJDs);
                                    }
                                    tv_yw_content.setVisibility(View.GONE);
                                }else{

                                    if(guanzhuJDs.size()==0){
                                        tv_yw_content.setVisibility(View.VISIBLE);
                                    }else{
                                        tv_yw_content.setVisibility(View.GONE);
                                    }

                                    lists.addAll(guanzhuJDs);

                                }

                                if(jiaodianadapter==null){
                                    jiaodianadapter=new Jiaodianadapter(getActivity(),lists,shareListener,stateClickListener);
                                    jiao_list.setAdapter(jiaodianadapter);
                                }else{

                                    jiaodianadapter.notifyDataSetChanged();
                                }


                            }else if (code == 201) {

                            }else{
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
            if(NetUtil.checkNet(getActivity())){
                network(objId,obj_type,operation_type,pos);
            }else{
                Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void DianZanClickListener(int objId, int obj_type, int operation_type, int fx,int pos) {
            if(NetUtil.checkNet(getActivity())){
                network(objId,obj_type,operation_type,pos);
            }else{
                Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void QuXiaoShouCangClickListener(int objId, int obj_type, int operation_type, int pos) {
            if(NetUtil.checkNet(getActivity())){
                QXnetwork(objId,obj_type,operation_type,pos);
            }else{
                Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void QuXiaoDianZanClickListener(int objId, int obj_type, int operation_type, int pos) {
            if(NetUtil.checkNet(getActivity())){
                QXnetwork(objId,obj_type,operation_type,pos);
            }else{
                Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            }
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
                        Log.d("TAG", "数量数据：：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                JSONObject datas = jsonobj.getJSONObject("datas");

                                if(operation_type==0){
                                    lists.get(pos).is_zan="1";
                                    lists.get(pos).dianZanCount=datas.getString("zanCount");
                                }else{
                                    lists.get(pos).is_collect="1";
                                    lists.get(pos).collectCount=datas.getString("zanCount");
                                }

                                Intent gz = new Intent();
                                gz.setAction(GlobalParams.DONGTAI_SX);
                                getActivity().sendBroadcast(gz);

                                if(jiaodianadapter==null){
                                    jiaodianadapter=new Jiaodianadapter(getActivity(),lists,shareListener,stateClickListener);
                                    jiao_list.setAdapter(jiaodianadapter);
                                }else{
                                    jiaodianadapter.setEnable(true);
                                    jiaodianadapter.notifyDataSetChanged();
                                }
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
                                JSONObject datas = jsonobj.getJSONObject("datas");
                                if(operation_type==0){
                                    lists.get(pos).is_zan="0";
                                    lists.get(pos).dianZanCount=datas.getString("zanCount");
                                }else{
                                    lists.get(pos).is_collect="0";
                                    lists.get(pos).collectCount=datas.getString("zanCount");
                                }

                                Intent gz = new Intent();
                                gz.setAction(GlobalParams.DONGTAI_SX);
                                getActivity().sendBroadcast(gz);

                                if(jiaodianadapter==null){
                                    jiaodianadapter=new Jiaodianadapter(getActivity(),lists,shareListener,stateClickListener);
                                    jiao_list.setAdapter(jiaodianadapter);
                                }else{
                                    jiaodianadapter.setEnable(true);
                                    jiaodianadapter.notifyDataSetChanged();
                                }
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



    protected  void SharebyQQ(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+lists.get(bq).id);
        web.setTitle(lists.get(bq).postContent);//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription("每日郊点");//描述

        new ShareAction(context)
                .withMedia(web)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyQzon(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+lists.get(bq).id);
        web.setTitle(lists.get(bq).postContent);//标题
        web.setThumb(new UMImage(context,R.drawable.icon_tu));  //缩略图
        web.setDescription("每日郊点");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeixin(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+lists.get(bq).id);
        web.setTitle(lists.get(bq).postContent);//标题
        web.setThumb(new UMImage(context,R.drawable.icon_tu));  //缩略图
        web.setDescription("每日郊点");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void SharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+lists.get(bq).id);
        web.setTitle(lists.get(bq).postContent);//标题
        web.setThumb(new UMImage(context,R.drawable.icon_tu));  //缩略图
        web.setDescription("每日郊点");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .withMedia(web)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void SharebyWeiBo(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+lists.get(bq).id);
        web.setTitle(lists.get(bq).postContent);//标题
        web.setThumb(new UMImage(context,R.drawable.icon_tu));  //缩略图
        web.setDescription("每日郊点");//描述
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

}
