package kr.co.m2m.example.demo.api.vacation.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ScodeVO extends BaseModel<ScodeVO> {
	private String grCode;
	private String sCode;
	private String scodeNm;
	private int priority;
	private String useYn;
	private String bigo1;
	private String bigo2;
	private String bigo3;
}