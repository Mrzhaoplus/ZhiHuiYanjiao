package www.diandianxing.com.diandianxing.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.ShujuBean.Coll_Bean;
import www.diandianxing.com.diandianxing.util.ImageLoder;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyCollectionAdapter extends BaseQuickAdapter<Coll_Bean.DatasBean, BaseViewHolder> {

    boolean isqh;

    private GZClickListener gzClickListener;

    public MyCollectionAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<Coll_Bean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Coll_Bean.DatasBean item) {
        helper.setIsRecyclable(false);
        ImageView iv_wdsc_sc=helper.getView(R.id.iv_wdsc_sc);
        ImageView iv_wdsc_zw = helper.getView(R.id.iv_wdsc_zw);
        final TextView tv_gz = helper.getView(R.id.tv_gz);
        ImageView img_tou = helper.getView(R.id.img_tou);
        TextView uname = helper.getView(R.id.uname);
        TextView zixun = helper.getView(R.id.zixun);
        ImageView img_ztou = helper.getView(R.id.img_Ztou);

        img_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 gzClickListener.onTOUClickLinstener(helper.getAdapterPosition());
            }
        });



        Glide.with(helper.itemView).load(item.getPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img_tou);
        uname.setText(item.getNickName());
        zixun.setText(item.getPostTitle());
        List<String> postImagePaths = item.getPostImagePaths();
        if(postImagePaths.size()<1){
        }else{
            ImageLoader.getInstance().displayImage(item.getPostImagePaths().get(0),img_ztou, ImageLoder.getDefaultOption());
        }

        if(isqh){
            iv_wdsc_sc.setVisibility(View.VISIBLE);
            iv_wdsc_zw.setVisibility(View.INVISIBLE);
        }else{
            iv_wdsc_sc.setVisibility(View.GONE);
            iv_wdsc_zw.setVisibility(View.GONE);
        }
        if(item.getIsGz()==0){
            tv_gz.setVisibility(View.VISIBLE);
        }else{
            tv_gz.setVisibility(View.GONE);
        }
        tv_gz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gzClickListener.onGZClickListener(helper.getAdapterPosition());
            }
        });
        //点击暴露方法，暴露索引值
        iv_wdsc_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gzClickListener.onSCClickListener(helper.getAdapterPosition());
            }
        });
        //条目点击事件
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gzClickListener.onItemClickLisener(helper.getAdapterPosition());
            }
        });
    }
    public interface GZClickListener{
        void onGZClickListener(int pos);
        void onSCClickListener(int pos);
        void  onItemClickLisener(int pos);
        void  onTOUClickLinstener(int pos);
    }

    public  void setonGZClickListener(GZClickListener gzClickListener){
        this.gzClickListener=gzClickListener;
    }
    public  void setZT(boolean isqh){
        this.isqh=isqh;
    }



}
