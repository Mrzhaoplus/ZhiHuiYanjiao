package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.User_guanzhu_Bean;
import www.diandianxing.com.diandianxing.interfase.Userguanzhu_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Userguanzhu_presenter_interfase;
import www.diandianxing.com.diandianxing.model.User_Guanzhu_model;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class User_Guanzhu_presenter implements Userguanzhu_model_interfase {

    private final User_Guanzhu_model user_guanzhu_model;
    private  Userguanzhu_presenter_interfase jiekou;

    public User_Guanzhu_presenter(Userguanzhu_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        user_guanzhu_model = new User_Guanzhu_model(this);
    }
    public void getpath(String token,int uid){
        user_guanzhu_model.getpath(token,uid);
    }
    public void getkong(){
        if(jiekou!=null){
            jiekou=null;
        }
    }

    @Override
    public void getsuccess(User_guanzhu_Bean user_guanzhu_bean) {
          jiekou.getsuccess(user_guanzhu_bean);
    }
}
