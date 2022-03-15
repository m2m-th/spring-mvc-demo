package kr.co.m2m.example.demo.api.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.example.demo.api.mypage.model.ApprovalPO;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalSO;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalVO;
import kr.co.m2m.example.demo.api.mypage.model.CodeSO;
import kr.co.m2m.example.demo.api.mypage.model.CodeVO;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.mypage.mapper
 * @파일명 : MypageMapper.java
 * @작성자 : ihKim
 * @생성일자 : 2020. 6. 18.
 * @설명 : 마이페이지 Mapper
 */

@Mapper
public interface MypageMapper {

	public List<CodeVO> selectCodeList(CodeSO so);

	public String selectAppNum(String name);

	public List<ApprovalVO> selectApprovalRecList(ApprovalSO so);

	public ApprovalVO selectApprovalRecDetail(ApprovalSO so);

	public int updateApprovalRec(ApprovalPO po);

	public String selectAppStatus(ApprovalPO po);

	public int updateAppStatus(ApprovalPO po);

	public List<String> selectCalendar(ApprovalPO po);

	public int insertCalendar(ApprovalPO po);

	public List<ApprovalVO> selectApprovalSenList(ApprovalSO so);

	public ApprovalVO selectApprovalSenDetail(ApprovalSO so);

	public String selectMemberEmail(ApprovalPO po);

	public int updateAppStatusEnd(ApprovalPO po);

	public int updatePassword(ApprovalPO po);

	public String selectManageYn(String name);

	public List<ApprovalVO> selectManageList(ApprovalSO so);

	public ApprovalVO selectTemplateDetail(ApprovalSO so);

	public int updateTemplate(ApprovalPO po);

	public List<ApprovalVO> selectMemberList();
}
