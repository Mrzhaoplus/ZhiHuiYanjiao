package zhyj.dqjt.com.zhihuiyanjiao.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zhyj.dqjt.com.zhihuiyanjiao.MyCollectionActivity;
import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment.MydynamicActivity;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment.MyFansiActivity;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment.MyFllowActivity;
import zhyj.dqjt.com.zhihuiyanjiao.SignActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{
    public View rootView;
    public TextView text_day;
    public ImageView imageView2;
    public TextView guan_num;
    public LinearLayout text_guanzhu;
    public TextView fen_num;
    public LinearLayout text_fensi;
    public TextView dong_num;
    public LinearLayout text_dongtai;
    public TextView collect_num;
    public LinearLayout text_collect;
    public TextView text_qiandao;
    public TextView my_car;
    public RelativeLayout real_car;
    public TextView text_about;
    public RelativeLayout about_we;
    public TextView text_suggest;
    public RelativeLayout real_suggest;
    public TextView text_kefu;
    public RelativeLayout real_kefu;
    public TextView text_yaoqing;
    public RelativeLayout real_yaoqing;
    private ImageView iv_grtx;

    @Override
    protected int setContentView() {

        return R.layout.fragment_mine;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        this.text_day = (TextView) contentView.findViewById(R.id.text_day);
        this.imageView2 = (ImageView) contentView.findViewById(R.id.imageView2);
        this.guan_num = (TextView) contentView.findViewById(R.id.guan_num);
        this.text_guanzhu = (LinearLayout) contentView.findViewById(R.id.text_guanzhu);
        this.fen_num = (TextView) contentView.findViewById(R.id.fen_num);
        this.text_fensi = (LinearLayout) contentView.findViewById(R.id.text_fensi);
        this.dong_num = (TextView) contentView.findViewById(R.id.dong_num);
        this.text_dongtai = (LinearLayout) contentView.findViewById(R.id.text_dongtai);
        this.collect_num = (TextView) contentView.findViewById(R.id.collect_num);
        this.text_collect = (LinearLayout) contentView.findViewById(R.id.text_collect);
        this.text_qiandao = (TextView) contentView.findViewById(R.id.text_qiandao);
        this.my_car = (TextView) contentView.findViewById(R.id.my_car);
        this.real_car = (RelativeLayout) contentView.findViewById(R.id.real_car);
        this.text_about = (TextView) contentView.findViewById(R.id.text_about);
        this.about_we = (RelativeLayout) contentView.findViewById(R.id.about_we);
        this.text_suggest = (TextView) contentView.findViewById(R.id.text_suggest);
        this.real_suggest = (RelativeLayout) contentView.findViewById(R.id.real_suggest);
        this.text_kefu = (TextView) contentView.findViewById(R.id.text_kefu);
        this.real_kefu = (RelativeLayout) contentView.findViewById(R.id.real_kefu);
        this.text_yaoqing = (TextView) contentView.findViewById(R.id.text_yaoqing);
        this.real_yaoqing = (RelativeLayout) contentView.findViewById(R.id.real_yaoqing);
        iv_grtx=contentView.findViewById(R.id.iv_grtx);
        text_guanzhu.setOnClickListener(this);//设置监听
        text_fensi.setOnClickListener(this);
        text_dongtai.setOnClickListener(this);
        text_collect.setOnClickListener(this);
        text_qiandao.setOnClickListener(this);
        iv_grtx.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

         switch (view.getId()){
             case R.id.text_guanzhu://关注
                 Intent intent=new Intent(getActivity(), MyFllowActivity.class);
                 startActivity(intent);
                 break;

             case R.id.text_fensi://粉丝
                 Intent intent1=new Intent(getActivity(), MyFansiActivity.class);
                 startActivity(intent1);
                 break;
             case R.id.text_dongtai:
                  Intent intent2=new Intent(getActivity(), MydynamicActivity.class);
                 startActivity(intent2);
                 break;
             case R.id.text_collect:
                 Intent intent3=new Intent(getActivity(), MyCollectionActivity.class);
                 startActivity(intent3);
                 break;
             case R.id.text_qiandao:
                 Intent intent4=new Intent(getActivity(), SignActivity.class);
                 startActivity(intent4);
                 break;
             case R.id.iv_grtx:
                 Intent intent5=new Intent(getActivity(), MydynamicActivity.class);
                 startActivity(intent5);
                 break;
         }

    }
}
