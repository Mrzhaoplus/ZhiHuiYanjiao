package www.diandianxing.com.diandianxing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;

public class LukuangDitailsActivity extends AppCompatActivity {

    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_lukuang_ditails);
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
