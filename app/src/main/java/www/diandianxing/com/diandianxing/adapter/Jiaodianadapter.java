package www.diandianxing.com.diandianxing.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.MsgItmeActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.Info;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.StateClickListener;
import www.diandianxing.com.diandianxing.util.ToastUtils;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Jiaodianadapter extends BaseAdapter {
    private Context context;
    private List<GuanzhuJD> lists ;
    private ShareListener shareListener;

    private StateClickListener stateClickListener;

    public Jiaodianadapter(Context context,List<GuanzhuJD> lists,ShareListener shareListener,StateClickListener stateClickListener) {
        this.context = context;
        this.shareListener=shareListener;
        this.lists=lists;
        this.stateClickListener=stateClickListener;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

                    convertView = LayoutInflater.from(context).inflate(R.layout.fragment_duoone, null);
                    holder.img_tou = (ImageView) convertView.findViewById(R.id.img_tou);
                    holder.text_name = (TextView) convertView.findViewById(R.id.text_name);
                    holder.da_address = (TextView) convertView.findViewById(R.id.da_address);
                    holder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
                    holder.text_dengji = (TextView) convertView.findViewById(R.id.text_dengji);
                    holder.item_count = (TextView) convertView.findViewById(R.id.item_count);
                    holder.item_recycler = (RecyclerView) convertView.findViewById(R.id.item_recycler);
                    holder.text_colltet = (TextView)     convertView .findViewById(R.id.text_collect);
                    holder.text_zan = (TextView)     convertView .findViewById(R.id.text_zan);
            holder.ll_view=convertView.findViewById(R.id.ll_view);
                    holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final GuanzhuJD guanzhuJD = lists.get(position);

        holder.text_name.setText(guanzhuJD.userName);

        holder.da_address.setText(guanzhuJD.address+" "+ MyUtils.stampToDate(guanzhuJD.createTime));

holder.item_count.setText(guanzhuJD.postContent);

        holder.text_colltet.setText(guanzhuJD.collectCount);

        holder.text_zan.setText(guanzhuJD.dianZanCount);

        holder.text_dengji.setText(guanzhuJD.userLevel);

        Glide.with(context).load(guanzhuJD.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img_tou);


        if(Integer.parseInt(guanzhuJD.is_collect)==0){
            Drawable nav_up=context.getResources().getDrawable(R.drawable.icon_collect);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_colltet.setCompoundDrawables(nav_up, null, null, null);
        }else{
            Drawable nav_up=context.getResources().getDrawable(R.drawable.shouchang_xz_icon_3x);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_colltet.setCompoundDrawables(nav_up, null, null, null);
        }

        if(Integer.parseInt(guanzhuJD.is_zan)==0){
            Drawable nav_up=context.getResources().getDrawable(R.drawable.icon_dianzan);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_zan.setCompoundDrawables(nav_up, null, null, null);
        }else{
            Drawable nav_up=context.getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_zan.setCompoundDrawables(nav_up, null, null, null);
        }




        //分享
                holder.text_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                    }
                });
                //收藏
                holder.text_colltet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Integer.parseInt(guanzhuJD.is_collect)==0){

                            stateClickListener.ShouCangClickListener(Integer.parseInt(guanzhuJD.id),0,1,-1,position);

                        }

                    }
                });
                //点赞
                holder.text_zan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Integer.parseInt(guanzhuJD.is_zan)==0){

                            stateClickListener.DianZanClickListener(Integer.parseInt(guanzhuJD.id),0,0,-1,position);

                        }
                    }
                });




        holder.item_recycler.setLayoutManager(new GridLayoutManager(context,3));
        holder.item_recycler.setNestedScrollingEnabled(false);



        TPAdapter1 tpAdapter1 = new TPAdapter1(context,guanzhuJD.imagesList);
        holder.item_recycler.setAdapter(tpAdapter1);
holder.ll_view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context,JiaoDetailActivity.class);
        context.startActivity(intent);
    }
});

        holder.img_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MydynamicActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    public static class ViewHolder {

        public RecyclerView item_recycler;
        public ImageView img_tou;
        public TextView text_name;
        public TextView da_address;
        public ImageView imageView2;
        public TextView text_dengji;
        public TextView item_count;
        public LinearLayout ll_view;
        public TextView text_share,text_colltet,text_zan;
    }

    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;
    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();

        dialog.show();
        ll_wx=dialog.findViewById(R.id.ll_wx);
        ll_pyq=dialog.findViewById(R.id.ll_pyq);
        ll_qq=dialog.findViewById(R.id.ll_qq);
        ll_kj=dialog.findViewById(R.id.ll_kj);
        ll_wb=dialog.findViewById(R.id.ll_wb);
        quxiao=dialog.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ll_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(0);
            }
        });
        ll_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(1);
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(2);
            }
        });
        ll_kj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(3);
            }
        });
        ll_wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(4);
            }
        });
    }


}
