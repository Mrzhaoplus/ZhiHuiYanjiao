package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.interfase.Zixun_modelinterfase;
import www.diandianxing.com.diandianxing.interfase.Zixun_presenterinterfase;
import www.diandianxing.com.diandianxing.model.Zixun_model;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Zixun_presenter implements Zixun_modelinterfase {

    private final Zixun_model home_zixun_model;
    private final Zixun_presenterinterfase jiekou;

    public Zixun_presenter(Zixun_presenterinterfase jiekou) {
        this.jiekou=jiekou;
        home_zixun_model = new Zixun_model(this);
    }
    public void getpath(int type,String token,int typeNo){
        home_zixun_model.getpath(1,token,typeNo);
    }
    public void kong(){

    }

    @Override
    public void getsuccess(zixun_Bean zixun) {
          jiekou.getsuccess(zixun);
    }
}
