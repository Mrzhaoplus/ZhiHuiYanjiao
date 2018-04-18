package www.diandianxing.com.diandianxing.fragment.messagefragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import www.diandianxing.com.diandianxing.adapter.Commentadapter;
import www.diandianxing.com.diandianxing.adapter.Praiseadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */
/*
   点赞消息
 */
public class Praisefragment extends BaseFragment {

    private RecyclerView praise_recycle;
    private SpringView spring_view;
    private TextView text_sure;

    @Override
    protected int setContentView() {
        return R.layout.fragment_praise;
    }
    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        praise_recycle = contentView.findViewById(R.id.praise_recycle);
        spring_view = contentView.findViewById(R.id.spring_view);
        Praiseadapter praiseadapter=new Praiseadapter(getActivity());
        praise_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        praise_recycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        praise_recycle.setAdapter(praiseadapter);
        praise_recycle.setNestedScrollingEnabled(false);
        spring_view.setType(SpringView.Type.FOLLOW);
        spring_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },5000);
               spring_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },5000);
                spring_view.onFinishFreshAndLoad();
            }
        });

         spring_view.setHeader(new DefaultHeader(getActivity()));
        spring_view.setFooter(new DefaultFooter(getActivity()));
        praiseadapter.setOnLongDeleteListener(longDeleteListener);
    }

    private Commentadapter.LongDeleteListener longDeleteListener = new Commentadapter.LongDeleteListener() {
        @Override
        public void OnLongDeleteListener(int pos) {

            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);

        }
    };

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_delete)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
