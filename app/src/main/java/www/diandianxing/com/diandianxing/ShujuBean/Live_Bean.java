package www.diandianxing.com.diandianxing.ShujuBean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Live_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":6,"title":"","textType":"2","isDeleted":0,"content":"每日焦点"},{"id":7,"title":"","textType":"2","isDeleted":0,"content":"失物招领"},{"id":8,"title":"","textType":"2","isDeleted":0,"content":"二手市场"},{"id":9,"title":"","textType":"2","isDeleted":0,"content":"房屋出租"},{"id":10,"title":"","textType":"2","isDeleted":0,"content":"求职招聘"},{"id":11,"title":"","textType":"2","isDeleted":0,"content":"宠物天地"}]
     */

    private String msg;
    private String code;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 6
         * title :
         * textType : 2
         * isDeleted : 0
         * content : 每日焦点
         */

        private int id;
        private String title;
        private String textType;
        private int isDeleted;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTextType() {
            return textType;
        }

        public void setTextType(String textType) {
            this.textType = textType;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
