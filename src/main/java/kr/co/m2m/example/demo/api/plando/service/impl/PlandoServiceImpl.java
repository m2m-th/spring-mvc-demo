package kr.co.m2m.example.demo.api.plando.service.impl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.example.demo.api.plando.mapper.PlandoMapper;
import kr.co.m2m.example.demo.api.plando.model.PlandoPO;
import kr.co.m2m.example.demo.api.plando.model.PlandoSO;
import kr.co.m2m.example.demo.api.plando.model.PlandoVO;
import kr.co.m2m.example.demo.api.plando.model.TestCommonVO;
import kr.co.m2m.example.demo.api.plando.service.PlandoService;
import kr.co.m2m.example.demo.common.AuthDetailHelper;
import kr.co.m2m.example.demo.common.model.CommonResponseModel;
import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.framework.auth.BEAuthDetailModel;
import kr.co.m2m.example.framework.util.MessageUtils;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class PlandoServiceImpl implements PlandoService {

	@Autowired
	private PlandoMapper plandoMapper;
	
	@Override
	public ResultListModel<PlandoVO> getLastWeekPlanCopy(PlandoSO so) {
		ResultListModel<PlandoVO> rv = new ResultListModel<>();
		BEAuthDetailModel beModel = AuthDetailHelper.getAuthDetail();
		so.setId(beModel.getId());
		rv.setResultList(plandoMapper.getLastWeekPlanCopy(so));
		return rv;
	}

	@Override
	public ResultListModel<TestCommonVO> selectCode(String groupCode) {
		ResultListModel<TestCommonVO> rv = new ResultListModel<>();
		rv.setResultList(plandoMapper.selectCode(groupCode));
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public CommonResponseModel<String> savePlando(PlandoPO po) {
		CommonResponseModel<String> rv = new CommonResponseModel<>();
		BEAuthDetailModel beModel = AuthDetailHelper.getAuthDetail();
		try {
			if(!validationCheck(rv, po).isSuccess())
				return rv;
			
			// 수정 시에는 delete 후에 -> insert
			if (po.getMode().equals("edit")) {
				po.setId(beModel.getId());
				plandoMapper.deletePlando(po);
			}
			
			po.getPlandoPOList()
				.stream()
				.filter(vo -> !StringUtils.isBlank(vo.getContent()))
				.forEach(vo -> {
					vo.setId(beModel.getId());
					plandoMapper.savePlando(vo);
				});
			rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
			rv.setData(MessageUtils.getMessage("server.common.process.success"));
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		return rv;
	}

	@Override
	public ResultListModel<PlandoVO> getDepartmentInfo(PlandoSO so) {
		ResultListModel<PlandoVO> rv = new ResultListModel<>();
		BEAuthDetailModel beModel = AuthDetailHelper.getAuthDetail();
		so.setId(beModel.getId());
		rv.setResultList(plandoMapper.getDepartmentInfo(so));
		return rv;
	}

	@Override
	public ResultListModel<PlandoVO> getPlandoList(PlandoSO so) {
		ResultListModel<PlandoVO> rv = new ResultListModel<>();
		BEAuthDetailModel beModel = AuthDetailHelper.getAuthDetail();
		so.setId(beModel.getId());
		rv.setResultList(plandoMapper.getPlandoList(so));
		return rv;
	}

	@Override
	public ResultListModel<PlandoVO> getDepartmentMember(PlandoSO so) {
		ResultListModel<PlandoVO> rv = new ResultListModel<>();
		rv.setResultList(plandoMapper.getDepartmentMember(so));
		return rv;
	}

	@Override
	public ResultListModel<PlandoVO> getDepartmentMemberPlando(PlandoSO so) {
		ResultListModel<PlandoVO> rv = new ResultListModel<>();
		rv.setResultList(plandoMapper.getDepartmentMemberPlando(so));
		return rv;
	}

	@Override
	public ResultModel<PlandoVO> getMyDepartmentCode(PlandoSO so) {
		ResultModel<PlandoVO> rv = new ResultModel<>();
		BEAuthDetailModel beModel = AuthDetailHelper.getAuthDetail();
		so.setId(beModel.getId());
		rv.setData(plandoMapper.getMyDepartmentCode(so));
		return rv;
	}

	@Override
	public ResultListModel<PlandoVO> thisWeekPlando(PlandoSO so) {
		ResultListModel<PlandoVO> rv = new ResultListModel<>();
		rv.setResultList(plandoMapper.thisWeekPlando(so));
		return rv;
	}

	@Override
	public ResultListModel<PlandoVO> nextWeekPlando(PlandoSO so) {
		ResultListModel<PlandoVO> rv = new ResultListModel<>();
		rv.setResultList(plandoMapper.nextWeekPlando(so));
		return rv;
	}
	
	public CommonResponseModel<String> validationCheck(CommonResponseModel<String> rv, PlandoPO po) {
		// 실적 중 한 항목이라도 글자수가 1000자가 넘는 것이 있는지 체크
		boolean contentLength = po.getPlandoPOList().stream()
								.anyMatch(vo -> vo.getContent().length() > 1000);
		// 시작일이 종료일보다 큰 경우가 하나라도 있는지 체크
		boolean validRangeDate = po.getPlandoPOList().stream()
								.anyMatch(vo -> Objects.requireNonNull(vo.getEDate()).compareTo(Objects.requireNonNull(vo.getSDate())) < 0);
		
		if (contentLength) {
			rv.setMessage(MessageUtils.getMessage("error.message.invalid-content"));
			rv.setData(MessageUtils.getMessage("server.common.process.fail"));
			rv.setSuccess(false);
			return rv;
		}
		
		if (validRangeDate) {
			rv.setMessage(MessageUtils.getMessage("error.message.invalid-date"));
			rv.setData(MessageUtils.getMessage("server.common.process.fail"));
			rv.setSuccess(false);
			return rv;
		}

		return rv;
	}
}
