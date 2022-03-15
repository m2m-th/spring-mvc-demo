package kr.co.m2m.example.demo.api.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.example.demo.api.example.model.Example01PO;
import kr.co.m2m.example.demo.api.example.model.Example01SO;
import kr.co.m2m.example.demo.api.example.model.Example01VO;
import kr.co.m2m.example.demo.api.example.service.Example01Service;
import kr.co.m2m.example.framework.annotation.ValidUpdate;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/example2")
public class Example02Controller {

	@Autowired
	Example01Service example01Service;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("selectExample01List")
	public ResultListModel<Example01VO> selectExample01List(Example01SO searchModel) {
		log.info("Contorller Start..... SO : {}", searchModel);
		return example01Service.selectExample01List(searchModel);
	}

	@GetMapping("searchExample01")
	public ResultModel<Example01VO> searchExample01(@RequestBody Example01SO searchModel) {
		log.info("Contorller Start..... SO : {}", searchModel);
		return example01Service.searchExample01(searchModel);
	}

	@PostMapping(value = "insertExample01")
	public ResultModel<String> insertExample01(@Valid @RequestBody Example01PO parameterModel) {
		log.info("Contorller1 Start..... PO : {}", parameterModel);
		return example01Service.insertExample01(parameterModel);
	}

//  @PostMapping(value="insertExample01", consumes=org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//  public ResultModel<String> insertExample02(@RequestBody Example01PO parameterModel) {
//    log.info("Contorller2 Start..... PO : {}", parameterModel);
//    return example01Service.insertExample01(parameterModel);
//  }

	@PostMapping("updateExample01")
	public ResultModel<String> updateExample01(@Validated(ValidUpdate.class) @RequestBody Example01PO parameterModel) {
		log.info("Contorller Start..... PO : {}", parameterModel);
		return example01Service.updateExample01(parameterModel);
	}

	@PostMapping("deleteExample01")
	public ResultModel<String> deleteExample01(@RequestBody Example01SO searchModel) {
		log.info("Contorller Start..... SO : {}", searchModel);
		return example01Service.deleteExample01(searchModel);
	}

}
