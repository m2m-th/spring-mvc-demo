
package kr.co.m2m.example.demo.api.vacation.model;

import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GuntaePO extends BaseModel<GuntaePO> {
	private String id;
	private String name;
	private String aCnt;
	private String sumMinusDayoff;

	// 월별 근태 조회
	private int dkey;
	private String title;
	private String dates;
	private String tempClass;
	private String year;
	private int month;
	private float minus;
	private String vCode;

	/* 2020/06/23 작성자 : 백승지 */
	private float prevMinus; // 기존 차감일수(일자 기준)
	private float reqMinus; // 필요 차감일수(일자 기준)
	private float myAcnt; // 내 모든 연차수 (년도 기준)
	private float myAminus; // 내 모든 연차차감 수 (년도 기준)

	/* 2020/06/24 작성자 : 백승지, 김현태 */
	private String months; // 월
	private String dayOff; // 월차 사용일자
	private String halfOff; // 반차 사용일자
}
