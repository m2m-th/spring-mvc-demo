package kr.co.m2m.example.demo.api.calendarView.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.example.demo.api.calendarView.mapper.CalendarViewMapper;
import kr.co.m2m.example.demo.api.calendarView.model.CalendarViewPO;
import kr.co.m2m.example.demo.api.calendarView.model.CalendarViewSO;
import kr.co.m2m.example.demo.api.calendarView.service.CalendarViewService;
import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.framework.util.MessageUtils;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class CalendarViewServiceImpl implements CalendarViewService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CalendarViewMapper calendarViewMapper;


	  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	  @Override
	  public ResultListModel<CalendarViewSO> selectCalYearsList(CalendarViewPO paramModel) {
	  	ResultListModel<CalendarViewSO> rv = new ResultListModel<>();
	  	String [] sDate = paramModel.getStrDate().split("월"); 
	  	String strDate = sDate[1].trim() + String.format("%02d", Integer.parseInt(sDate[0]));
	  	paramModel.setStrDate(strDate);
	  	System.out.println("srtDate Split :: " + paramModel.getStrDate());
	      rv.setResultList(calendarViewMapper.selectCalYearsList(paramModel));
	      rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
	  	return rv;
	  }
	  
	  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	  @Override
	  public ResultListModel<CalendarViewSO> selectHolidayList(CalendarViewPO paramModel) {
		  ResultListModel<CalendarViewSO> rv = new ResultListModel<>();
		  rv.setResultList(calendarViewMapper.selectHolidayList(paramModel));
		  System.out.println("rv :: " + rv);
		  rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
	  	return rv;
	  }

	  @Transactional(propagation = Propagation.NOT_SUPPORTED)
	  @Override
	  public ResultModel<String> saveCalYearsList(CalendarViewPO paramModel) {
	  	ResultModel<String> rv = new ResultModel<>();
	  	int cnt = Integer.parseInt(paramModel.getChk());
	  	if(cnt==0) {
	  		try {
	  		  this.calendarViewMapper.saveCalYearsList(paramModel);
	  		  rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
	  		  
	  		} catch (Exception e) {
	  		  throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
	  		}
	  		rv.setData(MessageUtils.getMessage("server.common.process.success"));
	  	}else {
	  		try {
  			  this.calendarViewMapper.deleteCalYearsList(paramModel);
  			  rv.setMessage(MessageUtils.getMessage("server.common.message.delete.success"));
	  		  this.calendarViewMapper.saveCalYearsList(paramModel);
	  		  rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
	  		  
	  		} catch (Exception e) {
	  		  throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
	  		}
	  		rv.setData(MessageUtils.getMessage("server.common.process.success"));
	  	}
	  	return rv;
	  }

	  @Transactional(propagation = Propagation.NOT_SUPPORTED)
	  @Override
	  public ResultModel<String> registHoliday(CalendarViewPO paramModel) {
		ResultModel<String> rv = new ResultModel<>();
	  	// String strDate = "";
	  	// paramModel.setStrDate(strDate);
	  	// System.out.println("strDate :: " + strDate);
		System.out.println("strDate: " + paramModel.getStrDate());
	  	try {
		  this.calendarViewMapper.registHoliday(paramModel);
		  rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
		  
		} catch (Exception e) {
		  throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
			rv.setData(MessageUtils.getMessage("server.common.process.success"));
	  	return rv;
	  }

	@Override
	public ResultModel<String> selectCalYearsCnt(CalendarViewPO paramModel) {
		ResultModel<String> rv = new ResultModel<>();
		int chk = 0;
		try {
//			chk = calendarViewMapper.selectCalYearsCnt(paramModel);
			chk = calendarViewMapper.selectCalYearsCnt(paramModel);
			rv.setData(Integer.toString(chk));
			rv.setMessage("server.common.message.select.success");
		}catch (Exception e) {
			throw new BEMessageException("server.common.process.fail", e);
		}
		return rv;
	}

	@Override
	public ResultModel<String> registFixedHoliday(CalendarViewPO paramModel) {
		ResultModel<String> rv = new ResultModel<>();
		int mYears = 0;
	  	System.out.println("mYears :: " + mYears);
	  	try {
		  this.calendarViewMapper.registFixedHoliday(paramModel);
		  rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
		  
		} catch (Exception e) {
		  throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
			rv.setData(MessageUtils.getMessage("server.common.process.success"));
	  	return rv;
	}
}
