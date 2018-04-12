package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by ASUS on 2018/3/20.
 *
 *   我的 Fragment 关注适配器
 */

public class Myfollowadapter extends RecyclerView.Adapter<Myfollowadapter.MyviewHolder> {
    private OnItemClickLister mOnItemClickListener = null;
       private Context context;
    private List<String> list=new ArrayList<>();

    public Myfollowadapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {

        for (int i = 0; i < 10; i++) {
               list.add("我真的好喜欢你"+i);
            
        }
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyviewHolder myviewHolder=new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_itemguanzhu,parent,false));

        return myviewHolder;
    }

    @Override
    public void onBindViewHolder( final MyviewHolder holder, int position) {
                 if(mOnItemClickListener!=null){
                     holder.itemView.setOnClickListener(  new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             int layoutPosition = holder.getLayoutPosition();
                             Log.d("POS",layoutPosition+"---layoutPosition-");
                             mOnItemClickListener.onItemClick(holder.itemView,layoutPosition);
                         }
                     });
                 }
            holder.text_username.setText(list.get(position).toString());
        
           

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public ImageView img_tou;
        public TextView text_username;


        public MyviewHolder(View rootView) {
            super(rootView);
            this.img_tou = (ImageView) rootView.findViewById(R.id.img_tou);
            this.text_username = (TextView) rootView.findViewById(R.id.text_username);
        }
    }

       //接口回调


       public interface  OnItemClickLister{
           void onItemClick(View view, int position);
       }
      public void SetonItemClicklistener(OnItemClickLister onItemClickLister){

            this.mOnItemClickListener=onItemClickLister;

      }
}
