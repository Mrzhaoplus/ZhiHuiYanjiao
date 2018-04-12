package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.adapter.LocationAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by Administrator on 2018/4/4.
 */

public class LocationActivity extends BaseActivity {

    private RecyclerView rv_wz;

    private ImageView iv_ss;

    ArrayList<String> mList=new ArrayList<>();

    private ImageView include_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_location);
        rv_wz= (RecyclerView) findViewById(R.id.rv_wz);
        iv_ss= (ImageView) findViewById(R.id.iv_ss);
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
        rv_wz.setLayoutManager(new LinearLayoutManager(LocationActivity.this));
        rv_wz.setNestedScrollingEnabled(false);
        LocationAdapter locationAdapter  = new LocationAdapter(R.layout.location_item_view, mList);
        rv_wz.setAdapter(locationAdapter);
        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationActivity.this,SearchLocationActivity.class);
                startActivity(intent);
            }
        });
    }
}
