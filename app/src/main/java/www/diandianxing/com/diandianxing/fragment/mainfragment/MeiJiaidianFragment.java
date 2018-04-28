package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    dianzan_presenter.setpath(Api.token, id, 0, 0);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }else{
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    quxiaozan_presenter.setpath(Api.token, id, 0, 0);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }
        } else if (state == 1) {
            if (flag == 0) {
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    dianzan_presenter.setpath(Api.token, id, 0, 1);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            } else if (flag == 1) {
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    quxiaozan_presenter.setpath(Api.token, id, 0, 1);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }
        }
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
        /*
        * 引用
        * */

        if(NetUtil.checkNet(getActivity())){
            //获取引用
            fenLei_presenter.getpath(Api.token,id,page);
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
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
                            fenLei_presenter.getpath(Api.token,id, page);
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
                            fenLei_presenter.getpath(Api.token,id, page);
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




    private ShareListener shareListener = new ShareListener() {
        @Override
        public void OnShareListener(int poss) {
            switch (poss){
                case 0:
                    SharebyWeixin(getActivity());
                    break;
                case 1:
                    SharebyWeixincenter(getActivity());
                    break;
                case 2:
                    SharebyQQ(getActivity());
                    break;
                case 3:
                    SharebyQzon(getActivity());
                    break;
                case 4:
                    break;
            }
        }
    };

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
                list.addAll(datas);
            }
            chowuAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void setsuccess(DianzanAndFenxiang_Bean zan) {
        if(zan.getCode().equals("200")){
            int zanCount = zan.getDatas().getZanCount();

            if (what == 0) {
                list.get(postion).setDianZanCount((list.get(postion).getDianZanCount()+1));
                list.get(postion).setIs_zan(1);
                chowuAdapter.notifyDataSetChanged();
            }else if (what == 1) {
                list.get(postion).setCollectCount(list.get(postion).getCollectCount() + 1);
                list.get(postion).setIs_collect(1);
                chowuAdapter.notifyDataSetChanged();
            }
            chowuAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setsuccess(QuxiaozanAndFenxiang_Bean zan) {
        if (zan.getCode().equals("200")) {
            int zanCount = zan.getDatas().getZanCount();
            if (what == 0) {
                list.get(postion).setDianZanCount(list.get(postion).getDianZanCount() - 1);
                list.get(postion).setIs_zan(0);
                chowuAdapter.notifyDataSetChanged();
            }else if (what == 1) {
                list.get(postion).setCollectCount(list.get(postion).getCollectCount() - 1);
                list.get(postion).setIs_collect(0);
                chowuAdapter.notifyDataSetChanged();
            }
            chowuAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
       // fenLei_presenter.getkong();
        dianzan_presenter.getkong();
        quxiaozan_presenter.getkong();
        user_guanzhu_presenter.getkong();
    }
    @Override
    public void guanzhu_click(int postion) {
        int userId = list.get(postion).getUserId();
        user_guanzhu_presenter.getpath(Api.token,userId);
    }


    @Override
    public void getsuccess(User_guanzhu_Bean user_guanzhu_bean) {
        if(user_guanzhu_bean.getCode().equals("200")){
            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
        }else if(user_guanzhu_bean.getCode().equals("201")){
            ToastUtils.showShort(getActivity(),user_guanzhu_bean.getMsg());
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
