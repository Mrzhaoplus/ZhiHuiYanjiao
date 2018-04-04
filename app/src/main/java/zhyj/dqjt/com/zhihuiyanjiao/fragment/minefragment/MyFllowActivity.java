package zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.Myfollowadapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;
import zhyj.dqjt.com.zhihuiyanjiao.util.DividerItemDecoration;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

/**
 * Created by ASUS on 2018/3/20.
 */

public class MyFllowActivity extends BaseActivity implements View.OnClickListener{

    private ImageView include_back;
    private RecyclerView recycle_guan;
    private TextView include_title;
    private SpringView springView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_mineguanzhu);
        initView();
    }

    private void initView() {
        include_back = (ImageView) findViewById(R.id.include_back);
        recycle_guan = (RecyclerView) findViewById(R.id.recycle_guan);
        include_title = (TextView) findViewById(R.id.include_title);
        springView = (SpringView) findViewById(R.id.spring_view);
        //设置模式
        springView.setType(SpringView.Type.FOLLOW);
        //设置是适配器
        Myfollowadapter myfollowadapter =new Myfollowadapter(this);
         recycle_guan.setLayoutManager(new LinearLayoutManager(this));
        recycle_guan.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recycle_guan.setNestedScrollingEnabled(false);
        recycle_guan.setAdapter(myfollowadapter);
        myfollowadapter.SetonItemClicklistener(new Myfollowadapter.OnItemClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                    //根据position 跳转道不同用户的主页
                Intent  intent=new Intent(MyFllowActivity.this,MydynamicActivity.class);
                 startActivity(intent);
            }
        });

        include_back.setOnClickListener(this);


         //设置监听
       springView.setListener(new SpringView.OnFreshListener() {
           @Override
           public void onRefresh() {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                   }
               }, 5000);
               springView.onFinishFreshAndLoad();
           }

           @Override
           public void onLoadmore() {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {


                   }
               }, 5000);
               springView.onFinishFreshAndLoad();
           }
       });
        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));
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
