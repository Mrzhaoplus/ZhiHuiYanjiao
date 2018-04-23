package www.diandianxing.com.diandianxing.ShujuBean;

/**
 * Created by Mr赵 on 2018/4/20.
 */

public class QuxiaozanAndFenxiang_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"zanCount":3}
     */

    private String msg;
    private String code;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * zanCount : 3
         */

        private int zanCount;

        public int getZanCount() {
            return zanCount;
        }

        public void setZanCount(int zanCount) {
            this.zanCount = zanCount;
        }
    }
}
