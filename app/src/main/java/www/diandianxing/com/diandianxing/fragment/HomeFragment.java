package www.diandianxing.com.diandianxing.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.DianDianActivity;
import www.diandianxing.com.diandianxing.LiveActivity;
import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.MsgItmeActivity;
import www.diandianxing.com.diandianxing.SearchActivity;
import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.ZixunDitailsActivity;
import www.diandianxing.com.diandianxing.adapter.Homeadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.ShujuBean.Lunbo_Bean;
import www.diandianxing.com.diandianxing.bean.Evebtbus_fragment;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaodianActivity;
import www.diandianxing.com.diandianxing.fragment.mainfragment.LuKuangActivity;
import www.diandianxing.com.diandianxing.interfase.Zixun_presenterinterfase;
import www.diandianxing.com.diandianxing.interfase.LunPresenter_interfase;
import www.diandianxing.com.diandianxing.interfase.RecyGetonclick;
import www.diandianxing.com.diandianxing.presenter.Zixun_presenter;
import www.diandianxing.com.diandianxing.presenter.Lunbo_presenter;
import www.diandianxing.com.diandianxing.set.AboutweActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BannerUtils;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.util.ImageLoder;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.SpUtils;

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
    public RelativeLayout rl_top;
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
    private Lunbo_presenter lunbo_presenter;
    private Zixun_presenter home_zixun_presenter = new Zixun_presenter(this);
    private int typeNo=1;
    private Homeadapter homeadapter;

    @Override
    protected int setContentView() {
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
        this.rl_top = (RelativeLayout) rootView.findViewById(R.id.rl_top);
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
        //获取引用
        lunbo_presenter = new Lunbo_presenter(this);
        lunbo_presenter.getString(Api.token);
        home_zixun_presenter.getpath(1,Api.token,typeNo);
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

        spring_view.setType(SpringView.Type.FOLLOW);

         spring_view.setListener(new SpringView.OnFreshListener() {
             //下拉树新
             @Override
             public void onRefresh() {
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         list.clear();
                         typeNo=1;
                         home_zixun_presenter.getpath(1, Api.token,typeNo);
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
                         home_zixun_presenter.getpath(1, Api.token,typeNo);
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
        switch (view.getId()){
            case R.id.text_jiao:
                Intent jiao=new Intent(getActivity(), JiaodianActivity.class);
                startActivity(jiao);
                break;
            case R.id.text_lu:
                EventBus.getDefault().postSticky(new Evebtbus_fragment(0));
                Intent lu=new Intent(getActivity(), LuKuangActivity.class);
                startActivity(lu);
                break;
            case R.id.life:
                Intent live=new Intent(getActivity(), LiveActivity.class);
                startActivity(live);
                break;
            case R.id.ll_msg:
                Intent intent = new Intent(getActivity(), MsgItmeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_search:
                Intent search = new Intent(getActivity(), SearchActivity.class);
                startActivity(search);
                break;
            case R.id.imageView:
                Intent intent1 = new Intent(getActivity(), ZixunDitailsActivity.class);
                startActivity(intent1);
                break;
            case R.id.text_diandian:

                int guid = SpUtils.getInt(getActivity(), "guid", 1);
                if(guid!=2){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
                else {
                    Intent dd = new Intent(getActivity(), DianDianActivity.class);
                    startActivity(dd);
                }
                break;
            case R.id.img_tu:
                Intent gy=new Intent(getActivity(),AboutweActivity.class);
                startActivity(gy);
                break;
        }
    }

    @Override
    public void onclick(int position) {
        Intent intent = new Intent(getActivity(), ZixunDitailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void getsuccess(Lunbo_Bean lunbo) {
        if(lunbo.getCode().equals("200")){
            List<Lunbo_Bean.DatasBean> datas = lunbo.getDatas();
            for (int i=0;i<datas.size();i++){
                banlist.add(datas.get(i).getImageUrl());
            }
        }
        //动态设置banner的高度
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.height = MyUtils.getScreenWidth(mContext) * 7 / 15;
        banner.setLayoutParams(layoutParams);
        banner.setDelayTime(3000);

        BannerUtils.startBanner(banner, banlist);
        //轮播图点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startActivity(new Intent(getActivity(),ZixunDitailsActivity.class));
            }
        });
    }

    @Override
    public void getsuccess(zixun_Bean zixun) {
        if(zixun.getCode().equals("200")){
            List<zixun_Bean.DatasBean> datas = zixun.getDatas();
            ImageLoader.getInstance().displayImage(datas.get(0).getBigImage(),imageView, ImageLoder.getDefaultOption());
            zixun_itme.setText(datas.get(0).getInfoTitle());
            zan.setText(datas.get(0).getDianZanCount()+"");
            if(typeNo>1){
                if(datas.size()>0){
                    list.addAll(datas);
                }else{
                    Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();
                }
            }else{
                list.addAll(datas);
            }
            homeadapter.notifyDataSetChanged();
        }
        }
    @Override
    public void onDestroy() {
        super.onDestroy();
        lunbo_presenter.getkong();
        home_zixun_presenter.kong();
    }
}
