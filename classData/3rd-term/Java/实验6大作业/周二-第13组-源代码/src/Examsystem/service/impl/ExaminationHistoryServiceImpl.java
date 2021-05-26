package Examsystem.service.impl;

import Examsystem.service.ExaminationHistoryService;
import Examsystem.bean.ExaminationHistory;
import Examsystem.bean.ScoreReport;
import Examsystem.dao.impl.ExaminationHistoryDaoImpl;

import java.io.*;
import java.util.List;

/*
 * 历史试卷对象实现类
 */
public class ExaminationHistoryServiceImpl implements ExaminationHistoryService {
    private ExaminationHistoryDaoImpl examinationHistoryDao = new ExaminationHistoryDaoImpl();
    /*
     * 得到历史试卷对象
     * */
    @Override
    public List<ExaminationHistory> getAllHistory() {
        return examinationHistoryDao.findAll();
    }
    /*
     *  点击事件发生更新历史试卷
     * */
    @Override
    public void onQuestionOptionChangedDan(String admNum, String stuNum, Integer optionId) {
        ExaminationHistory eh = examinationHistoryDao.isQuestionExistByHisOId(stuNum, admNum, optionId);
        if (eh == null) {
            //历史中没有记录到这个题，那么就新增
            examinationHistoryDao.insert(stuNum, admNum, optionId);
        } else {
            //更新
            examinationHistoryDao.update(stuNum, admNum, optionId);
        }
    }
    /*
     *  点击事件发生更新历史试卷,不能自动更新的，只需要新增
     */
    @Override
    public void onQuestionOptionChangedDuoChecked(String admNum, String stuNum, Integer optionId) {
        examinationHistoryDao.insert(stuNum, admNum, optionId);
    }
    @Override
    /*
     *  点击事件发生更新历史试卷,更新当前选项选中的答案为null
     * */
    public void onQuestionOptionChangedDuoNoChceked(String admNum, String stuNum, Integer optionId, String str) {
        examinationHistoryDao.updatetWithStr(stuNum, admNum, optionId, str);
    }
    @Override
    /*
     * 新建文件路径 ，将取到的做题历史对象传过来
     */
    public Integer adjust() {
        List<ScoreReport> scoreReports = examinationHistoryDao.calculateScore();
        String fileName = "score.csv";
        File file = new File("D:\\Code\\Java\\" + fileName);
        OutputStream out = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            System.out.println("已导出");
            bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            bw.write("姓名,准考证号,学生学号,性别,多选题得分,单选和判断得分,总分\n");
            for (ScoreReport scoreReport : scoreReports) {
                bw.write(
                        scoreReport.getStu_name()+","+
                                scoreReport.getAdm_num()+","+
                                scoreReport.getStu_num() +","+
                                scoreReport.getStu_gender() +","+
                                scoreReport.getSum_multiple_score() +","+
                                scoreReport.getSum_single_score() +","+
                                scoreReport.getSum() +
                                "\n");
            }
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  0;
    }
}
