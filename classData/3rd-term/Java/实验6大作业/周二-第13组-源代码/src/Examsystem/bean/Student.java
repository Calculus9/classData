package Examsystem.bean;
public class Student {
    /*属性*/
    private String stuNum;
    private String stuName;
    private String stuGender;
    public Student() {

    }
    /*封装*/
    public String getStuNum() {
        return stuNum;
    }
    public Student setStuNum(String stuNum) {
        this.stuNum = stuNum;
        return this;
    }
    public String getStuName() {
        return stuName;
    }
    public Student setStuName(String stuName) {
        this.stuName = stuName;
        return this;
    }
    public String getStuGender() {
        return stuGender;
    }
    public Student setStuGender(String stuGender) {
        this.stuGender = stuGender;
        return this;
    }
}
