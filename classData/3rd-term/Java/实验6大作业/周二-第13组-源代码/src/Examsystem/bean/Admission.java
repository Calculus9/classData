package Examsystem.bean;
/*登录类*/
public class Admission {
    /*属性*/
    private String admNum;
    private String stuNum;
    public Admission(){
    }
    /*封装*/
    public String getAdmNum() {
        return admNum;
    }
    public Admission setAdmNum(String admNum) {
        this.admNum = admNum;
        return this;
    }
    public String getStuNum() {
        return stuNum;
    }
    public Admission setStuNum(String stuNum) {
        this.stuNum = stuNum;
        return this;
    }
}
