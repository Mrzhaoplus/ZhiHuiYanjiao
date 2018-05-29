package www.diandianxing.com.diandianxing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.ShujuBean.Live_Bean;
import www.diandianxing.com.diandianxing.adapter.MyPagerAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.fragment.mainfragment.GuanzhuFragment;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaodianActivity;
import www.diandianxing.com.diandianxing.fragment.mainfragment.MeiJiaidianFragment;
import www.diandianxing.com.diandianxing.fragment.mainfragment.TuijianFragment;
import www.diandianxing.com.diandianxing.interfase.Live_Presenter_interfase;
import www.diandianxing.com.diandianxing.presenter.Live_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;

public class LiveActivity extends BaseActivity implements Live_Presenter_interfase {
    private ImageView img_back;
    private ViewPager vp_pager;
    private TabLayout tab;

    private Live_presenter live_presenter;
    List<String> name;
    List<Live_Bean.DatasBean> datas;

    List<BaseFragment> list= new ArrayList<>();
    MyPageFragment myPageFragment;

    TuijianFragment tuijianfragment;
    private ImageView img_question;

    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_life);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.SHFW);
        registerReceiver(broadcastReceiver,intentFilter);
        initView();
    }
    private void initView() {
        pos=getIntent().getIntExtra("pos",-1);
        img_back = (ImageView) findViewById(R.id.img_back);
        vp_pager = (ViewPager) findViewById(R.id.vp_pager);
        img_question= (ImageView) findViewById(R.id.img_question);
        tab = (TabLayout) findViewById(R.id.tab);
        tuijianfragment = new TuijianFragment();
        //返回点击事件
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int guid = SpUtils.getInt(LiveActivity.this, "guid", 0);
                if(guid!=2){
                    startActivity(new Intent(LiveActivity.this,LoginActivity.class));
                }else{
                    Intent intent = new Intent(LiveActivity.this,ReleaseFocusActivity.class);
                    intent.putExtra("type",1);
                    startActivity(intent);
                }
            }
        });
        //引用
        live_presenter = new Live_presenter(this);
        live_presenter.getpath(SpUtils.getString(this,"token",null));


    }
    @Override
    public void getsuccess(final Live_Bean live_bean) {
         name=new ArrayList<>();
         name.add("关注");
         name.add("推荐");
            datas  = live_bean.getDatas();

            for (int i=0;i<datas.size();i++){
            name.add(datas.get(i).getContent());

        }

        for(int j=0;j<name.size();j++){
            if(j==0){
                list.add(new GuanzhuFragment());
            }else if(j==1){
                list.add(tuijianfragment);
            }else{
                list.add(new MeiJiaidianFragment(datas.get(j-2).getId()));
            }
        }

        myPageFragment = new MyPageFragment(getSupportFragmentManager());

        vp_pager.setAdapter(myPageFragment);
        vp_pager.setOffscreenPageLimit(name.size());


//            vp_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//                @Override
//                public CharSequence getPageTitle(int position) {
//                    return name.get(position);
//                }
//                //tab套数据
//                @Override
//                public Fragment getItem(int position) {
//                    Fragment fragment=null;
//                    if(position==0){
//                        fragment=new GuanzhuFragment();
//                    }else {
//                        fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
//                    }
////                    switch (position){
////                        case 0:
////                            fragment=new GuanzhuFragment();
////                            break;
////                        case 1:
////                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
////                            break;
////                        case 2:
////                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
////                            break;
////                        case 3:
////                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
////                            break;
////                        case 4:
////                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
////                            break;
////                        case 5:
////                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
////                            break;
////                        case 6:
////                            fragment = new MeiJiaidianFragment(datas.get(position-1).getId());
////                            break;
////                    }
//                    return fragment;
//                }
//                @Override
//                public int getCount() {
//                    return name.size();
//                }
//            });
            tab.setupWithViewPager(vp_pager);

        if(pos>0){
            vp_pager.setCurrentItem(pos+2);
            tab.getTabAt(pos+2).select();
        }
        }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(GlobalParams.SHFW.equals(action)){

                pos=intent.getIntExtra("pos",0);

                vp_pager.setCurrentItem(pos+2);
                tab.getTabAt(pos+2).select();

            }

        }
    };



    class MyPageFragment extends FragmentPagerAdapter{

        public MyPageFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return name.get(position);
        }
        //tab套数据
        @Override
        public Fragment getItem(int position) {

            return list.get(position);
        }
        @Override
        public int getCount() {
            return name.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        live_presenter.kong();
        unregisterReceiver(broadcastReceiver);
    }
}
