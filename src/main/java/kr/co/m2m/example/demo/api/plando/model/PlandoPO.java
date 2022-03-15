package kr.co.m2m.example.demo.api.plando.model;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlandoPO extends BaseModel<PlandoPO> {
	private String id;
	private Integer weeks;
	private String gubun;
	private Integer serial;
	// 날짜 형식인지 체크
	// @DateTimeFormat(pattern="yyyyMMdd")
	private String sDate;
	// 날짜 형식인지 체크, 상관체크(sDate)
	// @DateTimeFormat(pattern="yyyyMMdd")
	private String eDate;
	private String proceed;
	private String content;
	private String bigo;
	private String mode;
	@DateTimeFormat(pattern="yyyyMMdd")
	private String editSDate; /* 수정시 넘겨주는 날짜 */

	private List<PlandoPO> plandoPOList;
}
