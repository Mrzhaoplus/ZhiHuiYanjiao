package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.DianzanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.interfase.Dianzan_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/20.
 */

public class Dianzan_model {
    private final Dianzan_model_interfase jiekou;

    public Dianzan_model(Dianzan_model_interfase jiekou) {
        this.jiekou=jiekou;

    }
    public void setpath(String tohen,int objId,int obj_type,int operation_type){
        Observable<DianzanAndFenxiang_Bean> zanandguan = MyRetrofit.getSerViceAPI().zanandguan(tohen, objId, obj_type, operation_type);
        zanandguan.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DianzanAndFenxiang_Bean>() {
                    @Override
                    public void accept(DianzanAndFenxiang_Bean zan) throws Exception {
                           jiekou.setsuccess(zan);
                    }
                });
    }

}
