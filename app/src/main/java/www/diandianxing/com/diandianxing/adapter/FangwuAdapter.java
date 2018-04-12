package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.bean.Imagebean;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class FangwuAdapter extends BaseAdapter {
    private Context context;
    final int ONEPHOTO = 0;
    final int TWOHOTO = 1;
    final int MANy = 2;
    private List<Imagebean> lists = new ArrayList<>();
    private String[] mUrls = new String[]{
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg"};
    private TextView text_sure;
    public FangwuAdapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        Imagebean imagebean4;
        for (int i = 0; i < 8; i++) {
            imagebean4 = new Imagebean();
            imagebean4.setImage(mUrls[i]);
            lists.add(imagebean4);
        }
        Log.d("Tag","--------"+lists.size());
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case ONEPHOTO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.frag_fangwu1, null);
                    holder.img_tou = (ImageView) convertView.findViewById(R.id.img_tou);
                    holder.text_name = (TextView) convertView.findViewById(R.id.text_name);
                    holder.da_address = (TextView) convertView.findViewById(R.id.da_address);
                    holder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
                    holder.text_dengji = (TextView) convertView.findViewById(R.id.text_dengji);
                    holder.item_count = (TextView) convertView.findViewById(R.id.item_count);
                    holder.item_pho = (ImageView) convertView.findViewById(R.id.item_pho);
                    holder.text_colltet = (TextView)     convertView .findViewById(R.id.text_collect);
                    holder.text_zan = (TextView)     convertView .findViewById(R.id.text_zan);
                    holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
                    holder.rela_guanzhu = convertView.findViewById(R.id.rela_guanzhu);
                    break;
                case TWOHOTO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.frag_fangwu2, null);
                    holder.img_tou = (ImageView) convertView.findViewById(R.id.img_tou);
                    holder.text_name = (TextView) convertView.findViewById(R.id.text_name);
                    holder.da_address = (TextView) convertView.findViewById(R.id.da_address);
                    holder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
                    holder.text_dengji = (TextView) convertView.findViewById(R.id.text_dengji);
                    holder.item_count = (TextView) convertView.findViewById(R.id.item_count);
                    holder.item_pho = (ImageView) convertView.findViewById(R.id.item_pho);
                    holder.item_pho1 = (ImageView) convertView.findViewById(R.id.item_pho1);
                    holder.text_colltet = (TextView)     convertView .findViewById(R.id.text_collect);
                    holder.text_zan = (TextView)     convertView .findViewById(R.id.text_zan);
                    holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
                    holder.rela_guanzhu = convertView.findViewById(R.id.rela_guanzhu);
                    break;
                case MANy:
                    convertView = LayoutInflater.from(context).inflate(R.layout.frag_fangwu3, null);
                    holder.img_tou = (ImageView)     convertView .findViewById(R.id.img_tou);
                    holder.text_name = (TextView)     convertView .findViewById(R.id.text_name);
                    holder.da_address = (TextView)     convertView .findViewById(R.id.da_address);
                    holder.imageView2 = (ImageView)     convertView .findViewById(R.id.imageView2);
                    holder.text_dengji = (TextView)     convertView .findViewById(R.id.text_dengji);
                    holder.item_count = (TextView)     convertView .findViewById(R.id.item_count);
                    holder.text_colltet = (TextView)     convertView .findViewById(R.id.text_collect);
                    holder.text_zan = (TextView)     convertView .findViewById(R.id.text_zan);
                    holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
                    holder.rela_guanzhu = convertView.findViewById(R.id.rela_guanzhu);
                    holder.item_pho = (ImageView) convertView.findViewById(R.id.item_pho);
                    holder.item_pho1 = (ImageView) convertView.findViewById(R.id.item_pho1);
                    holder.item_pho2 = (ImageView) convertView.findViewById(R.id.item_pho2);

                    break;

            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch (type) {

            case ONEPHOTO:
                Glide.with(context).load(lists.get(position).getImage()).into(holder.item_pho);
                //分享
                holder.text_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.show(context,"分享",1);
                    }
                });

                break;
            case TWOHOTO:

                Glide.with(context).load(lists.get(position).getImage()).into(holder.item_pho);
                Glide.with(context).load(lists.get(position).getImage()).into(holder.item_pho1);
                break;
            case MANy:


               break;

        }
        holder.rela_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
            }
        });
        //分享
        holder.text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(context,"分享",1);
            }
        });
        //收藏
        holder.text_colltet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(context,"收藏",1);
            }
        });
        //点赞
        holder.text_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(context,"点赞",1);
            }
        });
        return convertView;
    }

    /*
      返回类型
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0 ) {
            return MANy;
        }else if (position == 1) {
            return TWOHOTO;
        }else{
            return ONEPHOTO;
        }
    }

    /*
      返回的类型数目
     */
    @Override
    public int getViewTypeCount() {
        return 3;
    }



    public static class ViewHolder {

        public ImageView item_pho;
        public ImageView item_pho1;
        public ImageView item_pho2;
        public ImageView img_tou;
        public TextView text_name;
        public TextView da_address;
        public ImageView imageView2;
        public TextView text_dengji;
        public TextView item_count;
        public TextView text_share;
        public TextView text_zan;
        public TextView text_colltet;
        public RelativeLayout rela_guanzhu;
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
