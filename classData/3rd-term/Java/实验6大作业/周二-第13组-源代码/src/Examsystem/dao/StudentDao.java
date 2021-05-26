package Examsystem.dao;

import Examsystem.bean.Student;

import java.util.List;

/**
 * 学生数据持久层实现类
 */
public interface StudentDao {
    public List<Student> findAll();
}
