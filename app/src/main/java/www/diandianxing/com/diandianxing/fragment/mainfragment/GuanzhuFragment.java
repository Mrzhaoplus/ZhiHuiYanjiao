package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.ShujuBean.DianzanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Live_gunzhu_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.QuxiaozanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.adapter.GuanzhuAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.Event_coll_size;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.interfase.Dianzan_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.List_view;
import www.diandianxing.com.diandianxing.interfase.Live_guanzhu_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.Quxiaozan_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.RecyGetonclick;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.presenter.Dianzan_presenter;
import www.diandianxing.com.diandianxing.presenter.Live_guanzhu_presenter;
import www.diandianxing.com.diandianxing.presenter.Quxiaozan_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class GuanzhuFragment extends BaseFragment implements Live_guanzhu_presenter_interfase, Dianzan_presenter_interfase, List_view, Quxiaozan_presenter_interfase {

    private ListView lv;
    private Live_guanzhu_presenter live_guanzhu_presenter;
    private int pageNo = 1;
    List<Live_gunzhu_Bean.DatasBean> list = new ArrayList<>();
    private SpringView springView;
    private GuanzhuAdapter guanzhuAdapter;
    private int what;
    private int postion;
    private int flag;
    Dianzan_presenter   dianzan_presenter = new Dianzan_presenter(this);
    Quxiaozan_presenter  quxiaozan_presenter = new Quxiaozan_presenter(this);
    private int conten;

    @Override
    public void onclick(int position, int state, int id, int flag,int itmeconten) {
        this.postion = position;
        this.what = state;
        this.flag = flag;
        this.conten=itmeconten;
        if (state == 0) {
            Log.i("===============", flag + "");
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
        //引用
        live_guanzhu_presenter = new Live_guanzhu_presenter(this);
        if(NetUtil.checkNet(getActivity())){
            //获取引用
            live_guanzhu_presenter.getpath(Api.token, pageNo);
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }



        guanzhuAdapter = new GuanzhuAdapter(getActivity(), shareListener, list);
        guanzhuAdapter.getclick(this);
        lv.setAdapter(guanzhuAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), JiaoDetailActivity.class);
                startActivity(intent);
            }
        });

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        pageNo = 1;
                        if(NetUtil.checkNet(getActivity())){
                            //获取引用
                            live_guanzhu_presenter.getpath(Api.token, pageNo);
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }

                        guanzhuAdapter.notifyDataSetChanged();
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        if(NetUtil.checkNet(getActivity())){
                            //获取引用
                            live_guanzhu_presenter.getpath(Api.token, pageNo);
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }

                        guanzhuAdapter.notifyDataSetChanged();
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setHeader(new DefaultHeader(getActivity()));
    }

    private ShareListener shareListener = new ShareListener() {
        @Override
        public void OnShareListener(int poss) {
            switch (poss) {
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

    protected void SharebyQQ(Activity context) {
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

    protected void SharebyQzon(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context, R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected void SharebyWeixin(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context, R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected void SharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context, R.drawable.img_diandianlogo));  //缩略图
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
            map.put("uid", SpUtils.getString(getActivity(), "userid", null));
            map.put("type", type);
            map.put("token", SpUtils.getString(getActivity(), "token", null));
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
    public void onDestroy() {
        super.onDestroy();
        live_guanzhu_presenter.getkong();
        dianzan_presenter.getkong();
        quxiaozan_presenter.getkong();
    }

    @Override
    public void getsuccess(Live_gunzhu_Bean guanzhu) {
        if (guanzhu.getCode().equals("200")) {
            List<Live_gunzhu_Bean.DatasBean> datas = guanzhu.getDatas();
            if (pageNo > 1) {
                if (datas.size() > 0) {
                    list.addAll(datas);
                } else {
                    Toast.makeText(getActivity(), Api.TOAST, Toast.LENGTH_SHORT).show();
                }
            } else {
                list.addAll(datas);
            }
            guanzhuAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setsuccess(DianzanAndFenxiang_Bean zan) {

        if (zan.getCode().equals("200")) {
            int zanCount = zan.getDatas().getZanCount();
            if (what == 0) {
                list.get(postion).setDianZanCount(list.get(postion).getDianZanCount()+1);
              list.get(postion).setIs_zan(1);
                guanzhuAdapter.notifyDataSetChanged();
            } else if (what == 1) {
                EventBus.getDefault().postSticky(new Event_coll_size(4,0));
                    list.get(postion).setCollectCount(list.get(postion).getCollectCount() + 1);
                list.get(postion).setIs_collect(1);
                guanzhuAdapter.notifyDataSetChanged();

                }
            guanzhuAdapter.notifyDataSetChanged();
            }
        }



    @Override
    public void setsuccess(QuxiaozanAndFenxiang_Bean zan) {
        if (zan.getCode().equals("200")) {
            int zanCount = zan.getDatas().getZanCount();
            if (what == 0) {
                    list.get(postion).setDianZanCount(list.get(postion).getDianZanCount() - 1);
                    list.get(postion).setIs_zan(0);
                guanzhuAdapter.notifyDataSetChanged();
            } else if (what == 1) {
                EventBus.getDefault().postSticky(new Event_coll_size(4,0));
                    list.get(postion).setCollectCount(list.get(postion).getCollectCount() - 1);
                    list.get(postion).setIs_collect(0);
                guanzhuAdapter.notifyDataSetChanged();

            }
            guanzhuAdapter.notifyDataSetChanged();
        }
    }
}
