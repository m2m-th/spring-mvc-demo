package kr.co.m2m.example.demo.api.mypage.model;

import kr.co.m2m.example.framework.web.model.BaseSearchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.mypage.model
 * @파일명 : ApprovalSO.java
 * @작성자 : ihKim
 * @생성일자 : 2020. 6. 18.
 * @설명 : 결재 SO
 */

@Data
@EqualsAndHashCode
public class ApprovalSO extends BaseSearchVO<ApprovalSO> {

	private static final long serialVersionUID = 1121061730593592453L;

	private int serial; /* 결재serial */
	private String submitId; /* 결재상신ID */
	private String appGubun; /* 결재종류 */
	private String appStatus; /* 결재상태 */
	private String appId; /* 결재자ID */
	private String app1; /* 대표결재1 */
	private String app2; /* 대표결재2 */
	private String app3; /* 대표결재3 */
	private String appCode; /* 결재코드 */
	private String gubun; /* 템플릿구분 */
	private String chiefYn; /* 부서장여부 */
}
