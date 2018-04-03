package zhyj.dqjt.com.zhihuiyanjiao;

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

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;

import zhyj.dqjt.com.zhihuiyanjiao.adapter.MyCollectionAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;
import zhyj.dqjt.com.zhihuiyanjiao.util.BaseDialog;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyCollectionActivity extends BaseActivity {

    private ImageView include_back;
    private SpringView springView;
    private RecyclerView mRecycler_wdsc;
    private TextView tv_bj;
    ArrayList<String> mList=new ArrayList<>();
    MyCollectionAdapter changegameAdapter;
    boolean isqh;
    private TextView text_sure;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        include_back= (ImageView) findViewById(R.id.include_back);
        springView= (SpringView) findViewById(R.id.springView);
        mRecycler_wdsc= (RecyclerView) findViewById(R.id.mRecycler_wdsc);
        tv_bj= (TextView) findViewById(R.id.tv_bj);
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
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


    private MyCollectionAdapter.GZClickListener gzClickListener = new MyCollectionAdapter.GZClickListener() {
        @Override
        public void onGZClickListener(int pos) {

            Log.e("TAG","pos==="+pos);
            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
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

}
