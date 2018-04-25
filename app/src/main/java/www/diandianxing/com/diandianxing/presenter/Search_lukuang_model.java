package www.diandianxing.com.diandianxing.presenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.LuKuang_Bean;
import www.diandianxing.com.diandianxing.interfase.Lukuang_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class Search_lukuang_model {
    private final Lukuang_model_interfase jiekou;

    public Search_lukuang_model(Lukuang_model_interfase jiekou) {
        this.jiekou=jiekou;
    }
    public void getpath(int pageNo,String token,String searchText){
        Observable<LuKuang_Bean> luKuang_beanObservable = MyRetrofit.getSerViceAPI().sear_lukuang(pageNo, token, searchText);
        luKuang_beanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LuKuang_Bean>() {
                    @Override
                    public void accept(LuKuang_Bean luKuang_bean) throws Exception {
                        jiekou.getsuccess(luKuang_bean);
                    }
                });
    }
}
