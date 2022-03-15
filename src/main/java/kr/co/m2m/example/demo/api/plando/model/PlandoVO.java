package kr.co.m2m.example.demo.api.plando.model;

import java.util.List;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class PlandoVO extends BaseModel<PlandoVO> {
	private String id;
	private String gubun;
	private Integer serial;
	private String sDate;
	private String eDate;
	private String proceed;
	private String proceedNm;
	private String content; /* 업무 내용 */
	private String bigo; /* 비고 */
	private Integer scode;
	private String scodeNm;
	private String myDept; /* 내 부서 여부 */
	private String name; /* 이름 */
	private Integer rnum; /* No */
	private String mon;
	private String tue;
	private String wed;
	private String thur;
	private String fri;
	
	private List<PlandoVO> plandoVOList;
}
