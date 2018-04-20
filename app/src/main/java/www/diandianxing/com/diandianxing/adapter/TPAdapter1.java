package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.TPDetailActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.Info;

/**
 * Created by Mr赵 on 2018/4/9.
 */

public class TPAdapter1 extends RecyclerView.Adapter<TPAdapter1.ViewHolder> {
    Context context;
    List<String> list;
    public TPAdapter1(Context context,List<String>  list) {
        this.context = context;
        this.list=list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tpp_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv_tp=view.findViewById(R.id.iv_tp);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        Glide.with(context).load(list.get(position)).into(holder.iv_tp);


          holder.view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(context, TPDetailActivity.class);
                  intent.putExtra("size",list.size());

                  Info info = new Info();
                  info.imgs=list;
                  intent.putExtra("imgs",  info);

                  intent.putExtra("position",position);
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        private ImageView iv_tp;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
