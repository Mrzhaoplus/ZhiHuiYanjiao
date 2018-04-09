package zhyj.dqjt.com.zhihuiyanjiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_search);
    }
}
