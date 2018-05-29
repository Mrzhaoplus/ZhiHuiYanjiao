package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.zackratos.ultimatebar.UltimateBar;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.Login.LoginActivitys;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.Info;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class GuidePageActivity extends BaseActivity {
    private ViewPager vp;
    private GuidePagerAdapter mGuidePagerAdapter;
    private int[] imgurls = {R.drawable.guid1,R.drawable.guide2,R.drawable.guide3,R.drawable.guide4};

    private LinearLayout liner;
    private Handler handler;
    private Info info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();

        setContentView(R.layout.activity_guidepage);
        vp = (ViewPager) findViewById(R.id.vp);
        liner = (LinearLayout) findViewById(R.id.liner);
        info= (Info) getIntent().getSerializableExtra("imgs");



        for(int i=0;i<info.imgs.size();i++){

            View view  = new View(GuidePageActivity.this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30,30);
            layoutParams.rightMargin=15;
            layoutParams.leftMargin=15;
            view.setLayoutParams(layoutParams);
            if(i==0){
                view.setBackgroundResource(R.drawable.icon_true);
            }else {
                view.setBackgroundResource(R.drawable.icon_flase);
            }

            liner.addView(view);

        }


        handler = new Handler();
        if (mGuidePagerAdapter == null) {
            mGuidePagerAdapter = new GuidePagerAdapter();
        }

        vp.setAdapter(mGuidePagerAdapter);
//        vp.setOnPageChangeListener(new ViewPagerIndicator(this,vp,liner,5));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i=0;i<info.imgs.size();i++){
                    View view  = liner.getChildAt(i);
                    if(i==position){
                        view.setBackgroundResource(R.drawable.icon_true);
                    }else {
                        view.setBackgroundResource(R.drawable.icon_flase);
                    }
                }

                if (position == 3) {

                    handler.postDelayed(runnable,3000);


                }else{
                    handler.removeCallbacks(runnable);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {

            SpUtils.putBoolean(GuidePageActivity.this,"sousou",false);
            SpUtils.putInt(GuidePageActivity.this, "guid", 1);
            Intent intent = new Intent(GuidePageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    };

    private class GuidePagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(GuidePageActivity.this, R.layout.item_guide, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_guide);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            View tv_into = view.findViewById(R.id.tv_into);
            if (position ==info.imgs.size()-1) {
//                tv_into.setVisibility(View.VISIBLE);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    /*
                       存boolean值获取状态
                     */

                        SpUtils.putBoolean(GuidePageActivity.this,"sousou",false);
                        SpUtils.putInt(GuidePageActivity.this, "guid", 1);
                        Intent intent = new Intent(GuidePageActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            } else {
//                tv_into.setVisibility(View.GONE);
            }


//            Glide.with(GuidePageActivity.this).load(info.imgs.get(position)).into(imageView);

            Glide.with(GuidePageActivity.this)
                    .load(info.fileList.get(position))
                    .into(imageView);


            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return info.imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
