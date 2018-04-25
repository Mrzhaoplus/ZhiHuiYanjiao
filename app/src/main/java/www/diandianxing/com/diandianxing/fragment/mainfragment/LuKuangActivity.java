package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import www.diandianxing.com.diandianxing.bean.Evebtbus_fragment;
import www.diandianxing.com.diandianxing.bean.FragEventBug;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;

public class LuKuangActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back;
    private TextView text_zixun;
    private RelativeLayout liner1;
    private TextView text_lukuang;
    private RelativeLayout liner2;
    private FrameLayout fl;
    private Fragment currfit;
    ZiXunFragment ziXunFragment;
    LuKuangFragment luKuangFragment;
    private View zixun_view;
    private View lu_view;
    private boolean flag=true;
    private String content;
    private int typeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_lu_kuang);
        initView();
    }

    private void initView() {
        if(flag==true){
            //注册
            EventBus.getDefault().register(this);
            flag=false;
        }
        img_back = (ImageView) findViewById(R.id.img_back);
        text_zixun = (TextView) findViewById(R.id.text_zixun);
        liner1 = (RelativeLayout) findViewById(R.id.liner1);
        text_lukuang = (TextView) findViewById(R.id.text_lukuang);
        liner2 = (RelativeLayout) findViewById(R.id.liner2);
        fl = (FrameLayout) findViewById(R.id.fl);
        zixun_view = (View) findViewById(R.id.zixun_view);
        lu_view = (View) findViewById(R.id.lu_view);
        //点击事件
        img_back.setOnClickListener(this);
        liner1.setOnClickListener(this);
        liner2.setOnClickListener(this);

        //默认显示页面
        if (ziXunFragment == null) {
            if(typeid==1){
                ziXunFragment = new ZiXunFragment(content);
            }else{
                ziXunFragment = new ZiXunFragment();
            }

        }
        AddFragment(ziXunFragment);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Evebtbus_fragment eveen){
        typeid = eveen.getTypeid();
        if(typeid==1){
            Intent intent = getIntent();
            content = intent.getStringExtra("content");
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.liner1:
                text_zixun.setTextColor(getResources().getColor(R.color.guancolor));
                text_lukuang.setTextColor(Color.BLACK);
                zixun_view.setVisibility(View.VISIBLE);
                lu_view.setVisibility(View.INVISIBLE);
                //跳转页面
                if (ziXunFragment == null) {
                    if(typeid==1){
                        ziXunFragment = new ZiXunFragment(content);
                    }else{
                        ziXunFragment = new ZiXunFragment();
                    }

                }
                AddFragment(ziXunFragment);
                break;
            case R.id.liner2:
                text_zixun.setTextColor(Color.BLACK);
                text_lukuang.setTextColor(getResources().getColor(R.color.guancolor));
                zixun_view.setVisibility(View.INVISIBLE);
                lu_view.setVisibility(View.VISIBLE);
                //跳转页面
                if (luKuangFragment == null) {
                    if(typeid==1){
                        luKuangFragment = new LuKuangFragment(content);
                    }else{
                        luKuangFragment = new LuKuangFragment();
                    }

                }
                AddFragment(luKuangFragment);
                break;
        }
    }

    //动态添加工具类
    public void AddFragment(Fragment f) {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (currfit != null) {
            fragmentTransaction.hide(currfit);

        }
        if (!f.isAdded()) {
            fragmentTransaction.add(R.id.fl, f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit = f;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
