package kr.co.m2m.example.demo.api.example.model;

import kr.co.m2m.example.framework.web.model.BaseSearchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Example01SO extends BaseSearchVO<Example01SO> {
	private long memberNo;
	private String memberId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String phone;
	private String dept;

	private String months;
	private String dayOff;
	private String halfOff;

	private int YEAR;

	private String id;
	private String name;
	private String s_date;
	private String year;
	private String chief_yn;

	// 월별 근태 조회
	private String strDate;

}
