package zhyj.dqjt.com.zhihuiyanjiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.zackratos.ultimatebar.UltimateBar;

import zhyj.dqjt.com.zhihuiyanjiao.fragment.HomeFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.MessageFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.MineFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.PaikewFragment;
import zhyj.dqjt.com.zhihuiyanjiao.util.AddPopwindow;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

/*
  zhuye
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout fl;
    private RadioButton rb_home;
    private RadioButton rb_find;
    private RadioButton rb_message;
    private RadioButton rb_mine;
    private RadioGroup rgp;
    private LinearLayout activity_main;
    private Fragment currentf;
    private HomeFragment homeFragment;
    private PaikewFragment findFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private ImageView frag_add;

    //哈喽

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        MyContants.windows(this);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        addFragments(homeFragment);
        initView();

    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_find = (RadioButton) findViewById(R.id.rb_find);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        rgp = (RadioGroup) findViewById(R.id.rgp);
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
        frag_add = (ImageView) findViewById(R.id.frag_add);
        rb_home.setOnClickListener(this);
        rb_find.setOnClickListener(this);
        rb_message.setOnClickListener(this);
        rb_mine.setOnClickListener(this);
        frag_add.setOnClickListener(this);
    }

    private void addFragments(Fragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (currentf != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(currentf);
        }
        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.fl, f);
        }
        //显示当前的fragment
        transaction.show(f);
        // 第四步：提交
        transaction.commit();
        currentf = f;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                addFragments(homeFragment);
                break;
            case R.id.rb_find:
                if (findFragment == null) {
                    findFragment = new PaikewFragment();
                }
                addFragments(findFragment);
                break;
            case R.id.rb_message:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                }
                addFragments(messageFragment);
                break;
            case R.id.rb_mine:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
                addFragments(mineFragment);
                break;
            case R.id.frag_add:
                AddPopwindow popWindow = new AddPopwindow(MainActivity.this);
                popWindow.showMoreWindow(v);
                //加号
                break;
        }
    }


}



