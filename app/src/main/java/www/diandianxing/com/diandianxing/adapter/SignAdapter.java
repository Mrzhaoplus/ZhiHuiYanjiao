package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.DengJiGZinfo;

/**
 * Created by Administrator on 2018/4/3.
 */

public class SignAdapter extends BaseQuickAdapter<DengJiGZinfo, BaseViewHolder> {

    private String maxinteger;

    public SignAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<DengJiGZinfo> data,String maxinteger) {
        super(layoutResId, data);

        this.maxinteger=maxinteger;

    }

    @Override
    protected void convert(BaseViewHolder helper, DengJiGZinfo item) {
        ProgressBar pb_jd = helper.getView(R.id.pb_jd);
        pb_jd.setMax(Integer.parseInt(maxinteger));
        TextView tv_dj_qd= helper.getView(R.id.tv_dj_qd);
        TextView tv_jf_qd=helper.getView(R.id.tv_jf_qd);

        tv_dj_qd.setText("等级"+item.signLevel+"：");


        pb_jd.setProgress(item.signInteger);

        tv_jf_qd.setText(item.signInteger+"积分");

    }
}
