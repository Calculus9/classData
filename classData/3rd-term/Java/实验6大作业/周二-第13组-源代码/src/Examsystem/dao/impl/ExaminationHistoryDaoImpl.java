package Examsystem.dao.impl;

import Examsystem.utils.JDBCUtils;
import Examsystem.bean.ExaminationHistory;
import Examsystem.bean.ScoreReport;
import Examsystem.dao.ExaminationHistoryDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
* 考试历史数据持久层实现类
* */
public class ExaminationHistoryDaoImpl implements ExaminationHistoryDao {
    @Override
    public List<ExaminationHistory> findAll() {
        String sql = "SELECT * FROM examination_history where his_stu_selected !=? ";

        List<ExaminationHistory> examinationHistoryList = new ArrayList<>();
        try {
            PreparedStatement queryStatement = JDBCUtils.connection.prepareStatement(sql);
            // 去掉没有选择的 &&
            queryStatement.setString(1, "&&");
            ResultSet resultSet = queryStatement.executeQuery();
            while (resultSet.next()) {
                ExaminationHistory examinationHistory = historyMapping(resultSet);
                examinationHistoryList.add(examinationHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return examinationHistoryList;
    }

    @Override
    public ExaminationHistory update(String stuNum, String admNum, Integer optionId) {
        String sql = "";
        /**
         * 1. 通过传入的optionId,检索到它的questionId,content,
         * 3. 更新一整条数据，共2个字段。
         */
        Integer questionId = null;
        String optionNum = "";
        String querySql = "SELECT parent_id,o_num FROM question_option where o_id=?";
        String updateSql = "Update examination_history SET his_o_id=?,his_stu_selected=? where his_stu_num=? and his_adm_num=? and his_q_id=?";
        try {
            //1.查询选项，得到题目ID
            PreparedStatement queryStatement = JDBCUtils.connection.prepareStatement(querySql);
            queryStatement.setInt(1, optionId);
            ResultSet resultSet = queryStatement.executeQuery();
            while (resultSet.next()) {
                questionId = resultSet.getInt("parent_id");
                optionNum = resultSet.getString("o_num");
            }

            //2.插入数据
            PreparedStatement updateStatement = JDBCUtils.connection.prepareStatement(updateSql);
            updateStatement.setInt(1,optionId);
            updateStatement.setString(2,optionNum);
            updateStatement.setString(3,stuNum);
            updateStatement.setString(4,admNum);
            updateStatement.setInt(5,questionId);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExaminationHistory updatetWithStr(String stuNum, String admNum, Integer optionId,String str){
        String sql = "";
        /**
         * 1. 通过传入的optionId,检索到它的questionId,content,
         * 2. 更新一整条数据，只需要更新所选的选项的那部分
         */
        Integer questionId = null;
        String optionNum = "";
        String querySql = "SELECT parent_id,o_num FROM question_option where o_id=?";
        String updateSql = "Update examination_history SET his_o_id=?,his_stu_selected=?  where his_stu_num=? and his_adm_num=? and his_q_id=? and his_o_id=?";
        try {
            //1.查询选项，得到题目ID
            PreparedStatement queryStatement = JDBCUtils.connection.prepareStatement(querySql);
            queryStatement.setInt(1, optionId);
            ResultSet resultSet = queryStatement.executeQuery();
            while (resultSet.next()) {
                // 得到题目编号
                questionId = resultSet.getInt("parent_id");
                optionNum = resultSet.getString("o_num");
            }
            //3.重新更新这一条数据，换成^^
            PreparedStatement updateStatement = JDBCUtils.connection.prepareStatement(updateSql);
            updateStatement.setInt(1,optionId);
            updateStatement.setString(2,"^^");
            updateStatement.setString(3,stuNum);
            updateStatement.setString(4,admNum);
            updateStatement.setInt(5,questionId);
            updateStatement.setInt(6,optionId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExaminationHistory insert(String stuNum, String admNum, Integer optionId) {
        /*
         * 1. 通过传入的optionId,检索到它的questionId,通过questionId取出正确答案和题目分值。
         * 2. 通过questionId取出正确答案和题目分值。
         * 3. 插入一整条数据，共5个字段。
         */
        Integer questionId = null;
        String optionNum = "";
        Integer score = 0;
        String rightAnswer = "";
        String querySql = "SELECT parent_id,o_num FROM question_option where o_id=?";
        String questionSql = "SELECT * FROM question where q_id=?";
        String insertSql = "INSERT examination_history SET his_o_id=?,his_stu_selected=?,his_right_answer=?,his_q_score=? , his_stu_num=? , his_adm_num=? , his_q_id=?";
        try {
            //1.查询选项，得到题目ID
            PreparedStatement queryStatement = JDBCUtils.connection.prepareStatement(querySql);
            queryStatement.setInt(1, optionId);
            ResultSet resultSet = queryStatement.executeQuery();
            while (resultSet.next()) {
                questionId = resultSet.getInt("parent_id");
                optionNum = resultSet.getString("o_num");
            }
            //2.查询题目，得到题目的正确答案和分数。
            PreparedStatement questionState = JDBCUtils.connection.prepareStatement(questionSql);
            questionState.setInt(1, questionId);
            ResultSet questionSet = questionState.executeQuery();
            while (questionSet.next()) {
                rightAnswer = questionSet.getString("q_right_answer");
                score = questionSet.getInt("q_score");
            }
            //3.插入数据
            PreparedStatement insertStatement = JDBCUtils.connection.prepareStatement(insertSql);
            insertStatement.setInt(1,optionId);
            insertStatement.setString(2,optionNum);
            insertStatement.setString(3,rightAnswer);
            insertStatement.setInt(4,score);
            insertStatement.setString(5,stuNum);
            insertStatement.setString(6,admNum);
            insertStatement.setInt(7,questionId);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExaminationHistory isQuestionExistByHisOId(String stuNum, String admNum, Integer optionId) {
        /**
         * 1. 通过optionId得到questionId
         * 2. 判断这道题，在不在历史记录中。(当前这个人是不是在这里)
         * 3. 如果在历史记录中，我们就更新这道题的选项，如果不在，我们就新增
         */
        Integer questionId = null;

        String isExistSql = "SELECT * FROM examination_history where his_q_id=? and his_stu_num=? and his_adm_num=?";
        String querySql = "SELECT parent_id FROM question_option where o_id=?";
        ExaminationHistory examinationHistory = null;
        try {
            //1.查询选项，得到题目ID
            PreparedStatement queryStatement = JDBCUtils.connection.prepareStatement(querySql);
            queryStatement.setInt(1, optionId);

            ResultSet questionSet = queryStatement.executeQuery();
            while (questionSet.next()) {
                questionId = questionSet.getInt("parent_id");
            }
            //2.判断此题在不在历史记录中
            //2.1是不是当前这个人所答的这道题，如果是才看是不是在历史记录中
            PreparedStatement preparedStatement = JDBCUtils.connection.prepareStatement(isExistSql);
            preparedStatement.setInt(1,questionId);
            preparedStatement.setString(2, stuNum);
            preparedStatement.setString(3, admNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                examinationHistory = historyMapping(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examinationHistory;
    }

    @Override
    public List<ScoreReport> calculateScore() {
        String querySql = "SELECT * FROM score_result";
        ArrayList<ScoreReport> scoreReports = new ArrayList<ScoreReport>();
        try {
            //1.查询选项，得到题目ID
            Statement statement = JDBCUtils.connection.createStatement();
            ResultSet questionSet = statement.executeQuery(querySql);
            while (questionSet.next()) {
                ScoreReport scoreReport = new ScoreReport();
                scoreReport.setAdm_num(questionSet.getString("adm_num"));
                scoreReport.setStu_gender(questionSet.getString("stu_gender"));
                scoreReport.setStu_name(questionSet.getString("stu_name"));
                scoreReport.setStu_num(questionSet.getString("stu_num"));
                scoreReport.setSum_multiple_score(questionSet.getInt("sum_multiple_score"));
                scoreReport.setSum_single_score(questionSet.getInt("sum_single_score"));
                scoreReport.setSum(questionSet.getInt("sum"));
                scoreReports.add(scoreReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scoreReports;
    }

    /**
     * 传入resultSet对象，返回新对象。
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private ExaminationHistory historyMapping(ResultSet resultSet) throws SQLException {
        ExaminationHistory examinationHistory = new ExaminationHistory();
        examinationHistory.setHisId(resultSet.getInt("his_id"));
        examinationHistory.setHisStuNum(resultSet.getString("his_stu_num"));
        examinationHistory.setHisAdmNum(resultSet.getString("his_adm_num"));
        examinationHistory.setHisExaId(resultSet.getString("his_exa_id"));
        examinationHistory.setHisQtId(resultSet.getInt("his_qt_id"));
        examinationHistory.setHisQId(resultSet.getInt("his_q_id"));
        examinationHistory.setHisOId(resultSet.getInt("his_o_id"));
        examinationHistory.setHisStuSelected(resultSet.getString("his_stu_selected"));
        examinationHistory.setCreateTime(resultSet.getTimestamp("create_time"));
        examinationHistory.setLastModifyTime(resultSet.getTimestamp("last_modify_time"));
        return examinationHistory;
    }
}
