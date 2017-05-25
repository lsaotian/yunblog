package cn.qinxianyun.blog.vo;

public class PhotoResult {

    private int success;    //成功标准 0失败 1成功
    private String url;     //图片url
    private String message; //错误信息

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
