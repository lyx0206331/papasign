package com.papa.signature.model;

/**
 * @author PAPA-GuoBa
 * @Desc 图片返回结果
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/26 16:58
 */
public class PictureRes {


    /**
     * code : 1
     * data : {"imgPath":"img001/1/agreement/20190926/1909/26/6b/d6/cf/6bd6cf064ae927dda2cbc5ec9d1fd283.jpg","imgUrl":"http://spapa.wicp.net:8055/img001/1/agreement/20190926/1909/26/6b/d6/cf/6bd6cf064ae927dda2cbc5ec9d1fd283.jpg","imgSmallUrl":"http://spapa.wicp.net:8055/img001/1/agreement/20190926/1909/26/6b/d6/cf/6bd6cf064ae927dda2cbc5ec9d1fd283.jpg?w=100&h=100"}
     */

    private int code;
    private DataBean data;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgPath : img001/1/agreement/20190926/1909/26/6b/d6/cf/6bd6cf064ae927dda2cbc5ec9d1fd283.jpg
         * imgUrl : http://spapa.wicp.net:8055/img001/1/agreement/20190926/1909/26/6b/d6/cf/6bd6cf064ae927dda2cbc5ec9d1fd283.jpg
         * imgSmallUrl : http://spapa.wicp.net:8055/img001/1/agreement/20190926/1909/26/6b/d6/cf/6bd6cf064ae927dda2cbc5ec9d1fd283.jpg?w=100&h=100
         */

        private String imgPath;
        private String imgUrl;
        private String imgSmallUrl;

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgSmallUrl() {
            return imgSmallUrl;
        }

        public void setImgSmallUrl(String imgSmallUrl) {
            this.imgSmallUrl = imgSmallUrl;
        }
    }
}
