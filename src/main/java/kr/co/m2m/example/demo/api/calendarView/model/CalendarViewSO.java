package kr.co.m2m.example.demo.api.calendarView.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CalendarViewSO extends BaseModel<CalendarViewSO> {
	private String id;
	private int weeks;
	private String gubun;
	private int serial;
	private String s_date;
	private String e_date;
	private String proceed;
	private String content;
	private String bigo;
	
	// 캘린더 조회
	private String title;
	private String tempClass;
	private int dkey;
	private int years;
	private int months;
	
	// 휴일구분 조회
	private String grcode;
	private String scode;
	private String scodeNm;
	private String useYn;
	private String bigo1;
	private String bigo2;
	private String bigo3;

}
