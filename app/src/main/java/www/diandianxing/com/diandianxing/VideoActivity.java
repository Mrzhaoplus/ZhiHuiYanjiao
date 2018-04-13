package www.diandianxing.com.diandianxing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.fragment.paikefragment.VideoFragment;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;

public class VideoActivity extends AppCompatActivity {

    private FrameLayout fl;
    private View view;
    private EditText ed_text;
    private Button button_fabu;
    private Fragment currfit;
    private VideoFragment videoFragment;
List<String>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_video);
        initView();
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        view = (View) findViewById(R.id.view);
        ed_text = (EditText) findViewById(R.id.ed_text);
        button_fabu = (Button) findViewById(R.id.button_fabu);
          if(videoFragment==null){
              videoFragment = new VideoFragment();
          }
        AddFragment(videoFragment);
    }
    /*
    * 动态添加fragment方法
    * */
    public void AddFragment(Fragment f){

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if(currfit !=null){
            fragmentTransaction.hide(currfit);

        }
        if(!f.isAdded()){
            fragmentTransaction.add(R.id.fl,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;


    }



}