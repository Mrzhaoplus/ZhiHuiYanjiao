package www.diandianxing.com.diandianxing;

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

import www.diandianxing.com.diandianxing.ShujuBean.Coll_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.User_guanzhu_Bean;
import www.diandianxing.com.diandianxing.adapter.MyCollectionAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MyFansiActivity;
import www.diandianxing.com.diandianxing.interfase.Coll_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.Userguanzhu_presenter_interfase;
import www.diandianxing.com.diandianxing.presenter.Coll_presenter;
import www.diandianxing.com.diandianxing.presenter.User_Guanzhu_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyCollectionActivity extends BaseActivity implements Coll_presenter_interfase, Userguanzhu_presenter_interfase {

    private ImageView include_back;
    private SpringView springView;
    private RecyclerView mRecycler_wdsc;
    private TextView tv_bj;
    private int pageNo=1;
    Coll_presenter coll_presenter = new Coll_presenter(this);
    User_Guanzhu_presenter user_guanzhu_presenter = new User_Guanzhu_presenter(this);
    ArrayList<Coll_Bean.DatasBean> mList=new ArrayList<>();
    MyCollectionAdapter changegameAdapter;
    boolean isqh;
    private TextView text_sure;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_my_collection);
        //引用
        coll_presenter.getpath(pageNo, Api.token);


        include_back= (ImageView) findViewById(R.id.include_back);
        springView= (SpringView) findViewById(R.id.springView);
        mRecycler_wdsc= (RecyclerView) findViewById(R.id.mRecycler_wdsc);
        tv_bj= (TextView) findViewById(R.id.tv_bj);

        mRecycler_wdsc.setLayoutManager(new LinearLayoutManager(MyCollectionActivity.this));
        mRecycler_wdsc.setNestedScrollingEnabled(false);
        changegameAdapter  = new MyCollectionAdapter(R.layout.my_collection_item_view, mList);
        mRecycler_wdsc.setAdapter(changegameAdapter);
        changegameAdapter.setonGZClickListener(gzClickListener);
        initRefresh();

        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isqh){
                    tv_bj.setText("编辑");
                }else{
                    tv_bj.setText("完成");
                }

                isqh=!isqh;
                changegameAdapter.setZT(isqh);
                changegameAdapter.notifyDataSetChanged();
            }
        });

    }

/*
* 关注
* */
    private MyCollectionAdapter.GZClickListener gzClickListener = new MyCollectionAdapter.GZClickListener() {
        @Override
        public void onGZClickListener(int pos) {
            Log.e("TAG","pos==="+pos);
            int userId = mList.get(pos).getUserId();
            user_guanzhu_presenter.getpath(Api.token,userId);
        }
          //点击删除条目刷新适配器
        @Override
        public void onSCClickListener(int pos) {
            mList.remove(pos);
            changegameAdapter.notifyDataSetChanged();
        }
          //跳转详情页
        @Override
        public void onItemClickLisener(int pos) {
             startActivity(new Intent(MyCollectionActivity.this, JiaoDetailActivity.class));
        }
    };

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(MyCollectionActivity.this);
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
        text_sure = dialog.getView(R.id.text_sure);

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


    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new DefaultHeader(MyCollectionActivity.this));
        springView.setFooter(new DefaultFooter(MyCollectionActivity.this));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(mContext,"下拉刷新中",Toast.LENGTH_SHORT).show();
                // list.clear();
                // 网络请求;
                // mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法
                finishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                finishFreshAndLoad();
            }
        });
    }

    /**
     * 关闭加载提示
     */
    private void finishFreshAndLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                springView.onFinishFreshAndLoad();
            }
        }, 2000);
    }

    @Override
    public void getsuccess(Coll_Bean coll_bean) {
        if(coll_bean.getCode().equals("200")){
            List<Coll_Bean.DatasBean> datas = coll_bean.getDatas();
            if(pageNo>1){
                if(datas.size()>0){
                    mList.addAll(datas);
                }else{
                    Toast.makeText(MyCollectionActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();
                }
            }else{
                mList.addAll(datas);
            }
            changegameAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getsuccess(User_guanzhu_Bean user_guanzhu_bean) {
        if(user_guanzhu_bean.getCode().equals("200")){
            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
        }else if(user_guanzhu_bean.getCode().equals("201")){
            ToastUtils.showShort(MyCollectionActivity.this,user_guanzhu_bean.getMsg());
        }else if(user_guanzhu_bean.getCode().equals("203")){
            ToastUtils.showShort(MyCollectionActivity.this,user_guanzhu_bean.getMsg());
        }else if(user_guanzhu_bean.getCode().equals("500")){
            ToastUtils.showShort(MyCollectionActivity.this,user_guanzhu_bean.getMsg());
        }
        changegameAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        coll_presenter.getkong();
    }
}
