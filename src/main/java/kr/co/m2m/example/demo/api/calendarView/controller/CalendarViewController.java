package kr.co.m2m.example.demo.api.calendarView.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.example.demo.api.calendarView.model.CalendarViewPO;
import kr.co.m2m.example.demo.api.calendarView.model.CalendarViewSO;
import kr.co.m2m.example.demo.api.calendarView.service.CalendarViewService;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/calendar")
public class CalendarViewController {

	@Autowired
	CalendarViewService calendarViewService;

	@Autowired
	private MessageSource messageSource;
	
	// 캘린더 조회
		@GetMapping("selectCalYearsList")
		public ResultListModel<CalendarViewSO> selectCalYearsList(CalendarViewPO paramModel) {
			log.info("Contorller Start..... SO : {}", paramModel);
			log.info("stupid JDH");
			return calendarViewService.selectCalYearsList(paramModel);
		}
		
		// 휴일구분 조회
		@GetMapping("selectHolidayList")
		public ResultListModel<CalendarViewSO> selectHolidayList(CalendarViewPO paramModel) {
			log.info("Contorller Start..... SO : {}", paramModel);
			log.info("stupid JDH");
			return calendarViewService.selectHolidayList(paramModel);
		}
		
		// 캘린더 중복체크
		@GetMapping("selectCalYearsCnt")
		public ResultModel<String> chkCalYearsList(CalendarViewPO paramModel) {
			log.info("Contorller Start..... SO : {}", paramModel);
			log.info("paramModel.getMYears ===> " + paramModel.getMYears());
			return calendarViewService.selectCalYearsCnt(paramModel);
		}
		
		// 캘린더 생성
		@GetMapping("saveCalYearsList")
		public ResultModel<String> saveCalYearsList(CalendarViewPO paramModel) {
			log.info("Contorller Start..... SO : {}", paramModel);
			log.info("paramModel.getMYears ===> " + paramModel.getMYears());
			return calendarViewService.saveCalYearsList(paramModel);
		}
		
		// 법정공휴일 등록
		@GetMapping("registHoliday")
		public ResultModel<String> registHoliday(CalendarViewPO paramModel) {
			log.info("Contorller Start..... PO : {}", paramModel);
			return calendarViewService.registHoliday(paramModel);
		}
		
		// 고정 공휴일 등록
		@GetMapping("registFixedHoliday")
		public ResultModel<String> registFixedHoliday(CalendarViewPO paramModel) {
			log.info("Contorller Start..... PO : {}", paramModel);
			return calendarViewService.registFixedHoliday(paramModel);
		}
		
		// 법정공휴일 저장  saveNewHoliday
}
