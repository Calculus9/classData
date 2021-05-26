package Examsystem.dao;

import Examsystem.bean.Examination;

import java.util.List;
/*
* 考试数据持久层
* */
public interface ExaminationDao {
//    返回试卷对象
    public List<Examination> findAll();
}
