package www.diandianxing.com.diandianxing.set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.my.BalanceActivity;
import www.diandianxing.com.diandianxing.my.CashpayActivity;
import www.diandianxing.com.diandianxing.my.MingxiActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.EventMessage;
import www.diandianxing.com.diandianxing.util.GGUtils;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class WalletActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_cz;
    private LinearLayout ll_yj;
    private ImageView iv_callback;
    private TextView wallet_yue;
    private TextView wallet_yanjin;
    private TextView look_mingxi;
    private String credit_normal;
    private LinearLayout ll_hbsr,ll_yhj;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_mywallet);
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(GGUtils.SX_DATA);
//        registerReceiver(receiver,intentFilter);
        EventBus.getDefault().register(this);//注册eventbus
        initView();
    }

    private void initView() {
        iv_callback = (ImageView) findViewById(R.id.iv_callback);
        rl_cz= (RelativeLayout) findViewById(R.id.rl_cz);
        wallet_yue = (TextView) findViewById(R.id.wallet_yue);
        look_mingxi = (TextView) findViewById(R.id.look_mingxi);
        ll_yhj= (LinearLayout) findViewById(R.id.ll_yhj);
        wallet_yanjin= (TextView) findViewById(R.id.wallet_yanjin);
        ll_hbsr= (LinearLayout) findViewById(R.id.ll_hbsr);
        ll_yj= (LinearLayout) findViewById(R.id.ll_yj);
        iv_callback.setOnClickListener(this);
        look_mingxi.setOnClickListener(this);
        ll_yj.setOnClickListener(this);
        rl_cz.setOnClickListener(this);
        ll_hbsr.setOnClickListener(this);
        ll_yhj.setOnClickListener(this);
        String yajin = SpUtils.getString(this, "yajin", null);

        if("0.00".equals(yajin)){
            wallet_yanjin.setText("未交");
        }else{
            wallet_yanjin.setText(yajin + "元");
        }
        String yue = SpUtils.getString(this, "yue", null);
        wallet_yue.setText(yue + "元");
        String fanbei = SpUtils.getString(WalletActivity.this, "fanbei", null);
        credit_normal = SpUtils.getString(WalletActivity.this, "credit_normal", null);
    }






    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

                    if(GGUtils.SX_DATA.equals(intent.getAction())){

                        String yajin=intent.getStringExtra("securityDeposit");
                        Log.e("TAG","支付广播接受到了yajin=="+yajin);
                        if("0.00".equals(yajin)){
                            wallet_yanjin.setText("未交");
                        }else{
                            wallet_yanjin.setText(yajin + "元");
                        }
                        String yue=intent.getStringExtra("balances");
                        Log.e("TAG","支付广播接受到了yue=="+yue);
                        wallet_yue.setText(yue+"元");

                    }

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_callback:
                finish();
                break;
            case R.id.ll_yj:
                Log.e("TAG","点击到里面");
                if("未交".equals(wallet_yanjin.getText().toString())){
                 //跳转到叫押金
                    Intent intent=new Intent(this, CashpayActivity.class);
                    startActivity(intent);
                }
                else {
                    //退换押金
                    showphotoDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                }
                break;
            case R.id.look_mingxi://查看明细
                Intent intent2=new Intent(this, MingxiActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_cz:
                Intent cz=new Intent(this, BalanceActivity.class);
                startActivity(cz);
                break;
            case R.id.ll_hbsr:
                Intent hb=new Intent(this, RedPacketRecordActivity.class);
                startActivity(hb);
                break;
            case R.id.ll_yhj:

                Intent yhj=new Intent(this, CouponActivity.class);
                startActivity(yhj);



                break;
        }
    }
    private void showphotoDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_yajin)
                //设置dialogpadding
                .setPaddingdp(30, 0, 30, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.getView(R.id.tuihuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //押金退换
                 /*
                    网络请求退换押金
                  */
                     networkmoney();
                dialog.dismiss();
            }


        });
        //取消
        dialog.getView(R.id.pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void networkmoney() {
        HttpParams params = new HttpParams();
        params.put("uid", SpUtils.getString(this, "userid", null));
        params.put("token", SpUtils.getString(this, "token", null));
        OkGo.<String>post(MyContants.BASEURL +"s=Payment/refundDeposit")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("Tag","押金"+body);
                        try {
                            JSONObject jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            String datas = jsonobj.getString("datas");
                            if (code == 200) {
                                ToastUtils.show(WalletActivity.this, "退还成功", 1);
                                wallet_yanjin.setText("未交");
                            } else {
                                ToastUtils.show(WalletActivity.this, datas, 1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
//        Map<String,String> map=new HashMap<>();
//         map.put("uid",SpUtils.getString(this,"userid",null));
//         map.put("token",SpUtils.getString(this,"token",null));
//        RetrofitManager.post(MyContants.BASEURL+"s=Payment/refundDeposit", map, new BaseObserver1<Tuikuanbean>("") {
//            @Override
//            public void onSuccess(Tuikuanbean result, String tag) {
//                if(result.getCode()==200){
//
//                }
//            }
//
//            @Override
//            public void onFailed(int code, String data) {
//
//            }
//        });
//    }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        //支付完好跳到详情，看完后返回主界面
        if (eventMessage.getMsg().equals("dingdan")) {
            String yajin = SpUtils.getString(this, "yajin", null);
            if("0.00".equals(yajin)){
                wallet_yanjin.setText("未交");
            }else{
                wallet_yanjin.setText(yajin + "元");
            }
            String yue = SpUtils.getString(this, "yue", null);
            wallet_yue.setText(yue+"元");
        }
        if(eventMessage.getMsg().equals("chongzhi")){
            String yajin = SpUtils.getString(this, "securityDeposit", null);
            if("0.00".equals(yajin)){
                wallet_yanjin.setText("未交");
            }else{
                wallet_yanjin.setText(yajin + "元");
            }
            String yue = SpUtils.getString(this, "balances", null);
            wallet_yue.setText(yue+"元");
        }
    }
}
