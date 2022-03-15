package kr.co.m2m.example.demo.api.deptMember.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.example.demo.api.deptMember.model.MemberPO;
import kr.co.m2m.example.demo.api.deptMember.model.SCodeVO;
import kr.co.m2m.example.demo.api.deptMember.service.MemberService;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @패키지명 : kr.co.m2mglobal.vuetest.api.deptMember.controller
 * @파일명 : MemberController.java
 * @작성자 : hyHwang
 * @생성일자 : 2020. 6. 27.
 * @설명 :
 */

@Slf4j
@RestController
@RequestMapping("/api/deptMember")
public class MemberController {
	@Autowired
	MemberService memberService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * @메소드명 : insertMember
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 부서원 등록
	 * @param parameterModel
	 * @return
	 */
	@PostMapping(value = "insertMember")
	public ResultModel<String> insertMember(@Valid @RequestBody List<MemberPO> parameterModel) {
		log.info("Contorller1 Start..... PO : {}", parameterModel);
		return memberService.insertMember(parameterModel);
	}

	/**
	 * @메소드명 : insertAcnt
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 30.
	 * @설명 : 연도별 휴가 일수 등록
	 * @param vo
	 * @return
	 */
	@PostMapping(value = "insertAcnt")
	public ResultModel<String> insertAcnt(@RequestBody List<MemberPO> vo) {
		log.info(" insertAcnt Contorller Start..... SO : {}", vo);
		return memberService.insertAcnt(vo);
	}

	/**
	 * @메소드명 : selectDept
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 부서 조회
	 * @param vo
	 * @return
	 */
	@GetMapping("selectDept")
	public ResultListModel<SCodeVO> selectDept(SCodeVO vo) {
		log.info("Contorller Start..... SO : {}", vo);
		return memberService.selectDept(vo);
	}

	/**
	 * @메소드명 : selectDeptMemList
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 부서원 조회
	 * @param vo
	 * @return
	 */
	@PostMapping(value = "selectDeptMemList")
	public ResultListModel<SCodeVO> selectDeptMemList(@RequestBody SCodeVO vo) {
		log.info("Contorller Start..... SO : {}", vo);
		return memberService.selectDeptMemList(vo);
	}

	/**
	 * @메소드명 : selectJikgubList
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 직급 조회
	 * @param vo
	 * @return
	 */
	@GetMapping("selectJikgubList")
	public ResultListModel<SCodeVO> selectJikgubList(SCodeVO vo) {
		log.info("Contorller Start..... SO : {}", vo);
		return memberService.selectJikgubList(vo);
	}

	/**
	 * @메소드명 : checkChiefYn
	 * @작성자 : jdh
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 부서장 조회
	 * @param vo
	 * @return
	 */
	@PostMapping(value = "checkChiefYn")
	public ResultListModel<SCodeVO> checkChiefYn(@RequestBody SCodeVO vo) {
		log.info("Contorller Start..... SO : {}", vo);
		return memberService.checkChiefYn(vo);
	}

	/**
	 * @메소드명 : updateMember
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 부서원 수정
	 * @param vo
	 * @return
	 */
	@PostMapping(value = "updateMember")
	public ResultListModel<SCodeVO> updateMember(@RequestBody List<SCodeVO> vo) {
		log.info("Contorller Start..... SO : {}", vo);
		// log.info(vo.getScodeNm());
		return memberService.updateMember(vo);
	}

	/**
	 * @메소드명 : checkId
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 아이디 중복 조회
	 * @param checkId
	 * @return
	 */
	@GetMapping("checkId")
	public ResultListModel<MemberPO> checkId(MemberPO checkId) {
		log.info("Contorller Start..... userId : {}", checkId);
		return memberService.checkId(checkId);
	}

}
