package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Fensi_Bean;
import www.diandianxing.com.diandianxing.interfase.FenSi_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class Fensi_model {
    private final FenSi_model_interfase jiekou;

    public Fensi_model(FenSi_model_interfase jiekou) {
        this.jiekou=jiekou;
    }
    public void getpath(int pageNo,String uid){
        Observable<Fensi_Bean> fensi = MyRetrofit.getSerViceAPI().fensi(pageNo, uid);
        fensi.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Fensi_Bean>() {
                    @Override
                    public void accept(Fensi_Bean fensi_bean) throws Exception {
                        jiekou.getsuccess(fensi_bean);
                    }
                });
    }
}
