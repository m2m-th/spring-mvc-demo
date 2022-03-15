package kr.co.m2m.example.demo.api.commonCode.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import kr.co.m2m.example.framework.annotation.ValidInsert;
import kr.co.m2m.example.framework.annotation.ValidUpdate;
import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonCodePO extends BaseModel<CommonCodePO> {
	@NotEmpty(groups = { ValidInsert.class, ValidUpdate.class })

	@NotNull
	private String grCode; /* 그룹코드 */

	private String grCodeNm; /* 그룹코드명 */

	private String guseYn; /* 사용여부 */

}
