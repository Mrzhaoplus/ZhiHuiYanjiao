package zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

public class LuKuangActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back;
    private TextView text_zixun;
    private LinearLayout liner1;
    private TextView text_lukuang;
    private LinearLayout liner2;
    private FrameLayout fl;
    private Fragment currfit;
    ZiXunFragment ziXunFragment;
   LuKuangFragment luKuangFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_lu_kuang);
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        text_zixun = (TextView) findViewById(R.id.text_zixun);
        liner1 = (LinearLayout) findViewById(R.id.liner1);
        text_lukuang = (TextView) findViewById(R.id.text_lukuang);
        liner2 = (LinearLayout) findViewById(R.id.liner2);
        fl = (FrameLayout) findViewById(R.id.fl);
       //点击事件
        img_back.setOnClickListener(this);
        liner1.setOnClickListener(this);
        liner2.setOnClickListener(this);
        //默认显示页面
        if(ziXunFragment==null){
            ziXunFragment = new ZiXunFragment();
        }
        AddFragment(ziXunFragment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.liner1:
                liner1.setBackgroundColor(Color.BLUE);
                text_zixun.setTextColor(Color.WHITE);
                liner2.setBackgroundColor(Color.WHITE);
                text_lukuang.setTextColor(Color.BLACK);
                //跳转页面
                if(ziXunFragment==null){
                    ziXunFragment = new ZiXunFragment();
                }
                AddFragment(ziXunFragment);
                break;
            case R.id.liner2:
                liner1.setBackgroundColor(Color.WHITE);
                text_zixun.setTextColor(Color.BLACK);
                liner2.setBackgroundColor(Color.BLUE);
                text_lukuang.setTextColor(Color.WHITE);
                //跳转页面
                if(luKuangFragment==null){
                    luKuangFragment = new LuKuangFragment();
                }
                AddFragment(luKuangFragment);
                break;
        }
    }

//动态添加工具类
    public void AddFragment(Fragment f){

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if(currfit !=null){
            fragmentTransaction.hide(currfit);

        }
        if(!f.isAdded()){
            fragmentTransaction.add(R.id.fl,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;


    }
}
