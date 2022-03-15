package kr.co.m2m.example.demo.api.vacation.model;

import kr.co.m2m.example.framework.web.model.BaseSearchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MemberSO extends BaseSearchVO<MemberSO> {
	private String dept;
	private String sDate;
}
