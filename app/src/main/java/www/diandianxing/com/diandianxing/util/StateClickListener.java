package www.diandianxing.com.diandianxing.util;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/4/19.
 */

public interface StateClickListener {

    void ShouCangClickListener(int objId , int obj_type, int operation_type, int fx, int pos);


    void DianZanClickListener(int objId , int obj_type,int operation_type,int fx,int pos);

    void QuXiaoShouCangClickListener(int objId , int obj_type,int operation_type,int pos);

    void QuXiaoDianZanClickListener(int objId , int obj_type,int operation_type,int pos);


}
