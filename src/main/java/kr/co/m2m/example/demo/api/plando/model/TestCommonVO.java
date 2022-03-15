package kr.co.m2m.example.demo.api.plando.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TestCommonVO extends BaseModel<TestCommonVO> {
	private Integer scode;
	private String scodeNm;
}
