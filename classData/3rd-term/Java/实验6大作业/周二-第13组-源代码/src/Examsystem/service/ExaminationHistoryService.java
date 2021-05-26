package Examsystem.service;

import Examsystem.bean.ExaminationHistory;

import java.util.List;
/*
*  历史试卷服务层
* */
public interface ExaminationHistoryService {
    /**
     *  得到历史试卷对象
     */
    public List<ExaminationHistory> getAllHistory();
    /*
    *  点击事件发生更新历史试卷,包括自动更新
    */
    public void onQuestionOptionChangedDan(String admNum,String stuNum,Integer optionId);
    /*
     *  点击事件发生更新历史试卷,不能自动更新的，只需要新增
     */
    public void onQuestionOptionChangedDuoChecked(String admNum,String stuNum,Integer optionId);
    /*
     *  点击事件发生更新历史试卷,更新当前选项选中的答案为null
     */
    public void onQuestionOptionChangedDuoNoChceked(String admNum,String stuNum,Integer optionId,String srt);
    /**
     * 判分接口
     */
    public Integer adjust();
}
