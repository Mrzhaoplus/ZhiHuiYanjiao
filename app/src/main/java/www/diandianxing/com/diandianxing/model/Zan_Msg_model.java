package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Zan_msg_Bean;
import www.diandianxing.com.diandianxing.interfase.Zan_msg_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/23.
 */

public class Zan_Msg_model {
    private final Zan_msg_model_interfase jiekou;

    public Zan_Msg_model(Zan_msg_model_interfase jiekou) {
        this.jiekou=jiekou;

    }
    public void getpath(String token,int type){
        Observable<Zan_msg_Bean> zan_msg = MyRetrofit.getSerViceAPI().zan_msg(token, type);
        zan_msg.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Zan_msg_Bean>() {
                    @Override
                    public void accept(Zan_msg_Bean zan_msg_bean) throws Exception {
                        jiekou.getsuccess(zan_msg_bean);
                    }
                });
    }

}
