package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.adapter.SignAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.DengJiGZinfo;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Administrator on 2018/4/3.
 */

public class SignActivity extends BaseActivity {

    ImageView include_back;

    RecyclerView rv_dj;
    TextView tv_qdkz;
    ArrayList<DengJiGZinfo> mList = new ArrayList<>();
    Handler handler;
    BaseDialog dialog;
    private TextView tv_qd_zjf;
    private TextView tv_qw,tv_bw,tv_sw,tv_gw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_sign);
        rv_dj= (RecyclerView) findViewById(R.id.rv_dj);
        include_back= (ImageView) findViewById(R.id.include_back);
        tv_qdkz= (TextView) findViewById(R.id.tv_qdkz);
        tv_qd_zjf= (TextView) findViewById(R.id.tv_qd_zjf);
        tv_qw= (TextView) findViewById(R.id.tv_qw);
        tv_bw= (TextView) findViewById(R.id.tv_bw);
        tv_sw= (TextView) findViewById(R.id.tv_sw);
        tv_gw= (TextView) findViewById(R.id.tv_gw);
        if(NetUtil.checkNet(SignActivity.this)){
            networkQD();
            networkDJ();
        }else{
            Toast.makeText(SignActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }


        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_qdkz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this, SignRuleActivity.class);
                startActivity(intent);
            }
        });

    }

    private void shumaDialog(int grary, int animationStyle,String integral) {
        BaseDialog.Builder builder = new BaseDialog.Builder(SignActivity.this);
        dialog = builder.setViewId(R.layout.dialog_qdjf)
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

        TextView tv_jf_zhi = dialog.getView(R.id.tv_jf_zhi);
        tv_jf_zhi.setText(integral);

        dialog.show();
    }

    private void networkQD() {

        HttpParams params = new HttpParams();
        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/usersignin/insertusersign")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "签到成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONObject datas = jsonobj.getJSONObject("datas");
                            if (code == 200) {

                                SpannableStringBuilder spannable = new SpannableStringBuilder("总积分："+datas.getString("signinintegral")+"分");

                                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")),0,3
                                        , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f55a0b")),4,datas.getString("signinintegral").length()+4
                                        , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")),datas.getString("signinintegral").length()+4,("总积分："+datas.getString("signinintegral")+"分").length()
                                        , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                tv_qd_zjf.setText(spannable);

                                int day=datas.getInt("signindays");


                                if(day<=9){

                                    tv_gw.setText(day+"");

                                }else if(day<=99){


                                    tv_gw.setText((day%10)+"");
                                    tv_sw.setText((day/10)+"");

                                }else if(day<=999){

                                    tv_bw.setText((day/100)+"");
                                    int shi = day % 100;

                                    tv_gw.setText((shi%10)+"");

                                    tv_sw.setText((shi/10)+"");

                                }else if(day<=9999){

                                    tv_qw.setText((day/1000)+"");

                                    int bai = day % 1000;


                                    tv_bw.setText((bai/100)+"");

                                    int shi = bai % 100;
                                    tv_gw.setText((shi%10)+"");

                                    tv_sw.setText((shi/10)+"");

                                }


                                if(Integer.parseInt(datas.getString("isqd"))==0){
                                    shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion,datas.getString("integral"));
                                    handler = new Handler();

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.dismiss();
                                        }
                                    },2000);
                                }


                            } else {
                                Toast.makeText(SignActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private void networkDJ() {

        HttpParams params = new HttpParams();
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/signinrules/list")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "等级：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONObject datas = jsonobj.getJSONObject("datas");


                            if (code == 200) {

                                JSONArray ja = datas.getJSONArray("list");

                                for(int i=0;i<ja.length();i++){

                                    JSONObject jo = ja.getJSONObject(i);

                                    DengJiGZinfo dengJiGZinfo = new DengJiGZinfo();
                                    dengJiGZinfo.signLevel=jo.getString("signLevel");
                                    dengJiGZinfo.signInteger=jo.getInt("signInteger");
                                    Log.e("TAG","s1="+jo.getString("signInteger"));
                                    mList.add(dengJiGZinfo);



                                }

                                rv_dj.setLayoutManager(new LinearLayoutManager(SignActivity.this));
                                rv_dj.setNestedScrollingEnabled(false);
                                SignAdapter wccAdapter = new SignAdapter(R.layout.sign_item_view, mList,datas.getString("maxinteger"));
                                rv_dj.setAdapter(wccAdapter);

                            } else {
                                Toast.makeText(SignActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


}
