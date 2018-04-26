package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.CustomReplayList;

/**
 * Created by Administrator on 2018/4/25.
 */

public class ZiJiAdapter extends BaseQuickAdapter<CustomReplayList, BaseViewHolder> {

    public ZiJiAdapter(@LayoutRes int layoutResId, @Nullable List<CustomReplayList> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, CustomReplayList item) {


        TextView tv_name1=helper.getView(R.id.tv_name1);
        TextView tv_name2=helper.getView(R.id.tv_name2);
        TextView tv_content_text=helper.getView(R.id.tv_content_text);

        tv_name1.setText(item.replName);
        tv_name2.setText(item.beReturnedName);
        tv_content_text.setText(item.replyContent);


    }
}
