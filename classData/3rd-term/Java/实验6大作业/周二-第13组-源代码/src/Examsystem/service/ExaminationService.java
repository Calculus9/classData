package Examsystem.service;

import Examsystem.bean.Examination;

import java.util.List;
/*
*  考试试卷服务层
* */
public interface ExaminationService {
    /*
    * 得到所有的考试试卷
    * */
    public List<Examination> getExamination();
}
