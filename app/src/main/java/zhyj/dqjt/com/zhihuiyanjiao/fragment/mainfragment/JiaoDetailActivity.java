package zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.ArrayList;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.NoScrollGridAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;
import zhyj.dqjt.com.zhihuiyanjiao.util.NoScrollGridView;
import zhyj.dqjt.com.zhihuiyanjiao.util.lookphoto.ImagePagerActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class JiaoDetailActivity extends BaseActivity implements View.OnClickListener{
    ArrayList<String> urls_3 = new ArrayList<String>();
    private ImageView tv_back;
    private ImageView img_collect;
    private RelativeLayout real;
    private TextView text_title;
    private LinearLayout liner;
    private ImageView img_tou;
    private TextView text_name;
    private TextView text_address;
    private ImageView imageView2;
    private TextView text_dengji;
    private RelativeLayout relan;
    private RelativeLayout relas;
    private TextView text_count;
    private LinearLayout liners;
    private NoScrollGridView gridview;
    private NestedScrollView scroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_jiaodetail);
        initView();
    }

    private void initView() {
        tv_back = (ImageView) findViewById(R.id.tv_back);
        img_collect = (ImageView) findViewById(R.id.img_collect);
        real = (RelativeLayout) findViewById(R.id.real);
        text_title = (TextView) findViewById(R.id.text_title);
        liner = (LinearLayout) findViewById(R.id.liner);
        img_tou = (ImageView) findViewById(R.id.img_tou);
        text_name = (TextView) findViewById(R.id.text_name);
        text_address = (TextView) findViewById(R.id.text_address);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text_dengji = (TextView) findViewById(R.id.text_dengji);
        relan = (RelativeLayout) findViewById(R.id.relan);
        relas = (RelativeLayout) findViewById(R.id.relas);
        text_count = (TextView) findViewById(R.id.text_count);
        liners = (LinearLayout) findViewById(R.id.liners);
        gridview = (NoScrollGridView) findViewById(R.id.gridview);
        scroll = (NestedScrollView) findViewById(R.id.scroll);
        img_collect.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        data();
    }

    private void data() {
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
      //  urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698839_2302.jpg");

        if (urls_3  == null || urls_3 .size() == 0) { // 没有图片资源就隐藏GridView
            gridview.setVisibility(View.GONE);

        }

        if(urls_3 .size() == 1){
            gridview.setNumColumns(1);
            gridview.setAdapter(new NoScrollGridAdapter(this, urls_3));
        }

        else {
            gridview.setNumColumns(2);
            gridview.setAdapter(new NoScrollGridAdapter(this, urls_3));
        }
        //监听事件
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 imageBrower(position, urls_3);


            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.img_collect:
                break;
        }
    }

    /**
     * 打开图片查看器
     *
     * @param position
     * @param urls2
     */
    protected void imageBrower(int position, ArrayList<String> urls2) {
        Intent intent = new Intent(this, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        this.startActivity(intent);
    }
}
