package Examsystem.bean;

public class ScoreReport {
    /*属性*/
    private String stu_name;
    private String adm_num;
    private String stu_num;
    private String stu_gender;
    private Integer sum_multiple_score;
    private Integer sum_single_score;
    private Integer sum;
    public ScoreReport(){

    }
    /*封装*/
    public String getStu_name() {
        return stu_name;
    }
    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }
    public String getAdm_num() {
        return adm_num;
    }
    public void setAdm_num(String adm_num) {
        this.adm_num = adm_num;
    }
    public String getStu_num() {
        return stu_num;
    }
    public void setStu_num(String stu_num) {
        this.stu_num = stu_num;
    }
    public String getStu_gender() {
        return stu_gender;
    }
    public void setStu_gender(String stu_gender) {
        this.stu_gender = stu_gender;
    }
    public Integer getSum_multiple_score() {
        return sum_multiple_score;
    }
    public void setSum_multiple_score(Integer sum_multiple_score) {
        this.sum_multiple_score = sum_multiple_score;
    }
    public Integer getSum_single_score() {
        return sum_single_score;
    }
    public void setSum_single_score(Integer sum_single_score) {
        this.sum_single_score = sum_single_score;
    }
    public Integer getSum() {
        return sum;
    }
    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
