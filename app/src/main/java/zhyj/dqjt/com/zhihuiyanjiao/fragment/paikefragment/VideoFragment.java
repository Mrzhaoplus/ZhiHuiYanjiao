package zhyj.dqjt.com.zhihuiyanjiao.fragment.paikefragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.Video_pinglun_Adapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment.MydynamicActivity;
import zhyj.dqjt.com.zhihuiyanjiao.util.BaseDialog;
import zhyj.dqjt.com.zhihuiyanjiao.util.DividerItemDecoration;

/**
 * Created by Mr赵 on 2018/4/8.
 */

public class VideoFragment extends BaseFragment implements View.OnClickListener {

    private ImageView video;
    private RecyclerView recy_view;
    private LinearLayout img_back;
    private RelativeLayout guanzhu;
    private ImageView tou;
    private ImageView frnxiang;
    private RadioButton zan;
    private TextView time;
    private TextView pinglun;
    private TextView text_zan;

    @Override
    protected int setContentView() {
        return R.layout.fragment_video;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        video = contentView.findViewById(R.id.video_view);
        recy_view = contentView.findViewById(R.id.rcy_view);
        img_back = contentView.findViewById(R.id.img_back);
        guanzhu = contentView.findViewById(R.id.guanzhu);
        tou = contentView.findViewById(R.id.video_tou);
        zan = contentView.findViewById(R.id.video_zan);
        frnxiang = contentView.findViewById(R.id.video_fenxiang);
        time = contentView.findViewById(R.id.text_time);
        pinglun = contentView.findViewById(R.id.text_pinglun);
        text_zan = contentView.findViewById(R.id.text_zan);
        img_back.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        tou.setOnClickListener(this);
        frnxiang.setOnClickListener(this);
        zan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String s = text_zan.getText().toString();
                int i = Integer.parseInt(s);
                if(b){
                    text_zan.setText((i+1)+"");
                }
            }
        });
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        recy_view.setNestedScrollingEnabled(false);
       //recy_view.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        Video_pinglun_Adapter video_pinglun_adapter = new Video_pinglun_Adapter(getActivity());
        recy_view.setAdapter(video_pinglun_adapter);
    }
    /*
    * 点击事件
    * */
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            //点击返回
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.guanzhu:
                   guanzhu.setVisibility(View.GONE);
                break;
            case R.id.video_tou:
                Intent intent = new Intent(getActivity(), MydynamicActivity.class);
                startActivity(intent);
                break;
            case R.id.video_fenxiang:
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
        }
    }

    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        //设置触摸dialog外围是否关闭
        //设置监听事件
        Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();

        dialog.show();
    }
}
