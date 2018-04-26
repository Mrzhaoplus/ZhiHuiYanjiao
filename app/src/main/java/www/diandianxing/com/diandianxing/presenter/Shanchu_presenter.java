package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Shanchu_Bean;
import www.diandianxing.com.diandianxing.interfase.Shanchu_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Shanchu_presenter_interfase;
import www.diandianxing.com.diandianxing.model.Shanchu_model;

/**
 * Created by Mr赵 on 2018/4/26.
 */

public class Shanchu_presenter implements Shanchu_model_interfase {

    private final Shanchu_model shanchu_model;
    private  Shanchu_presenter_interfase jiekou;

    public Shanchu_presenter(Shanchu_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        shanchu_model = new Shanchu_model(this);
    }
    public void getpath(String token,int uid){
        shanchu_model.getpath(token, uid);
    }
    public void getkong(){
        if(jiekou!=null){
            jiekou=null;
        }
    }

    @Override
    public void getsuccess(Shanchu_Bean shanchu_bean) {
        jiekou.getsuccess(shanchu_bean);
    }
}
