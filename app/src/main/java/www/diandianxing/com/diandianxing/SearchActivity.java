package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import www.diandianxing.com.diandianxing.adapter.Search_adapter;
import www.diandianxing.com.diandianxing.interfase.SouLadel_presenter_interfise;
import www.diandianxing.com.diandianxing.model.SouLabel_bean;
import www.diandianxing.com.diandianxing.presenter.SouLabel_Presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, SouLadel_presenter_interfise {

    private ImageView img_back;
    private ImageView sou;
    private EditText ed_seach;
    private TextView search;
    private EditText ed_search;
    private RecyclerView recycler_view;
    private SouLabel_Presenter souLabel_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        ed_seach = (EditText) findViewById(R.id.ed_search);
        search = (TextView) findViewById(R.id.search);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_search);
        img_back.setOnClickListener(this);
        search.setOnClickListener(this);
        //引用
        souLabel_presenter = new SouLabel_Presenter(this);
        souLabel_presenter.getpath(Api.token);





    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.search:
                //获取输入框内容
                String content = ed_seach.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(SearchActivity.this, "输入内容为空", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent1 = new Intent(SearchActivity.this, ZixunDitailsActivity.class);
                    startActivity(intent1);

                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       souLabel_presenter.getkong();
    }

    @Override
    public void getsuccess(SouLabel_bean souLabel_bean) {
        List<String> datas = souLabel_bean.getDatas();
        recycler_view.setLayoutManager(new GridLayoutManager(SearchActivity.this,4));
        recycler_view.setNestedScrollingEnabled(false);
        Search_adapter search_adapter = new Search_adapter(SearchActivity.this,datas);
        recycler_view.setAdapter(search_adapter);




    }
}
