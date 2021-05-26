package Examsystem.enums;

public enum QuestionTypeEnum {
    SINGLE(0, "单选题"),
    MUlTIPLE(1, "多选题"),
    ADJUST(2, "判断题");

    private Integer code;
    private String message;

    QuestionTypeEnum(Integer code, String message) {
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
