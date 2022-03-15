package kr.co.m2m.example.demo.api.calendarView.model;

import javax.validation.constraints.NotEmpty;

import kr.co.m2m.example.framework.annotation.ValidInsert;
import kr.co.m2m.example.framework.annotation.ValidUpdate;
import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CalendarViewPO extends BaseModel<CalendarViewPO> {
	private int weeks;
	private int serial;

	@NotEmpty(groups = { ValidInsert.class, ValidUpdate.class })
	private String id;

	@NotEmpty
	private String gubun;

	@NotEmpty(groups = ValidInsert.class)
	@NotEmpty
	private String s_date;
	private String e_date;
	private String proceed;
	private String content;
	private String bigo;
	
	// 캘린더 생성 파라메터
	private String mYears;
	private String strDate;
	private String holiday;
	private String chk;
}
