package kr.co.m2m.example.demo.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.m2m.example.demo.common.AuthDetailHelper;
import kr.co.m2m.example.framework.auth.BEAuthentication;
import kr.co.m2m.example.framework.auth.BEAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@Autowired
	private BEAuthenticationProvider beAuthProvider;

	@RequestMapping("/main")
	public String main() {
		BEAuthentication authentication = AuthDetailHelper.getAuthentication();
		log.debug("# [MainController] - authentication : {}", authentication);
		if (authentication == null || AuthDetailHelper.isAuthenticated()) {
			return "redirect:/login";
		}
		return "main";
	}

	@RequestMapping("/favicon.ico")
	@ResponseBody
	void returnNoFavicon() {
	}

	@RequestMapping("/member")
	public String member() {
		return "memberPage";
	}

	@RequestMapping("/accessDenied_page")
	public String accessDenied() {
		return "accessDenied_page";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "adminPage";
	}

	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}

	// 로그인 페이지
	@GetMapping("/login")
	public String dispLogin() {
		return "login";
	}

	@RequestMapping("/")
	public String startPoint() {
		return "login";
	}

}
