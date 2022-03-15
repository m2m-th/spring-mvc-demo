package kr.co.m2m.example.demo.api.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.m2m.example.demo.api.example.model.Example01PO;
import kr.co.m2m.example.demo.api.example.model.Example01SO;
import kr.co.m2m.example.demo.api.example.model.Example01VO;
import kr.co.m2m.example.demo.api.example.service.Example01Service;
import kr.co.m2m.example.framework.annotation.ValidInsert;
import kr.co.m2m.example.framework.web.model.BasicResponse;
import kr.co.m2m.example.framework.web.model.CommonResponse;
import kr.co.m2m.example.framework.web.model.ErrorResponse;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/example1")
public class Example01Controller {

	@Autowired
	Example01Service example01Service;

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/postJsp")
    public String postJsp(Model model) {
		Example01VO post1 = new Example01VO("001", "lee", "book1");
		Example01VO post2 = new Example01VO("002", "choi", "book2");
		Example01VO post3 = new Example01VO("003", "kim", "book3");
        List<Example01VO> list = new ArrayList<>();
        list.add(post1);
        list.add(post2);
        list.add(post3);
        model.addAttribute("list", list);
        return "test01";
    }
	
	@GetMapping("/listAll")
    public ModelAndView listAll(Example01SO so, Model model) {
		log.debug("Input Parameter (SO) : {}", so);
		ModelAndView mv = new ModelAndView();
		ResultListModel<Example01VO> resultListModel = example01Service.selectExample01List(so);
		log.debug("ResultListModel (resultListModel) : {}", resultListModel);
		mv.addObject("list", resultListModel.getResultList());// jstl로 호출
        mv.setViewName("test02");
        return mv;
    }
	
	@GetMapping("/memberList")
    public ModelAndView memberList(Example01SO so, Model model) {
		log.debug("Input Parameter (SO) : {}", so);
		ModelAndView mv = new ModelAndView();
		ResultListModel<Example01VO> resultListModel = example01Service.selectExample01List(so);
		log.debug("ResultListModel (resultListModel) : {}", resultListModel);
		mv.addObject("memberList", resultListModel);
        mv.setViewName("sample/members");
        return mv;
    }
	
	@RequestMapping("/memberDetail")
    public ModelAndView memberDetail(Example01SO so, Model model) {
		log.debug("Input Parameter (SO) : {}", so);
		ModelAndView mv = new ModelAndView();
		ResultModel<Example01VO> resultModel = example01Service.searchExample01(so);
		log.debug("member : {}", resultModel.getData());
		mv.addObject("member", resultModel.getData());
        mv.setViewName("sample/member");
        return mv;
    }
	
	@GetMapping("/memberRegist")
    public String memberRegist(Model model) {
        return "sample/addMember";
    }
	
	@ResponseBody
	@RequestMapping("/addMember")
    public ResponseEntity<? extends BasicResponse> addMember(@Validated(ValidInsert.class) Example01PO po) {
		log.debug("Input Parameter (PO) : {}", po);
		ResultModel<String> result = example01Service.insertExample01(po);
        return ResponseEntity.ok().body(new CommonResponse<ResultModel<String>>(result));
    }
	
	@ExceptionHandler({BindException.class})
	public ResponseEntity<?> errorValid2(BindException exception) {
	  BindingResult bindingResult = exception.getBindingResult();

	  StringBuilder stringBuilder = new StringBuilder();

	  for (FieldError fieldError : bindingResult.getFieldErrors()) {
	    stringBuilder.append(fieldError.getField()).append(":");
	    stringBuilder.append(fieldError.getDefaultMessage());
	    stringBuilder.append(", ");
	  }
	  log.debug("stringBuilder : {}", stringBuilder);
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(stringBuilder.toString()));
	}
}
