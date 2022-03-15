package kr.co.m2m.example.demo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.NestedServletException;

import kr.co.m2m.example.demo.common.model.CommonResponseModel;
import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.demo.exception.BEUnauthorizedException;
import kr.co.m2m.example.framework.auth.BEWorkingContext;
import kr.co.m2m.example.framework.util.MessageUtils;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RestControllerAdvice
//@RestController
//@ResponseBody
public class BERestResponseAdvice implements ResponseBodyAdvice<Object>, ErrorController {

	public static final String DEFAULT_SUCCES_RETURN_CODE = "SUCCESS";
	public static final String ERROR_PATH = "/error";
	private static final String[] RAW_RESPONSE_PATHS = new String[] { "/swagger-ui.html", "/webjars/", "/swagger-resources", "/v2/api-docs" };

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		CommonResponseModel rv;
		HttpStatus httpStatus;
		String returnCode;
		String resMsg;
		String[] resSubMsgs;
		String uri;
		Integer bctxDataCount;

		uri = request.getURI().getPath();
		log.debug("body write.... uri : {}", uri);
		for (String rawResponsePath : RAW_RESPONSE_PATHS) {
			if (uri.startsWith(rawResponsePath)) {
				return body;
			}
		}

		httpStatus = BEWorkingContext.getResponseHttpStatus();
		if (httpStatus != null) {
			response.setStatusCode(httpStatus);
		}

		if (body instanceof CommonResponseModel) {
			rv = (CommonResponseModel) body;

		} else if (body instanceof ResultModel) {
			rv = new CommonResponseModel<Object>();
			rv.setData(((ResultModel) body).getData());
			rv.setMessage(((ResultModel) body).getMessage());

		} else if (body instanceof ResultListModel) {
			rv = new CommonResponseModel<Object>();
			rv.setData(((ResultListModel) body).getResultList());
			rv.setMessage(((ResultListModel) body).getMessage());

		} else {
			rv = new CommonResponseModel<Object>();
			rv.setData(body);
		}
		returnCode = rv.getReturnCode();
		if (returnCode == null) {
			returnCode = BEWorkingContext.getResponseReturnCode();
		}
		rv.setReturnCode(returnCode == null ? DEFAULT_SUCCES_RETURN_CODE : returnCode);
		resMsg = BEWorkingContext.getMessage();
		if (StringUtils.isNotEmpty(resMsg)) {
			rv.setMessage(resMsg);
		}
		return rv;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public CommonResponseModel<List<ObjectError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		this.logger.debug("invalid input data", e);

		return this.handleValidateException(e.getBindingResult());
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public CommonResponseModel<List<ObjectError>> handleBindException(BindException e) {

		this.logger.debug("invalid input data", e);

		return this.handleValidateException(e.getBindingResult());
	}

	private CommonResponseModel<List<ObjectError>> handleValidateException(BindingResult br) {
		CommonResponseModel<List<ObjectError>> rv;
		List<ObjectError> ivrList;
		List<String> ivrMessages;
		Locale userLocale;

		userLocale = LocaleContextHolder.getLocale();

		ivrList = br.getAllErrors();
		ivrMessages = new ArrayList<>();
		ivrList.forEach(ivr -> {
			ivrMessages.add(ivr.getDefaultMessage());
		});

		rv = new CommonResponseModel<>();
		rv.setReturnCode(BEMessageException.INVALID_INPUT_RETURN_CODE);
//		rv.setMessage(this.messageSource.getMessage("server.global.validation-fail", null, userLocale));
		rv.setData(ivrList);

		return rv;
	}

	@ExceptionHandler(BEMessageException.class)
	public CommonResponseModel<Object> handleMessageException(BEMessageException boe, HttpServletResponse res) {
		CommonResponseModel<Object> rv;
		Locale userLocale;

		this.logger.error("bo message exception thrown", boe);

		userLocale = LocaleContextHolder.getLocale();
		rv = new CommonResponseModel<>();
		rv.setReturnCode(boe.getReturnCode());
//		rv.setMessage(this.messageSource.getMessage(boe.getMessage(), boe.getMessageArgs(), userLocale));
		res.setStatus(boe.getHttpStatusCode());

		return rv;
	}

	@ExceptionHandler(BEUnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public CommonResponseModel<Object> handleUnauthorizedException(Throwable t) {
		CommonResponseModel<Object> rv;
		Locale userLocale;
		this.logger.error("unauthorized error Occaused", t);

		userLocale = LocaleContextHolder.getLocale();
		rv = new CommonResponseModel<>();
		rv.setMessage(MessageUtils.getMessage("error.message.unauthorized"));
		rv.setReturnCode(BEMessageException.UNAUTHORIZED_RETURN_CODE);
		rv.setSuccess(false);
		return rv;
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResponseModel<Object> handleUncachedException(Throwable t) {
		CommonResponseModel<Object> rv;
		Locale userLocale;

		this.logger.error("uncached error Occaused", t);

		userLocale = LocaleContextHolder.getLocale();
		rv = new CommonResponseModel<>();
		rv.setReturnCode(BEMessageException.DEFAULT_RETURN_CODE);
//		rv.setMessage(this.messageSource.getMessage("server.global.process-fail", null, userLocale));

		return rv;
	}

	@RequestMapping(ERROR_PATH)
	public CommonResponseModel<Object> renderError(HttpServletRequest req, HttpServletResponse res) throws Throwable {
		CommonResponseModel<Object> rv;
		Throwable thrown;
		Integer errStatus;
		String errStatusText;
		Locale userLocale;

		thrown = (Throwable) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		log.debug("thrown : {}", thrown);

		if (thrown != null) {
			if (thrown instanceof NestedServletException) {
				thrown = ((NestedServletException) thrown).getCause();
			}
			if (thrown instanceof BEUnauthorizedException) {
				return this.handleUnauthorizedException((BEUnauthorizedException) thrown);
			} else if (thrown instanceof BEMessageException) {
				return this.handleMessageException((BEMessageException) thrown, res);
			} else {
				return this.handleUncachedException(thrown);
			}
		} else {
			if (res.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
				return this.handleUnauthorizedException((BEUnauthorizedException) thrown);
			}
		}
		errStatus = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		errStatusText = HttpStatus.valueOf(errStatus).toString();
		userLocale = LocaleContextHolder.getLocale();
		rv = new CommonResponseModel<>();
		rv.setReturnCode(BEMessageException.DEFAULT_RETURN_CODE);
		// rv.setMessage(this.messageSource.getMessage("server.global.raw-message", new
		// Object[] {errStatusText}, userLocale));
		// rv.setMessage(this.messageSource.getMessage("server.global.raw-message", new
		// Object[] {errStatusText}, userLocale));
		return rv;

	}

	public String getErrorPath() {
		log.debug("getErrorPath => {}", ERROR_PATH);
		return ERROR_PATH;
	}

}
