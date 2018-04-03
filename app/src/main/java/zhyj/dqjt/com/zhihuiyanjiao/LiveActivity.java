package zhyj.dqjt.com.zhihuiyanjiao;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment.ChowuFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment.ErshouFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment.FangwuFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment.GuanzhuFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment.ZhaoLingFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment.ZhaopinFragment;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

public class LiveActivity extends AppCompatActivity {
    private ImageView img_back;
    private ViewPager vp_pager;
    private TabLayout tab;
    private List<String> name = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_life);
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        vp_pager = (ViewPager) findViewById(R.id.vp_pager);
        tab = (TabLayout) findViewById(R.id.tab);
        //返回点击事件
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //添加数据
        name.add("关注");
        name.add("宠物天地");
        name.add("失物招领");
        name.add("二手市场");
        name.add("房屋租售");
        name.add("求职招聘");
        vp_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return name.get(position);
            }
            //tab套数据
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        fragment=new GuanzhuFragment();
                        break;
                    case 1:
                        fragment=new ChowuFragment();
                        break;
                    case 2:
                        fragment=new ZhaoLingFragment();
                        break;
                    case 3:
                        fragment=new ErshouFragment();
                        break;
                    case 4:
                        fragment=new FangwuFragment();
                        break;
                    case 5:
                        fragment=new ZhaopinFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return name.size();
            }
        });
        tab.setupWithViewPager(vp_pager);
    }
}
