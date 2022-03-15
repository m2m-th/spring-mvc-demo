package kr.co.m2m.example.demo.api.deptMember.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.example.demo.api.deptMember.mapper.MemberMapper;
import kr.co.m2m.example.demo.api.deptMember.model.MemberPO;
import kr.co.m2m.example.demo.api.deptMember.model.SCodeVO;
import kr.co.m2m.example.demo.api.deptMember.service.MemberService;
import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.framework.util.MessageUtils;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class MemberServiceImpl implements MemberService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> insertMember(List<MemberPO> po) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			for (int i = 0; i < po.size(); i++) {
				po.get(i).setPw(passwordEncoder.encode(po.get(i).getPw()));
				this.memberMapper.insertMember(po.get(i));
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
			}
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> insertAcnt(List<MemberPO> po) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			for (int i = 0; i < po.size(); i++) {
				this.memberMapper.insertAcnt(po.get(i));
				log.info("insertAcnt log :: " + po.get(i));
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
			}
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		return rv;
	}

	@Override
	public ResultListModel<SCodeVO> selectDept(SCodeVO vo) {
		ResultListModel<SCodeVO> rv = new ResultListModel<>();
		rv.setResultList(memberMapper.selectDept(vo));
		return rv;
	}

	@Override
	public ResultListModel<SCodeVO> selectDeptMemList(SCodeVO vo) {
		ResultListModel<SCodeVO> rv = new ResultListModel<>();
		rv.setResultList(memberMapper.selectDeptMemList(vo));
		return rv;
	}

	@Override
	public ResultListModel<SCodeVO> selectJikgubList(SCodeVO vo) {
		ResultListModel<SCodeVO> rv = new ResultListModel<>();
		rv.setResultList(memberMapper.selectJikgubList(vo));
		return rv;
	}

	@Override
	public ResultListModel<SCodeVO> updateMember(List<SCodeVO> vo) {
		ResultListModel<SCodeVO> rv = new ResultListModel<>();
		SCodeVO sVO = new SCodeVO();
		try {
			for (int i = 0; i < vo.size(); i++) {
				sVO.setSdate(vo.get(i).getSdate().replace(".", ""));
				sVO.setName(vo.get(i).getName());
				sVO.setDept(vo.get(i).getDept());
				sVO.setJikgub(vo.get(i).getJikgub());
				sVO.setChiefYn(vo.get(i).getChiefYn());
				sVO.setRetire(vo.get(i).getRetire());
				sVO.setRetireDt(vo.get(i).getRetireDt().replace(".", ""));
				sVO.setId(vo.get(i).getId());
				sVO.setEmail(vo.get(i).getEmail());
				this.memberMapper.updateMember(sVO);
			}
			rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}

		return rv;
	}

	@Override
	public ResultListModel<SCodeVO> checkChiefYn(SCodeVO vo) {
		ResultListModel<SCodeVO> rv = new ResultListModel<SCodeVO>();
		try {
			rv.setResultList(memberMapper.checkChiefYn(vo));
			rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		return rv;
	}

	@Override
	public ResultListModel<MemberPO> checkId(MemberPO checkId) {
		// String userId = "";
		ResultListModel<MemberPO> rv = new ResultListModel<MemberPO>();
		try {
			// userId =
			rv.setResultList(memberMapper.checkId(checkId));
			// rv.setData((MemberPO) memberMapper.checkId(checkId));
			// log.info("userId다 ==> " + userId);
			rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		return rv;
	}
}
