package kr.co.m2m.example.demo.api.vacation.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GuntaeVO extends BaseModel<GuntaeVO> {
	private long memberId;
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
	private String chiefYn;

	// 20200624 kht
	private String id;
	private String name;
	private String loginId;
	private String year;

	private String months; // 월
	private String dayOff; // 월차 사용일자
	private String halfOff; // 반차 사용일자
}
