package kr.co.m2m.example.demo.api.deptMember.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MemberSO extends BaseModel<MemberSO> {
	private String id;
	private String pw;
	private String name;
	private String dept;
	private String jikgub;
	private String s_date;
	private String chief_yn;
	private String retire;
	private String retire_dt;
	private String eMail;
}
