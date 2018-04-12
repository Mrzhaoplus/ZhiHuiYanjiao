package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.adapter.SignAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.MyContants;

/**
 * Created by Administrator on 2018/4/3.
 */

public class SignActivity extends BaseActivity {

    ImageView include_back;

    RecyclerView rv_dj;
    TextView tv_qdkz;
    ArrayList<String> mList = new ArrayList<>();
    Handler handler;
    BaseDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_sign);
        rv_dj= (RecyclerView) findViewById(R.id.rv_dj);
        include_back= (ImageView) findViewById(R.id.include_back);
        tv_qdkz= (TextView) findViewById(R.id.tv_qdkz);
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
        handler = new Handler();
        rv_dj.setLayoutManager(new LinearLayoutManager(SignActivity.this));
        rv_dj.setNestedScrollingEnabled(false);
        SignAdapter wccAdapter = new SignAdapter(R.layout.sign_item_view, mList);
        rv_dj.setAdapter(wccAdapter);

        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_qdkz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this, SignRuleActivity.class);
                startActivity(intent);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },2000);
    }

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(SignActivity.this);
        dialog = builder.setViewId(R.layout.dialog_qdjf)
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

        TextView tv_jf_zhi = dialog.findViewById(R.id.tv_jf_zhi);


        dialog.show();
    }


}
