package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;

/**
 * Created by Mr赵 on 2018/4/8.
 */

public class Video_pinglun_Adapter extends RecyclerView.Adapter<Video_pinglun_Adapter.ViewHodel> {
 private Context ctx;
 private List<String>list=new ArrayList<>();
    public Video_pinglun_Adapter(Context ctx) {
        this.ctx = ctx;
        data();
    }

    private void data() {
        for (int i=0;i<10;i++){
            list.add("");
        }
    }
    @Override
    public ViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.video_pinglun, parent, false);
        ViewHodel hodel = new ViewHodel(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(ViewHodel holder, int position) {
       //点击头像跳转至主页
        holder.img_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, MydynamicActivity.class);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder{
        public ImageView img_tou;
        public TextView name;
        public TextView address;
        public TextView pinglun;

        public ViewHodel(View itemView) {
            super(itemView);
             img_tou = itemView.findViewById(R.id.img_tou);
            name = itemView.findViewById(R.id.text_name);
            address = itemView.findViewById(R.id.da_address);
            pinglun = itemView.findViewById(R.id.pinglun);
        }
    }
}
