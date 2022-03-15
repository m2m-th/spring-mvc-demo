package kr.co.m2m.example.demo.api.vacation.model;

import javax.validation.constraints.NotEmpty;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class VacationVO extends BaseModel<VacationVO> {
	private String serial;
	private String id;

	@NotEmpty
	private String s_date;
	private String e_date;
	private String v_code;

	private String v_reason;
	private String v_date; /* 근태일 */

}
