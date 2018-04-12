package www.diandianxing.com.diandianxing.set;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.MyCollectionActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.adapter.MyCollectionAdapter;
import www.diandianxing.com.diandianxing.adapter.RedPacketRecordAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.SwitchButton;

/**
 * Created by Administrator on 2018/4/11.
 */

public class RedPacketRecordActivity extends BaseActivity {

    private ImageView iv_callback;
    private TextView zhong;
    private TextView you;

    private SpringView springView;
    private RecyclerView mRecycler_wdsc;
    ArrayList<String> mList=new ArrayList<>();
    RedPacketRecordAdapter changegameAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_red_packetrecord);

        initView();

    }

    private void initView() {
        iv_callback = (ImageView) findViewById(R.id.iv_callback);
        zhong = (TextView) findViewById(R.id.zhong);
        you = (TextView) findViewById(R.id.you);
        springView= (SpringView) findViewById(R.id.springView);
        mRecycler_wdsc= (RecyclerView) findViewById(R.id.mRecycler_wdsc);
        zhong.setText("红包记录");
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
        mRecycler_wdsc.setLayoutManager(new LinearLayoutManager(RedPacketRecordActivity.this));
        mRecycler_wdsc.setNestedScrollingEnabled(false);
        changegameAdapter  = new RedPacketRecordAdapter(R.layout.item_red_packetrecord, mList);
        mRecycler_wdsc.setAdapter(changegameAdapter);
        iv_callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initRefresh();

    }

    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new DefaultHeader(RedPacketRecordActivity.this));
        springView.setFooter(new DefaultFooter(RedPacketRecordActivity.this));
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
