package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.ShujuBean.GZ_person_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.User_guanzhu_Bean;
import www.diandianxing.com.diandianxing.adapter.Myfollowadapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.Event_coll_size;
import www.diandianxing.com.diandianxing.interfase.GZ_person_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.GZ_state;
import www.diandianxing.com.diandianxing.interfase.Userguanzhu_presenter_interfase;
import www.diandianxing.com.diandianxing.presenter.GZ_person_presenter;
import www.diandianxing.com.diandianxing.presenter.User_Guanzhu_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * Created by ASUS on 2018/3/20.
 */

public class MyFllowActivity extends BaseActivity implements View.OnClickListener, GZ_person_presenter_interfase ,GZ_state,Userguanzhu_presenter_interfase {

    private ImageView include_back;
    private RecyclerView recycle_guan;
    private TextView include_title;
    private SpringView springView;
    private int pageNo=1;
    private GZ_person_presenter gz_person_presenter = new GZ_person_presenter(this);
    List<GZ_person_Bean.DatasBean>list=new ArrayList<>();
    private Myfollowadapter myfollowadapter;
    private TextView perpho;
    private String title,uid;
    private int postion;
    private int state;
    private int userid;
    User_Guanzhu_presenter user_guanzhu_presenter = new User_Guanzhu_presenter(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_mineguanzhu);
        initView();
    }

    private void initView() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.LOGING_SX);
        intentFilter.addAction(GlobalParams.GZ);
        registerReceiver(broadcastReceiver,intentFilter);
        title=getIntent().getStringExtra("title");
        uid = getIntent().getStringExtra("uid");
        include_back = (ImageView) findViewById(R.id.include_back);
        recycle_guan = (RecyclerView) findViewById(R.id.recycle_guan);
        include_title = (TextView) findViewById(R.id.include_title);
        perpho = (TextView) findViewById(R.id.person_pho);
        springView = (SpringView) findViewById(R.id.spring_view);

        if("我的主页".equals(title)){
            perpho.setVisibility(View.VISIBLE);
        }else {
            perpho.setVisibility(View.GONE);
        }

        //引用
        if(NetUtil.checkNet(this)){
            //获取引用
            gz_person_presenter.getpath(pageNo, SpUtils.getString(this,"userid",null),uid);
        }else{
            Toast.makeText(this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }

        //设置模式
        springView.setType(SpringView.Type.FOLLOW);
        //设置是适配器
        myfollowadapter = new Myfollowadapter(this,list);
        myfollowadapter.getstate(this);
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
                       if(NetUtil.checkNet(MyFllowActivity.this)){
                           //获取引用
                           gz_person_presenter.getpath(pageNo, SpUtils.getString(MyFllowActivity.this,"userid",null),uid);
                       }else{
                           Toast.makeText(MyFllowActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                       }

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
                       if(NetUtil.checkNet(MyFllowActivity.this)){
                           //获取引用
                           gz_person_presenter.getpath(pageNo, SpUtils.getString(MyFllowActivity.this,"userid",null),uid);
                       }else{
                           Toast.makeText(MyFllowActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                       }
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


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(GlobalParams.LOGING_SX.equals(action)){

                list.clear();
                pageNo=1;
                if(NetUtil.checkNet(MyFllowActivity.this)){
                    //获取引用
                    gz_person_presenter.getpath(pageNo, SpUtils.getString(MyFllowActivity.this,"userid",null),uid);
                }else{
                    Toast.makeText(MyFllowActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

                myfollowadapter.notifyDataSetChanged();
            }else if(GlobalParams.GZ.equals(action)){

                list.clear();
                pageNo=1;
                if(NetUtil.checkNet(MyFllowActivity.this)){
                    //获取引用
                    gz_person_presenter.getpath(pageNo, SpUtils.getString(MyFllowActivity.this,"userid",null),uid);
                }else{
                    Toast.makeText(MyFllowActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

                myfollowadapter.notifyDataSetChanged();

            }

        }
    };

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
          else if(guanzhu.getCode().equals("201")){
              startActivity(new Intent(MyFllowActivity.this,LoginActivity.class));
              SpUtils.putInt(MyFllowActivity.this, "guid", 1);
              finish();
          }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gz_person_presenter.getkong();
        user_guanzhu_presenter.getkong();
        EventBus.getDefault().postSticky(new Event_coll_size(1,list.size()));
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void getsuccess(int postion, int state, int uid) {

        int guid = SpUtils.getInt(MyFllowActivity.this, "guid", 0);

        if(guid!=2){
            startActivity(new Intent(MyFllowActivity.this,LoginActivity.class));
        }else{
            this.postion=postion;
            this.state=state;
            this.userid=uid;
            if(NetUtil.checkNet(MyFllowActivity.this)){
                //获取引用
                user_guanzhu_presenter.getpath(SpUtils.getString(this,"token",null),userid);
            }else{
                Toast.makeText(MyFllowActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void getsuccess(User_guanzhu_Bean user_guanzhu_bean) {
        if(user_guanzhu_bean.getCode().equals("200")){
            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
            Intent intent = new Intent();
            intent.setAction(GlobalParams.GZ);
            sendBroadcast(intent);

        }else if(user_guanzhu_bean.getCode().equals("201")){
            startActivity(new Intent(MyFllowActivity.this,LoginActivity.class));
            SpUtils.putInt(MyFllowActivity.this, "guid", 1);
            finish();
        }else if(user_guanzhu_bean.getCode().equals("203")){
            ToastUtils.showShort(MyFllowActivity.this,user_guanzhu_bean.getMsg());
        }else if(user_guanzhu_bean.getCode().equals("500")){
            ToastUtils.showShort(MyFllowActivity.this,user_guanzhu_bean.getMsg());
        }
        myfollowadapter.notifyDataSetChanged();
    }

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(MyFllowActivity.this);
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
