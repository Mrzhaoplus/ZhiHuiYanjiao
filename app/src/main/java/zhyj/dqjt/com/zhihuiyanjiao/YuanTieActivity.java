package zhyj.dqjt.com.zhihuiyanjiao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

public class YuanTieActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back;
    private ImageView collect;
    private WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_yuan_tie);
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        collect = (ImageView) findViewById(R.id.collect);
        web_view = (WebView) findViewById(R.id.web_view);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}
