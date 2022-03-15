package kr.co.m2m.example.demo.api.login.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LoginVO extends BaseModel<LoginVO> {

	private String id; /* 아이디 */
	private String pw; /* 비밀번호 */
	private String name; /* 이름 */
	private String dept; /* 부서코드 */
	private String jikgub; /* 직급코드 */
	private String sDate; /* 입사일 */
	private String chiefYn; /* 부서장여부 */
	private String retire; /* 퇴직여부 */
	private String retireDt; /* 퇴직일자 */
	private String email; /* 이메일 */
	private String deptNm; /* 부서명 */
	private String jikgubNm; /* 직급명 */
}
