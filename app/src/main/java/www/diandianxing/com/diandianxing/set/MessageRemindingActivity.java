package www.diandianxing.com.diandianxing.set;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.adapter.Tujianadapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.fragment.sousuofragment.SearchPaikeFragment;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.SwitchButton;

/**
 * Created by Administrator on 2018/4/11.
 */

public class MessageRemindingActivity extends BaseActivity implements View.OnClickListener{

    private ImageView iv_callback;
    private TextView zhong;
    private TextView you;

    private SwitchButton sb_xxtz;
private LinearLayout ll_sange;

    private RelativeLayout rl_pl,rl_dz,rl_hf;

    private RadioButton rb_pl,rb_dz,rb_hf;

    private SwitchButton sb_sytx,sb_zdtx,sb_xttz;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_message_reminding);
        initView();
    }

    private void initView() {
        iv_callback = (ImageView) findViewById(R.id.iv_callback);
        sb_xxtz= (SwitchButton) findViewById(R.id.sb_xxtz);
        ll_sange= (LinearLayout) findViewById(R.id.ll_sange);
        zhong = (TextView) findViewById(R.id.zhong);
        rl_pl= (RelativeLayout) findViewById(R.id.rl_pl);
        rl_dz= (RelativeLayout) findViewById(R.id.rl_dz);
        rl_hf= (RelativeLayout) findViewById(R.id.rl_hf);
        rb_pl= (RadioButton) findViewById(R.id.rb_pl);
        rb_dz= (RadioButton) findViewById(R.id.rb_dz);
        rb_hf= (RadioButton) findViewById(R.id.rb_hf);
        sb_sytx= (SwitchButton) findViewById(R.id.sb_sytx);
        sb_zdtx= (SwitchButton) findViewById(R.id.sb_zdtx);
        sb_xttz= (SwitchButton) findViewById(R.id.sb_xttz);
        you = (TextView) findViewById(R.id.you);
        networkMsg();

//        boolean sy = SpUtils.getBoolean(MessageRemindingActivity.this, GlobalParams.SYTS, false);


//        boolean zd = SpUtils.getBoolean(MessageRemindingActivity.this, GlobalParams.ZDTS, false);
//        boolean xt = SpUtils.getBoolean(MessageRemindingActivity.this, GlobalParams.XTTS, false);


//        boolean pl = SpUtils.getBoolean(MessageRemindingActivity.this, GlobalParams.PL_TS, false);
//        boolean dz = SpUtils.getBoolean(MessageRemindingActivity.this, GlobalParams.DZ_TS, false);
//        boolean hf = SpUtils.getBoolean(MessageRemindingActivity.this, GlobalParams.HF_TS, false);
//
//        rb_pl.setChecked(pl);
//        rb_dz.setChecked(dz);
//        rb_hf.setChecked(hf);

//        if(pl|dz|hf){
//            sb_xxtz.setChecked(true);
//        }else{
//            sb_xxtz.setChecked(false);
//            ll_sange.setVisibility(View.GONE);
//        }


//        if(sy){
//            sb_sytx.setChecked(true);
//        }else {
//            sb_sytx.setChecked(false);
//        }
//
//        if(zd){
//            sb_zdtx.setChecked(true);
//        }else{
//            sb_zdtx.setChecked(false);
//        }

//        if(xt){
//            sb_xttz.setChecked(true);
//        }else{
//            sb_xttz.setChecked(false);
//        }

        iv_callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        zhong.setText("消息通知");

        rl_pl.setOnClickListener(this);
        rl_dz.setOnClickListener(this);
        rl_hf.setOnClickListener(this);

        sb_xxtz.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if(isChecked){

                    ll_sange.setVisibility(View.VISIBLE);
                    rb_pl.setChecked(true);
                    rb_dz.setChecked(true);
                    rb_hf.setChecked(true);
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.PL_TS, true);
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.DZ_TS, true);
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.HF_TS, true);
                    networkGXMsg("0","5");
                }else{
                    ll_sange.setVisibility(View.GONE);
                    rb_pl.setChecked(false);
                    rb_dz.setChecked(false);
                    rb_hf.setChecked(false);
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.PL_TS, false);
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.DZ_TS, false);
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.HF_TS, false);
                    networkGXMsg("1","5");
                }

            }
        });

        sb_zdtx.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.ZDTS, true);
                    networkGXMsg("0","1");
                }else{
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.ZDTS, false);
                    networkGXMsg("1","1");
                }


            }
        });

        sb_xttz.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if(isChecked){
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.XTTS, true);
                    networkGXMsg("0","6");
                }else{
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.XTTS, false);
                    networkGXMsg("1","6");
                }

            }
        });

        sb_sytx.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if(isChecked){
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.SYTS, true);

                    networkGXMsg("0","0");

                }else{
                    SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.SYTS, false);
                    networkGXMsg("1","0");
                }

            }
        });


    }


    private void networkMsg() {

        HttpParams params = new HttpParams();

        params.put("token", SpUtils.getString(MessageRemindingActivity.this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/getMsgStatusList")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "取消数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONObject datas = jsonobj.getJSONObject("datas");
                            if (code == 200) {

                                if(Integer.parseInt(datas.getString("sound"))==0){

                                    sb_sytx.setChecked(true);

                                }else{
                                    sb_sytx.setChecked(false);
                                }


                                if(Integer.parseInt(datas.getString("shake"))==0){
                                    Log.e("TAG","执行2222222");
                                    sb_zdtx.setChecked(true);

                                }else{
                                    sb_zdtx.setChecked(false);
                                }


                                if(Integer.parseInt(datas.getString("systemStatus"))==0){
                                    sb_xttz.setChecked(true);
                                }else {
                                    sb_xttz.setChecked(false);
                                }

                                if(Integer.parseInt(datas.getString("messageNotification"))==0){
                                    sb_xxtz.setChecked(true);

                                    if(Integer.parseInt(datas.getString("comment"))==0){
                                        rb_pl.setChecked(true);
                                    }else {
                                        rb_pl.setChecked(false);
                                    }

                                    if(Integer.parseInt(datas.getString("dianZan"))==0){
                                        rb_dz.setChecked(true);
                                    }else {
                                        rb_dz.setChecked(false);
                                    }

                                    if(Integer.parseInt(datas.getString("reply"))==0){
                                        rb_hf.setChecked(true);
                                    }else {
                                        rb_hf.setChecked(false);
                                    }


                                }else{

                                    sb_xxtz.setChecked(false);
                                    rb_pl.setChecked(false);
                                    rb_hf.setChecked(false);
                                    rb_pl.setChecked(false);
                                }



                            } else {
                                Toast.makeText(MessageRemindingActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private void networkGXMsg(String status,String msgType) {

        HttpParams params = new HttpParams();

        params.put("token", SpUtils.getString(MessageRemindingActivity.this,"token",null));
        params.put("status",status);
        params.put("msgType", msgType);
        Log.d("TAG","更改"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/updateMsgStatus")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "取消数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONObject datas = jsonobj.getJSONObject("datas");
                            if (code == 200) {






                            } else {
                                Toast.makeText(MessageRemindingActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        boolean checked;
        switch (view.getId()){
            case R.id.rl_pl:

                checked = rb_pl.isChecked();

                SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.PL_TS, !checked);

                rb_pl.setChecked(!checked);

                if(!checked){

                    networkGXMsg("0","2");

                }else{
                    networkGXMsg("1","2");
                }


                break;
            case R.id.rl_dz:
                checked = rb_dz.isChecked();
                SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.DZ_TS, !checked);
                rb_dz.setChecked(!checked);

                if(!checked){

                    networkGXMsg("0","3");

                }else{
                    networkGXMsg("1","3");
                }

                break;
            case R.id.rl_hf:
                checked = rb_hf.isChecked();
                SpUtils.putBoolean(MessageRemindingActivity.this, GlobalParams.HF_TS, !checked);
                rb_hf.setChecked(!checked);
                if(!checked){

                    networkGXMsg("0","4");

                }else{
                    networkGXMsg("1","4");
                }
                break;
        }
        if(!rb_pl.isChecked()&&!rb_dz.isChecked()&&!rb_hf.isChecked()){

            ll_sange.setVisibility(View.GONE);
            sb_xxtz.setChecked(false);
//            networkGXMsg("1","5");

        }else{
            ll_sange.setVisibility(View.VISIBLE);
            sb_xxtz.setChecked(true);
//            networkGXMsg("0","5");
        }
    }
}
