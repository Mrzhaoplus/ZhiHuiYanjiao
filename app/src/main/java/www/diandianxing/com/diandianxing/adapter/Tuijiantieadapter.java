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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.Imagebean;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.GuanzhuClickListener;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.StateClickListener;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by ASUS on 2018/3/19.
 * m每日焦点推荐热帖
 */

public class Tuijiantieadapter extends BaseAdapter {
    private Context context;
    private List<GuanzhuJD> lists ;
    private TextView text_sure;
    private ShareListener shareListener;
    private StateClickListener stateClickListener;
    private GuanzhuClickListener guanzhuClickListener;
    public Tuijiantieadapter(Context context,ShareListener shareListener,List<GuanzhuJD> lists,StateClickListener stateClickListener,GuanzhuClickListener guanzhuClickListener) {
        this.context = context;
        this.shareListener=shareListener;
        this.lists=lists;
        this.stateClickListener=stateClickListener;
        this.guanzhuClickListener=guanzhuClickListener;
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
        final ViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();

                    convertView = LayoutInflater.from(context).inflate(R.layout.fragment_tuiduoone, null);
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
                    holder.rela_guanzhu = convertView.findViewById(R.id.rela_guanzhu);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final GuanzhuJD guanzhuJD = lists.get(position);

        holder.text_name.setText(guanzhuJD.userName);


        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String end = sf.format(date);
        String fb = MyUtils.stampToDate(guanzhuJD.updateTime);

        String time = MyUtils.dateDiff(fb, end, "yyyy-MM-dd HH:mm:ss",guanzhuJD.updateTime);

        holder.da_address.setText(time+" "+ guanzhuJD.address);

        holder.item_count.setText(guanzhuJD.postContent);

        if(Integer.parseInt(guanzhuJD.collectCount)==0){
            holder.text_colltet.setText("收藏");
        }else {
            holder.text_colltet.setText(guanzhuJD.collectCount);
        }

        if(Integer.parseInt(guanzhuJD.dianZanCount)==0){
            holder.text_zan.setText("点赞");
        }else{
            holder.text_zan.setText(guanzhuJD.dianZanCount);
        }

        holder.text_dengji.setText(guanzhuJD.userLevel);

        Glide.with(context).load(guanzhuJD.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img_tou);

        if(isjd){

            holder.text_colltet.setEnabled(true);
            holder.text_zan.setEnabled(true);
        }

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

        if(Integer.parseInt(guanzhuJD.is_focus)==0){
            holder.rela_guanzhu.setVisibility(View.VISIBLE);
        }else{
            holder.rela_guanzhu.setVisibility(View.INVISIBLE);
        }


        holder.rela_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int guid = SpUtils.getInt(context, "guid", 0);
                if(guid!=2){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else{

                    if(Integer.parseInt(guanzhuJD.is_focus)==0){
                        shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion,position);
                    }
                }

            }
        });
        //分享
        holder.text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion,position);
            }
        });
        //收藏
        holder.text_colltet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int guid = SpUtils.getInt(context, "guid", 0);
                if(guid!=2){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else{
                    holder.text_colltet.setEnabled(false);
                    if(Integer.parseInt(guanzhuJD.is_collect)==0){

                        stateClickListener.ShouCangClickListener(Integer.parseInt(guanzhuJD.id),0,1,-1,position);

                    }else{
                        stateClickListener.QuXiaoShouCangClickListener(Integer.parseInt(guanzhuJD.id),0,1,position);
                    }

                }
            }
        });
        //点赞
        holder.text_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int guid = SpUtils.getInt(context, "guid", 0);
                if(guid!=2){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else{
                    holder.text_zan.setEnabled(false);
                    if(Integer.parseInt(guanzhuJD.is_zan)==0){

                        stateClickListener.DianZanClickListener(Integer.parseInt(guanzhuJD.id),0,0,-1,position);

                    }else{
                        stateClickListener.QuXiaoDianZanClickListener(Integer.parseInt(guanzhuJD.id),0,0,position);
                    }
                }
            }
        });
        if(guanzhuJD.imagesList.size()>3){
            holder.item_recycler.setLayoutManager(new GridLayoutManager(context,3));
        }else{
            holder.item_recycler.setLayoutManager(new GridLayoutManager(context,guanzhuJD.imagesList.size()));
        }
        holder.item_recycler.setNestedScrollingEnabled(false);
        TPAdapter1 tpAdapter1 = new TPAdapter1(context,guanzhuJD.imagesList);
        holder.item_recycler.setAdapter(tpAdapter1);


        holder.ll_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,JiaoDetailActivity.class);
                intent.putExtra("guanzhu",guanzhuJD);
                intent.putExtra("title",guanzhuJD.objName);
                context.startActivity(intent);
            }
        });

        holder.img_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MydynamicActivity.class);
                intent.putExtra("uid",guanzhuJD.userId);
                context.startActivity(intent);
            }
        });

        return convertView;
    }


    boolean isjd=true;

    public void setEnable(boolean isjd){
        this.isjd=isjd;
    }



    public static class ViewHolder {

        public RecyclerView item_recycler;
        public ImageView img_tou;
        public TextView text_name;
        public TextView da_address;
        public ImageView imageView2;
        public TextView text_dengji;
        public TextView item_count;
        public RecyclerView recy_grade;
        public LinearLayout ll_view;
        public TextView text_share,text_colltet,text_zan;
        public RelativeLayout rela_guanzhu;
    }


    private void shumaDialog(int grary, int animationStyle, final int pos) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_guanzhu)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
       text_sure.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               GuanzhuJD guanzhuJD = lists.get(pos);

               guanzhuClickListener.OnGuanzhuClickListener(Integer.parseInt(guanzhuJD.userId),pos);

               dialog.dismiss();
           }
       });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;
    private void showFXDialog(int grary, int animationStyle, final int pos) {
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
                shareListener.OnShareListener(0,pos);
            }
        });
        ll_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(1,pos);
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(2,pos);
            }
        });
        ll_kj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(3,pos);
            }
        });
        ll_wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(4,pos);
            }
        });
    }
}
