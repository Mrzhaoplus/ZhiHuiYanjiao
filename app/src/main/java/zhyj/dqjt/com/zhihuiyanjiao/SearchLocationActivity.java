package zhyj.dqjt.com.zhihuiyanjiao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import zhyj.dqjt.com.zhihuiyanjiao.adapter.SearchLocationAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

/**
 * Created by Administrator on 2018/4/4.
 */

public class SearchLocationActivity extends BaseActivity {

    private RecyclerView rv_ss;

    ArrayList<String> mList=new ArrayList<>();

    private ImageView include_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_search_location);

        rv_ss= (RecyclerView) findViewById(R.id.rv_ss);
        include_back= (ImageView) findViewById(R.id.include_back);
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        rv_ss.setLayoutManager(new LinearLayoutManager(SearchLocationActivity.this));
        rv_ss.setNestedScrollingEnabled(false);
        SearchLocationAdapter locationAdapter  = new SearchLocationAdapter(R.layout.location_item_view, mList);
        rv_ss.setAdapter(locationAdapter);
        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
