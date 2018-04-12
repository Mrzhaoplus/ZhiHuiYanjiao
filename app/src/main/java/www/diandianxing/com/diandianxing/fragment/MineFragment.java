package www.diandianxing.com.diandianxing.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import www.diandianxing.com.diandianxing.MyCollectionActivity;
import www.diandianxing.com.diandianxing.SignActivity;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.fragment.minefragment.MyFansiActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MyFllowActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.my.JourActivity;
import www.diandianxing.com.diandianxing.my.ShareActivity;
import www.diandianxing.com.diandianxing.set.AboutweActivity;
import www.diandianxing.com.diandianxing.set.Feedback;
import www.diandianxing.com.diandianxing.set.SetActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{
    public View rootView;
    public TextView text_day;
    public ImageView imageView2;
    public TextView guan_num;
    public LinearLayout text_guanzhu;
    public TextView fen_num;
    public LinearLayout text_fensi;
    public TextView dong_num;
    public LinearLayout text_dongtai;
    public TextView collect_num;
    public LinearLayout text_collect;
    public TextView text_qiandao;
    public TextView my_car;
    public RelativeLayout real_car;
    public TextView text_about;
    public RelativeLayout about_we;
    public TextView text_suggest;
    public RelativeLayout real_suggest;
    public TextView text_kefu;
    public RelativeLayout real_kefu;
    public TextView text_yaoqing;
    public RelativeLayout real_yaoqing;
    private ImageView iv_grtx;
    private ImageView iv_sz;

    @Override
    protected int setContentView() {

        return R.layout.fragment_mine;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        this.text_day = (TextView) contentView.findViewById(R.id.text_day);
        this.imageView2 = (ImageView) contentView.findViewById(R.id.imageView2);
        this.guan_num = (TextView) contentView.findViewById(R.id.guan_num);
        this.text_guanzhu = (LinearLayout) contentView.findViewById(R.id.text_guanzhu);
        this.fen_num = (TextView) contentView.findViewById(R.id.fen_num);
        this.text_fensi = (LinearLayout) contentView.findViewById(R.id.text_fensi);
        this.dong_num = (TextView) contentView.findViewById(R.id.dong_num);
        this.text_dongtai = (LinearLayout) contentView.findViewById(R.id.text_dongtai);
        this.collect_num = (TextView) contentView.findViewById(R.id.collect_num);
        this.text_collect = (LinearLayout) contentView.findViewById(R.id.text_collect);
        this.text_qiandao = (TextView) contentView.findViewById(R.id.text_qiandao);
        this.my_car = (TextView) contentView.findViewById(R.id.my_car);
        this.real_car = (RelativeLayout) contentView.findViewById(R.id.real_car);
        this.text_about = (TextView) contentView.findViewById(R.id.text_about);
        this.about_we = (RelativeLayout) contentView.findViewById(R.id.about_we);
        this.text_suggest = (TextView) contentView.findViewById(R.id.text_suggest);
        this.real_suggest = (RelativeLayout) contentView.findViewById(R.id.real_suggest);
        this.text_kefu = (TextView) contentView.findViewById(R.id.text_kefu);
        this.real_kefu = (RelativeLayout) contentView.findViewById(R.id.real_kefu);
        this.text_yaoqing = (TextView) contentView.findViewById(R.id.text_yaoqing);
        this.real_yaoqing = (RelativeLayout) contentView.findViewById(R.id.real_yaoqing);
        iv_sz=contentView.findViewById(R.id.iv_sz);
        iv_grtx=contentView.findViewById(R.id.iv_grtx);
        text_guanzhu.setOnClickListener(this);//设置监听
        text_fensi.setOnClickListener(this);
        text_dongtai.setOnClickListener(this);
        text_collect.setOnClickListener(this);
        text_qiandao.setOnClickListener(this);
        iv_grtx.setOnClickListener(this);
        real_car.setOnClickListener(this);
        about_we.setOnClickListener(this);
        real_suggest.setOnClickListener(this);
        real_kefu.setOnClickListener(this);
        real_yaoqing.setOnClickListener(this);
        iv_sz.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

         switch (view.getId()){
             case R.id.text_guanzhu://关注
                 Intent intent=new Intent(getActivity(), MyFllowActivity.class);
                 startActivity(intent);
                 break;

             case R.id.text_fensi://粉丝
                 Intent intent1=new Intent(getActivity(), MyFansiActivity.class);
                 startActivity(intent1);
                 break;
             case R.id.text_dongtai:
                  Intent intent2=new Intent(getActivity(), MydynamicActivity.class);
                 startActivity(intent2);
                 break;
             case R.id.text_collect:
                 Intent intent3=new Intent(getActivity(), MyCollectionActivity.class);
                 startActivity(intent3);
                 break;
             case R.id.text_qiandao:
                 Intent intent4=new Intent(getActivity(), SignActivity.class);
                 startActivity(intent4);
                 break;
             case R.id.iv_grtx:
                 Intent intent5=new Intent(getActivity(), MydynamicActivity.class);
                 startActivity(intent5);
                 break;
             case R.id.real_car:
                 Intent intent6=new Intent(getActivity(),JourActivity.class);
                 startActivity(intent6);
                 break;
             case R.id.about_we:
                 Intent gy=new Intent(getActivity(),AboutweActivity.class);
                 startActivity(gy);
                 break;
             //意见反馈
             case R.id.real_suggest:
                 Intent yjfk=new Intent(getActivity(),Feedback.class);
                 startActivity(yjfk);
                 break;
             case R.id.real_kefu:
                 showphotoDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion);

                 break;
             case R.id.real_yaoqing:
                 Intent fx=new Intent(getActivity(),ShareActivity.class);
                 startActivity(fx);
                 break;

             //设置
             case R.id.iv_sz:
                 Intent sz=new Intent(getActivity(), SetActivity.class);
                 startActivity(sz);
                 break;

         }

    }

    private void showphotoDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_kefu)
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
        //拍照
        dialog.getView(R.id.text_kefu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机选取

                dialog.dismiss();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "010-1234563245"));
                //跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });

        dialog.show();
    }

}
