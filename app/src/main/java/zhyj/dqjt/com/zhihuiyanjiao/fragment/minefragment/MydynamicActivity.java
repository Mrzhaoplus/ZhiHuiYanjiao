package zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.zackratos.ultimatebar.UltimateBar;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;

/**
 * Created by ASUS on 2018/3/21.
 * 他人主页
 */

public class MydynamicActivity extends BaseActivity implements View.OnClickListener {
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
    private FrameLayout fram_layout;
    Fragment currentf;//当前fragment
     //动态Fragment
     DongtaiFragment dongtaifragment;
     TieziFragment tiezifragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        setContentView(R.layout.activity_minedynamic);
        initView();
    }

    private void initView() {
        img_tou = (ImageView) findViewById(R.id.img_tou);
        text_deng = (TextView) findViewById(R.id.text_deng);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text_name = (TextView) findViewById(R.id.text_name);
        text_sex = (ImageView) findViewById(R.id.text_sex);
        guan_num = (TextView) findViewById(R.id.guan_num);
        text_guanzhu = (LinearLayout) findViewById(R.id.text_guanzhu);
        fen_num = (TextView) findViewById(R.id.fen_num);
        text_fensi = (LinearLayout) findViewById(R.id.text_fensi);
        collect_num = (TextView) findViewById(R.id.collect_num);
        text_collect = (LinearLayout) findViewById(R.id.text_collect);
        text_pin = (TextView) findViewById(R.id.text_pin);
        v1 = (View) findViewById(R.id.v1);
        liner_tiezi = (LinearLayout) findViewById(R.id.liner_tiezi);
        text_zan = (TextView) findViewById(R.id.text_zan);
        v2 = (View) findViewById(R.id.v2);
        liner_dongtai = (LinearLayout) findViewById(R.id.liner_dongtai);
        fram_layout = (FrameLayout) findViewById(R.id.fram_layout);
          /*
           设置监听
         */
        liner_dongtai.setOnClickListener(this);
        liner_tiezi.setOnClickListener(this);
            dongtaifragment=new DongtaiFragment();
        addFragment(dongtaifragment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.liner_tiezi://帖子
                text_pin.setTextColor(getResources().getColor(R.color.text_orage));
                v1.setVisibility(View.VISIBLE);
                text_zan.setTextColor(getResources().getColor(R.color.black_san));
                v2.setVisibility(View.INVISIBLE);
                if(tiezifragment==null){
                    tiezifragment=new TieziFragment();
                }
                addFragment(tiezifragment);
                break;

            case R.id.liner_dongtai://动态
                text_zan.setTextColor(getResources().getColor(R.color.text_orage));
                v2.setVisibility(View.VISIBLE);
                text_pin.setTextColor(getResources().getColor(R.color.black_san));
                v1.setVisibility(View.INVISIBLE);
                if(dongtaifragment==null){
                    dongtaifragment=new DongtaiFragment();
                }
                addFragment(dongtaifragment);
                break;
        }
    }

       //添加fragment

     public void addFragment(Fragment f){

         // 第一步：得到fragment管理类
         android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
         // 第二步：开启一个事务
         FragmentTransaction transaction = manager.beginTransaction();

         if (currentf != null) {
             //每次把前一个fragment给隐藏了
             transaction.hide(currentf);
         }
         //isAdded:判断当前的fragment对象是否被加载过
         if (!f.isAdded()) {
             // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
             transaction.add(R.id.fram_layout, f);
         }
         //显示当前的fragment
         transaction.show(f);
         // 第四步：提交
         transaction.commit();
         currentf = f;

     }
}
