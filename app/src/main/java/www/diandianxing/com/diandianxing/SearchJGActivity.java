package www.diandianxing.com.diandianxing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.fragment.mainfragment.LuKuangFragment;
import www.diandianxing.com.diandianxing.fragment.mainfragment.ZiXunFragment;
import www.diandianxing.com.diandianxing.fragment.sousuofragment.SearchJdFragment;
import www.diandianxing.com.diandianxing.fragment.sousuofragment.SearchPaikeFragment;
import www.diandianxing.com.diandianxing.fragment.sousuofragment.SearchTieZiFragment;
import www.diandianxing.com.diandianxing.util.MyContants;

/**
 * Created by Administrator on 2018/5/8.
 */

public class SearchJGActivity extends BaseActivity implements View.OnClickListener{


    private ImageView img_back;

    private LinearLayout liner1,liner2,liner3,liner4,liner5;

    private TextView text_guanzhu,text_tuijian,text_daren,text_zixun,text_lukuang;
    private View v1,v2,v3,v4,v5;
    private Fragment currfit;

    private SearchPaikeFragment paike;
    private SearchTieZiFragment tiezi;
    private SearchJdFragment jd;
    private ZiXunFragment zixun;
    private LuKuangFragment lukuang;
    private String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_search_jg);
        img_back= (ImageView) findViewById(R.id.img_back);

        liner1= (LinearLayout) findViewById(R.id.liner1);
        liner2= (LinearLayout) findViewById(R.id.liner2);
        liner3= (LinearLayout) findViewById(R.id.liner3);
        liner4= (LinearLayout) findViewById(R.id.liner4);
        liner5= (LinearLayout) findViewById(R.id.liner5);
        text_guanzhu= (TextView) findViewById(R.id.text_guanzhu);
        text_tuijian= (TextView) findViewById(R.id.text_tuijian);
        text_daren= (TextView) findViewById(R.id.text_daren);
        text_zixun= (TextView) findViewById(R.id.text_zixun);
        text_lukuang= (TextView) findViewById(R.id.text_lukuang);
        v1=findViewById(R.id.v1);
        v2=findViewById(R.id.v2);
        v3=findViewById(R.id.v3);
        v4=findViewById(R.id.v4);
        v5=findViewById(R.id.v5);

        content=getIntent().getStringExtra("content");

        if(paike==null){
            paike = new SearchPaikeFragment();
            Bundle b = new Bundle();
            b.putString("search",content);
            paike.setArguments(b);
        }
        AddFragment(paike);


        img_back.setOnClickListener(this);
        liner1.setOnClickListener(this);
        liner2.setOnClickListener(this);
        liner3.setOnClickListener(this);
        liner4.setOnClickListener(this);
        liner5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.liner1:

                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                text_zixun.setTextColor(getResources().getColor(R.color.black_san));
                text_lukuang.setTextColor(getResources().getColor(R.color.black_san));
                if(paike==null){
                    paike = new SearchPaikeFragment();
                    Bundle b = new Bundle();
                    b.putString("search",content);
                    paike.setArguments(b);
                }
                AddFragment(paike);
                break;
            case R.id.liner2:

                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                text_tuijian.setTextColor(getResources().getColor(R.color.text_orage));
                text_zixun.setTextColor(getResources().getColor(R.color.black_san));
                text_lukuang.setTextColor(getResources().getColor(R.color.black_san));

                if(tiezi==null){
                    tiezi = new SearchTieZiFragment();
                    Bundle b = new Bundle();
                    b.putString("search",content);
                    tiezi.setArguments(b);
                }
                AddFragment(tiezi);

                break;
            case R.id.liner3:

                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.VISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                text_daren.setTextColor(getResources().getColor(R.color.text_orage));
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                text_zixun.setTextColor(getResources().getColor(R.color.black_san));
                text_lukuang.setTextColor(getResources().getColor(R.color.black_san));

                if(jd==null){
                    jd = new SearchJdFragment();
                    Bundle b = new Bundle();
                    b.putString("search",content);
                    jd.setArguments(b);
                }
                AddFragment(jd);

                break;
            case R.id.liner4:
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.VISIBLE);
                v5.setVisibility(View.INVISIBLE);
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                text_zixun.setTextColor(getResources().getColor(R.color.text_orage));
                text_lukuang.setTextColor(getResources().getColor(R.color.black_san));

                if(zixun==null){
                    zixun = new ZiXunFragment();
                    Bundle b = new Bundle();
                    b.putString("search",content);
                    zixun.setArguments(b);
                }
                AddFragment(zixun);

                break;
            case R.id.liner5:
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.VISIBLE);
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                text_zixun.setTextColor(getResources().getColor(R.color.black_san));
                text_lukuang.setTextColor(getResources().getColor(R.color.text_orage));
                if(lukuang==null){
                    lukuang = new LuKuangFragment();
                    Bundle b = new Bundle();
                    b.putString("search",content);
                    lukuang.setArguments(b);
                }
                AddFragment(lukuang);
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
            fragmentTransaction.add(R.id.fl_ss,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit=f;


    }


}
