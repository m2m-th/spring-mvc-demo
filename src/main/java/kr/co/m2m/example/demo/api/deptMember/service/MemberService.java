package kr.co.m2m.example.demo.api.deptMember.service;

import java.util.List;

import javax.validation.Valid;

import kr.co.m2m.example.demo.api.deptMember.model.MemberPO;
import kr.co.m2m.example.demo.api.deptMember.model.SCodeVO;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;

public interface MemberService {

	public ResultModel<String> insertMember(@Valid List<MemberPO> parameterModel);

	public ResultModel<String> insertAcnt(@Valid List<MemberPO> parameterModel);

	public ResultListModel<SCodeVO> selectDept(SCodeVO vo);

	public ResultListModel<SCodeVO> selectDeptMemList(SCodeVO vo);

	public ResultListModel<SCodeVO> updateMember(List<SCodeVO> vo);

	public ResultListModel<SCodeVO> selectJikgubList(SCodeVO vo);

	public ResultListModel<SCodeVO> checkChiefYn(SCodeVO vo);

	public ResultListModel<MemberPO> checkId(MemberPO checkId);

}
