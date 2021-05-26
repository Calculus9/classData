package Examsystem.dao.impl;

import Examsystem.utils.JDBCUtils;
import Examsystem.bean.Student;
import Examsystem.dao.StudentDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生数据持久层实现类
 */
public class StudentDaoImpl implements StudentDao {
    private Connection connection = JDBCUtils.connection;

    /**
     *
     * @return 学生列表
     */
    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        ArrayList<Student> studentList = new ArrayList<>();
        /**
         * 从数据库中找到所有学生，并且返回学生列表。
         */
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = new Student();
                String stuNum = resultSet.getString("stu_num");
                String stuName = resultSet.getString("stu_name");
                String stuGender = resultSet.getString("stu_gender");
                student.setStuNum(stuNum)
                        .setStuName(stuName)
                        .setStuGender(stuGender);

                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
