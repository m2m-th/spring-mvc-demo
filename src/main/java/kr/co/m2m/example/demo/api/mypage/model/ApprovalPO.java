package kr.co.m2m.example.demo.api.mypage.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.mypage.model
 * @파일명 : ApprovalPO.java
 * @작성자 : ihKim
 * @생성일자 : 2020. 6. 18.
 * @설명 : 결재 PO
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ApprovalPO extends BaseModel<ApprovalPO> {

	private static final long serialVersionUID = 6293053550884879474L;

	private int serial; /* 결재serial */
	private String submitId; /* 결재상신ID */
	private String appId; /* 결재자ID */
	private String appCode; /* 결재코드 */
	private String appContent; /* 결재내용 */
	private String app1; /* 대표결재1 */
	private String app2; /* 대표결재2 */
	private String app3; /* 대표결재3 */
	private String sDate; /* 시작일 */
	private String eDate; /* 종료일 */
	private String vCode; /* 근태코드 */
	private String vDate; /* 근태일 */
	private String appGubunNm; /* 결재종류명 */
	private String appStatus; /* 결재상태명 */
	private String id; /* 아이디 */
	private String pwd1; /* 패스워드1 */
	private String pwd2; /* 패스워드2 */
	private String name; /* 이름 */
	private String vReason; /* 근태사유 */
	private String gubun; /* 템플릿구분 */
	private String message; /* 템플릿내용 */
}
