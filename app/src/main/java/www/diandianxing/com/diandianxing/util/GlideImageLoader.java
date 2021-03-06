package www.diandianxing.com.diandianxing.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

import www.diandianxing.com.diandianxing.R;

/**
 * Created by Administrator on 2017/7/20.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, final ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.wisdom_yan_jiao_3x)
                .error(R.drawable.wisdom_yan_jiao_3x)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        //Glide 加载图片简单用法
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(path).apply(options).into(imageView);
    }

}
