package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Live_gunzhu_Bean;
import www.diandianxing.com.diandianxing.interfase.Live_guanzhu_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Live_guanzhu_presenter_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/19.
 */

public class Live_guanzhu_model {
    private final Live_guanzhu_model_interfase jiekou;

    public Live_guanzhu_model(Live_guanzhu_model_interfase jiekou) {
        this.jiekou=jiekou;
    }
    public void getpath(String token,int pageNo){
        Observable<Live_gunzhu_Bean> guanzhu = MyRetrofit.getSerViceAPI().live_guanzhu(token, pageNo);
        guanzhu.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Live_gunzhu_Bean>() {
                    @Override
                    public void accept(Live_gunzhu_Bean live_gunzhu_bean) throws Exception {
                        jiekou.getsuccess(live_gunzhu_bean);
                    }
                });
    }

}
