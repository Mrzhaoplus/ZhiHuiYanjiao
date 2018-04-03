package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import zhyj.dqjt.com.zhihuiyanjiao.R;


public class NoScrollGridAdapter extends BaseAdapter {

	/** 上下文 */
	private Context ctx;
	/** 图片Url集合 */
	private ArrayList<String> imageUrls;

	public NoScrollGridAdapter(Context ctx, ArrayList<String> urls) {
		this.ctx = ctx;
		this.imageUrls = urls;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageUrls == null ? 0 : imageUrls.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imageUrls.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(imageUrls.size()==1){
			View view = View.inflate(ctx, R.layout.item_onegridview, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);

			Glide.with(ctx).load(imageUrls.get(position)).into(imageView);
			return view;
		}
		else if(imageUrls.size()==2){
			View view = View.inflate(ctx, R.layout.item_twogridview, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
		//	ImageView imageViews = (ImageView) view.findViewById(R.id.iv_images);
			Glide.with(ctx).load(imageUrls.get(position)).into(imageView);
		//	Glide.with(ctx).load(imageUrls.get(1)).into(imageViews);
			return view;

		}
		else {
			View view = View.inflate(ctx, R.layout.item_gridview, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);

			Glide.with(ctx).load(imageUrls.get(position)).into(imageView);
			return view;
		}

	}

}
