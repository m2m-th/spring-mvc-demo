package kr.co.m2m.example.demo.api.deptMember.model;

import javax.validation.constraints.NotEmpty;

import kr.co.m2m.example.framework.annotation.ValidInsert;
import kr.co.m2m.example.framework.annotation.ValidUpdate;
import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class YearCntPO extends BaseModel<YearCntPO> {
	@NotEmpty(groups = { ValidInsert.class, ValidUpdate.class })
	private String id;
	private String year;
	private String a_cnt;
}
