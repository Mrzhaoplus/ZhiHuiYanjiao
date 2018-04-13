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
 * Created by Mr赵 on 2018/4/9.
 */

public class JiaoLiuyanAdapter extends RecyclerView.Adapter<JiaoLiuyanAdapter.ViewHolder> {
   private Context context;
    private List<String>list=new ArrayList<>();
    public JiaoLiuyanAdapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<10;i++){
            list.add("");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_pinglun, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.img_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MydynamicActivity.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_tou;
        public TextView name;
        public TextView address;
        public TextView pinglun;
        public ViewHolder(View itemView) {
            super(itemView);
            img_tou = itemView.findViewById(R.id.img_tou);
            name = itemView.findViewById(R.id.text_name);
            address = itemView.findViewById(R.id.da_address);
            pinglun = itemView.findViewById(R.id.pinglun);
        }
    }
}
