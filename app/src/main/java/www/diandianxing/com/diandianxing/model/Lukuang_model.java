package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.LuKuang_Bean;
import www.diandianxing.com.diandianxing.interfase.Lukuang_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/19.
 */

public class Lukuang_model {
    private final Lukuang_model_interfase jeikou;

    public Lukuang_model(Lukuang_model_interfase jiekou) {
        this.jeikou=jiekou;
    }
    public void getpath(int type){
        Observable<LuKuang_Bean> lukuang = MyRetrofit.getSerViceAPI().lukuang(type);
        lukuang.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LuKuang_Bean>() {
                    @Override
                    public void accept(LuKuang_Bean luKuang_bean) throws Exception {
                        jeikou.getsuccess(luKuang_bean);
                    }
                });
    }
}
