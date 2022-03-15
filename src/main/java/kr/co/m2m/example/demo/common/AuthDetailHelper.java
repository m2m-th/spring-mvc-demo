package kr.co.m2m.example.demo.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import kr.co.m2m.example.framework.auth.BEAuthDetailModel;
import kr.co.m2m.example.framework.auth.BEAuthentication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthDetailHelper {

	public static BEAuthentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			log.debug("## authentication object is null!!");
			return null;
		}
		log.debug("## [AuthDetailHelper] - authentication : {}", authentication);
		if (authentication instanceof BEAuthentication) {
			BEAuthentication details = (BEAuthentication) authentication;
			// log.debug("## BEAuthentication Authenticated user is {}",
			// details.getPrincipal());
			return details;
		} else {
			return null;
		}
	}

	public static BEAuthDetailModel getAuthDetail() {
		BEAuthentication authentication = getAuthentication();
		if (authentication != null) {
			return (BEAuthDetailModel) authentication.getDetails();
		} else {
			return null;
		}
	}

	public static List<String> getAuthorities() {
		List<String> listAuth = new ArrayList<String>();

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			log.debug("## authentication object is null!!");
			return null;
		}

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		Iterator<? extends GrantedAuthority> iter = authorities.iterator();

		while (iter.hasNext()) {
			GrantedAuthority auth = iter.next();
			listAuth.add(auth.getAuthority());
			log.debug("## UserDetailHelper.getAuthorities : Authority is {}", auth.getAuthority());
		}

		return listAuth;
	}

	/**
	 * 인증된 사용자 여부를 체크한다.
	 * 
	 * @return 인증된 사용자 여부(TRUE / FALSE)
	 */
	public static Boolean isAuthenticated() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			log.debug("## authentication object is null!!");
			return Boolean.FALSE;
		}
		log.debug("##### authentication : {}", authentication);
		String userName = (String) authentication.getPrincipal();
		log.debug("## userName is {}", userName);
		if (userName.equals("anonymousUser")) {
			return Boolean.FALSE;
		}
		Object principal = authentication.getPrincipal();

		return (Boolean.valueOf(principal != null));
	}
}
