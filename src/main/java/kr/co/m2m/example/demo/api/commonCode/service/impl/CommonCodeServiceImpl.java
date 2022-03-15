package kr.co.m2m.example.demo.api.commonCode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.example.demo.api.commonCode.mapper.CommonCodeMapper;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodePO;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodeSO;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodeVO;
import kr.co.m2m.example.demo.api.commonCode.service.CommonCodeService;
import kr.co.m2m.example.demo.api.deptMember.model.SCodeVO;
import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.framework.util.MessageUtils;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class CommonCodeServiceImpl implements CommonCodeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonCodeMapper commonCodeMapper;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<CommonCodeVO> selectCommonCodeList(CommonCodeSO so) {
		ResultListModel<CommonCodeVO> rv = new ResultListModel<>();
		rv.setResultList(commonCodeMapper.selectCommonCodeList(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<CommonCodeVO> searchCommonCode(CommonCodeSO so) {
		ResultListModel<CommonCodeVO> rv = new ResultListModel<>();
		rv.setResultList(commonCodeMapper.searchCommonCode(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	// 그룹코드 추가
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> insertCommonCode(List<CommonCodePO> po) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			for (int i = 0; i < po.size(); i++) {
				log.info("this is po.get(i) 사이즈 ==>" + po.size());
				log.info("this is po.get(i) 피오==>" + po);
				this.commonCodeMapper.insertCommonCode(po.get(i));
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
			}
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		return rv;
	}

	// 세부 코드 추가
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> insertSCode(List<SCodeVO> po) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			for (int i = 0; i < po.size(); i++) {
				log.info("this is po.get(i) ==>" + po);
				this.commonCodeMapper.insertSCode(po.get(i));
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
			}
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> updateCommonCode(List<CommonCodePO> po) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			for (int i = 0; i < po.size(); i++) {
				this.commonCodeMapper.updateCommonCode(po.get(i));
				rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));
			}
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> updateSCode(List<SCodeVO> po) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			for (int i = 0; i < po.size(); i++) {
				this.commonCodeMapper.updateSCode(po.get(i));
				rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));
			}
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	}

}
