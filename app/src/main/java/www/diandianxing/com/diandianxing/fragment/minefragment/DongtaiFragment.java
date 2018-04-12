package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.view.View;

import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by ASUS on 2018/3/22.
 */

public class DongtaiFragment extends BaseFragment {
    @Override
    protected int setContentView() {
        return R.layout.fragment_dongtai;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();

    }
}
