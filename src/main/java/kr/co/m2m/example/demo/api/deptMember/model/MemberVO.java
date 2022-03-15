package kr.co.m2m.example.demo.api.deptMember.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MemberVO extends BaseModel<MemberVO> {
	private String id;
	private String pw;
	private String name;
	private String dept;
	private String jikgub;
	private String sdate;
	private String chiefYn;
	private String retire;
	private String retireDt;
	private String email;
	private String scodeNm;
}
