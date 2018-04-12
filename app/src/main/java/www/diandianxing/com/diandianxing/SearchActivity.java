package www.diandianxing.com.diandianxing;

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

import www.diandianxing.com.diandianxing.adapter.Search_adapter;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back;
    private ImageView sou;
    private EditText ed_seach;
    private TextView search;
    private EditText ed_search;
    private RecyclerView recycler_view;

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
        recycler_view.setLayoutManager(new GridLayoutManager(SearchActivity.this,4));
        recycler_view.setNestedScrollingEnabled(false);
        Search_adapter search_adapter = new Search_adapter(this);
          recycler_view.setAdapter(search_adapter);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.search:
                //获取输入框内容
                String content = ed_seach.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(SearchActivity.this, "输入框为空", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SearchActivity.this, content, Toast.LENGTH_SHORT).show();
                    ed_seach.setText("");
                }
                break;
        }
    }

    private void submit() {
        // validate
        String search = ed_search.getText().toString().trim();
        if (TextUtils.isEmpty(search)) {
            Toast.makeText(this, "请输入地址", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
