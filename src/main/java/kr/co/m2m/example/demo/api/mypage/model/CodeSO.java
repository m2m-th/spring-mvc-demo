package kr.co.m2m.example.demo.api.mypage.model;

import kr.co.m2m.example.framework.web.model.BaseSearchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.mypage.model
 * @파일명 : CodeSO.java
 * @작성자 : ihKim
 * @생성일자 : 2020. 6. 18.
 * @설명 : 공통코드 SO
 */

@Data
@EqualsAndHashCode
public class CodeSO extends BaseSearchVO<CodeSO> {

	private static final long serialVersionUID = 3465256802367432249L;

	private String grcode; /* 공통코드 */
}
