package kr.co.m2m.example.demo.api.plando.service;

import kr.co.m2m.example.demo.api.plando.model.PlandoPO;
import kr.co.m2m.example.demo.api.plando.model.PlandoSO;
import kr.co.m2m.example.demo.api.plando.model.PlandoVO;
import kr.co.m2m.example.demo.api.plando.model.TestCommonVO;
import kr.co.m2m.example.demo.common.model.CommonResponseModel;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;

public interface PlandoService {
	public ResultListModel<PlandoVO> getLastWeekPlanCopy(PlandoSO so);
	
	public ResultListModel<TestCommonVO> selectCode(String groupCode);
	
	public CommonResponseModel<String> savePlando(PlandoPO po);
	
	public ResultListModel<PlandoVO> getDepartmentInfo(PlandoSO so);
	
	public ResultListModel<PlandoVO> getPlandoList(PlandoSO so);
	
	public ResultListModel<PlandoVO> getDepartmentMember(PlandoSO so);
	
	public ResultListModel<PlandoVO> getDepartmentMemberPlando(PlandoSO so);
	
	public ResultModel<PlandoVO> getMyDepartmentCode(PlandoSO so);
	
	public ResultListModel<PlandoVO> thisWeekPlando(PlandoSO so);
	
	public ResultListModel<PlandoVO> nextWeekPlando(PlandoSO so);
	
	/**
	 * @메소드명 : validationCheck
	 * @작성자 : "pje"
	 * @작성일 : 2020. 6. 24.
	 * @설명 : 등록, 수정 시 validation check
	 * @param rv
	 * @param po
	 * @return
	 */
	public CommonResponseModel<String> validationCheck(CommonResponseModel<String> rv, PlandoPO po);
}
