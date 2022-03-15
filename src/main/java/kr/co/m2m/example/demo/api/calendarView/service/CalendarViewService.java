package kr.co.m2m.example.demo.api.calendarView.service;

import kr.co.m2m.example.demo.api.calendarView.model.CalendarViewPO;
import kr.co.m2m.example.demo.api.calendarView.model.CalendarViewSO;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;

public interface CalendarViewService {
	public ResultListModel<CalendarViewSO> selectCalYearsList(CalendarViewPO paramModel);

	public ResultModel<String> saveCalYearsList(CalendarViewPO paramModel);

	public ResultModel<String> registHoliday(CalendarViewPO paramModel);
	
	public ResultListModel<CalendarViewSO> selectHolidayList(CalendarViewPO paramModel);

	public ResultModel<String> selectCalYearsCnt(CalendarViewPO paramModel);
	
	public ResultModel<String> registFixedHoliday(CalendarViewPO paramModel);
}
