package Examsystem.service.impl;

import Examsystem.dao.impl.AdmissionDaoImpl;
import Examsystem.enums.LoginEnum;
import Examsystem.service.LoginService;
import Examsystem.bean.Admission;
import Examsystem.vo.ResultVO;
/*
*  登录服务层实现类
* */
public class LoginServiceImpl implements LoginService {
    private AdmissionDaoImpl admissionDao = new AdmissionDaoImpl();
    @Override
    public ResultVO login(String admNum, String stuDum) {
        Admission admission = admissionDao.findByAdmNum(admNum);
        if (admission == null) {
            return new ResultVO()
                    .setStatus(LoginEnum.ADMNUMERROR.getCode())
                    .setMessage(LoginEnum.ADMNUMERROR.getMessage());
        } else {
            if (admission.getStuNum().equals(stuDum)) {
                return new ResultVO().setStatus(LoginEnum.SUCCESS.getCode()).setMessage(LoginEnum.SUCCESS.getMessage());
            } else {
                return new ResultVO().setStatus(LoginEnum.STUNUMERROR.getCode()).setMessage(LoginEnum.STUNUMERROR.getMessage());
            }
        }
    }
}
