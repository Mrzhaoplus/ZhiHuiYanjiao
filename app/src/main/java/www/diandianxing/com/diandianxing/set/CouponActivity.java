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

import www.diandianxing.com.diandianxing.MainActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.adapter.CouponAdapter;
import www.diandianxing.com.diandianxing.adapter.RedPacketRecordAdapter;
import www.diandianxing.com.diandianxing.adapter.Tuijiantieadapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.CouponInfo;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.fragment.paikefragment.DarenFragment;
import www.diandianxing.com.diandianxing.fragment.paikefragment.GuanzhuFragment;
import www.diandianxing.com.diandianxing.fragment.paikefragment.TuijianFragment;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;
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
    ArrayList<CouponInfo> mList=new ArrayList<>();
    CouponAdapter changegameAdapter;

    private int page=1;

    private int type=0;

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
        mRecycler_wdsc.setLayoutManager(new LinearLayoutManager(CouponActivity.this));
        mRecycler_wdsc.setNestedScrollingEnabled(false);
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
        finishFreshAndLoad();
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
                if(changegameAdapter!=null){
                    changegameAdapter.setType(0);
                    changegameAdapter.notifyDataSetChanged();
                }

                mList.clear();
                page=1;
                type=0;
                finishFreshAndLoad();

                break;
            case R.id.liner2:
                text_tuijian.setTextColor(getResources().getColor(R.color.text_orage));
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                if(changegameAdapter!=null){
                    changegameAdapter.setType(1);
                    changegameAdapter.notifyDataSetChanged();
                }
                mList.clear();
                page=1;
                type=1;
                finishFreshAndLoad();
                break;
            case R.id.liner3:
                text_daren.setTextColor(getResources().getColor(R.color.text_orage));
                v3.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                if(changegameAdapter!=null){
                    changegameAdapter.setType(2);
                    changegameAdapter.notifyDataSetChanged();
                }
                mList.clear();
                page=1;
                type=2;
                finishFreshAndLoad();
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
        params.put("type", type);
        params.put("uid", SpUtils.getString(this, "userid", null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(MyContants.BASEURL +"s=Purse/userCouponList")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "优惠间" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<CouponInfo> couponInfos = new ArrayList<CouponInfo>();
                            if (code == 200) {
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);

                                    CouponInfo couponInfo = new CouponInfo();
                                    couponInfo.money=jo.getString("money");

                                    couponInfo.id=jo.getString("id");
                                    couponInfo.finish_time=jo.getString("finish_time");
                                    couponInfos.add(couponInfo);
                                }
                                if(page>1){

                                    if(couponInfos.size()<=0){

                                        Toast.makeText(CouponActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        mList.addAll(couponInfos);
                                    }

                                }else{

                                    mList.addAll(couponInfos);

                                }

                                if(changegameAdapter==null){
                                    changegameAdapter  = new CouponAdapter(R.layout.item_coupon_view, mList);
                                    mRecycler_wdsc.setAdapter(changegameAdapter);
                                }else{

                                    changegameAdapter.notifyDataSetChanged();
                                }

                            } else {
                                Toast.makeText(CouponActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }



}
