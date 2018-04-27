package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import www.diandianxing.com.diandianxing.ShujuBean.Zan_msg_Bean;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Commentadapter extends RecyclerView.Adapter<Commentadapter.Myviewholder> {
    private Context context;
    List<Zan_msg_Bean.DatasBean> list=new ArrayList<>();
    private LongDeleteListener longDeleteListener;

    public Commentadapter(Context context, List<Zan_msg_Bean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        Myviewholder myviewholder = new Myviewholder(LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false));
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(final Myviewholder holder, final int position) {
        Glide.with(context).load(list.get(position).getPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img_tou);
           holder.text_name.setText(list.get(position).getNickName());
        holder.dengji.setText(list.get(position).getOperationType()+"");
        String dateToString = getDateToString(String.valueOf(list.get(position).getCreateTime() / 1000));
        holder.da_zan.setText(dateToString);
        holder.text_username.setText("评论："+list.get(position).getContent());
        int objType = list.get(position).getObjType();
        if(objType==0){
            holder.yuantie.setText("原帖："+list.get(position).getTitle());
        }else if (objType==1){
            holder.yuantie.setText("拍客："+list.get(position).getTitle());
        }

        //长按删除
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longDeleteListener.OnLongDeleteListener(position);
                return true;
            }
        });

        //点击跳转详情页
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JiaoDetailActivity.class);
                context.startActivity(intent);
            }
        });
        holder.img_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MydynamicActivity.class);
                intent.putExtra("uid",list.get(position).getUserId()+"");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Myviewholder extends RecyclerView.ViewHolder {
        public View view;
        public ImageView img_tou;
        public TextView text_name;
        public TextView da_zan;
        public ImageView imageView2;
        public RelativeLayout relan;
        public TextView text_username;
        public LinearLayout liners;
        public LinearLayout qqqq;
        public  TextView dengji;
        private final TextView yuantie;

        public Myviewholder(View rootView) {
            super(rootView);
            this.view = rootView;
            this.img_tou = (ImageView) rootView.findViewById(R.id.img_tou);
            this.text_name = (TextView) rootView.findViewById(R.id.text_name);
            this.da_zan = (TextView) rootView.findViewById(R.id.da_zan);
            dengji = (TextView) rootView.findViewById(R.id.text_dengji);
            yuantie = rootView.findViewById(R.id.yuantie);
            this.imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
            this.relan = (RelativeLayout) rootView.findViewById(R.id.relan);
            this.text_username = (TextView) rootView.findViewById(R.id.text_username);
            this.liners = (LinearLayout) rootView.findViewById(R.id.liners);
            this.qqqq = (LinearLayout) rootView.findViewById(R.id.yuantie_liner);
        }
    }

    public interface LongDeleteListener{
        void OnLongDeleteListener(int pos);
    }

    public void setOnLongDeleteListener(LongDeleteListener longDeleteListener){
        this.longDeleteListener=longDeleteListener;
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
