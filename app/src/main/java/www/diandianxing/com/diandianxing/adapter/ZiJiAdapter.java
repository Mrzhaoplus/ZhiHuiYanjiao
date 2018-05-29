package www.diandianxing.com.diandianxing.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.CustomReplayList;
import www.diandianxing.com.diandianxing.interfase.HuiFuClickListener;
import www.diandianxing.com.diandianxing.interfase.PinLunZJListener;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Administrator on 2018/4/25.
 */

public class ZiJiAdapter extends BaseQuickAdapter<CustomReplayList, BaseViewHolder> {

    private PinLunZJListener zj;

    public ZiJiAdapter(@LayoutRes int layoutResId, @Nullable List<CustomReplayList> data,PinLunZJListener zj) {
        super(layoutResId, data);
        this.zj=zj;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CustomReplayList item) {


        TextView tv_name1=helper.getView(R.id.tv_name1);
        TextView tv_name2=helper.getView(R.id.tv_name2);
        TextView tv_content_text=helper.getView(R.id.tv_content_text);
//        LinearLayout ll_item_hf=helper.getView(R.id.ll_item_hf);
//        tv_name1.setText(item.replName);
//        tv_name2.setText(item.beReturnedName);
//        tv_content_text.setText(item.replName+"回复"+item.beReturnedName+"："+item.replyContent);

        TextView tv_sc_pl = helper.getView(R.id.tv_sc_pl);

        if(SpUtils.getString(mContext,"userid","").equals(item.replyId)){

            tv_sc_pl.setVisibility(View.VISIBLE);

            tv_sc_pl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    zj.onPinLunZJListener(item.id);

                }
            });
        }else {
            tv_sc_pl.setVisibility(View.GONE);
        }




        SpannableStringBuilder spannable = new SpannableStringBuilder(item.replName+"回复"+item.beReturnedName+"："+item.replyContent);

        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#5cb3eb")),0,item.replName.length()
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#5cb3eb")),item.replName.length()+2,item.replName.length()+2+item.beReturnedName.length()
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_content_text.setText(spannable);

    }
}
