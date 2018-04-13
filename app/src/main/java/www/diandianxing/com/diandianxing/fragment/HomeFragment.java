package www.diandianxing.com.diandianxing.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.DianDianActivity;
import www.diandianxing.com.diandianxing.LiveActivity;
import www.diandianxing.com.diandianxing.MsgItmeActivity;
import www.diandianxing.com.diandianxing.SearchActivity;
import www.diandianxing.com.diandianxing.ZixunDitailsActivity;
import www.diandianxing.com.diandianxing.adapter.Homeadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaodianActivity;
import www.diandianxing.com.diandianxing.fragment.mainfragment.LuKuangActivity;
import www.diandianxing.com.diandianxing.interfase.RecyGetonclick;
import www.diandianxing.com.diandianxing.set.AboutweActivity;
import www.diandianxing.com.diandianxing.util.BannerUtils;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, RecyGetonclick {

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
        //获取id
        banner = contentView.findViewById(R.id.banner);

        //动态设置banner的高度
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.height = MyUtils.getScreenWidth(mContext) * 7 / 15;
        banner.setLayoutParams(layoutParams);
        List<String> banlist = new ArrayList<>();
        banlist.add("http://h.hiphotos.baidu.com/zhidao/pic/item/c2fdfc039245d688bb61de94a2c27d1ed21b249a.jpg");
        banlist.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1301/25/c0/17712320_1359096063354.jpg");
        banlist.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1212/04/c1/16339958_1354613158701.jpg");
        BannerUtils.startBanner(banner, banlist);
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
        Homeadapter homeadapter=new Homeadapter(getActivity());
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


                     }
                 }, 5000);
                 spring_view.onFinishFreshAndLoad();
             }
             //上拉加载
             @Override
             public void onLoadmore() {
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {


                     }
                 }, 5000);
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

                Intent dd = new Intent(getActivity(), DianDianActivity.class);
                startActivity(dd);

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
}
