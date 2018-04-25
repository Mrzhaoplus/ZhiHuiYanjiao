package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.User_guanzhu_Bean;
import www.diandianxing.com.diandianxing.interfase.Userguanzhu_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class User_Guanzhu_model {
    private final Userguanzhu_model_interfase jiekou;

    public User_Guanzhu_model(Userguanzhu_model_interfase jiekou) {
        this.jiekou=jiekou;
    }
    public void getpath(String token,int uid){
        Observable<User_guanzhu_Bean> uguanzhu = MyRetrofit.getSerViceAPI().uGuanzhu(token, uid);
        uguanzhu.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User_guanzhu_Bean>() {
                    @Override
                    public void accept(User_guanzhu_Bean user_guanzhu_bean) throws Exception {
                        jiekou.getsuccess(user_guanzhu_bean);
                    }
                });
    }
}
