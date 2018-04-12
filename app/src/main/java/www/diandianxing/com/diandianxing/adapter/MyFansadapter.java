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

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by ASUS on 2018/3/20.
 *   我的 Fragment 粉丝适配器
 */

public class MyFansadapter extends RecyclerView.Adapter<MyFansadapter.MyviewHolder> {

       private Context context;
    private List<String> list=new ArrayList<>();
    private OnItemClickLister mOnItemClickListener;
    private TextView text_sure;

    public MyFansadapter(Context context) {
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

        MyviewHolder myviewHolder=new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_itemfensi,parent,false));

        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(final  MyviewHolder holder, int position) {
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

        holder.liner_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
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

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_guanzhu)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
