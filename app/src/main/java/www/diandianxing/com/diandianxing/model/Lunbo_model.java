package www.diandianxing.com.diandianxing.model;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Lunbo_Bean;
import www.diandianxing.com.diandianxing.interfase.LunModel_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Lunbo_model {

    private final LunModel_interfase jiekou;

    public Lunbo_model(LunModel_interfase jiekou) {
        this.jiekou=jiekou;
    }

    public void getString(String token) {
        Observable<Lunbo_Bean> lun = MyRetrofit.getSerViceAPI().lun(token);
        lun.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Lunbo_Bean>() {
                    @Override
                    public void accept(Lunbo_Bean lunbo_bean) throws Exception {
                            jiekou.getsuccess(lunbo_bean);

                    }
                });


    }
}
