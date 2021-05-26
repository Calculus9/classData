package Examsystem.vo;

public class ResultVO {
    private Integer status;
    private String message;
    public ResultVO(){

    }

    public Integer getStatus() {
        return status;
    }

    public ResultVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultVO setMessage(String message) {
        this.message = message;
        return this;
    }
}
