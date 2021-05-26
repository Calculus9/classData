package Examsystem.bean;

import java.io.Serializable;

/*
* 试卷对象 -> 直接渲染页面
* */
public class Examination  implements Serializable {
    private String examName;
    private String examQuestionNum;
    private String examQuestionName;
    private String examQuestionDescribe;
    private String questionNum;
    private String questionDescribe;
    private String questionRightAnswer;
    private String questionScore;
    private String optionNum;
    private String optionContent;

    public Examination(){
    }
    /*封装*/
    public String getExamName() {
        return examName;
    }
    public Examination setExamName(String examName) {
        this.examName = examName;
        return this;
    }
    public String getExamQuestionNum() {
        return examQuestionNum;
    }
    public Examination setExamQuestionNum(String examQuestionNum) {
        this.examQuestionNum = examQuestionNum;
        return this;
    }
    public String getExamQuestionName() {
        return examQuestionName;
    }
    public Examination setExamQuestionName(String examQuestionName) {
        this.examQuestionName = examQuestionName;
        return this;
    }
    public String getExamQuestionDescribe() {
        return examQuestionDescribe;
    }
    public Examination setExamQuestionDescribe(String examQuestionDescribe) {
        this.examQuestionDescribe = examQuestionDescribe;
        return this;
    }
    public String getQuestionNum() {
        return questionNum;
    }
    public Examination setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
        return this;
    }
    public String getQuestionDescribe() {
        return questionDescribe;
    }
    public Examination setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
        return this;
    }
    public String getQuestionRightAnswer() {
        return questionRightAnswer;
    }
    public Examination setQuestionRightAnswer(String questionRightAnswer) {
        this.questionRightAnswer = questionRightAnswer;
        return this;
    }
    public String getQuestionScore() {
        return questionScore;
    }
    public Examination setQuestionScore(String questionScore) {
        this.questionScore = questionScore;
        return this;
    }
    public String getOptionNum() {
        return optionNum;
    }
    public Examination setOptionNum(String optionNum) {
        this.optionNum = optionNum;
        return this;
    }
    public String getOptionContent() {
        return optionContent;
    }
    public Examination setOptionContent(String optionContent) {
        this.optionContent = optionContent;
        return this;
    }
}
