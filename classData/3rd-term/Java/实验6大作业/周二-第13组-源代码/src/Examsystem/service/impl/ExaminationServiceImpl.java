package Examsystem.service.impl;

import Examsystem.service.ExaminationService;
import Examsystem.bean.Examination;
import Examsystem.dao.impl.ExaminationDaoImpl;

import java.util.List;
/*
*  试卷对象服务层
* */
public class ExaminationServiceImpl implements ExaminationService {
    private ExaminationDaoImpl examinationDao = new ExaminationDaoImpl();
    /*
    *  得到数据库全部考试试卷对象
    */
    @Override
    public List<Examination> getExamination() {
        return examinationDao.findAll();
    }
}
