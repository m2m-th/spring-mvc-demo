package kr.co.m2m.example.demo.api.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.example.demo.api.login.model.LoginSO;
import kr.co.m2m.example.demo.api.login.model.LoginVO;

@Mapper
public interface LoginMapper {

	public LoginVO selectMember(LoginSO so);
}
