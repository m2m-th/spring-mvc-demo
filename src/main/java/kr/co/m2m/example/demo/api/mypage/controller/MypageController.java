package kr.co.m2m.example.demo.api.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.example.demo.api.mypage.model.ApprovalPO;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalSO;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalVO;
import kr.co.m2m.example.demo.api.mypage.model.CodeSO;
import kr.co.m2m.example.demo.api.mypage.model.CodeVO;
import kr.co.m2m.example.demo.api.mypage.service.MypageService;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.mypage.controller
 * @파일명 : MypageController.java
 * @작성자 : ihKim
 * @생성일자 : 2020. 6. 18.
 * @설명 : 마이페이지 Controller
 */

@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MypageController {

	@Autowired
	MypageService mypageService;

	/**
	 * @메소드명 : codeList
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 공통코드 조회
	 * @param so
	 * @return
	 */
	@GetMapping("/codeList")
	public ResultListModel<CodeVO> codeList(CodeSO so) {

		return mypageService.selectCodeList(so);
	}

	/**
	 * @메소드명 : approvalRecList
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 결재수신함 목록
	 * @param so
	 * @return
	 */
	@GetMapping("/approvalRecList")
	public ResultListModel<ApprovalVO> approvalRecList(ApprovalSO so) {

		return mypageService.selectApprovalRecList(so);
	}

	/**
	 * @메소드명 : approvalRecDetail
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 결재수신함 상세
	 * @param so
	 * @return
	 */
	@GetMapping("/approvalRecDetail")
	public ResultModel<ApprovalVO> approvalRecDetail(ApprovalSO so) {

		return mypageService.selectApprovalRecDetail(so);
	}

	/**
	 * @메소드명 : approvalRecUpdate
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 결재수신함 수정
	 * @param po
	 * @return
	 */
	@PostMapping("/approvalRecUpdate")
	public ResultModel<String> approvalRecUpdate(@RequestBody ApprovalPO po) {

		return mypageService.updateApprovalRec(po);
	}

	/**
	 * @메소드명 : approvalRepList
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 결재상신함 목록
	 * @param so
	 * @return
	 */
	@GetMapping("/approvalRepList")
	public ResultListModel<ApprovalVO> approvalRepList(ApprovalSO so) {

		return mypageService.selectApprovalSenList(so);
	}

	/**
	 * @메소드명 : approvalRepDetail
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 결재상신함 상세
	 * @param so
	 * @return
	 */
	@GetMapping("/approvalRepDetail")
	public ResultModel<ApprovalVO> approvalRepDetail(ApprovalSO so) {

		return mypageService.selectApprovalSenDetail(so);
	}

	/**
	 * @메소드명 : passwordChange
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 30.
	 * @설명 : 패스워드 변경
	 * @param po
	 * @return
	 */
	@PostMapping("/passwordChange")
	public ResultModel<String> passwordChange(@RequestBody ApprovalPO po) {

		return mypageService.passwordChange(po);
	}

	/**
	 * @메소드명 : TemplateDetail
	 * @작성자 : ihKim
	 * @작성일 : 2020. 7. 6.
	 * @설명 : 템플릿 상세 조회
	 * @param so
	 * @return
	 */
	@GetMapping("/templateDetail")
	public ResultModel<ApprovalVO> templateDetail(ApprovalSO so) {

		return mypageService.selectTemplateDetail(so);
	}

	/**
	 * @메소드명 : templateUpdate
	 * @작성자 : ihKim
	 * @작성일 : 2020. 7. 6.
	 * @설명 : 템플릿 관리
	 * @param po
	 * @return
	 */
	@PostMapping("/templateUpdate")
	public ResultModel<String> templateUpdate(@RequestBody ApprovalPO po) {

		return mypageService.updateTemplate(po);
	}

	/**
	 * @메소드명 : memberList
	 * @작성자 : ihKim
	 * @작성일 : 2020. 7. 13.
	 * @설명 : 회원 목록
	 * @return
	 */
	@GetMapping("/memberList")
	public ResultListModel<ApprovalVO> memberList() {

		return mypageService.selectMemberList();
	}
}
