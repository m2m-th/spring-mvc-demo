package kr.co.m2m.example.demo.api.calendarView.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.example.demo.api.calendarView.model.CalendarViewPO;
import kr.co.m2m.example.demo.api.calendarView.model.CalendarViewSO;

@Mapper
public interface CalendarViewMapper {

public List<CalendarViewSO> selectCalYearsList(CalendarViewPO paramModel);
	
	// 캘린더 생성
	public int saveCalYearsList(CalendarViewPO paramModel);
	
	// 캘린더 중복체크
	public int selectCalYearsCnt(CalendarViewPO paramModel);
	
	// 법정 공휴일 등록
	public void registHoliday(CalendarViewPO paramModel);
	
	// 휴일구분 리스트 조회
	public List<CalendarViewSO> selectHolidayList(CalendarViewPO paramModel);

	// 캘린더 제거
	public void deleteCalYearsList(CalendarViewPO paramModel);

	// 고정 휴일 추가
	public void registFixedHoliday(CalendarViewPO paramModel);

}
