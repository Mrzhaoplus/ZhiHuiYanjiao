package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.ShujuBean.GZ_person_Bean;
import www.diandianxing.com.diandianxing.adapter.Myfollowadapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.interfase.GZ_person_presenter_interfase;
import www.diandianxing.com.diandianxing.presenter.GZ_person_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyContants;

/**
 * Created by ASUS on 2018/3/20.
 */

public class MyFllowActivity extends BaseActivity implements View.OnClickListener, GZ_person_presenter_interfase {

    private ImageView include_back;
    private RecyclerView recycle_guan;
    private TextView include_title;
    private SpringView springView;
    private int pageNo=1;
    private GZ_person_presenter gz_person_presenter = new GZ_person_presenter(this);
    List<GZ_person_Bean.DatasBean>list=new ArrayList<>();
    private Myfollowadapter myfollowadapter;
    private TextView perpho;

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
        perpho = (TextView) findViewById(R.id.person_pho);
        springView = (SpringView) findViewById(R.id.spring_view);
        //引用
        gz_person_presenter.getpath(pageNo, Api.userid);
        //设置模式
        springView.setType(SpringView.Type.FOLLOW);
        //设置是适配器
        myfollowadapter = new Myfollowadapter(this,list);
         recycle_guan.setLayoutManager(new LinearLayoutManager(this));
        recycle_guan.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycle_guan.setNestedScrollingEnabled(false);
        recycle_guan.setAdapter(myfollowadapter);
        myfollowadapter.SetonItemClicklistener(new Myfollowadapter.OnItemClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                    //根据position 跳转道不同用户的主页
                Intent  intent=new Intent(MyFllowActivity.this,MydynamicActivity.class);
                  intent.putExtra("uid",list.get(position).getUid()+"");
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
                       list.clear();
                       pageNo=1;
                       gz_person_presenter.getpath(pageNo, Api.userid);
                       myfollowadapter.notifyDataSetChanged();
                   }
               }, 0);
               springView.onFinishFreshAndLoad();
           }

           @Override
           public void onLoadmore() {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       pageNo++;
                       gz_person_presenter.getpath(pageNo, Api.userid);
                       myfollowadapter.notifyDataSetChanged();
                   }
               }, 0);
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

    @Override
    public void getsuccess(GZ_person_Bean guanzhu) {
          if(guanzhu.getCode().equals("200")){
              List<GZ_person_Bean.DatasBean> dates = guanzhu.getDatas();
              if(pageNo>1){
                   if(dates.size()>0){
                     list.addAll(dates);
                   }else{
                       Toast.makeText(MyFllowActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();
                   }
               }else{
                  list.addAll(dates);
              }
              perpho.setText("您关注了"+dates.size()+"人");
              myfollowadapter.notifyDataSetChanged();
          }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gz_person_presenter.getkong();
    }
}
