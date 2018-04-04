package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import zhyj.dqjt.com.zhihuiyanjiao.R;

/**
 * Created by Administrator on 2018/4/4.
 */

public class TagAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int pos=-1;

    private Context content;

    public TagAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data, Context content) {
        super(layoutResId, data);
        this.content=content;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RelativeLayout rl_bk=helper.getView(R.id.rl_bk);
        TextView tv_type_content=helper.getView(R.id.tv_type_content);

        if(pos==helper.getAdapterPosition()){

            rl_bk.setBackgroundResource(R.drawable.shape_xz_ls_jx);
            tv_type_content.setTextColor(content.getResources().getColor(R.color.white));
        }else{
            rl_bk.setBackgroundResource(R.drawable.shape_ls_jx);
            tv_type_content.setTextColor(content.getResources().getColor(R.color.ls_text));
        }


    }

    public void setXZ(int pos){
        this.pos=pos;
    }

}
