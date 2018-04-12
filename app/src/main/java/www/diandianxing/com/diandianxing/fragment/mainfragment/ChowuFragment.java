package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import www.diandianxing.com.diandianxing.adapter.ChowuAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class ChowuFragment extends BaseFragment {

    private ListView lv;

    @Override
    protected int setContentView() {
        return R.layout.frag_chowu;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        lv = contentView.findViewById(R.id.lv);
        ChowuAdapter chowuAdapter = new ChowuAdapter(getActivity());
        lv.setAdapter(chowuAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), JiaoDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
