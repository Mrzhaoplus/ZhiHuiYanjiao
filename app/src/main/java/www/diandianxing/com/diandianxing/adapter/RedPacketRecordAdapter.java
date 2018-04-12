package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.R;

/**
 * Created by Administrator on 2018/4/11.
 */

public class RedPacketRecordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RedPacketRecordAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
