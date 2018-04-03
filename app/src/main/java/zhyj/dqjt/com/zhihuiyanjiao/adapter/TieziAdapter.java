package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.bean.Imagebean;
import zhyj.dqjt.com.zhihuiyanjiao.util.ToastUtils;

/**
 * Created by Administrator on 2018/4/3.
 */

public class TieziAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    OnItemClickListener mOnItemClickListener;

    DianClickListener dianClickListener;

    public TieziAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {

        RecyclerView rv_tp = helper.getView(R.id.rv_tp);
        ImageView iv_ddd = helper.getView(R.id.iv_ddd);
        TextView text_share = helper.getView(R.id.text_share);
        ArrayList<String> mList=new ArrayList<>();
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
        }
        rv_tp.setLayoutManager(new GridLayoutManager(mContext,3));
        rv_tp.setNestedScrollingEnabled(false);
        TPAdapter changegameAdapter = new TPAdapter(R.layout.tpp_item_view, mList);
        rv_tp.setAdapter(changegameAdapter);
        iv_ddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dianClickListener.onDianClickListener(helper.getAdapterPosition());
            }
        });
        text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.FxClickListener(helper.getAdapterPosition());
            }
        });
    }

    public class TPAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TPAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
            super(layoutResId, data);



        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {







        }






    }

    public interface DianClickListener{
        void onDianClickListener(int pos);
    }

    public void setOnDianClickListener(DianClickListener dianClickListener){
        this.dianClickListener=dianClickListener;
    }

    //设置接口回调用于adapter监听

    public interface OnItemClickListener{
        void ItemClick(View view,int position);

        void FxClickListener(int pos);
        void ScClickListener(int pos);
        void DzClickListener(int pos);

    }
    public void SetOnItemClickListener(OnItemClickListener listener){

        this.mOnItemClickListener=listener;

    }
}
