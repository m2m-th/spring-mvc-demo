package kr.co.m2m.example.demo.api.login.service;

import kr.co.m2m.example.demo.api.login.model.LoginSO;
import kr.co.m2m.example.demo.api.login.model.LoginVO;

public interface LoginService {

	public LoginVO selectMember(LoginSO so);
}
