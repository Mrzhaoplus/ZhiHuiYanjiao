package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by Mr赵 on 2018/4/9.
 */

public class Search_adapter extends RecyclerView.Adapter<Search_adapter.ViewHodel> {
    private Context context;
     private List<String>list=new ArrayList<>();
    public Search_adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
         for (int i=0;i<8;i++){
            list.add("");
         }
    }

    @Override
    public ViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_frag, parent, false);
        ViewHodel hodel = new ViewHodel(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(ViewHodel holder, final int position) {
    holder.view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHodel extends RecyclerView.ViewHolder{

        public View view;

        public ViewHodel(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
