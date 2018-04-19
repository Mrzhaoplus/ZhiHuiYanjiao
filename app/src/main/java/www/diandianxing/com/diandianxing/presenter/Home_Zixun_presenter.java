package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Home_zixun_Bean;
import www.diandianxing.com.diandianxing.interfase.Home_Zixun_modelinterfase;
import www.diandianxing.com.diandianxing.interfase.Home_Zixun_presenterinterfase;
import www.diandianxing.com.diandianxing.model.Home_Zixun_model;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Home_Zixun_presenter implements Home_Zixun_modelinterfase {

    private final Home_Zixun_model home_zixun_model;
    private final Home_Zixun_presenterinterfase jiekou;

    public Home_Zixun_presenter(Home_Zixun_presenterinterfase jiekou) {
        this.jiekou=jiekou;
        home_zixun_model = new Home_Zixun_model(this);
    }
    public void getpath(int type){
        home_zixun_model.getpath(1);
    }
    public void kong(){

    }

    @Override
    public void getsuccess(Home_zixun_Bean zixun) {
          jiekou.getsuccess(zixun);
    }
}
