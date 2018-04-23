package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Fenlei_Bean;
import www.diandianxing.com.diandianxing.interfase.Fenlei_presenter_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/23.
 */

public class FenLei_model {
    private final Fenlei_presenter_interfase jiekou;

    public FenLei_model(Fenlei_presenter_interfase jiekou) {
        this.jiekou=jiekou;
    }
    public void getpath(String token,int postTypeId,int page){
        Observable<Fenlei_Bean> fenlei = MyRetrofit.getSerViceAPI().fenlei(token, postTypeId, page);
        fenlei.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Fenlei_Bean>() {
                    @Override
                    public void accept(Fenlei_Bean fenlei_bean) throws Exception {
                        jiekou.getsuccess(fenlei_bean);
                    }
                });
    }

}
