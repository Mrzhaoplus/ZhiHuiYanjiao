package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.MessInfo;

/**
 * Created by Mr赵 on 2018/4/4.
 */

public class MsgitmeAdapter extends BaseAdapter {
    private List<MessInfo> list=new ArrayList<>();
    private Context ctx;

    public MsgitmeAdapter(List<MessInfo> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Viewhodel hodel;
        if(view==null){
            hodel=new Viewhodel();
              view=View.inflate(ctx, R.layout.msg_item,null);
            hodel.be_read = view.findViewById(R.id.be_read);
            hodel.text_tile = view.findViewById(R.id.text_tile);
            view.findViewById(R.id.liner1);
            view.setTag(hodel);
        }else{
            hodel= (Viewhodel) view.getTag();
        }

        MessInfo messInfo = list.get(position);

        if(Integer.parseInt(messInfo.is_read)==0){
            hodel.be_read.setVisibility(View.VISIBLE);
        }else{
            hodel.be_read.setVisibility(View.INVISIBLE);
        }
        hodel.text_tile.setText(messInfo.msgTitle);




        return view;
    }
    public class Viewhodel{
        View be_read;
        TextView text_tile;
    }
}
