package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Live_Bean;
import www.diandianxing.com.diandianxing.interfase.SouLadel_model_interfise;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/19.
 */

public class SouLabel_model {
    private final SouLadel_model_interfise jiekou;

    public SouLabel_model(SouLadel_model_interfise jiekou) {
          this.jiekou=jiekou;
    }
    public void getpath(String token){
        Observable<SouLabel_bean> live = MyRetrofit.getSerViceAPI().soulabel(token);
        live.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SouLabel_bean>() {
                    @Override
                    public void accept(SouLabel_bean live_bean) throws Exception {
                        jiekou.getsuccess(live_bean);
                    }
                });



    }
}
