package kr.co.m2m.example.demo.api.vacation.model;

import kr.co.m2m.example.framework.web.model.BaseSearchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GuntaeSO extends BaseSearchVO<GuntaeSO> {
	private String vCode;
	private String id;
	private String gDate;
	private String minusDayoff;
	private String sumMinusDayoff;
	private String acnt;

	private String strDate;
	private String dept;
	private String jikgub;
	private String name;
	private String s_date;
	private String year;
	private String month;
	private String chiefYn;
}
