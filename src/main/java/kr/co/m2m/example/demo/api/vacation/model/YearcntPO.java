package kr.co.m2m.example.demo.api.vacation.model;

import javax.validation.constraints.NotEmpty;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class YearcntPO extends BaseModel<YearcntPO> {
	@NotEmpty
	private String year;
}
