package zhyj.dqjt.com.zhihuiyanjiao.util.lookphoto;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class HackyViewPager extends ViewPager {
    public HackyViewPager(Context context) {
        super(context);
    }
    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // 不理会
            Log.e("TAG", "hacky viewpager error1");
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            // 不理会
            Log.e("TAG", "hacky viewpager error2");
            return false;
        }
    }

}
