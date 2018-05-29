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

    boolean iszy;
    public TPAdapter1(Context context,List<String>  list) {
        this.context = context;
        this.list=list;
    }

    public void setZY(boolean iszy){
        this.iszy=iszy;
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

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.iv_tp.getLayoutParams();
        if(iszy){

            if(list.size()==1){


                layoutParams.width= RelativeLayout.LayoutParams.MATCH_PARENT;

                layoutParams.height=450;


            }else if(list.size()==2){

                layoutParams.height=400;
                layoutParams.width=400;

            }else
            {
                layoutParams.height=280;
                layoutParams.width=280;
            }

        }else {
            if(list.size()==1){

                layoutParams.width= RelativeLayout.LayoutParams.MATCH_PARENT;

                layoutParams.height=450;


            }else if(list.size()==2){

                layoutParams.height=440;
                layoutParams.width=440;

            }
        }


//        layoutParams.leftMargin=10;
//        layoutParams.rightMargin=10;


        holder.iv_tp.setLayoutParams(layoutParams);

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
