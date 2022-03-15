package kr.co.m2m.example.demo.api.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.example.demo.api.example.model.Example01PO;
import kr.co.m2m.example.demo.api.example.model.Example01SO;
import kr.co.m2m.example.demo.api.example.model.Example01VO;

@Mapper
public interface Example01Mapper {
	// 월별 근태 조회 > 캘린더 요소 조회
	public List<Example01VO> selectCalendarElement(Example01SO so);

	public List<Example01VO> selectExample01List(Example01SO so);

	public Example01VO searchExample01(Example01SO so);

	public int insertExample01(Example01PO so);

	public int updateExample01(Example01PO so);

	public int deleteExample01(Example01SO so);
	
	public List<Example01VO> userList(Example01SO searchModel);
	
	public List<Example01VO> lookupVacationList(Example01SO searchModel);
	
	public float useVacation(Example01SO searchModel);
	
	public List<Example01VO> usedVacationTable(Example01SO searchModel);
	
}
