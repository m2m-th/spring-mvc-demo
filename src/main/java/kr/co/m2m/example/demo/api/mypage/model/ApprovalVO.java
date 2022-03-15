package kr.co.m2m.example.demo.api.mypage.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.mypage.model
 * @파일명 : ApprovalVO.java
 * @작성자 : ihKim
 * @생성일자 : 2020. 6. 18.
 * @설명 : 결재VO
 */

@Data
@EqualsAndHashCode
public class ApprovalVO extends BaseModel<ApprovalVO> {

	private static final long serialVersionUID = 1712562527363404109L;

	private int serial; /* 결재serial */
	private String submitId; /* 결재상신ID */
	private String submitDt; /* 결재상신일 */
	private String appGubun; /* 결재종류 */
	private String appStatus; /* 결재상태 */
	private String appId; /* 결재자ID */
	private String appDt; /* 결재일 */
	private String appCode; /* 결재코드 */
	private String appContent; /* 결재내용 */
	private String app1; /* 대표결재1 */
	private String app1Dt; /* 대표결재1일시 */
	private String app1Code; /* 결재1코드 */
	private String app1Content; /* 결재1사유 */
	private String app2; /* 대표결재2 */
	private String app2Dt; /* 대표결재2일시 */
	private String app2Code; /* 결재2코드 */
	private String app2Content; /* 결재2사유 */
//	private String app3; /* 대표결재3 */
//	private String app3Dt; /* 대표결재3일시 */
	private String app3Code; /* 결재3코드 */
	private String app3Content; /* 결재3사유 */
	private String sDate; /* 시작일 */
	private String eDate; /* 종료일 */
	private String vCode; /* 근태코드 */
	private String vReason; /* 근태사유 */
	private String name; /* 이름 */
	private String appGubunNm; /* 결재종류명 */
	private String appStatusNm; /* 결재상태명 */
	private String vCodeNm; /* 근태코드명 */
	private String appCodeNm; /* 결재상태명 */
	private String app3; /* 최종결재자 */
	private String app3Dt; /* 최종결재일시 */
	private String app3Nm; /* 최종결재자명 */
	private String app1CodeNm; /* 대표1결재상태명 */
	private String app2CodeNm; /* 대표2결재상태명 */
	private String app3CodeNm; /* 대표3결재상태명 */
	private String deptNm; /* 부서명 */
	private String jikgubNm; /* 직급명 */
	private String content; /* 템플릿내용 */
	private String id; /* 아이디 */
}
