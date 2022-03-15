package kr.co.m2m.example.demo.api.commonCode.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.example.demo.api.commonCode.model.CommonCodePO;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodeSO;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodeVO;
import kr.co.m2m.example.demo.api.commonCode.service.CommonCodeService;
import kr.co.m2m.example.demo.api.deptMember.model.SCodeVO;
import kr.co.m2m.example.framework.annotation.ValidUpdate;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.commonCode.controller
 * @파일명 : CommonCodeController.java
 * @작성자 : hyHwang
 * @생성일자 : 2020. 6. 27.
 * @설명 : 공통코드 관리 화면 컨트롤러
 */

@Slf4j
@RestController
@RequestMapping("/api/commonCode")
public class CommonCodeController {

	@Autowired
	CommonCodeService commonCodeService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * @메소드명 : selectCommonCodeList
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 공통코드 조회
	 * @param searchModel
	 * @return
	 */
	@GetMapping("selectCommonCodeList")
	public ResultListModel<CommonCodeVO> selectCommonCodeList(CommonCodeSO searchModel) {
		log.info("Contorller Start..... SO : {}", searchModel);
		return commonCodeService.selectCommonCodeList(searchModel);
	}

	/**
	 * @메소드명 : searchCommonCode
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 세부 코드 조회
	 * @param searchModel
	 * @return
	 */
	@PostMapping(value = "searchCommonCode")
	public ResultListModel<CommonCodeVO> searchCommonCode(@RequestBody CommonCodeSO searchModel) {
		log.info("Contorller Start..... SO : {}", searchModel);
		return commonCodeService.searchCommonCode(searchModel);
	}

	/**
	 * @메소드명 : insertCommonCode
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 공통 코드 입력
	 * @param parameterModel
	 * @return
	 */
	@PostMapping(value = "insertCommonCode")
	public ResultModel<String> insertCommonCode(@Valid @RequestBody List<CommonCodePO> parameterModel) {
		log.info("인서트 insert Contorller1 Start..... PO : {}", parameterModel);
		return commonCodeService.insertCommonCode(parameterModel);
	}

	/**
	 * @메소드명 : insertScode
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 세부 코드 입력
	 * @param parameterModel
	 * @return
	 */
	@PostMapping(value = "insertSCode")
	public ResultModel<String> insertScode(@Valid @RequestBody List<SCodeVO> parameterModel) {
		log.info("insert Contorller1 Start..... PO : {}", parameterModel);
		return commonCodeService.insertSCode(parameterModel);
	}

	/**
	 * @메소드명 : updateCommonCode
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 공통 코드 수정
	 * @param parameterModel
	 * @return
	 */
	@PostMapping(value = "updateCommonCode")
	public ResultModel<String> updateCommonCode(@Validated(ValidUpdate.class) @RequestBody List<CommonCodePO> parameterModel) {
		log.info("업업 Contorller Start..... PO : {}", parameterModel);
		return commonCodeService.updateCommonCode(parameterModel);
	}

	/**
	 * @메소드명 : updateSCode
	 * @작성자 : hyHwang
	 * @작성일 : 2020. 6. 27.
	 * @설명 : 세부 코드 수정
	 * @param parameterModel
	 * @return
	 */
	@PostMapping(value = "updateSCode")
	public ResultModel<String> updateSCode(@Validated(ValidUpdate.class) @RequestBody List<SCodeVO> parameterModel) {
		log.info("업업 Contorller Start..... PO : {}", parameterModel);
		return commonCodeService.updateSCode(parameterModel);
	}

}
