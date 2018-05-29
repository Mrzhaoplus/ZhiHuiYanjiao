package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import www.diandianxing.com.diandianxing.IssueReportActivity;
import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.MainActivity;
import www.diandianxing.com.diandianxing.ReleaseFocusActivity;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.FragEventBug;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class JiaodianActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_back;
    private ImageView img_question;
    private TextView text_guanzhu;
    private View v1;
    private LinearLayout liner1;
    private TextView text_tuijian;
    private View v2;
    private LinearLayout liner2;
    private TextView text_daren;
    private View v3;
    private LinearLayout liner3;
    private RelativeLayout relar;
    private FrameLayout fram_layout;
    private Fragment currfit;
    JIaodianFragment jiaodianfragment;
    OldnewFragment oldnewfragment;
    private boolean flag=true;
    private int msgg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_jiaodian);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.MRJD);
        registerReceiver(broadcastReceiver,intentFilter);
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        img_question = (ImageView) findViewById(R.id.img_question);
        text_guanzhu = (TextView) findViewById(R.id.text_guanzhu);
        v1 = (View) findViewById(R.id.v1);
        liner1 = (LinearLayout) findViewById(R.id.liner1);
        text_tuijian = (TextView) findViewById(R.id.text_tuijian);
        v2 = (View) findViewById(R.id.v2);
        liner2 = (LinearLayout) findViewById(R.id.liner2);
        text_daren = (TextView) findViewById(R.id.text_daren);
        v3 = (View) findViewById(R.id.v3);
        liner3 = (LinearLayout) findViewById(R.id.liner3);
        relar = (RelativeLayout) findViewById(R.id.relar);
        fram_layout = (FrameLayout) findViewById(R.id.fram_layout);
        boolean tianzhuan = getIntent().getBooleanExtra("tianzhuan", false);
        liner2.setOnClickListener(this);
        liner1.setOnClickListener(this);
        liner3.setOnClickListener(this);
        img_back.setOnClickListener(this);
        img_question.setOnClickListener(this);
        v1.setVisibility(View.VISIBLE);
        v2.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.INVISIBLE);
        text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
        text_daren.setTextColor(getResources().getColor(R.color.black_san));
        text_tuijian.setTextColor(getResources().getColor(R.color.black_san));


        if(tianzhuan){
            v3.setVisibility(View.VISIBLE);
            v2.setVisibility(View.INVISIBLE);
            v1.setVisibility(View.INVISIBLE);
            text_daren.setTextColor(getResources().getColor(R.color.text_orage));
            text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
            text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
            if(oldnewfragment==null){
                oldnewfragment=new OldnewFragment();
            }
            AddFragment(oldnewfragment);
        }else{
            if(jiaodianfragment==null){
                jiaodianfragment=new JIaodianFragment();
            }
            AddFragment(jiaodianfragment);
        }
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag=false;
        }

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            String action = intent.getAction();

            if(GlobalParams.MRJD.equals(action)){

                boolean tianzhuan = intent.getBooleanExtra("tianzhuan", false);
                if(tianzhuan){
                    v3.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.INVISIBLE);
                    v1.setVisibility(View.INVISIBLE);
                    text_daren.setTextColor(getResources().getColor(R.color.text_orage));
                    text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                    text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                    if(oldnewfragment==null){
                        oldnewfragment=new OldnewFragment();
                    }
                    AddFragment(oldnewfragment);
                }else{
                    if(jiaodianfragment==null){
                        jiaodianfragment=new JIaodianFragment();
                    }
                    AddFragment(jiaodianfragment);
                }


            }

        }
    };




    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(FragEventBug eveen){
        msgg = eveen.getMsgg();
        if(msgg ==3){
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.INVISIBLE);
            v3.setVisibility(View.INVISIBLE);
            text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
            text_daren.setTextColor(getResources().getColor(R.color.black_san));
            text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
            if(jiaodianfragment==null){
                jiaodianfragment=new JIaodianFragment();
            }
            AddFragment(jiaodianfragment);
        }else{
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.INVISIBLE);
            v3.setVisibility(View.INVISIBLE);
            text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
            text_daren.setTextColor(getResources().getColor(R.color.black_san));
            text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
            if(jiaodianfragment==null){
                jiaodianfragment=new JIaodianFragment();
            }
            AddFragment(jiaodianfragment);
        }
    }
    @Override
    public void onClick(View view) {

        Intent intent;
        int guid = SpUtils.getInt(JiaodianActivity.this, "guid", 0);
        switch (view.getId()){
            case R.id.liner1:
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
            if(jiaodianfragment==null){
                jiaodianfragment=new JIaodianFragment();
            }
                AddFragment(jiaodianfragment);
                break;
            case R.id.liner2:
//                v2.setVisibility(View.VISIBLE);
//                v1.setVisibility(View.INVISIBLE);
//                v3.setVisibility(View.INVISIBLE);
//                text_tuijian.setTextColor(getResources().getColor(R.color.text_orage));
//                text_daren.setTextColor(getResources().getColor(R.color.black_san));
//                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
//                if(tuijianfragment==null){
//                    tuijianfragment=new TuijianFragment();
//                }
//                AddFragment(tuijianfragment);
                break;
            case R.id.liner3:
                v3.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v1.setVisibility(View.INVISIBLE);
                text_daren.setTextColor(getResources().getColor(R.color.text_orage));
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                if(oldnewfragment==null){
                    oldnewfragment=new OldnewFragment();
                }
                AddFragment(oldnewfragment);
                break;

            case R.id.img_back:
                finish();
                break;
            case R.id.img_question:

                if(guid!=2){
                    startActivity(new Intent(JiaodianActivity.this,LoginActivity.class));
                }else{
                    intent = new Intent(JiaodianActivity.this, ReleaseFocusActivity.class);
                    intent.putExtra("type",0);
                    startActivity(intent);
                }

                break;
        }
    }

      public void AddFragment(Fragment f){

          FragmentManager supportFragmentManager = getSupportFragmentManager();
          FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
             if(currfit!=null){
                 fragmentTransaction.hide(currfit);

             }
           if(!f.isAdded()){
              fragmentTransaction.add(R.id.fram_layout,f);
           }
             fragmentTransaction.show(f);
          fragmentTransaction.commitAllowingStateLoss();
          currfit=f;


      }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
        unregisterReceiver(broadcastReceiver);
    }
}
