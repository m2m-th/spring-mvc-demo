package kr.co.m2m.example.demo.api.commonCode.service;

import java.util.List;

import javax.validation.Valid;

import kr.co.m2m.example.demo.api.commonCode.model.CommonCodePO;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodeSO;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodeVO;
import kr.co.m2m.example.demo.api.deptMember.model.SCodeVO;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;

public interface CommonCodeService {
	public ResultListModel<CommonCodeVO> selectCommonCodeList(CommonCodeSO so);

	public ResultModel<String> insertCommonCode(List<CommonCodePO> parameterModel);

	public ResultListModel<CommonCodeVO> searchCommonCode(CommonCodeSO so);

	public ResultModel<String> updateCommonCode(List<CommonCodePO> po);

	public ResultModel<String> updateSCode(List<SCodeVO> po);

	public ResultModel<String> insertSCode(@Valid List<SCodeVO> parameterModel);

}
