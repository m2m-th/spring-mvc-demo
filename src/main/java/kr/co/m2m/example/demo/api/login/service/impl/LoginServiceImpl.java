package kr.co.m2m.example.demo.api.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.example.demo.api.login.mapper.LoginMapper;
import kr.co.m2m.example.demo.api.login.model.LoginSO;
import kr.co.m2m.example.demo.api.login.model.LoginVO;
import kr.co.m2m.example.demo.api.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public LoginVO selectMember(LoginSO so) {

		return loginMapper.selectMember(so);
	}
}
