package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Live_Bean;
import www.diandianxing.com.diandianxing.interfase.Live_Model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Live_model {
    private final Live_Model_interfase jiekou;

    public Live_model(Live_Model_interfase jiekou) {
      this.jiekou=jiekou;
    }
    public void getpath(){
        Observable<Live_Bean> live = MyRetrofit.getSerViceAPI().live();
        live.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Live_Bean>() {
                    @Override
                    public void accept(Live_Bean live_bean) throws Exception {
                        jiekou.getsuccess(live_bean);
                    }
                });
    }

}
