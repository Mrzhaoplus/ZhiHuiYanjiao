package www.diandianxing.com.diandianxing.set;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.util.MyContants;
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
        you = (TextView) findViewById(R.id.you);
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
                }else{
                    ll_sange.setVisibility(View.GONE);
                    rb_pl.setChecked(false);
                    rb_dz.setChecked(false);
                    rb_hf.setChecked(false);
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
                rb_pl.setChecked(!checked);


                break;
            case R.id.rl_dz:
                checked = rb_dz.isChecked();
                rb_dz.setChecked(!checked);

                break;
            case R.id.rl_hf:
                checked = rb_hf.isChecked();
                rb_hf.setChecked(!checked);


                break;
        }
        if(!rb_pl.isChecked()&&!rb_dz.isChecked()&&!rb_hf.isChecked()){

            ll_sange.setVisibility(View.GONE);
            sb_xxtz.setChecked(false);


        }else{
            ll_sange.setVisibility(View.VISIBLE);
            sb_xxtz.setChecked(true);
        }
    }
}
