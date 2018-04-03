package zhyj.dqjt.com.zhihuiyanjiao.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.SignRuleActivity;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.SignAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;

/**
 * Created by Administrator on 2018/4/3.
 */

public class SignActivity extends BaseActivity {

    ImageView include_back;

    RecyclerView rv_dj;
    TextView tv_qdkz;
    ArrayList<String> mList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        rv_dj= (RecyclerView) findViewById(R.id.rv_dj);
        include_back= (ImageView) findViewById(R.id.include_back);
        tv_qdkz= (TextView) findViewById(R.id.tv_qdkz);
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }

        rv_dj.setLayoutManager(new LinearLayoutManager(SignActivity.this));
        rv_dj.setNestedScrollingEnabled(false);
        SignAdapter wccAdapter = new SignAdapter(R.layout.sign_item_view, mList);
        rv_dj.setAdapter(wccAdapter);

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
}
