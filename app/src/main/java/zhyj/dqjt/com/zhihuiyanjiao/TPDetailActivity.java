package zhyj.dqjt.com.zhihuiyanjiao;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.adapter.ViewPager_Adapter;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

public class TPDetailActivity extends AppCompatActivity {

    private int size;
    private ImageView tv_back;
    private RelativeLayout real;
    private ViewPager vp;
    private TextView shuliang;
    private int position;
    List<View> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_tpdetail);
        initView();


    }

    private void initView() {
        size = getIntent().getIntExtra("size",0);
        position = getIntent().getIntExtra("position", 0);
        tv_back = (ImageView) findViewById(R.id.tv_back);
        vp = (ViewPager) findViewById(R.id.vp);
        shuliang = (TextView) findViewById(R.id.shuliang);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                shuliang.setText("图片（"+(position+1)+"/"+size+"）");
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       for(int i=0;i<size;i++){
           //list.add(R.drawable.icon_mine)
       }

        vp.setAdapter(new ViewPager_Adapter(list,this));

    }
}
