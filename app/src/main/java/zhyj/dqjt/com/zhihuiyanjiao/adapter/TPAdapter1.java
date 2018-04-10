package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.TPDetailActivity;

/**
 * Created by Mr赵 on 2018/4/9.
 */

public class TPAdapter1 extends RecyclerView.Adapter<TPAdapter1.ViewHolder> {
    Context context;
List<String>list=new ArrayList<>();
    public TPAdapter1(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<3;i++){
            list.add("");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tpp_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
          holder.view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(context, TPDetailActivity.class);
                  intent.putExtra("size",list.size());
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

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
