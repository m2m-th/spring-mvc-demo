package kr.co.m2m.example.demo.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.framework.auth.BEAuthDetailModel;
import kr.co.m2m.example.framework.auth.BEAuthTokenResolver;
import kr.co.m2m.example.framework.auth.BEWorkingContext;
import kr.co.m2m.example.framework.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BECommonInterceptor extends HandlerInterceptorAdapter {

	private static final String[] ALLOW_PATHS = new String[] { "/", "/login", "/main", "/error", "/favicon.ico" };
	private static final String[] SWAGGER_UI_PATHS = new String[] { "/swagger-ui.html", "/webjars/", "/swagger-resources" };
	private static final String BEARER_TOKEN_PREFIX = "Bearer ";

	@Autowired
	private BEAuthTokenResolver authResolver;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle - request : {}", request);
		this.checkPermission(request);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("afterCompletion - response : {}", response);
	}

	private void checkPermission(HttpServletRequest request) throws IOException {
		log.info("checkPermission - request : {}", request);

		String uri;
		BEAuthDetailModel authDetail;
		boolean isAccessible;

		uri = request.getRequestURI();
		log.info("[ Permisson ] - check uri:: '{}'", uri);
		for (String allowPath : ALLOW_PATHS) {
			if (uri.startsWith(allowPath)) {
				log.debug("[ Permisson ] - ignore : {}", uri);
				return;
			}
		}
		String headerAuth = request.getHeader("Authorization");
		log.info("#### AUTH header is (for API권한체크) '{}'", headerAuth);
		authDetail = null;
		isAccessible = false;
		if (headerAuth != null && headerAuth.startsWith(BEARER_TOKEN_PREFIX)) {
			String authToken = headerAuth.substring(BEARER_TOKEN_PREFIX.length());
			authDetail = this.authResolver.getAuthDetail(authToken, false);
			log.info("######### authDetail :: '{}'", authDetail);
			if (authDetail != null) {
				log.info("######### check Permission for :: '{}({})'", authDetail.getId(), authDetail.getName());
				// :TODO

			}
		}
		log.info("#########_ isAccessable:: '{}'", isAccessible);
		// ------------------------------------------------------------------
		if (BEWorkingContext.getAuthToken() == null) {
			System.out.println("require auth uri " + uri);
			throw new BEMessageException(401, "REQUIRED_LOGIN", MessageUtils.getMessage("server.message.require-login"));
		}
	}

}
