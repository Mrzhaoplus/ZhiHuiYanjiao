package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.RedPacketInfo;

/**
 * Created by Administrator on 2018/4/11.
 */

public class RedPacketRecordAdapter extends BaseQuickAdapter<RedPacketInfo, BaseViewHolder> {

    public RedPacketRecordAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<RedPacketInfo> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, RedPacketInfo item) {

        TextView tv_hqsj=helper.getView(R.id.tv_hqsj);
        TextView tv_hbqs = helper.getView(R.id.tv_hbqs);

        String[] split = item.add_time.split(" ");
        tv_hqsj.setText(split[0]);
        tv_hbqs.setText("+"+item.gold+"元");

    }
}
