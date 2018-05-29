package www.diandianxing.com.diandianxing.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.DianDianActivity;
import www.diandianxing.com.diandianxing.DisconnectActivity;
import www.diandianxing.com.diandianxing.LiveActivity;
import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.LukuangDitailsActivity;
import www.diandianxing.com.diandianxing.LunBoActivity;
import www.diandianxing.com.diandianxing.MainActivity;
import www.diandianxing.com.diandianxing.MsgItmeActivity;
import www.diandianxing.com.diandianxing.SearchActivity;
import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.ZixunDitailsActivity;
import www.diandianxing.com.diandianxing.adapter.Homeadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.ShujuBean.Lunbo_Bean;
import www.diandianxing.com.diandianxing.bean.Evebtbus_fragment;
import www.diandianxing.com.diandianxing.bean.LunBoTable;
import www.diandianxing.com.diandianxing.bean.TuijianTable;
import www.diandianxing.com.diandianxing.bean.ZixunTable;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaodianActivity;
import www.diandianxing.com.diandianxing.fragment.mainfragment.LuKuangActivity;
import www.diandianxing.com.diandianxing.interfase.Zixun_presenterinterfase;
import www.diandianxing.com.diandianxing.interfase.LunPresenter_interfase;
import www.diandianxing.com.diandianxing.interfase.RecyGetonclick;
import www.diandianxing.com.diandianxing.my.MessageActivity;
import www.diandianxing.com.diandianxing.my.MessagedetailActivity;
import www.diandianxing.com.diandianxing.presenter.Zixun_presenter;
import www.diandianxing.com.diandianxing.presenter.Lunbo_presenter;
import www.diandianxing.com.diandianxing.set.AboutweActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BannerUtils;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.util.GlideImageLoader;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.ImageLoder;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 * c69fdd9ef168a35da9450b9313d08b43
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, RecyGetonclick, LunPresenter_interfase, Zixun_presenterinterfase {

    public View rootView;
    public ImageView img_tu;
    public LinearLayout tv_search;
    public ImageView iv_msg;
    public TextView tv_text_msg;
    public LinearLayout ll_msg;
    public LinearLayout rl_top;
    public Banner banner;
    public TextView text_diandian;
    public TextView text_jiao;
    public TextView text_lu;
    public ImageView imageView;
    public RecyclerView recy_view;
    private SpringView spring_view;
    private ImageView life;
    List<String> banlist = new ArrayList<>();
    private TextView zixun_itme;
    private TextView zan;
     List<zixun_Bean.DatasBean> list=new ArrayList<>();
    private Lunbo_presenter lunbo_presenter = new Lunbo_presenter(this);
    private Zixun_presenter home_zixun_presenter = new Zixun_presenter(this);
    private int typeNo=1;
    private Homeadapter homeadapter;

    @Override
    protected int setContentView() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.SY_ZX);
        getActivity().registerReceiver(broadcastReceiver,intentFilter);

        //加载布局
        return R.layout.fragment_home;
    }

    @Override
    protected void lazyLoad() {

        View contentView = getContentView();
        this.rootView = contentView;
        this.img_tu = (ImageView) rootView.findViewById(R.id.img_tu);
        this.tv_search = (LinearLayout) rootView.findViewById(R.id.tv_search);
        this.iv_msg = (ImageView) rootView.findViewById(R.id.iv_msg);
        this.tv_text_msg = (TextView) rootView.findViewById(R.id.tv_text_msg);
        this.ll_msg = (LinearLayout) rootView.findViewById(R.id.ll_msg);
        this.rl_top = (LinearLayout) rootView.findViewById(R.id.rl_top);
        this.banner = (Banner) rootView.findViewById(R.id.banner);
        this.text_diandian = (TextView) rootView.findViewById(R.id.text_diandian);
        this.text_jiao = (TextView) rootView.findViewById(R.id.text_jiao);
        this.text_lu = (TextView) rootView.findViewById(R.id.text_lu);
        this.imageView = (ImageView) rootView.findViewById(R.id.imageView);
        this.life = rootView.findViewById(R.id.life);
        this.recy_view = (RecyclerView) rootView.findViewById(R.id.recy_view);
        spring_view = rootView.findViewById(R.id.spring_view);
        zixun_itme = rootView.findViewById(R.id.zixun);
        zan = rootView.findViewById(R.id.zan);

        //获取id
        banner = contentView.findViewById(R.id.banner);

        if(NetUtil.checkNet(getActivity())){
            //获取引用
            lunbo_presenter.getString(SpUtils.getString(getActivity(),"token",null));
            home_zixun_presenter.getpath(1,SpUtils.getString(getActivity(),"token",null),typeNo);
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            List<ZixunTable> mlist = new ArrayList<>();

            List<LunBoTable> lunbo = new ArrayList<>();

            lunbo.addAll(DataSupport.findAll(LunBoTable.class));
            mlist.addAll(DataSupport.findAll(ZixunTable.class));


            for(int i=0;i<mlist.size();i++){

                ZixunTable zixunTable = mlist.get(i);

                zixun_Bean.DatasBean datasBean = new zixun_Bean.DatasBean();
                datasBean.setBigImage(zixunTable.bigImage);
                datasBean.setCreateTime(zixunTable.createTime);
                datasBean.setDianZanCount(zixunTable.dianZanCount);
                datasBean.setId(zixunTable.zxid);
                datasBean.setInfoBody(zixunTable.infoBody);
                datasBean.setInfoTitle(zixunTable.infoTitle);
                datasBean.setIsDeleted(zixunTable.isDeleted);
                datasBean.setShareCount(zixunTable.shareCount);
                datasBean.setSmallImage(zixunTable.smallImage);
                datasBean.setStatus(zixunTable.status);
                datasBean.setUpdateTime(zixunTable.updateTime);
                datasBean.setViewCount(zixunTable.viewCount);
                list.add(datasBean);
            }
            Log.e("TAG","数据数量：："+list.size());
            if(list!=null&&list.size()>0){
                RequestOptions options = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL);

                Glide.with(mContext).load(list.get(0).getBigImage()).
                        apply(options)
                        .into(imageView);
                zixun_itme.setText(list.get(0).getInfoTitle());
                zan.setText(list.get(0).getDianZanCount()+"");
                zxid=list.get(0).getId()+"";
                list.remove(0);
            }


            for(int j=0;j<lunbo.size();j++){

                LunBoTable lunBoTable = lunbo.get(j);

                Lunbo_Bean.DatasBean datasBean = new Lunbo_Bean.DatasBean();
                datasBean.setId(lunBoTable.loid);
                datasBean.setImageName(lunBoTable.imageName);
                datasBean.setImageLink(lunBoTable.imageLink);
                datasBean.setImageDesc(lunBoTable.imageDesc);
                datasBean.setImageType(lunBoTable.imageType);
                datasBean.setIsDeleted(lunBoTable.isDeleted);
                datasBean.setCreateTime(lunBoTable.createTime);
                datasBean.setUpdateTime(lunBoTable.updateTime);
                datasBean.setActiveTime(lunBoTable.activeTime);
                datasBean.setIsActive(lunBoTable.isActive);
                datasBean.setImageUrl(lunBoTable.imageUrl);
                datasBean.setUserId(lunBoTable.userId);
                datasBean.setObjId(lunBoTable.objId);
                datasBean.setObjIdType(lunBoTable.objIdType);
                datasBean.setLinkType(lunBoTable.linkType);

                datas.add(datasBean);
            }
            for (int i=0;i<datas.size();i++){
                banlist.add(datas.get(i).getImageUrl());
            }

//动态设置banner的高度
            ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
            layoutParams.height = MyUtils.getScreenWidth(mContext) * 7 / 15;


            banner.setImages(banlist);
            banner.setImageLoader(new GlideImageLoader());
            banner.setLayoutParams(layoutParams);

//            banner.setLayoutParams(layoutParams);
            banner.setDelayTime(3000);
            banner.start();
//            BannerUtils.startBanner(banner, banlist);
            //轮播图点击事件
            Banner banner = this.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    Lunbo_Bean.DatasBean datasBean = datas.get(position);
                    Intent intent;
                    if (datasBean.getLinkType().length() > 0) {
                        if (Integer.parseInt(datasBean.getLinkType()) == 1) {//外联

                            if(NetUtil.checkNet(getActivity())){
                                intent = new Intent(getActivity(), LunBoActivity.class);

                                intent.putExtra("url", datasBean.getImageLink());
                                intent.putExtra("title", datasBean.getImageName());
                                startActivity(intent);
                            }else{

                                startActivity(new Intent(getActivity(), DisconnectActivity.class));

                            }

                        } else {
                            Log.e("TAG","轮播数据内容：：："+datasBean.getObjIdType());
                            if (datasBean.getObjIdType().length() > 0) {
                                switch (Integer.parseInt(datasBean.getObjIdType())) {
                                    case 0:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent xx = new Intent(getActivity(), MessagedetailActivity.class);
                                            xx.putExtra("mes_id", datasBean.getObjId()+"");
                                            startActivity(xx);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                    case 1:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent tz = new Intent(getActivity(), JiaoDetailActivity.class);
                                            tz.putExtra("id", datasBean.getObjId()+"");
                                            startActivity(tz);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                    case 2:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent zx = new Intent(getActivity(), ZixunDitailsActivity.class);
                                            zx.putExtra("id", datasBean.getObjId()+"");
                                            startActivity(zx);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                    case 3:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent jd = new Intent(getActivity(), JiaoDetailActivity.class);
                                            jd.putExtra("id", datasBean.getObjId()+"");
                                            startActivity(jd);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                    case 4:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent lk = new Intent(getActivity(), LukuangDitailsActivity.class);
                                            lk.putExtra("id", datasBean.getObjId()+"");
                                            startActivity(lk);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                }
                            }



                        }


                    }

                }
            });


        }




        text_jiao.setOnClickListener(this);
        text_lu.setOnClickListener(this);
        life.setOnClickListener(this);
        ll_msg.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        imageView.setOnClickListener(this);
        text_diandian.setOnClickListener(this);
        img_tu.setOnClickListener(this);
         /*
             适配器
          */
        homeadapter = new Homeadapter(list,getActivity());
        homeadapter.getthis(this);
        //创建管理器
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        //列表管理器
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recy_view.setAdapter(homeadapter);
        //加载刷新
        recy_view.setNestedScrollingEnabled(false);

//        spring_view.setType(SpringView.Type.FOLLOW);

         spring_view.setListener(new SpringView.OnFreshListener() {
             //下拉树新
             @Override
             public void onRefresh() {
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         //获取引用
                         list.clear();
                         typeNo=1;
                         if(NetUtil.checkNet(getActivity())){
                             //获取引用
                             home_zixun_presenter.getpath(1,SpUtils.getString(getActivity(),"token",null),typeNo);
                             lunbo_presenter.getString(SpUtils.getString(getActivity(),"token",null));

                         }else{
                             Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();

                             List<ZixunTable> mlist = new ArrayList<>();
                             List<LunBoTable> lunbo = new ArrayList<>();

                             lunbo.addAll(DataSupport.findAll(LunBoTable.class));
                             mlist.addAll(DataSupport.findAll(ZixunTable.class));

                             for(int i=0;i<mlist.size();i++){

                                 ZixunTable zixunTable = mlist.get(i);

                                 zixun_Bean.DatasBean datasBean = new zixun_Bean.DatasBean();
                                 datasBean.setBigImage(zixunTable.bigImage);
                                 datasBean.setCreateTime(zixunTable.createTime);
                                 datasBean.setDianZanCount(zixunTable.dianZanCount);
                                 datasBean.setId(zixunTable.zxid);
                                 datasBean.setInfoBody(zixunTable.infoBody);
                                 datasBean.setInfoTitle(zixunTable.infoTitle);
                                 datasBean.setIsDeleted(zixunTable.isDeleted);
                                 datasBean.setShareCount(zixunTable.shareCount);
                                 datasBean.setSmallImage(zixunTable.smallImage);
                                 datasBean.setStatus(zixunTable.status);
                                 datasBean.setUpdateTime(zixunTable.updateTime);
                                 datasBean.setViewCount(zixunTable.viewCount);
                                 list.add(datasBean);
                             }


                             if(list!=null&&list.size()>0){
                                 RequestOptions options = new RequestOptions()
                                         .diskCacheStrategy(DiskCacheStrategy.ALL);

                                 Glide.with(mContext).load(list.get(0).getBigImage()).
                                         apply(options)
                                         .into(imageView);
                                 zixun_itme.setText(list.get(0).getInfoTitle());
                                 zan.setText(list.get(0).getDianZanCount()+"");
                                 zxid=list.get(0).getId()+"";
                                 list.remove(0);
                             }


                             datas.clear();
                             banlist.clear();

                             for(int j=0;j<lunbo.size();j++){

                                 LunBoTable lunBoTable = lunbo.get(j);

                                 Lunbo_Bean.DatasBean datasBean = new Lunbo_Bean.DatasBean();
                                 datasBean.setId(lunBoTable.loid);
                                 datasBean.setImageName(lunBoTable.imageName);
                                 datasBean.setImageLink(lunBoTable.imageLink);
                                 datasBean.setImageDesc(lunBoTable.imageDesc);
                                 datasBean.setImageType(lunBoTable.imageType);
                                 datasBean.setIsDeleted(lunBoTable.isDeleted);
                                 datasBean.setCreateTime(lunBoTable.createTime);
                                 datasBean.setUpdateTime(lunBoTable.updateTime);
                                 datasBean.setActiveTime(lunBoTable.activeTime);
                                 datasBean.setIsActive(lunBoTable.isActive);
                                 datasBean.setImageUrl(lunBoTable.imageUrl);
                                 datasBean.setUserId(lunBoTable.userId);
                                 datasBean.setObjId(lunBoTable.objId);
                                 datasBean.setObjIdType(lunBoTable.objIdType);
                                 datasBean.setLinkType(lunBoTable.linkType);

                                 datas.add(datasBean);
                             }
                             for (int i=0;i<datas.size();i++){
                                 banlist.add(datas.get(i).getImageUrl());
                             }

//动态设置banner的高度
                             ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
                             layoutParams.height = MyUtils.getScreenWidth(mContext) * 7 / 15;


                             banner.setImages(banlist);
                             banner.setImageLoader(new GlideImageLoader());
                             banner.setLayoutParams(layoutParams);

//            banner.setLayoutParams(layoutParams);
                             banner.setDelayTime(3000);
                             banner.start();
//            BannerUtils.startBanner(banner, banlist);
                             //轮播图点击事件
                             Banner banner = HomeFragment.this.banner.setOnBannerListener(new OnBannerListener() {
                                 @Override
                                 public void OnBannerClick(int position) {

                                     Lunbo_Bean.DatasBean datasBean = datas.get(position);
                                     Intent intent;
                                     if (datasBean.getLinkType().length() > 0) {
                                         if (Integer.parseInt(datasBean.getLinkType()) == 1) {//外联

                                             if(NetUtil.checkNet(getActivity())){
                                                 intent = new Intent(getActivity(), LunBoActivity.class);

                                                 intent.putExtra("url", datasBean.getImageLink());
                                                 intent.putExtra("title", datasBean.getImageName());
                                                 startActivity(intent);
                                             }else{

                                                 startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                             }

                                         } else {
                                             Log.e("TAG","轮播数据内容：：："+datasBean.getObjIdType());
                                             if (datasBean.getObjIdType().length() > 0) {
                                                 switch (Integer.parseInt(datasBean.getObjIdType())) {
                                                     case 0:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent xx = new Intent(getActivity(), MessagedetailActivity.class);
                                                             xx.putExtra("mes_id", datasBean.getObjId()+"");
                                                             startActivity(xx);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                     case 1:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent tz = new Intent(getActivity(), JiaoDetailActivity.class);
                                                             tz.putExtra("id", datasBean.getObjId()+"");
                                                             startActivity(tz);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                     case 2:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent zx = new Intent(getActivity(), ZixunDitailsActivity.class);
                                                             zx.putExtra("id", datasBean.getObjId()+"");
                                                             startActivity(zx);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                     case 3:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent jd = new Intent(getActivity(), JiaoDetailActivity.class);
                                                             jd.putExtra("id", datasBean.getObjId()+"");
                                                             startActivity(jd);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                     case 4:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent lk = new Intent(getActivity(), LukuangDitailsActivity.class);
                                                             lk.putExtra("id", datasBean.getObjId()+"");
                                                             startActivity(lk);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                 }
                                             }



                                         }


                                     }

                                 }
                             });


                         }
                         homeadapter.notifyDataSetChanged();
                     }
                 }, 0);
                 spring_view.onFinishFreshAndLoad();
             }
             //上拉加载
             @Override
             public void onLoadmore() {
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                       typeNo++;
                         if(NetUtil.checkNet(getActivity())){
                             //获取引用
                             home_zixun_presenter.getpath(1,SpUtils.getString(getActivity(),"token",null),typeNo);
                             Log.e("TAG","页码：：："+typeNo);
                         }else{

                             list.clear();

                             Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                             List<ZixunTable> mlist = new ArrayList<>();
                             List<LunBoTable> lunbo = new ArrayList<>();

                             lunbo.addAll(DataSupport.findAll(LunBoTable.class));
                             mlist.addAll(DataSupport.findAll(ZixunTable.class));

                             for(int i=0;i<mlist.size();i++){

                                 ZixunTable zixunTable = mlist.get(i);

                                 zixun_Bean.DatasBean datasBean = new zixun_Bean.DatasBean();
                                 datasBean.setBigImage(zixunTable.bigImage);
                                 datasBean.setCreateTime(zixunTable.createTime);
                                 datasBean.setDianZanCount(zixunTable.dianZanCount);
                                 datasBean.setId(zixunTable.zxid);
                                 datasBean.setInfoBody(zixunTable.infoBody);
                                 datasBean.setInfoTitle(zixunTable.infoTitle);
                                 datasBean.setIsDeleted(zixunTable.isDeleted);
                                 datasBean.setShareCount(zixunTable.shareCount);
                                 datasBean.setSmallImage(zixunTable.smallImage);
                                 datasBean.setStatus(zixunTable.status);
                                 datasBean.setUpdateTime(zixunTable.updateTime);
                                 datasBean.setViewCount(zixunTable.viewCount);
                                 list.add(datasBean);
                             }


                             if(list!=null&&list.size()>0){
                                 RequestOptions options = new RequestOptions()
                                         .diskCacheStrategy(DiskCacheStrategy.ALL);

                                 Glide.with(mContext).load(list.get(0).getBigImage()).
                                         apply(options)
                                         .into(imageView);
                                 zixun_itme.setText(list.get(0).getInfoTitle());
                                 zan.setText(list.get(0).getDianZanCount()+"");
                                 zxid=list.get(0).getId()+"";
                                 list.remove(0);
                             }


                             datas.clear();
                             banlist.clear();

                             for(int j=0;j<lunbo.size();j++){

                                 LunBoTable lunBoTable = lunbo.get(j);

                                 Lunbo_Bean.DatasBean datasBean = new Lunbo_Bean.DatasBean();
                                 datasBean.setId(lunBoTable.loid);
                                 datasBean.setImageName(lunBoTable.imageName);
                                 datasBean.setImageLink(lunBoTable.imageLink);
                                 datasBean.setImageDesc(lunBoTable.imageDesc);
                                 datasBean.setImageType(lunBoTable.imageType);
                                 datasBean.setIsDeleted(lunBoTable.isDeleted);
                                 datasBean.setCreateTime(lunBoTable.createTime);
                                 datasBean.setUpdateTime(lunBoTable.updateTime);
                                 datasBean.setActiveTime(lunBoTable.activeTime);
                                 datasBean.setIsActive(lunBoTable.isActive);
                                 datasBean.setImageUrl(lunBoTable.imageUrl);
                                 datasBean.setUserId(lunBoTable.userId);
                                 datasBean.setObjId(lunBoTable.objId);
                                 datasBean.setObjIdType(lunBoTable.objIdType);
                                 datasBean.setLinkType(lunBoTable.linkType);

                                 datas.add(datasBean);
                             }
                             for (int i=0;i<datas.size();i++){
                                 banlist.add(datas.get(i).getImageUrl());
                             }

//动态设置banner的高度
                             ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
                             layoutParams.height = MyUtils.getScreenWidth(mContext) * 7 / 15;


                             banner.setImages(banlist);
                             banner.setImageLoader(new GlideImageLoader());
                             banner.setLayoutParams(layoutParams);

//            banner.setLayoutParams(layoutParams);
                             banner.setDelayTime(3000);
                             banner.start();
//            BannerUtils.startBanner(banner, banlist);
                             //轮播图点击事件
                             Banner banner =HomeFragment. this.banner.setOnBannerListener(new OnBannerListener() {
                                 @Override
                                 public void OnBannerClick(int position) {

                                     Lunbo_Bean.DatasBean datasBean = datas.get(position);
                                     Intent intent;
                                     if (datasBean.getLinkType().length() > 0) {
                                         if (Integer.parseInt(datasBean.getLinkType()) == 1) {//外联

                                             if(NetUtil.checkNet(getActivity())){
                                                 intent = new Intent(getActivity(), LunBoActivity.class);

                                                 intent.putExtra("url", datasBean.getImageLink());
                                                 intent.putExtra("title", datasBean.getImageName());
                                                 startActivity(intent);
                                             }else{

                                                 startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                             }

                                         } else {
                                             Log.e("TAG","轮播数据内容：：："+datasBean.getObjIdType());
                                             if (datasBean.getObjIdType().length() > 0) {
                                                 switch (Integer.parseInt(datasBean.getObjIdType())) {
                                                     case 0:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent xx = new Intent(getActivity(), MessagedetailActivity.class);
                                                             xx.putExtra("mes_id", datasBean.getObjId()+"");
                                                             startActivity(xx);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                     case 1:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent tz = new Intent(getActivity(), JiaoDetailActivity.class);
                                                             tz.putExtra("id", datasBean.getObjId()+"");
                                                             startActivity(tz);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                     case 2:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent zx = new Intent(getActivity(), ZixunDitailsActivity.class);
                                                             zx.putExtra("id", datasBean.getObjId()+"");
                                                             startActivity(zx);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                     case 3:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent jd = new Intent(getActivity(), JiaoDetailActivity.class);
                                                             jd.putExtra("id", datasBean.getObjId()+"");
                                                             startActivity(jd);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                     case 4:
                                                         if(NetUtil.checkNet(getActivity())){
                                                             Intent lk = new Intent(getActivity(), LukuangDitailsActivity.class);
                                                             lk.putExtra("id", datasBean.getObjId()+"");
                                                             startActivity(lk);
                                                         }else{

                                                             startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                         }
                                                         break;
                                                 }
                                             }



                                         }


                                     }

                                 }
                             });

                         }
                         homeadapter.notifyDataSetChanged();
                     }
                 }, 0);
                 spring_view.onFinishFreshAndLoad();
             }
         });
        spring_view.setFooter(new DefaultFooter(getActivity()));
        spring_view.setHeader(new DefaultHeader(getActivity()));
    }
    @Override
    public void onClick(View view) {
        int guid = SpUtils.getInt(getActivity(), "guid", 0);
        switch (view.getId()){
            case R.id.text_jiao:
                if(NetUtil.checkNet(getActivity())){
                    Intent jiao=new Intent(getActivity(), JiaodianActivity.class);
                    startActivity(jiao);
                }else{
                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                }

                break;
            case R.id.text_lu:

                if(NetUtil.checkNet(getActivity())){
                    EventBus.getDefault().postSticky(new Evebtbus_fragment(0));
                    Intent lu=new Intent(getActivity(), LuKuangActivity.class);
                    startActivity(lu);
                }else{

                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                }


                break;
            case R.id.life:
                if(NetUtil.checkNet(getActivity())){
                    Intent live=new Intent(getActivity(), LiveActivity.class);
                    startActivity(live);
                }else{

                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                }
                break;
            case R.id.ll_msg:
                if(NetUtil.checkNet(getActivity())){
                    if(guid!=2){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else{
                        Intent intent1 = new Intent(getActivity(), MessageActivity.class);
                        startActivity(intent1);
                    }
                }else{

                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                }
                break;
            case R.id.tv_search:
                if(NetUtil.checkNet(getActivity())){
                    Intent search = new Intent(getActivity(), SearchActivity.class);
                    startActivity(search);
                }else{

                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                }
                break;
            case R.id.imageView:
                if(NetUtil.checkNet(getActivity())){
                    Intent intent1 = new Intent(getActivity(), ZixunDitailsActivity.class);
                    intent1.putExtra("id",zxid);
                    startActivity(intent1);
                }else{

                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                }
                break;
            case R.id.text_diandian:
                if(NetUtil.checkNet(getActivity())){
                    guid = SpUtils.getInt(getActivity(), "guid", 1);
                    if(guid!=2){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }
                    else {
                        Intent dd = new Intent(getActivity(), DianDianActivity.class);
                        startActivity(dd);
                    }
                }else{

                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                }
                break;
            case R.id.img_tu:
                if(NetUtil.checkNet(getActivity())){
                    Intent gy=new Intent(getActivity(),AboutweActivity.class);
                    startActivity(gy);
                }else{

                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                }
                break;
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(GlobalParams.SY_ZX.equals(action)){
                Log.e("TAG","执行===="+action);
                list.clear();
                typeNo=1;
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    lunbo_presenter.getString(SpUtils.getString(getActivity(),"token",null));
                    home_zixun_presenter.getpath(1,SpUtils.getString(getActivity(),"token",null),typeNo);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();


                    list.clear();

                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                    List<ZixunTable> mlist = new ArrayList<>();
                    List<LunBoTable> lunbo = new ArrayList<>();

                    lunbo.addAll(DataSupport.findAll(LunBoTable.class));
                    mlist.addAll(DataSupport.findAll(ZixunTable.class));

                    for(int i=0;i<mlist.size();i++){

                        ZixunTable zixunTable = mlist.get(i);
                        zixun_Bean.DatasBean datasBean = new zixun_Bean.DatasBean();
                        datasBean.setBigImage(zixunTable.bigImage);
                        datasBean.setCreateTime(zixunTable.createTime);
                        datasBean.setDianZanCount(zixunTable.dianZanCount);
                        datasBean.setId(zixunTable.zxid);
                        datasBean.setInfoBody(zixunTable.infoBody);
                        datasBean.setInfoTitle(zixunTable.infoTitle);
                        datasBean.setIsDeleted(zixunTable.isDeleted);
                        datasBean.setShareCount(zixunTable.shareCount);
                        datasBean.setSmallImage(zixunTable.smallImage);
                        datasBean.setStatus(zixunTable.status);
                        datasBean.setUpdateTime(zixunTable.updateTime);
                        datasBean.setViewCount(zixunTable.viewCount);
                        list.add(datasBean);
                    }
                    if(list!=null&&list.size()>0){
                        RequestOptions options = new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL);

                        Glide.with(mContext).load(list.get(0).getBigImage()).
                                apply(options)
                                .into(imageView);
                        zixun_itme.setText(list.get(0).getInfoTitle());
                        zan.setText(list.get(0).getDianZanCount()+"");
                        zxid=list.get(0).getId()+"";
                        list.remove(0);
                    }

                    datas.clear();
                    banlist.clear();

                    for(int j=0;j<lunbo.size();j++){

                        LunBoTable lunBoTable = lunbo.get(j);

                        Lunbo_Bean.DatasBean datasBean = new Lunbo_Bean.DatasBean();
                        datasBean.setId(lunBoTable.loid);
                        datasBean.setImageName(lunBoTable.imageName);
                        datasBean.setImageLink(lunBoTable.imageLink);
                        datasBean.setImageDesc(lunBoTable.imageDesc);
                        datasBean.setImageType(lunBoTable.imageType);
                        datasBean.setIsDeleted(lunBoTable.isDeleted);
                        datasBean.setCreateTime(lunBoTable.createTime);
                        datasBean.setUpdateTime(lunBoTable.updateTime);
                        datasBean.setActiveTime(lunBoTable.activeTime);
                        datasBean.setIsActive(lunBoTable.isActive);
                        datasBean.setImageUrl(lunBoTable.imageUrl);
                        datasBean.setUserId(lunBoTable.userId);
                        datasBean.setObjId(lunBoTable.objId);
                        datasBean.setObjIdType(lunBoTable.objIdType);
                        datasBean.setLinkType(lunBoTable.linkType);

                        datas.add(datasBean);
                    }
                    for (int i=0;i<datas.size();i++){
                        banlist.add(datas.get(i).getImageUrl());
                    }

//动态设置banner的高度
                    ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
                    layoutParams.height = MyUtils.getScreenWidth(mContext) * 7 / 15;


                    banner.setImages(banlist);
                    banner.setImageLoader(new GlideImageLoader());
                    banner.setLayoutParams(layoutParams);

//            banner.setLayoutParams(layoutParams);
                    banner.setDelayTime(3000);
                    banner.start();
//            BannerUtils.startBanner(banner, banlist);
                    //轮播图点击事件
                    Banner banner = HomeFragment.this.banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                            Lunbo_Bean.DatasBean datasBean = datas.get(position);
                            Intent intent;
                            if (datasBean.getLinkType().length() > 0) {
                                if (Integer.parseInt(datasBean.getLinkType()) == 1) {//外联

                                    if(NetUtil.checkNet(getActivity())){
                                        intent = new Intent(getActivity(), LunBoActivity.class);

                                        intent.putExtra("url", datasBean.getImageLink());
                                        intent.putExtra("title", datasBean.getImageName());
                                        startActivity(intent);
                                    }else{

                                        startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                    }

                                } else {
                                    Log.e("TAG","轮播数据内容：：："+datasBean.getObjIdType());
                                    if (datasBean.getObjIdType().length() > 0) {
                                        switch (Integer.parseInt(datasBean.getObjIdType())) {
                                            case 0:
                                                if(NetUtil.checkNet(getActivity())){
                                                    Intent xx = new Intent(getActivity(), MessagedetailActivity.class);
                                                    xx.putExtra("mes_id", datasBean.getObjId()+"");
                                                    startActivity(xx);
                                                }else{

                                                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                }
                                                break;
                                            case 1:
                                                if(NetUtil.checkNet(getActivity())){
                                                    Intent tz = new Intent(getActivity(), JiaoDetailActivity.class);
                                                    tz.putExtra("id", datasBean.getObjId()+"");
                                                    startActivity(tz);
                                                }else{

                                                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                }
                                                break;
                                            case 2:
                                                if(NetUtil.checkNet(getActivity())){
                                                    Intent zx = new Intent(getActivity(), ZixunDitailsActivity.class);
                                                    zx.putExtra("id", datasBean.getObjId()+"");
                                                    startActivity(zx);
                                                }else{

                                                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                }
                                                break;
                                            case 3:
                                                if(NetUtil.checkNet(getActivity())){
                                                    Intent jd = new Intent(getActivity(), JiaoDetailActivity.class);
                                                    jd.putExtra("id", datasBean.getObjId()+"");
                                                    startActivity(jd);
                                                }else{

                                                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                }
                                                break;
                                            case 4:
                                                if(NetUtil.checkNet(getActivity())){
                                                    Intent lk = new Intent(getActivity(), LukuangDitailsActivity.class);
                                                    lk.putExtra("id", datasBean.getObjId()+"");
                                                    startActivity(lk);
                                                }else{

                                                    startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                                }
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    });

                }
                homeadapter.notifyDataSetChanged();

            }

        }
    };


    @Override
    public void onclick(int position) {
        if(NetUtil.checkNet(getActivity())){
            Intent intent = new Intent(getActivity(), ZixunDitailsActivity.class);
            Log.e("TAG","咨询id==="+list.get(position).getId());
            intent.putExtra("id",list.get(position).getId()+"");
            startActivity(intent);
        }else{

            startActivity(new Intent(getActivity(), DisconnectActivity.class));

        }
    }
    List<Lunbo_Bean.DatasBean> datas=new ArrayList<>();
    @Override
    public void getsuccess(Lunbo_Bean lunbo) {
        Log.i("==================",lunbo.getMsg());
        banlist.clear();
        datas.clear();

        DataSupport.deleteAll(LunBoTable.class);

        if(lunbo!=null){
            if(lunbo.getCode().equals("200")){


                datas.addAll(lunbo.getDatas());
                List<LunBoTable> list = new ArrayList<>();
                for(int i=0;i<datas.size();i++){

                    Lunbo_Bean.DatasBean datasBean = datas.get(i);
                    LunBoTable lunBoTable = new LunBoTable();

                    lunBoTable.loid=datasBean.getId();
                    lunBoTable.imageName=datasBean.getImageName();
                    lunBoTable.imageLink=datasBean.getImageLink();
                    lunBoTable.imageDesc=datasBean.getImageDesc();
                    lunBoTable.imageType=datasBean.getImageType();
                    lunBoTable.isDeleted=datasBean.getIsDeleted();
                    lunBoTable.createTime=datasBean.getCreateTime();
                    lunBoTable.updateTime=datasBean.getUpdateTime();
                    lunBoTable.activeTime=datasBean.getActiveTime();
                    lunBoTable.isActive=datasBean.getIsActive();
                    lunBoTable.imageUrl=datasBean.getImageUrl();
                    lunBoTable.userId=datasBean.getUserId();
                    lunBoTable.objIdType=datasBean.getObjIdType();
                    lunBoTable.linkType=datasBean.getLinkType();
                    lunBoTable.objId=datasBean.getObjId();
                    list.add(lunBoTable);

                }
                DataSupport.saveAll(list);
                for (int i=0;i<datas.size();i++){
                    banlist.add(datas.get(i).getImageUrl());
                }
            }
            //动态设置banner的高度
            ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
            layoutParams.height = MyUtils.getScreenWidth(mContext) * 7 / 15;


            banner.setImages(banlist);
            banner.setImageLoader(new GlideImageLoader());
            banner.setLayoutParams(layoutParams);

//            banner.setLayoutParams(layoutParams);
            banner.setDelayTime(3000);
            banner.start();
//            BannerUtils.startBanner(banner, banlist);
            //轮播图点击事件
            Banner banner = this.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    Lunbo_Bean.DatasBean datasBean = datas.get(position);
                    Intent intent;
                    if (datasBean.getLinkType().length() > 0) {
                        if (Integer.parseInt(datasBean.getLinkType()) == 1) {//外联

                            if(NetUtil.checkNet(getActivity())){
                                intent = new Intent(getActivity(), LunBoActivity.class);
                                Log.e("TAG","url=="+datasBean.getImageLink());
                                intent.putExtra("url", datasBean.getImageLink());
                                intent.putExtra("title", datasBean.getImageName());
                                startActivity(intent);
                            }else{

                                startActivity(new Intent(getActivity(), DisconnectActivity.class));

                            }

                        } else {
                            Log.e("TAG","轮播数据内容：：："+datasBean.getObjIdType());
                            if (datasBean.getObjIdType().length() > 0) {
                                switch (Integer.parseInt(datasBean.getObjIdType())) {
                                    case 0:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent xx = new Intent(getActivity(), MessagedetailActivity.class);
                                            xx.putExtra("mes_id", datasBean.getObjId()+"");
                                            startActivity(xx);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                    case 1:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent tz = new Intent(getActivity(), JiaoDetailActivity.class);
                                            tz.putExtra("id", datasBean.getObjId()+"");
                                            startActivity(tz);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                    case 2:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent zx = new Intent(getActivity(), ZixunDitailsActivity.class);
                                            zx.putExtra("id", datasBean.getObjId()+"");
                                            startActivity(zx);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                    case 3:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent jd = new Intent(getActivity(), JiaoDetailActivity.class);
                                            jd.putExtra("id", datasBean.getObjId()+"");
                                            startActivity(jd);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                    case 4:
                                        if(NetUtil.checkNet(getActivity())){
                                            Intent lk = new Intent(getActivity(), LukuangDitailsActivity.class);
                                            lk.putExtra("id", datasBean.getObjId()+"");
                                            startActivity(lk);
                                        }else{

                                            startActivity(new Intent(getActivity(), DisconnectActivity.class));

                                        }
                                        break;
                                }
                            }



                        }


                    }

                }
            });


        }


    }

    private String zxid;

    @Override
    public void getsuccess(zixun_Bean zixun) {
        if(zixun!=null){
            if(zixun.getCode().equals("200")){
                List<zixun_Bean.DatasBean> datas = zixun.getDatas();
                List<ZixunTable> zixunTables = new ArrayList<>();



                for(int i=0;i<datas.size();i++){

                    zixun_Bean.DatasBean datasBean = datas.get(i);
                    ZixunTable zixunTable = new ZixunTable();

                    zixunTable.bigImage=datasBean.getBigImage();
                    zixunTable.createTime=datasBean.getCreateTime();
                    zixunTable.dianZanCount=datasBean.getDianZanCount();
                    zixunTable.infoBody=datasBean.getInfoBody();
                    zixunTable.infoTitle=datasBean.getInfoTitle();
                    zixunTable.isDeleted=datasBean.getIsDeleted();
                    zixunTable.shareCount=datasBean.getShareCount();
                    zixunTable.smallImage=datasBean.getSmallImage();
                    zixunTable.status=datasBean.getStatus();
                    zixunTable.updateTime=datasBean.getUpdateTime();
                    zixunTable.zxid=datasBean.getId();
                    zixunTable.viewCount=datasBean.getViewCount();
                    zixunTables.add(zixunTable);

                }

                    if(typeNo>1){
                        if(datas.size()>0){
                            DataSupport.saveAll(zixunTables);
                            list.addAll(datas);
                        }else{
                            Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        DataSupport.deleteAll(ZixunTable.class);
                        if(datas!=null&&datas.size()>0){
//                            ImageLoader.getInstance().displayImage(datas.get(0).getBigImage(),imageView, ImageLoder.getDefaultOption());

                            RequestOptions options = new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL);

                            Glide.with(mContext).load(datas.get(0).getBigImage()).
                                    apply(options)
                                    .into(imageView);

                            zixun_itme.setText(datas.get(0).getInfoTitle());
                            zan.setText(datas.get(0).getDianZanCount()+"");
                            zxid=datas.get(0).getId()+"";
                            datas.remove(0);
                            list.addAll(datas);
                        }
                        DataSupport.saveAll(zixunTables);
                }

                homeadapter.notifyDataSetChanged();
            }
        }
        }
    @Override
    public void onDestroy() {
        super.onDestroy();
        lunbo_presenter.getkong();
        home_zixun_presenter.kong();
        getActivity().unregisterReceiver(broadcastReceiver);
    }
}
