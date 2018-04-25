package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.interfase.Seach_Zixun_modelinterfase;
import www.diandianxing.com.diandianxing.interfase.Zixun_modelinterfase;
import www.diandianxing.com.diandianxing.interfase.Zixun_presenterinterfase;
import www.diandianxing.com.diandianxing.model.Search_Zixun_model;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class Search_Zixun_presenter implements Zixun_modelinterfase {

    private final Search_Zixun_model search_zixun_model;
    private  Zixun_presenterinterfase jiekou;

    public Search_Zixun_presenter(Zixun_presenterinterfase jiekou) {
        this.jiekou=jiekou;
        search_zixun_model = new Search_Zixun_model(this);
    }
    public void getpath(int type,String token,int typeNo,String searchtext){
        search_zixun_model.getpath(type,token,typeNo,searchtext);
    }
    public void getkong(){
       if(jiekou!=null){
           jiekou=null;
       }
    }

    @Override
    public void getsuccess(zixun_Bean zixun) {
        jiekou.getsuccess(zixun);
    }
}
