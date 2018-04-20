package www.diandianxing.com.diandianxing.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.adapter.TPAdapter1;
import www.diandianxing.com.diandianxing.adapter.Tuijiantieadapter;
import www.diandianxing.com.diandianxing.bean.Imagebean;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class ChowuAdapter extends BaseAdapter {
    private Context context;
    private List<String> lists = new ArrayList<>();
    private TextView text_sure;
    private ShareListener shareListener;
    public ChowuAdapter(Context context,ShareListener shareListener) {
        this.context = context;
        this.shareListener=shareListener;
        data();
    }

    private void data() {
        for (int i = 0; i < 8; i++) {
            lists.add("");
        }
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Tuijiantieadapter.ViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new Tuijiantieadapter.ViewHolder();

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
            holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
            holder.rela_guanzhu = convertView.findViewById(R.id.rela_guanzhu);


            convertView.setTag(holder);
        } else {
            holder = (Tuijiantieadapter.ViewHolder) convertView.getTag();
        }

        holder.rela_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
            }
        });
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
                ToastUtils.show(context,"收藏",1);
            }
        });
        //点赞
        holder.text_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(context,"点赞",1);
            }
        });

List<String> aaa = new ArrayList<>();

        holder.item_recycler.setLayoutManager(new GridLayoutManager(context,3));
        holder.item_recycler.setNestedScrollingEnabled(false);
        TPAdapter1 tpAdapter1 = new TPAdapter1(context,aaa);
        holder.item_recycler.setAdapter(tpAdapter1);


        holder.item_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,JiaoDetailActivity.class);
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
        public RecyclerView recy_grade;
        public TextView text_share,text_colltet,text_zan;
        public RelativeLayout rela_guanzhu;
    }


    private void shumaDialog(int grary, int animationStyle) {
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
