package kr.co.m2m.example.demo.api.plando.model;

import javax.validation.constraints.NotEmpty;

import kr.co.m2m.example.framework.web.model.BaseSearchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PlandoSO extends BaseSearchVO<PlandoSO> {
	private String id; /* 아이디 */
	private Integer weeks; /* 주차 */
	private String gubun; /* 실적/계획 (1:실적, 2:계획) */
	private Integer serial; /* 시리얼번호 */
	private String sDate; /* 시작일 */
	private String eDate; /* 종료일 */
	private String proceed; /* 진행사항(공통코드: A006) */
	@NotEmpty
	private String content; /* 업무내용 */
	private String bigo; /* 비고 */
	private String grcode; /* 그룹코드 */
	private String scode; /* 세부코드 */
}
