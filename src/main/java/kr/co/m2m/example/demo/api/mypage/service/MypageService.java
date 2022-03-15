package kr.co.m2m.example.demo.api.mypage.service;

import kr.co.m2m.example.demo.api.mypage.model.ApprovalPO;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalSO;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalVO;
import kr.co.m2m.example.demo.api.mypage.model.CodeSO;
import kr.co.m2m.example.demo.api.mypage.model.CodeVO;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;

public interface MypageService {

	public ResultListModel<CodeVO> selectCodeList(CodeSO so);

	public ResultListModel<ApprovalVO> selectApprovalRecList(ApprovalSO so);

	public ResultModel<ApprovalVO> selectApprovalRecDetail(ApprovalSO so);

	public ResultModel<String> updateApprovalRec(ApprovalPO po);

	public ResultListModel<ApprovalVO> selectApprovalSenList(ApprovalSO so);

	public ResultModel<ApprovalVO> selectApprovalSenDetail(ApprovalSO so);

	public ResultModel<String> passwordChange(ApprovalPO po);

	public ResultModel<ApprovalVO> selectTemplateDetail(ApprovalSO so);

	public ResultModel<String> updateTemplate(ApprovalPO po);

	public ResultListModel<ApprovalVO> selectMemberList();
}
