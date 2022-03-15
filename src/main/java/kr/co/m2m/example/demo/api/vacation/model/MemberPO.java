package kr.co.m2m.example.demo.api.vacation.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MemberPO extends BaseModel<MemberPO> {
	private String id;
	private String name;
}
