package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Live_gunzhu_Bean;
import www.diandianxing.com.diandianxing.interfase.Live_guanzhu_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Live_guanzhu_presenter_interfase;
import www.diandianxing.com.diandianxing.model.Live_guanzhu_model;

/**
 * Created by Mr赵 on 2018/4/19.
 */

public class Live_guanzhu_presenter implements Live_guanzhu_model_interfase {

    private final Live_guanzhu_model live_guanzhu_model;
    private  Live_guanzhu_presenter_interfase jiekou;

    public Live_guanzhu_presenter(Live_guanzhu_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        live_guanzhu_model = new Live_guanzhu_model(this);
    }
    public void getpath(String token,int pageNo){
        live_guanzhu_model.getpath(token,pageNo);
    }
    public void getkong(){
    if(jiekou!=null){
        jiekou=null;
    }
    }


    @Override
    public void getsuccess(Live_gunzhu_Bean guanzhu) {
          jiekou.getsuccess(guanzhu);
    }
}
