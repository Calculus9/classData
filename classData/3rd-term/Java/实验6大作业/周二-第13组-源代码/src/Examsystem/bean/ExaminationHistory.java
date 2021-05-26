package Examsystem.bean;

import java.sql.Timestamp;

public class ExaminationHistory {
    /*属性*/
    private Integer hisId;
    private String hisStuNum;
    private String hisAdmNum;
    private String hisExaId;
    private Integer hisQtId;
    private Integer hisQId;
    private Integer hisOId;
    private String hisStuSelected;
    private Timestamp createTime;
    private Timestamp lastModifyTime;

    public ExaminationHistory() {

    }
    /*封装*/
    public Integer getHisId() {
        return hisId;
    }
    public ExaminationHistory setHisId(Integer hisId) {
        this.hisId = hisId;
        return this;
    }
    public String getHisStuNum() {
        return hisStuNum;
    }
    public ExaminationHistory setHisStuNum(String hisStuNum) {
        this.hisStuNum = hisStuNum;
        return this;
    }
    public String getHisAdmNum() {
        return hisAdmNum;
    }
    public ExaminationHistory setHisAdmNum(String hisAdmNum) {
        this.hisAdmNum = hisAdmNum;
        return this;
    }
    public String getHisExaId() {
        return hisExaId;
    }
    public ExaminationHistory setHisExaId(String hisExaId) {
        this.hisExaId = hisExaId;
        return this;
    }
    public Integer getHisQtId() {
        return hisQtId;
    }
    public ExaminationHistory setHisQtId(Integer hisQtId) {
        this.hisQtId = hisQtId;
        return this;
    }
    public Integer getHisQId() {
        return hisQId;
    }
    public ExaminationHistory setHisQId(Integer hisQId) {
        this.hisQId = hisQId;
        return this;
    }
    public Integer getHisOId() {
        return hisOId;
    }
    public ExaminationHistory setHisOId(Integer hisOId) {
        this.hisOId = hisOId;
        return this;
    }
    public String getHisStuSelected() {
        return hisStuSelected;
    }
    public ExaminationHistory setHisStuSelected(String hisStuSelected) {
        this.hisStuSelected = hisStuSelected;
        return this;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public ExaminationHistory setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }
    public Timestamp getLastModifyTime() {
        return lastModifyTime;
    }
    public ExaminationHistory setLastModifyTime(Timestamp lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
        return this;
    }
}
