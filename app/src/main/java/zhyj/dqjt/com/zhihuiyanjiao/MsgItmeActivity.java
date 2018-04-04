package zhyj.dqjt.com.zhihuiyanjiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.adapter.MsgitmeAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.util.BaseDialog;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

public class MsgItmeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back;
    private ListView list_view;
   private List<String>list=new ArrayList<>();
   private Boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_msg_itme);
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        list_view = (ListView) findViewById(R.id.list_view);
        img_back.setOnClickListener(this);

            //集合添加数据
        for (int i=0;i<6;i++){
            list.add(i+"");
        }
        /*
        * 适配器
        * */
        MsgitmeAdapter msgitmeAdapter = new MsgitmeAdapter(list, this);
        list_view.setAdapter(msgitmeAdapter);

        /*
        * 点击事件
        * */
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MsgItmeActivity.this, MagXiangqingActivity.class);
                startActivity(intent);
            }
        });

        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion,i);
                return true;
            }
        });


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }

/*
* 对话框
* */
    private void shumaDialog(int grary, int animationStyle, final int position) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_shanchu)
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
        TextView text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //删除
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MsgItmeActivity.this,"删除了"+position,Toast.LENGTH_SHORT).show();
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
