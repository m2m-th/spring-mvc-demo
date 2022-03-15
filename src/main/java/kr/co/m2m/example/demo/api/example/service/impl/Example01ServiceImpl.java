package kr.co.m2m.example.demo.api.example.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.example.demo.api.example.mapper.Example01Mapper;
import kr.co.m2m.example.demo.api.example.model.Example01PO;
import kr.co.m2m.example.demo.api.example.model.Example01SO;
import kr.co.m2m.example.demo.api.example.model.Example01VO;
import kr.co.m2m.example.demo.api.example.service.Example01Service;
import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.framework.util.MessageUtils;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class Example01ServiceImpl implements Example01Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Example01Mapper exmaple01Mapper;

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	@Override
	public ResultListModel<Example01VO> selectExample01List(Example01SO so) {
		ResultListModel<Example01VO> rv = new ResultListModel<>();
		rv.setResultList(exmaple01Mapper.selectExample01List(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultModel<Example01VO> searchExample01(Example01SO so) {
		Example01VO vo = exmaple01Mapper.searchExample01(so);
		ResultModel<Example01VO> rv = new ResultModel<>(vo);
		if (vo == null) {
			rv.setSuccess(false);
			rv.setMessage("Test Message");
		}
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> insertExample01(Example01PO po) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			this.exmaple01Mapper.insertExample01(po);
			rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));

		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> updateExample01(Example01PO po) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			this.exmaple01Mapper.updateExample01(po);
			rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));

		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> deleteExample01(Example01SO so) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			this.exmaple01Mapper.deleteExample01(so);
			rv.setMessage(MessageUtils.getMessage("server.common.message.delete.success"));

		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	}

	// 월별 근태 조회 > 캘린더 요소 조회 : bomi
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Example01VO> selectCalendarElement_(Example01SO so) {
		String getStrDate = so.getStrDate();
		System.out.println("겟 날짜 : " + getStrDate);
		String[] tempStrDate = getStrDate.split("월");

		String setStrDate = "";

		if (Integer.parseInt(tempStrDate[0]) < 10) {
			setStrDate = tempStrDate[1] + "0" + tempStrDate[0];
		} else {
			setStrDate = tempStrDate[1] + tempStrDate[0];
		}

		System.out.println("파람 : " + setStrDate);
		so.setStrDate(setStrDate);

		List<Example01VO> list = exmaple01Mapper.selectCalendarElement(so);
		return list;
	}

	// 월별 근태 조회 > 캘린더 요소 조회 : kht
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Example01VO> selectCalendarElement(Example01SO so) {
		List<Example01VO> list = exmaple01Mapper.selectCalendarElement(so);
		return list;
	}

	@Override
	public List<Example01VO> userList(Example01SO searchModel) {
		List<Example01VO> list = exmaple01Mapper.userList(searchModel);
		return list;
	}

	@Override
	public List<Example01VO> lookupVacationList(Example01SO searchModel) {
		List<Example01VO> list = exmaple01Mapper.lookupVacationList(searchModel);
		return list;
	}

	@Override
	public float useVacation(Example01SO searchModel) {
		float list = exmaple01Mapper.useVacation(searchModel);
		return list;
	}

	@Override
	public List<Example01VO> usedVacationTable(Example01SO searchModel) {
		List<Example01VO> list = exmaple01Mapper.usedVacationTable(searchModel);
		return list;
	}
}
