package zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.MyFansadapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;
import zhyj.dqjt.com.zhihuiyanjiao.util.DividerItemDecoration;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

/**
 * Created by ASUS on 2018/3/21.
 */

public class MyFansiActivity extends BaseActivity implements View.OnClickListener{

    private ImageView include_back;
    private TextView include_title;
    private RecyclerView recycle_guan;
    private LinearLayout liner;
    private SpringView spring_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_mineffensi);
        initView();
    }

    private void initView() {
        include_back = (ImageView) findViewById(R.id.include_back);
        include_title = (TextView) findViewById(R.id.include_title);
        recycle_guan = (RecyclerView) findViewById(R.id.recycle_guan);
        liner = (LinearLayout) findViewById(R.id.liner);
        spring_view = (SpringView) findViewById(R.id.spring_view);
        include_title.setText("我的粉丝");
        include_back.setOnClickListener(this);
        //设置适配器
        //设置模式
        spring_view.setType(SpringView.Type.FOLLOW);
        //设置是适配器
        MyFansadapter myFansadapter =new MyFansadapter(this);
        recycle_guan.setLayoutManager(new LinearLayoutManager(this));
        recycle_guan.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recycle_guan.setNestedScrollingEnabled(false);
        recycle_guan.setAdapter(myFansadapter);
         //点击跳转
           myFansadapter.SetonItemClicklistener(new MyFansadapter.OnItemClickLister() {
               @Override
               public void onItemClick(View view, int position) {
                   Intent intent=new Intent(MyFansiActivity.this,MydynamicActivity.class);
                    startActivity(intent);
               }
           });

        //设置监听
        spring_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                    }
                }, 5000);
                spring_view.onFinishFreshAndLoad();
            }

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

        spring_view.setFooter(new DefaultFooter(this));
        spring_view.setHeader(new DefaultHeader(this));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.include_back:
                finish();
                break;
        }
    }
}
