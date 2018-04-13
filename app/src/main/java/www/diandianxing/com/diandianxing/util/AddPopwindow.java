package www.diandianxing.com.diandianxing.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.MainActivity;
import www.diandianxing.com.diandianxing.ReleaseFocusActivity;
import www.diandianxing.com.diandianxing.VideoRecordingActivity;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AddPopwindow extends PopupWindow implements View.OnClickListener {

          private View rootView;
          private MainActivity mContext;
         private RelativeLayout contentView;
    private TextView btn_paike,btn_jiaodian;


    public AddPopwindow(MainActivity mContext) {
        this.mContext = mContext;
    }
    public void showMoreWindow(View anchor) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.pop_fragadd, null);
        int h = mContext.getWindowManager().getDefaultDisplay().getHeight();
        int w = mContext.getWindowManager().getDefaultDisplay().getWidth();
        setContentView(rootView);
        this.setWidth(w);
        this.setHeight(h - ScreenUtils.getStatusHeight(mContext));

        contentView = (RelativeLayout) rootView.findViewById(R.id.contentView);
        RelativeLayout close = (RelativeLayout) rootView.findViewById(R.id.r_close);
        /*
          寻找控件
         */
        btn_paike = (TextView) rootView.findViewById(R.id.btn_paike);
        btn_paike.setOnClickListener(this);
        btn_jiaodian = (TextView) rootView.findViewById(R.id.btn_jiaodian);
        btn_jiaodian.setOnClickListener(this);
        close.setBackgroundColor(0xFFFFFFFF);
        close.setOnClickListener(this);
        showAnimation(contentView);
        setOutsideTouchable(true);
        setFocusable(true);
        showAtLocation(anchor, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 显示进入动画效果
     * @param layout
     */
    private void showAnimation(ViewGroup layout) {
        //遍历根试图下的一级子试图
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            //忽略关闭组件
            if (child.getId() == R.id.r_close) {
                continue;
            }
            //设置所有一级子试图的点击事件
            child.setOnClickListener(this);
            child.setVisibility(View.INVISIBLE);
            //延迟显示每个子试图(主要动画就体现在这里)
            Observable.timer(i * 0, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            child.setVisibility(View.VISIBLE);
                            ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                            // fadeAnim.setDuration(300);
                            KickBackAnimator kickAnimator = new KickBackAnimator();
                            kickAnimator.setDuration(150);
                            fadeAnim.setEvaluator(kickAnimator);
                            fadeAnim.start();
                        }

                    });
        }

    }
    /**
     * 关闭动画效果
     * @param layout
     */
    private void closeAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == R.id.r_close) {
                continue;
            }
            Observable.timer((layout.getChildCount() - i - 1) * 30, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            child.setVisibility(View.VISIBLE);
                            ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 600);
                            fadeAnim.setDuration(200);
                            KickBackAnimator kickAnimator = new KickBackAnimator();
                            kickAnimator.setDuration(100);
                            fadeAnim.setEvaluator(kickAnimator);
                            fadeAnim.start();
                            fadeAnim.addListener(new Animator.AnimatorListener() {

                                @Override
                                public void onAnimationStart(Animator animation) {
                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
//                                    child.setVisibility(View.INVISIBLE);
                                    dismiss();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {
                                }
                            });
                        }


                    });

             }



    }

    @Override
    public void onClick(View v) {
        Intent intent;
          switch (v.getId()){
              case R.id.r_close:
                  if (isShowing()) {
                      closeAnimation(contentView);
                  }
                  break;
              case R.id.btn_paike:
//                  Toast.makeText(mContext, "aad", Toast.LENGTH_SHORT).show();
                  intent = new Intent(mContext, VideoRecordingActivity.class);
                  mContext.startActivity(intent);
                  dismiss();
                  break;
              case R.id.btn_jiaodian:
//                  Toast.makeText(mContext, "assad", Toast.LENGTH_SHORT).show();

                  intent = new Intent(mContext, ReleaseFocusActivity.class);
                  mContext.startActivity(intent);
                  dismiss();

                  break;

          }

    }



}
