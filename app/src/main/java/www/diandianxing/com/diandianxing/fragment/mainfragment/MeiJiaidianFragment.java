package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.ShujuBean.DianzanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Fenlei_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.QuxiaozanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.User_guanzhu_Bean;
import www.diandianxing.com.diandianxing.adapter.MeiJiaodianAdapter;
import www.diandianxing.com.diandianxing.adapter.onlick;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.interfase.Dianzan_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.Fenlei_model_interfase;
import www.diandianxing.com.diandianxing.interfase.List_view;
import www.diandianxing.com.diandianxing.interfase.Quxiaozan_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.Userguanzhu_presenter_interfase;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.presenter.Dianzan_presenter;
import www.diandianxing.com.diandianxing.presenter.FenLei_presenter;
import www.diandianxing.com.diandianxing.presenter.Quxiaozan_presenter;
import www.diandianxing.com.diandianxing.presenter.User_Guanzhu_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * Created by Mr赵 on 2018/4/3.
 */
public class MeiJiaidianFragment extends BaseFragment implements Fenlei_model_interfase, List_view, Dianzan_presenter_interfase, Quxiaozan_presenter_interfase, onlick, Userguanzhu_presenter_interfase {

    private  int id;
    private ListView lv;
    private SpringView springView;
    private int page=1;
    List<Fenlei_Bean.DatasBean>list=new ArrayList<>();
    private MeiJiaodianAdapter chowuAdapter;
    private int what;
    private int postion;
    private int flag;
    private TextView text_sure;
    private User_Guanzhu_presenter user_guanzhu_presenter= new User_Guanzhu_presenter(this);
    private Dianzan_presenter dianzan_presenter= new Dianzan_presenter(this);
    private Quxiaozan_presenter quxiaozan_presenter= new Quxiaozan_presenter(this);
    private FenLei_presenter fenLei_presenter= new FenLei_presenter(this);
    private int conten;
    private IntentFilter intentFilter;
    private TextView tv_yw_content;

    public MeiJiaidianFragment(int id) {
        this.id = id;
    }
    public MeiJiaidianFragment() {
    }

    @Override
    public void onclick(int position, int state, int id, int flag,int itmeconten) {
        this.postion = position;
        this.what = state;
        this.flag = flag;
        this.conten=itmeconten;
        if (state == 0) {
            if(flag==1){
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    dianzan_presenter.setpath(SpUtils.getString(getActivity(),"token",null), id, 0, 0);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }else{
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    quxiaozan_presenter.setpath(SpUtils.getString(getActivity(),"token",null), id, 0, 0);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }
        } else if (state == 1) {
            Log.e("TAG","xiafijasifjiajifja---------------------------------------------------");
            if (flag == 1) {
                Log.e("TAG","xiafijasifjiajifja=============================");
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    dianzan_presenter.setpath(SpUtils.getString(getActivity(),"token",null), id, 0, 1);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            } else{
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    quxiaozan_presenter.setpath(SpUtils.getString(getActivity(),"token",null), id, 0, 1);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }
        }
        Log.e("TAG","========点击======");
    }
    @Override
    protected int setContentView() {
        return R.layout.frag_gz;
    }
    @Override
    protected void lazyLoad() {

        View contentView = getContentView();

        lv = contentView.findViewById(R.id.lv);
        springView = contentView.findViewById(R.id.spring_view);
        tv_yw_content=contentView.findViewById(R.id.tv_yw_content);

        if(intentFilter==null){
            intentFilter= new IntentFilter();
            intentFilter.addAction(GlobalParams.JDSX);
            intentFilter.addAction(GlobalParams.GZ);
            getActivity().registerReceiver(broadcastReceiver,intentFilter);

             /*
        * 引用
        * */
            Log.e("TAG","执行到碎片里面的了#################################");
            if(NetUtil.checkNet(getActivity())){
                list.clear();
                page = 1;
                //获取引用
                fenLei_presenter.getpath(SpUtils.getString(getActivity(),"token",null),id,page);
            }else{
                Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            }

        }


        /*
        * 刷新
        * */
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        page = 1;
                        if(NetUtil.checkNet(getActivity())){
                            //获取引用
                            fenLei_presenter.getpath(SpUtils.getString(getActivity(),"token",null),id, page);
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }

                        chowuAdapter.notifyDataSetChanged();
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page ++;
                        if(NetUtil.checkNet(getActivity())){
                            //获取引用
                            fenLei_presenter.getpath(SpUtils.getString(getActivity(),"token",null),id, page);
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }

                        chowuAdapter.notifyDataSetChanged();
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setHeader(new DefaultHeader(getActivity()));

        /*
        * 适配器
        * */
        chowuAdapter = new MeiJiaodianAdapter(getActivity(),list,shareListener);
        chowuAdapter.getclick(this);
        chowuAdapter.guanzhu_click(this);
        lv.setAdapter(chowuAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), JiaoDetailActivity.class);
                startActivity(intent);
            }
        });
    }




    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(GlobalParams.JDSX.equals(action)){

                list.clear();
                page = 1;
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    fenLei_presenter.getpath(SpUtils.getString(getActivity(),"token",null),id, page);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

                chowuAdapter.notifyDataSetChanged();

            }else if(GlobalParams.GZ.equals(action)){

                list.clear();
                page = 1;
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    fenLei_presenter.getpath(SpUtils.getString(getActivity(),"token",null),id, page);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

                chowuAdapter.notifyDataSetChanged();

            }

        }
    };


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
        params.put("objId",list.get(bq).getId());
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

    int bq;
    protected  void SharebyQQ(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+list.get(bq).getId());
        web.setTitle(list.get(bq).getPostContent());//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(list.get(bq).getObjName());//描述

        new ShareAction(context)
                .withMedia(web)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyQzon(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+list.get(bq).getId());
        web.setTitle(list.get(bq).getPostContent());//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(list.get(bq).getObjName());//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeixin(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+list.get(bq).getId());
        web.setTitle(list.get(bq).getPostContent());//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(list.get(bq).getObjName());//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void SharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+list.get(bq).getId());
        web.setTitle(list.get(bq).getPostContent());//标题
        web.setThumb(new UMImage(context, R.drawable.icon_tu));  //缩略图
        web.setDescription(list.get(bq).getObjName());//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .withMedia(web)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeiBo(Activity context) {
        UMWeb web = new UMWeb(Api.H5_URL+"jdxq.html?token="+SpUtils.getString(getActivity(),"token",null)+"&id="+list.get(bq).getId());
        web.setTitle(list.get(bq).getPostContent());//标题
        web.setThumb(new UMImage(context,R.drawable.icon_tu));  //缩略图
        web.setDescription(list.get(bq).getObjName());//描述
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
    public void getsuccess(Fenlei_Bean fenlei_bean) {
        if(fenlei_bean.getCode().equals("200")){
            List<Fenlei_Bean.DatasBean> datas = fenlei_bean.getDatas();
            if(page>1){
                if(datas.size()>0){
                    list.addAll(datas);
                }else{
                    Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();
                }
            }else{

                list.clear();


                list.addAll(datas);
            }

            if(list.size()<=0){

                tv_yw_content.setVisibility(View.VISIBLE);
                tv_yw_content.setText("");
            }else{
                tv_yw_content.setVisibility(View.GONE);
            }

            chowuAdapter.notifyDataSetChanged();
        }else if(fenlei_bean.getCode().equals("201")){

//            startActivity(new Intent(getActivity(),LoginActivity.class));
//            SpUtils.putInt(getActivity(), "guid", 1);

        }
    }


    @Override
    public void setsuccess(DianzanAndFenxiang_Bean zan) {

        Log.e("TAG","zan.getCode()=="+zan.getCode());

        if(zan.getCode().equals("200")){
            int zanCount = zan.getDatas().getZanCount();

            if (what == 0) {
                list.get(postion).setDianZanCount((zanCount));
                list.get(postion).setIs_zan(1);
            }else if (what == 1) {
                list.get(postion).setCollectCount(zanCount);
                list.get(postion).setIs_collect(1);
            }

            Intent gz = new Intent();
            gz.setAction(GlobalParams.DONGTAI_SX);
            getActivity().sendBroadcast(gz);
            chowuAdapter.setEnable(true);
            chowuAdapter.notifyDataSetChanged();
        }else if(zan.getCode().equals("201")){

//            startActivity(new Intent(getActivity(),LoginActivity.class));
//            SpUtils.putInt(getActivity(), "guid", 1);
        }
    }

    @Override
    public void setsuccess(QuxiaozanAndFenxiang_Bean zan) {
        Log.e("TAG","zan.getCode()=="+zan.getCode());
        if (zan.getCode().equals("200")) {
            int zanCount = zan.getDatas().getZanCount();
            if (what == 0) {
                list.get(postion).setDianZanCount(zanCount);
                list.get(postion).setIs_zan(0);
            }else if (what == 1) {
                list.get(postion).setCollectCount(zanCount);
                list.get(postion).setIs_collect(0);
            }

            Intent gz = new Intent();
            gz.setAction(GlobalParams.DONGTAI_SX);
            getActivity().sendBroadcast(gz);
            chowuAdapter.setEnable(true);
            chowuAdapter.notifyDataSetChanged();
        }else if(zan.getCode().equals("201")){

//            startActivity(new Intent(getActivity(),LoginActivity.class));
//            SpUtils.putInt(getActivity(), "guid", 1);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
       // fenLei_presenter.getkong();
        dianzan_presenter.getkong();
        quxiaozan_presenter.getkong();
        user_guanzhu_presenter.getkong();
        if(intentFilter!=null){
            getActivity().unregisterReceiver(broadcastReceiver);
        }
    }
    @Override
    public void guanzhu_click(int postion) {
        int userId = list.get(postion).getUserId();

        list.get(postion).setIs_focus(1);

        chowuAdapter.notifyDataSetChanged();

        user_guanzhu_presenter.getpath(SpUtils.getString(getActivity(),"token",null),userId);

    }


    @Override
    public void getsuccess(User_guanzhu_Bean user_guanzhu_bean) {
        Log.e("TAG","zan.getCode()=="+user_guanzhu_bean.getCode());
        if(user_guanzhu_bean.getCode().equals("200")){
            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
            chowuAdapter.notifyDataSetChanged();
            Intent intent = new Intent();
            intent.setAction(GlobalParams.GZ);
            getActivity().sendBroadcast(intent);
        }else if(user_guanzhu_bean.getCode().equals("201")){

//                startActivity(new Intent(getActivity(),LoginActivity.class));
//            SpUtils.putInt(getActivity(), "guid", 1);
        }else if(user_guanzhu_bean.getCode().equals("203")){
            ToastUtils.showShort(getActivity(),user_guanzhu_bean.getMsg());
        }else if(user_guanzhu_bean.getCode().equals("500")){
            ToastUtils.showShort(getActivity(),user_guanzhu_bean.getMsg());
        }
    }

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
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
}
