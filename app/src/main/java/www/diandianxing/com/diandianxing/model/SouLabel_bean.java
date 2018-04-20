package www.diandianxing.com.diandianxing.model;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/19.
 */

public class SouLabel_bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : ["燕郊","111","222","333","666"]
     */

    private String msg;
    private String code;
    private List<String> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }
}
