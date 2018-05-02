package www.diandianxing.com.diandianxing.set;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.MyCollectionActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.adapter.CouponAdapter;
import www.diandianxing.com.diandianxing.adapter.MyCollectionAdapter;
import www.diandianxing.com.diandianxing.adapter.RedPacketRecordAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.CouponInfo;
import www.diandianxing.com.diandianxing.bean.RedPacketInfo;
import www.diandianxing.com.diandianxing.util.Api;
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
    ArrayList<RedPacketInfo> mList=new ArrayList<>();
    RedPacketRecordAdapter changegameAdapter;
    private int page;
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
        mRecycler_wdsc.setLayoutManager(new LinearLayoutManager(RedPacketRecordActivity.this));
        mRecycler_wdsc.setNestedScrollingEnabled(false);
        iv_callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initRefresh();
        finishFreshAndLoad();
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

                mList.clear();
                page=1;

                finishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                page++;
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
                networklist();
                springView.onFinishFreshAndLoad();
            }
        }, 0);
    }


    private void networklist() {

        HttpParams params = new HttpParams();
        params.put("page", page);
        params.put("uid", SpUtils.getString(this, "userid", null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(MyContants.BASEURL +"s=Purse/userRedPacket")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "红包" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<RedPacketInfo> redPacketInfos = new ArrayList<RedPacketInfo>();
                            if (code == 200) {
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);

                                    RedPacketInfo redPacketInfo = new RedPacketInfo();
                                    redPacketInfo.id=jo.getString("id");

                                    redPacketInfo.uid=jo.getString("uid");
                                    redPacketInfo.gold=jo.getString("gold");
                                    redPacketInfo.add_time=jo.getString("add_time");
                                    redPacketInfos.add(redPacketInfo);
                                }
                                if(page>1){

                                    if(redPacketInfos.size()<=0){

                                        Toast.makeText(RedPacketRecordActivity.this, Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        mList.addAll(redPacketInfos);
                                    }

                                }else{

                                    mList.addAll(redPacketInfos);

                                }

                                if(changegameAdapter==null){
                                    changegameAdapter  = new RedPacketRecordAdapter(R.layout.item_red_packetrecord, mList);
                                    mRecycler_wdsc.setAdapter(changegameAdapter);
                                }else{

                                    changegameAdapter.notifyDataSetChanged();
                                }

                            } else {
                                Toast.makeText(RedPacketRecordActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

}
