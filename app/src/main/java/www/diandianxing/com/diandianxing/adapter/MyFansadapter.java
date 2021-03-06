package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.ShujuBean.Fenlei_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Fensi_Bean;
import www.diandianxing.com.diandianxing.interfase.GZ_state;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by ASUS on 2018/3/20.
 *   我的 Fragment 粉丝适配器
 */

public class MyFansadapter extends RecyclerView.Adapter<MyFansadapter.MyviewHolder> {
    private Context context;
    private List<Fensi_Bean.DatasBean> list=new ArrayList<>();
    private OnItemClickLister mOnItemClickListener;
    private TextView text_sure;
    private GZ_state state;

    public void getstate(GZ_state state){
         this.state=state;
     }
    public MyFansadapter(Context context, List<Fensi_Bean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyviewHolder myviewHolder=new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_itemfensi,parent,false));
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(final  MyviewHolder holder, final int position) {
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
            holder.text_username.setText(list.get(position).getNickName());
        Glide.with(context).load(list.get(position).getPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img_tou);
          if(list.get(position).getIsGz()==0){
                holder.liner_guanzhu.setVisibility(View.VISIBLE);
          }else if(list.get(position).getIsGz()==1){
              holder.liner_guanzhu.setVisibility(View.GONE);
          }

        holder.liner_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 state.getsuccess(position,list.get(position).getIsGz(),list.get(position).getUid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public ImageView img_tou;
        public TextView text_username;
        LinearLayout liner_guanzhu;


        public MyviewHolder(View rootView) {
            super(rootView);
            this.img_tou = (ImageView) rootView.findViewById(R.id.img_tou);
            this.text_username = (TextView) rootView.findViewById(R.id.text_username);
            liner_guanzhu=  rootView.findViewById(R.id.liner_guanzhu);
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
