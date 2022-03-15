package kr.co.m2m.example.demo.api.example.model;

import javax.validation.constraints.NotEmpty;

import kr.co.m2m.example.framework.annotation.ValidDelete;
import kr.co.m2m.example.framework.annotation.ValidInsert;
import kr.co.m2m.example.framework.annotation.ValidUpdate;
import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Example01PO extends BaseModel<Example01PO> {
	@NotEmpty(groups = { ValidUpdate.class, ValidDelete.class })
	private long memberNo;

	@NotEmpty(groups = { ValidInsert.class, ValidUpdate.class }, message = "{member.id.empty}")
	private String memberId;

	@NotEmpty(groups = { ValidInsert.class, ValidUpdate.class }, message = "{member.fname.empty}")
	private String firstName;

	@NotEmpty(groups = { ValidUpdate.class }, message = "{member.lname.empty}")
	private String lastName;

	private String address;
	private String city;
	private String state;
	
	@NotEmpty(groups = { ValidInsert.class }, message = "{member.phone.empty}")
	private String phone;
}
