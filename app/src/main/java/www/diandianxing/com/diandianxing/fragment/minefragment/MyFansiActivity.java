package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.ShujuBean.Fensi_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.User_guanzhu_Bean;
import www.diandianxing.com.diandianxing.adapter.MyFansadapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.interfase.FenSi_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.GZ_state;
import www.diandianxing.com.diandianxing.interfase.Userguanzhu_presenter_interfase;
import www.diandianxing.com.diandianxing.presenter.Fensi_presenter;
import www.diandianxing.com.diandianxing.presenter.User_Guanzhu_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * Created by ASUS on 2018/3/21.
 */

public class MyFansiActivity extends BaseActivity implements View.OnClickListener, FenSi_presenter_interfase, GZ_state, Userguanzhu_presenter_interfase {

    private ImageView include_back;
    private TextView include_title;
    private RecyclerView recycle_guan;
    private LinearLayout liner;
    private SpringView spring_view;
    Fensi_presenter fensi_presenter = new Fensi_presenter(this);
    User_Guanzhu_presenter user_guanzhu_presenter = new User_Guanzhu_presenter(this);
    List<Fensi_Bean.DatasBean>list=new ArrayList<>();
    private int pageNo=1;
    private MyFansadapter myFansadapter;
    private int postion;
    private int state;
    private int userid;
    private String uid;

    @Override
    public void getsuccess(int postion, int state, int userid) {
       this.postion=postion;
       this.state=state;
       this.userid=userid;
        user_guanzhu_presenter.getpath(Api.token,userid);

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_mineffensi);
        initView();
    }

    private void initView() {
        uid = getIntent().getStringExtra("uid");
        include_back = (ImageView) findViewById(R.id.include_back);
        include_title = (TextView) findViewById(R.id.include_title);
        recycle_guan = (RecyclerView) findViewById(R.id.recycle_guan);
        liner = (LinearLayout) findViewById(R.id.liner);
        spring_view = (SpringView) findViewById(R.id.spring_view);

          //引用
        fensi_presenter.getpath(pageNo,uid, Api.userid);
        include_title.setText("我的粉丝");
        include_back.setOnClickListener(this);
        //设置适配器
        //设置模式
        spring_view.setType(SpringView.Type.FOLLOW);
        //设置是适配器
        myFansadapter = new MyFansadapter(this,list);
        myFansadapter.getstate(this);
        recycle_guan.setLayoutManager(new LinearLayoutManager(this));
        recycle_guan.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycle_guan.setNestedScrollingEnabled(false);
        recycle_guan.setAdapter(myFansadapter);
         //点击跳转
           myFansadapter.SetonItemClicklistener(new MyFansadapter.OnItemClickLister() {
               @Override
               public void onItemClick(View view, int position) {
                   Intent intent=new Intent(MyFansiActivity.this,MydynamicActivity.class);
                   intent.putExtra("uid",list.get(position).getUid()+"");
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
                        list.clear();
                        pageNo=1;
                        fensi_presenter.getpath(pageNo,uid,Api.userid);
                        myFansadapter.notifyDataSetChanged();
                    }
                }, 0);
                spring_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        fensi_presenter.getpath(pageNo,uid,Api.userid);
                        myFansadapter.notifyDataSetChanged();
                    }
                }, 0);
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

    @Override
    public void getsuccess(Fensi_Bean guanzhu) {
          if(guanzhu.getCode().equals("200")){
              List<Fensi_Bean.DatasBean> datas = guanzhu.getDatas();
              if(pageNo>1){
                    if(datas.size()>0){
                        list.addAll(datas);
                    }else{
                        Toast.makeText(MyFansiActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();
                    }
                }else{
                  list.addAll(datas);
              }
              myFansadapter.notifyDataSetChanged();
          }
    }


    @Override
    public void getsuccess(User_guanzhu_Bean user_guanzhu_bean) {
        if(user_guanzhu_bean.getCode().equals("200")){
            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
        }else if(user_guanzhu_bean.getCode().equals("201")){
            ToastUtils.showShort(MyFansiActivity.this,user_guanzhu_bean.getMsg());
        }else if(user_guanzhu_bean.getCode().equals("203")){
            ToastUtils.showShort(MyFansiActivity.this,user_guanzhu_bean.getMsg());
        }else if(user_guanzhu_bean.getCode().equals("500")){
            ToastUtils.showShort(MyFansiActivity.this,user_guanzhu_bean.getMsg());
        }
        myFansadapter.notifyDataSetChanged();
    }

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(MyFansiActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_guanzhu)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
