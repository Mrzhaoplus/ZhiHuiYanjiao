package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Zan_msg_Bean;
import www.diandianxing.com.diandianxing.interfase.Zan_msg_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Zan_msg_presenter_interfase;
import www.diandianxing.com.diandianxing.model.Zan_Msg_model;

/**
 * Created by Mr赵 on 2018/4/23.
 */

public class Zan_Msg_presenter implements Zan_msg_model_interfase {

    private final Zan_Msg_model zan_msg_model;
    private  Zan_msg_presenter_interfase jiekou;

    public Zan_Msg_presenter(Zan_msg_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        zan_msg_model = new Zan_Msg_model(this);
    }
    public void getpath(String token,int type){
        zan_msg_model.getpath(token,type);
    }
    public void getkong(){
       if(jiekou!=null){
           jiekou=null;
       }
    }

    @Override
    public void getsuccess(Zan_msg_Bean zan_msg_bean) {
        jiekou.getsuccess(zan_msg_bean);
    }
}
