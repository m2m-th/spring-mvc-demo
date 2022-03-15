package kr.co.m2m.example.demo.api.calendarView.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CalendarViewVO extends BaseModel<CalendarViewVO> {
	private String id;
	private int weeks;
	private String gubun;
	private int serial;
	private String s_date;
	private String e_date;
	private String proceed;
	private String content;
	private String bigo;

}
