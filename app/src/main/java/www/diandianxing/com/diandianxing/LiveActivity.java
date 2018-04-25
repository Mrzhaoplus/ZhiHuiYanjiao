package www.diandianxing.com.diandianxing;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.ShujuBean.Live_Bean;
import www.diandianxing.com.diandianxing.fragment.mainfragment.GuanzhuFragment;
import www.diandianxing.com.diandianxing.fragment.mainfragment.MeiJiaidianFragment;
import www.diandianxing.com.diandianxing.interfase.Live_Presenter_interfase;
import www.diandianxing.com.diandianxing.presenter.Live_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.MyContants;

public class LiveActivity extends AppCompatActivity implements Live_Presenter_interfase {
    private ImageView img_back;
    private ViewPager vp_pager;
    private TabLayout tab;

    private Live_presenter live_presenter;

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

        //引用
        live_presenter = new Live_presenter(this);
        live_presenter.getpath(Api.token);
    }
    @Override
    public void getsuccess(final Live_Bean live_bean) {
           final List<String> name=new ArrayList<>();
           name.add("关注");

             final List<Live_Bean.DatasBean> datas = live_bean.getDatas();
            for (int i=0;i<datas.size();i++){
            name.add(datas.get(i).getContent());
        }

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
                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
                            break;
                        case 2:
                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
                            break;
                        case 3:
                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
                            break;
                        case 4:
                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
                            break;
                        case 5:
                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
                            break;
                        case 6:
                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        live_presenter.kong();
    }
}
