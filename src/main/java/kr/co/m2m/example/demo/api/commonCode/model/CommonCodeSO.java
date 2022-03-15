package kr.co.m2m.example.demo.api.commonCode.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CommonCodeSO extends BaseModel<CommonCodeSO> {
	private String grCode;
	private String grCodeNm;
	private String guseYn;
	private String sCode;
	private String sCodeNm;
	private int priority;
	private String sUseYn;
	private String bigo1;
	private String bigo2;
	private String bigo3;

}
