package Examsystem.dao;

import Examsystem.bean.Admission;

/**
 *  登录数据持久层
 */
public interface AdmissionDao {
    /**
     * 通过 准考证号 查询
     */
    public Admission findByAdmNum(String admNum);
}
