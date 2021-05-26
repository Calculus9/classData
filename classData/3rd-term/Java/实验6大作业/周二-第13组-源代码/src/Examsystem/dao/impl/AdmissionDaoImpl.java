package Examsystem.dao.impl;

import Examsystem.bean.Admission;
import Examsystem.dao.AdmissionDao;
import Examsystem.utils.JDBCUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
* 准考证号数据持久层实现类
*/
public class AdmissionDaoImpl implements AdmissionDao {
   /*
   * 通过准考证号查询数据库
   */
    @Override
    public Admission findByAdmNum(String admNum) {
        String sql = "SELECT * FROM admission where adm_num=?";
        Admission admission = null;
        try {
            PreparedStatement preparedStatement =  JDBCUtils.connection.prepareStatement(sql);
            preparedStatement.setString(1, admNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            //为admission对象设置属性
            while (resultSet.next()) {
                admission = new Admission();
                String adm_num = resultSet.getString("adm_num");
                String stu_num = resultSet.getString("stu_num");
                admission.setAdmNum(adm_num).setStuNum(stu_num);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admission;
    }
}
