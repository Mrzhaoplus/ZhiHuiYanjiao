package www.diandianxing.com.diandianxing.model;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Coll_Bean;
import www.diandianxing.com.diandianxing.interfase.Coll_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/26.
 */

public class Coll_model {
    private final Coll_model_interfase jiekou;

    public Coll_model(Coll_model_interfase jiekou) {
        this.jiekou=jiekou;

    }
    public void getpath(int page,String token){
        Observable<Coll_Bean> coll = MyRetrofit.getSerViceAPI().coll(page, token);
        coll.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Coll_Bean>() {
                    @Override
                    public void accept(Coll_Bean coll_bean) throws Exception {

                        jiekou.getsuccess(coll_bean);
                    }
                });
    }
}
