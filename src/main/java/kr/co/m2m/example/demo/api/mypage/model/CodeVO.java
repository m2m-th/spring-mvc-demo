package kr.co.m2m.example.demo.api.mypage.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.mypage.model
 * @파일명 : CodeVO.java
 * @작성자 : ihKim
 * @생성일자 : 2020. 6. 18.
 * @설명 : 공통코드 VO
 */

@Data
@EqualsAndHashCode
public class CodeVO extends BaseModel<CodeVO> {

	private static final long serialVersionUID = -4753487147051676748L;

	private String scode; /* 세부코드 */
	private String scodeNm; /* 세부코드명 */
	private String bigo1; /* 비고1 */
}
