package Examsystem.bean;

public class Option {
    /*属性*/
    private Character optionId;
    private String optionContent;
    private Boolean correct;
    public Option() {

    }
    public Option(Character optionId, String optionContent, Boolean correct) {
        this.optionId = optionId;
        this.optionContent = optionContent;
        this.correct = correct;
    }
    /*封装*/
    public Character getOptionId() {
        return optionId;
    }
    public Option setOptionId(Character optionId) {
        this.optionId = optionId;
        return this;
    }
    public String getOptionContent() {
        return optionContent;
    }
    public Option setOptionContent(String optionContent) {
        this.optionContent = optionContent;
        return this;
    }
    public Boolean getCorrect() {
        return correct;
    }
    public Option setCorrect(Boolean correct) {
        this.correct = correct;
        return this;
    }
}
