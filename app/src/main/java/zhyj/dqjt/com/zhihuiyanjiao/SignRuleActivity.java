package zhyj.dqjt.com.zhihuiyanjiao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;

/**
 * Created by Administrator on 2018/4/3.
 */

public class SignRuleActivity extends BaseActivity {

    private ImageView include_back;
    private TextView include_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_rule);
        include_back= (ImageView) findViewById(R.id.include_back);
        include_title= (TextView) findViewById(R.id.include_title);
        include_title.setText("签到规则");
        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
