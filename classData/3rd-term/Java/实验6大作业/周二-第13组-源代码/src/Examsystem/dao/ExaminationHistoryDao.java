package Examsystem.dao;

import Examsystem.bean.ExaminationHistory;
import Examsystem.bean.ScoreReport;

import java.util.List;
/*
* 考卷历史数据持久层
* */
public interface ExaminationHistoryDao {
    public List<ExaminationHistory> findAll();
    /**
     * 覆盖记录
     */
    public ExaminationHistory update(String stuNum, String admNum, Integer optionId);
    /**
     * 更新所选的，重新给置为null
     */
    public ExaminationHistory updatetWithStr(String stuNum, String admNum, Integer OptionId,String str);

    /**
     * 新增记录
     */
    public ExaminationHistory insert(String stuNum, String admNum, Integer OptionId);

    /**
     * 通过optionId，查询历史。
     */
    public ExaminationHistory isQuestionExistByHisOId(String stuNum, String admNum, Integer optionId);

    /**
     * 统计得分
     */
    public List<ScoreReport> calculateScore();
}
