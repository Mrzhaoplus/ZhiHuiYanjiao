package www.diandianxing.com.diandianxing.set;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Feedback extends BaseActivity implements View.OnClickListener {

    private ImageView iv_callback;
    private TextView zhong;
    private TextView you;
    private TextView login_sso;
    private EditText feel_text;
    private TextView tv_shengyu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_fuwu);
        initView();
    }

    private void initView() {
        iv_callback = (ImageView) findViewById(R.id.iv_callback);
        zhong = (TextView) findViewById(R.id.zhong);
        you = (TextView) findViewById(R.id.you);
        feel_text = (EditText) findViewById(R.id.feel_edtext);
        login_sso = (TextView) findViewById(R.id.login_sso);
        tv_shengyu= (TextView) findViewById(R.id.tv_shengyu);
        zhong.setText("意见反馈");
        iv_callback.setOnClickListener(this);
        login_sso.setOnClickListener(this);

        feel_text.addTextChangedListener(textWatcher);

    }

    TextWatcher textWatcher  = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String trim = editable.toString().trim();

            if(trim.length()>140){
                Toast.makeText(Feedback.this,"输入超出140个字",Toast.LENGTH_SHORT).show();
            }else{
                tv_shengyu.setText((140-trim.length())+"/140");
            }
        }
    };

    private void network() {
        HttpParams httpar=new HttpParams();
         httpar.put("token", SpUtils.getString(this, "token", null));
        httpar.put("content", feel_text.getText().toString());

        Log.e("TAG","数据包：：："+httpar.toString());
        OkGo.<String>post(Api.BASE_URL+"app/home/feedBack")
                .tag(this)
                .params(httpar)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();

                        Log.e("TAG","反馈数据：：："+body);

                        try {
                            JSONObject jsonobj=new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if(code==200){
                                ToastUtils.showShort(Feedback.this,"提交成功");
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_callback:
                finish();
                break;
            case R.id.login_sso:
                sumbit();

                break;
        }
    }

    private void sumbit() {
        if(TextUtils.isEmpty(feel_text.getText().toString())){
            ToastUtils.showShort(Feedback.this,"请输入反馈内容");
            return;
        }
        if(NetUtil.checkNet(Feedback.this)){
            Log.e("TAG","反馈提交执行：：：");
            network();
        }else{
            Toast.makeText(Feedback.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }
    }
}
