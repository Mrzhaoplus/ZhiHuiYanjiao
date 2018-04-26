package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Shanchu_Bean;
import www.diandianxing.com.diandianxing.interfase.Shanchu_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/26.
 */

public class Shanchu_model {
    private final Shanchu_model_interfase jiekou;

    public Shanchu_model(Shanchu_model_interfase jiekou) {
        this.jiekou=jiekou;
    }
    public void getpath(String token,int uid){
        Observable<Shanchu_Bean> shanchu = MyRetrofit.getSerViceAPI().shanchu(token, uid);
        shanchu.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Shanchu_Bean>() {
                    @Override
                    public void accept(Shanchu_Bean shanchu_bean) throws Exception {
                        jiekou.getsuccess(shanchu_bean);
                    }
                });
    }
}
