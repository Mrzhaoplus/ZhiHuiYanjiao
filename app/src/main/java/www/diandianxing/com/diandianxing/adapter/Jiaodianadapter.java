package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.bean.Imagebean;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Jiaodianadapter extends BaseAdapter {
    private Context context;
    final int ONEPHOTO = 0;
    final int TWOHOTO = 1;
    final int MANy = 2;
    final int NO = 3;
    private RecyclerView.ItemDecoration mCurrentItemDecoration;
    private OnItemClickListener mOnItemClickListener = null;
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

    public Jiaodianadapter(Context context) {
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
                    convertView = LayoutInflater.from(context).inflate(R.layout.fragment_duoone, null);
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
                    break;
                case TWOHOTO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.fragment_duotwo, null);
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
                    break;
                case MANy:
                    convertView = LayoutInflater.from(context).inflate(R.layout.fragment_duothree, null);
                    holder.img_tou = (ImageView)     convertView .findViewById(R.id.img_tou);
                    holder.text_name = (TextView)     convertView .findViewById(R.id.text_name);
                    holder.da_address = (TextView)     convertView .findViewById(R.id.da_address);
                    holder.imageView2 = (ImageView)     convertView .findViewById(R.id.imageView2);
                    holder.text_dengji = (TextView)     convertView .findViewById(R.id.text_dengji);
                    holder.item_count = (TextView)     convertView .findViewById(R.id.item_count);
                    holder.recy_grade = (RecyclerView)   convertView .findViewById(R.id.recy_grade);
                    holder.text_colltet = (TextView)     convertView .findViewById(R.id.text_collect);
                    holder.text_zan = (TextView)     convertView .findViewById(R.id.text_zan);
                    holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
                    break;
                case NO://没有图片的时候
                    convertView = LayoutInflater.from(context).inflate(R.layout.fragment_duono, null);
                    holder.img_tou = (ImageView)     convertView .findViewById(R.id.img_tou);
                    holder.text_name = (TextView)     convertView .findViewById(R.id.text_name);
                    holder.da_address = (TextView)     convertView .findViewById(R.id.da_address);
                    holder.text_colltet = (TextView)     convertView .findViewById(R.id.text_collect);
                    holder.text_zan = (TextView)     convertView .findViewById(R.id.text_zan);
                    holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
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
                break;
            case TWOHOTO:
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
                Glide.with(context).load(lists.get(position).getImage()).into(holder.item_pho);
               Glide.with(context).load(lists.get(position).getImage()).into(holder.item_pho1);
                break;
            case MANy:
                holder.recy_grade.setLayoutManager(new GridLayoutManager(context, 3));
                if (lists != null && lists.size() > 0) {
                    holder.recy_grade.setAdapter(new Recygradeadapter(context, lists));
                }
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
                break;
            case NO:
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
                        ToastUtils.show(context,"分享",1);
                    }
                });
                //点赞
                holder.text_zan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.show(context,"分享",1);
                    }
                });
                break;
        }
        return convertView;
    }

    /*
      返回类型
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 4) {
            return ONEPHOTO;
        } else if (position == 1 || position == 3) {
            return TWOHOTO;

        }
        else if(position == 2){
            return NO;
        }else {
            return MANy;
        }

    }

    /*
      返回的类型数目
     */
    @Override
    public int getViewTypeCount() {
        return 4;
    }



    public static class ViewHolder {

        public ImageView item_pho;
        public ImageView item_pho1;
        public ImageView img_tou;
        public TextView text_name;
        public TextView da_address;
        public ImageView imageView2;
        public TextView text_dengji;
        public TextView item_count;
        public RecyclerView recy_grade;
        public TextView text_share,text_colltet,text_zan;
    }

     //设置接口回调用于adapter监听

     public interface OnItemClickListener{
         void ItemClick(View view, int position);
     }
      public void SetOnItemClickListener(OnItemClickListener listener){

              this.mOnItemClickListener=listener;

      }

}
