package www.diandianxing.com.diandianxing.set;

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

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.MainActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.adapter.CouponAdapter;
import www.diandianxing.com.diandianxing.adapter.RedPacketRecordAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.fragment.paikefragment.DarenFragment;
import www.diandianxing.com.diandianxing.fragment.paikefragment.GuanzhuFragment;
import www.diandianxing.com.diandianxing.fragment.paikefragment.TuijianFragment;
import www.diandianxing.com.diandianxing.util.XCPopwindow;

/**
 * Created by Administrator on 2018/4/11.
 */

public class CouponActivity extends BaseActivity implements View.OnClickListener{

    private ImageView iv_callback;
    private TextView zhong;
    private TextView you;
    private LinearLayout liner1;
    private LinearLayout liner2;
    private LinearLayout liner3;
    private TextView text_guanzhu;
    private TextView text_tuijian;
    private TextView text_daren;
    public View v1;
    public View v2;
    public View v3;
    private SpringView springView;
    private RecyclerView mRecycler_wdsc;
    ArrayList<String> mList=new ArrayList<>();
    CouponAdapter changegameAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        iv_callback = (ImageView) findViewById(R.id.iv_callback);
        zhong = (TextView) findViewById(R.id.zhong);
        you = (TextView) findViewById(R.id.you);
        this.v1 = (View)  findViewById(R.id.v1);
        this.v2 = (View) findViewById(R.id.v2);
        this.v3 = (View)  findViewById(R.id.v3);
        liner1 = (LinearLayout) findViewById(R.id.liner1);
        liner2 = (LinearLayout) findViewById(R.id.liner2);
        liner3 = (LinearLayout) findViewById(R.id.liner3);
        text_guanzhu = (TextView) findViewById(R .id.text_guanzhu);
        text_tuijian = (TextView) findViewById(R.id.text_tuijian);
        text_daren = (TextView) findViewById(R.id.text_daren);
        springView= (SpringView) findViewById(R.id.springView);
        mRecycler_wdsc= (RecyclerView) findViewById(R.id.mRecycler_wdsc);
        zhong.setText("优惠券");
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
        mRecycler_wdsc.setLayoutManager(new LinearLayoutManager(CouponActivity.this));
        mRecycler_wdsc.setNestedScrollingEnabled(false);
        changegameAdapter  = new CouponAdapter(R.layout.item_coupon_view, mList);
        mRecycler_wdsc.setAdapter(changegameAdapter);
        iv_callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initRefresh();

        liner1.setOnClickListener(this);
        liner2.setOnClickListener(this);
        liner3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.liner1:
                text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));

                changegameAdapter.setType(0);
                changegameAdapter.notifyDataSetChanged();

                break;
            case R.id.liner2:
                text_tuijian.setTextColor(getResources().getColor(R.color.text_orage));
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                changegameAdapter.setType(1);
                changegameAdapter.notifyDataSetChanged();
                break;
            case R.id.liner3:
                text_daren.setTextColor(getResources().getColor(R.color.text_orage));
                v3.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                changegameAdapter.setType(2);
                changegameAdapter.notifyDataSetChanged();
                break;

        }
    }

    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new DefaultHeader(CouponActivity.this));
        springView.setFooter(new DefaultFooter(CouponActivity.this));
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
