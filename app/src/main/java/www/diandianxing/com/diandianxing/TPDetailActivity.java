package www.diandianxing.com.diandianxing;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.adapter.ViewPager_Adapter;
import www.diandianxing.com.diandianxing.bean.Info;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;

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
        size = getIntent().getIntExtra("size", 0);
        position = getIntent().getIntExtra("position", 0);
        Info info = (Info) getIntent().getSerializableExtra("imgs");

        tv_back = (ImageView) findViewById(R.id.tv_back);
        vp = (ViewPager) findViewById(R.id.vp);
        shuliang = (TextView) findViewById(R.id.shuliang);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(info!=null){
            List<String> imgs = info.imgs;

            if(imgs!=null){
                for (int i = 0; i < size; i++) {
                    ImageView imageView = new ImageView(this);

                    Glide.with(TPDetailActivity.this).load(imgs.get(i)).into(imageView);

                    list.add(imageView);
                }
            }
        }





        vp.setAdapter(new ViewPager_Adapter(list, TPDetailActivity.this, position));
        vp.setCurrentItem(position);

        //监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("============", "position:" + position );
                shuliang.setText("图片（" + (position + 1) + "/" + size + "）");
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
