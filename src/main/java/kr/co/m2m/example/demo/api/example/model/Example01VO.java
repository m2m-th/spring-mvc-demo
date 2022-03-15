package kr.co.m2m.example.demo.api.example.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Example01VO extends BaseModel<Example01VO> {
	
	public Example01VO(String memberId, String firstName, String lastName) {
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	private long memberNo;
	private String memberId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String phone;

	// 월별 근태 조회
	private int dkey;
	private String title;
	private String dates;
	private String tempClass;
	private String year;
	private int month;
}
