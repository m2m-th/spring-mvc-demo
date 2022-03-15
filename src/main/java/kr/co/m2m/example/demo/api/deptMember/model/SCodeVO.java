package kr.co.m2m.example.demo.api.deptMember.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SCodeVO extends BaseModel<SCodeVO> {
	private String grCode;
	private String scode;
	private String scodeNm;
	private int priority;
	private String suseYn;
	private String bigo1;
	private String bigo2;
	private String bigo3;
	private String name;
	private String jikgub;
	private String jScode;
	private String sdate;
	private String chiefYn;
	private String email;
	private String retire;
	private String retireDt;
	private String dept;
	private String id;

}
