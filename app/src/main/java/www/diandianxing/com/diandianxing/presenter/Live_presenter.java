package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Live_Bean;
import www.diandianxing.com.diandianxing.interfase.Live_Model_interfase;
import www.diandianxing.com.diandianxing.interfase.Live_Presenter_interfase;
import www.diandianxing.com.diandianxing.model.Live_model;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Live_presenter implements Live_Model_interfase {

    private final Live_model live_model;
    private  Live_Presenter_interfase jiekou;

    public Live_presenter(Live_Presenter_interfase jiekou) {
        this.jiekou=jiekou;
        live_model = new Live_model(this);
    }
    public void getpath(){
        live_model.getpath();
    }
    public void kong(){
    if(jiekou!=null){
        jiekou=null;
    }
    }

    @Override
    public void getsuccess(Live_Bean live_bean) {
        jiekou.getsuccess(live_bean);
    }
}
