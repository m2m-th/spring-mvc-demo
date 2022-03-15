package kr.co.m2m.example.demo.api.deptMember.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class YearCntVO extends BaseModel<YearCntVO> {
	private String id;
	private String year;
	private String a_cnt;
}
