package www.diandianxing.com.diandianxing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyContants;

/**
 * Created by Administrator on 2018/4/3.
 */

public class TopRuleActivity extends BaseActivity {

    private ImageView include_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_sign_rule);
        include_back= (ImageView) findViewById(R.id.include_back);
        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
