package Examsystem.service;

import Examsystem.vo.ResultVO;
/*
*  登录服务层实现
* */
public interface LoginService {
    public ResultVO login(String admNum, String stuDum);
}
