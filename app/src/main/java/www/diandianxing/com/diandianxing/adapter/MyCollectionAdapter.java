package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.R;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyCollectionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    boolean isqh;

    private GZClickListener gzClickListener;

    public MyCollectionAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {

        ImageView iv_wdsc_sc=helper.getView(R.id.iv_wdsc_sc);
        ImageView iv_wdsc_zw = helper.getView(R.id.iv_wdsc_zw);

        TextView tv_gz = helper.getView(R.id.tv_gz);

        if(isqh){
            iv_wdsc_sc.setVisibility(View.VISIBLE);
            iv_wdsc_zw.setVisibility(View.INVISIBLE);
        }else{
            iv_wdsc_sc.setVisibility(View.GONE);
            iv_wdsc_zw.setVisibility(View.GONE);
        }
        tv_gz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gzClickListener.onGZClickListener(helper.getAdapterPosition());

            }
        });


    }


    public interface GZClickListener{
        void onGZClickListener(int pos);
    }

    public  void setonGZClickListener(GZClickListener gzClickListener){
        this.gzClickListener=gzClickListener;
    }



    public  void setZT(boolean isqh){
        this.isqh=isqh;
    }



}