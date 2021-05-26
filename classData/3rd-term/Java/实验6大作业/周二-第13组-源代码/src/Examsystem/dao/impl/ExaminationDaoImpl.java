package Examsystem.dao.impl;

import Examsystem.utils.JDBCUtils;
import Examsystem.bean.Examination;
import Examsystem.dao.ExaminationDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
* 考试数据持久层实现类
* */
public class ExaminationDaoImpl implements ExaminationDao {
    @Override
    public List<Examination> findAll() {
        String sql = "select * from emamination_view";
        ArrayList<Examination> examinationList = new ArrayList<>();
        /**
         * 从数据库中找到所有试卷相关信息，并且返回试卷对象。
         */
        try {
            Statement statement = JDBCUtils.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Examination examination = new Examination();
                String examName = resultSet.getString("exa_name");
                String examQuestionNum = resultSet.getString("qt_num");
                String examQuestionName = resultSet.getString("qt_name");
                String examQuestionDescribe=resultSet.getString("qt_describe");
                String questionNum = resultSet.getString("q_num");
                String questionDescribe = resultSet.getString("q_describe");
                String questionRightAnswer = resultSet.getString("q_right_answer");
                String questionScore = resultSet.getString("q_score");
                String optionNum = resultSet.getString("o_num");
                String optionContent = resultSet.getString("o_content");
                examination.setExamName(examName)
                        .setExamQuestionNum(examQuestionNum)
                        .setExamQuestionName(examQuestionName)
                        .setExamQuestionDescribe(examQuestionDescribe)
                        .setQuestionNum(questionNum)
                        .setQuestionDescribe(questionDescribe)
                        .setQuestionRightAnswer(questionRightAnswer)
                        .setQuestionScore(questionScore)
                        .setOptionNum(optionNum)
                        .setOptionContent(optionContent);
                examinationList.add(examination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examinationList;
    }
}
