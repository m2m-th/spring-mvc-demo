package kr.co.m2m.example.demo.api.example.service;

import java.util.List;

import kr.co.m2m.example.demo.api.example.model.Example01PO;
import kr.co.m2m.example.demo.api.example.model.Example01SO;
import kr.co.m2m.example.demo.api.example.model.Example01VO;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;

public interface Example01Service {
	// 월별 근태 조회 > 캘린더 요소 조회 : bomi
	public List<Example01VO> selectCalendarElement_(Example01SO so);

	// 월별 근태 조회 > 캘린더 요소 조회 : kht
	public List<Example01VO> selectCalendarElement(Example01SO so);

	public ResultListModel<Example01VO> selectExample01List(Example01SO so);

	public ResultModel<Example01VO> searchExample01(Example01SO so);

	public ResultModel<String> insertExample01(Example01PO po);

	public ResultModel<String> updateExample01(Example01PO po);

	public ResultModel<String> deleteExample01(Example01SO so);

	public List<Example01VO> userList(Example01SO searchModel);

	public List<Example01VO> lookupVacationList(Example01SO searchModel);

	public float useVacation(Example01SO searchModel);

	public List<Example01VO> usedVacationTable(Example01SO searchModel);

}
