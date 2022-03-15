package kr.co.m2m.example.demo.api.plando.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.example.demo.api.plando.model.PlandoPO;
import kr.co.m2m.example.demo.api.plando.model.PlandoSO;
import kr.co.m2m.example.demo.api.plando.model.PlandoVO;
import kr.co.m2m.example.demo.api.plando.model.TestCommonVO;
import kr.co.m2m.example.demo.api.plando.service.PlandoService;
import kr.co.m2m.example.demo.common.model.CommonResponseModel;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.plando.controller
 * @파일명 : PlandoController.java
 * @작성자 : "pje"
 * @생성일자 : 2020. 6. 18. 
 * @설명 : 업무실적 컨트롤러
 */

@Slf4j
@RestController
@RequestMapping("/api")
public class PlandoController {
	@Autowired
	PlandoService plandoService;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * @메소드명 : getLastWeekPlanCopy
	 * @작성자 : "pje"
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 전주 계획 복사
	 * @param so
	 * @return
	 */
	@GetMapping("getLastWeekPlanCopy")
	public ResultListModel<PlandoVO> getLastWeekPlanCopy(PlandoSO so) {
		log.info("getLastWeekPlanCopy Start..... SO: {}", so);
		return plandoService.getLastWeekPlanCopy(so);
	}
	
	@GetMapping("selectCode")
	public ResultListModel<TestCommonVO> selectCode(String groupCode) {
		log.info("selectCode Start..... SO: {}", groupCode);
		return plandoService.selectCode(groupCode);
	}
	
	/**
	 * @메소드명 : savePlando
	 * @작성자 : "pje"
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 업무실적 저장, 수정
	 * @param po
	 * @return
	 * @throws ParseException 
	 */
	@PostMapping("savePlando")
	public CommonResponseModel<String> savePlando(@RequestBody @Valid PlandoPO po, Errors errors) throws ParseException {
		return plandoService.savePlando(po);
	}
	
	/**
	 * @메소드명 : getDepartmentInfo
	 * @작성자 : "pje"
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 부서 정보 조회
	 * @param so
	 * @return
	 */
	@GetMapping("getDepartmentInfo")
	public ResultListModel<PlandoVO> getDepartmentInfo(PlandoSO so) {
		log.info("getDepartmentInfo Satrt..... SO: {}", so);
		return plandoService.getDepartmentInfo(so);
	}
	
	/**
	 * @메소드명 : getPlandoList
	 * @작성자 : "pje"
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 업무 실적 조회
	 * @param so
	 * @return
	 */
	@GetMapping("getPlandoList")
	public ResultListModel<PlandoVO> getPlandoList(PlandoSO so) {
		log.info("getPlando Satrt..... SO: {}", so);
		return plandoService.getPlandoList(so);
	}
	
	/**
	 * @메소드명 : getDepartmentMember
	 * @작성자 : "pje"
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 해당 부서 부서원 정보 조회
	 * @param so
	 * @return
	 */
	@GetMapping("getDepartmentMember")
	public ResultListModel<PlandoVO> getDepartmentMember(PlandoSO so) {
		log.info("getDepartmentMember Satrt..... SO: {}", so);
		return plandoService.getDepartmentMember(so);
	}
	
	/**
	 * @메소드명 : getDepartmentMemberPlando
	 * @작성자 : "pje"
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 해당 부서 전체 부서원 업무 실적 조회
	 * @param so
	 * @return
	 */
	@GetMapping("getDepartmentMemberPlando")
	public ResultListModel<PlandoVO> getDepartmentMemberPlando(PlandoSO so) {
		log.info("getDepartmentMemberPlando Satrt..... SO: {}", so);
		return plandoService.getDepartmentMemberPlando(so);
	}
	
	/**
	 * @메소드명 : getMyDepartmentCode
	 * @작성자 : "pje"
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 내 부서 코드 조회
	 * @param so
	 * @return
	 */
	@GetMapping("getMyDepartmentCode")
	public ResultModel<PlandoVO> getMyDepartmentCode(PlandoSO so) {
		log.info("getMyDepartmentCode Start..... SO: {}", so);
		return plandoService.getMyDepartmentCode(so);
	}
	
	@GetMapping("thisWeekPlando")
	public ResultListModel<PlandoVO> thisWeekPlando(PlandoSO so) {
		log.info("thisWeekPlando Start..... SO: {}", so);
		return plandoService.thisWeekPlando(so);
	}
	
	@GetMapping("nextWeekPlando")
	public ResultListModel<PlandoVO> nextWeekPlando(PlandoSO so) {
		log.info("thisWeekPlando Start..... SO: {}", so);
		return plandoService.nextWeekPlando(so);
	}
}
