package Examsystem.enums;

public enum LoginEnum {
    SUCCESS(1, "登录成功"),
    ADMNUMERROR(0, "准考证号输入错误"),
    STUNUMERROR(2, "学号输入错误");

    private Integer code;
    private String message;

    LoginEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
