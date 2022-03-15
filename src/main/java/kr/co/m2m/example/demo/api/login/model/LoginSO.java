package kr.co.m2m.example.demo.api.login.model;

import kr.co.m2m.example.framework.web.model.BaseSearchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LoginSO extends BaseSearchVO<LoginSO> {

	private String id; /* 아이디 */
	private String pw; /* 비밀번호 */
}
