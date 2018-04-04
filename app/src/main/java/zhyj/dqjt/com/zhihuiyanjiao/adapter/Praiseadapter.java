package zhyj.dqjt.com.zhihuiyanjiao.adapter;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.YuanTieActivity;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment.LuKuangActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 *
 *   消息 Fragment  评价和点赞消息适配器公用
 */

public class Praiseadapter extends RecyclerView.Adapter<Praiseadapter.Myviewholder> {
    private Context context;
    List<String> list=new ArrayList<>();
    private boolean flag=false;
    public Praiseadapter(Context context) {
        this.context = context;
        data();

    }

    private void data() {

        for (int i = 0; i <10 ; i++) {
              list.add("12-2"+i);


        }
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        Myviewholder myviewholder = new Myviewholder(LayoutInflater.from(context).inflate(R.layout.praise_item, parent, false));
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(final Myviewholder holder, int position) {
            holder.da_zan.setText("天安门\r\r "+list.get(position).toString());
        holder.shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.shanchu.setVisibility(View.GONE);
                flag=false;
            }
        });
        //长按事件
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(flag==false){
                    holder.shanchu.setVisibility(View.VISIBLE);
                    flag=true;
                }
                return true;
            }
        });
        //点击事件
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.shanchu.setVisibility(View.GONE);
                flag=false;
            }
        });
        //点击跳转详情页
        holder.liners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YuanTieActivity.class);
                context.startActivity(intent);
            }
        });
        holder.yuantie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YuanTieActivity.class);
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
        public LinearLayout yuantie;
        public LinearLayout shanchu;
        public Myviewholder(View rootView) {
            super(rootView);
            this.view = rootView;
            this.img_tou = (ImageView) rootView.findViewById(R.id.img_tou);
            this.text_name = (TextView) rootView.findViewById(R.id.text_name);
            this.da_zan = (TextView) rootView.findViewById(R.id.da_zan);
            this.imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
            this.relan = (RelativeLayout) rootView.findViewById(R.id.relan);
            this.text_username = (TextView) rootView.findViewById(R.id.text_username);
            this.liners = (LinearLayout) rootView.findViewById(R.id.zan_liners);
            this.yuantie = (LinearLayout) rootView.findViewById(R.id.yuantie_liner);
            this.shanchu = (LinearLayout) rootView.findViewById(R.id.shanchu);
        }
    }

}
