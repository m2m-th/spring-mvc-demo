'use strict';
var console = window.console || {log:function(){}};
function debug() {
	null != arguments && console.log(Array.prototype.slice.call(arguments, 0).join(':'))
}

var Mall = {};

// IE8인 경우 jQuery ajax 캐시를 비활성화
if(document.documentMode == 8) {
    jQuery.ajaxSetup({cache: false});
}

jQuery(document).ready(function() {
    var d = $(".bell_date_sc");
    d.mask("9999-99-99", {
        placeholder : ''
    });
    d.on('blur', function() {
        if (!Mall.validation.isEmpty($(this).val()) && !Mall.validation.date($(this).val())) {
            $(this).val('');
            $(this).focus();
            alert('올바르지 않은 날짜입니다.');
        }
    });

    $(".datepickerBtn").on('click', function() {
        $(this).prev().focus();
    });

    $('#a_id_logout').on('click', function(e) {
        Mall.EventUtil.stopAnchorAction(e);
//        checkLoginState();
//        Kakao.init('b991ded74cfc433aac7830f4eb491fa9');
//        KaKao.Auth.logout(function(result){
//        	if(result == true){
//        		console.log("성공")
//        	};
//        })
//
//        function checkLoginState(){
//            FB.getLoginStatus(function(response){
//            	FB.logout();
//            });
//        }
//
        Mall.FormUtil.submit('/login/logout.nh', {});
    });

    Mall.design();

    console.log('init');
    // 로컬에서 개발시 개발에 이미지가 없으면 테스트서버의 이미지를 보여주기위한 임시 처리
//    jQuery('img').on('error', function(e) {
//        var $this = jQuery(this),
//            src = $this.attr('src'),
//            hostname = document.location.hostname;
//
//        if(hostname.indexOf('localhost:8090') > 0) {
//            hostname = hostname.replaceAll('localhost:8090', "http://dv.www.nonghyupmall.com")
//            $this.attr('src', '//'+hostname + $this.attr('src'));
//            console.log('이미지처리 :' + src);
//        }
//        /*
//        if (src.indexOf("/") == 0){
//        	$this.attr('src', 'http://dv.www.nonghyupmall.com' + src);
//        	console.log('이미지처리 :' + src);
//        }
//        */
//        // $this.off('error');
//    });
});
/**
 * 디자인 관련 초기화 스크립트
 */
Mall.design = function() {
    // 달력
	Mall.init.datepicker();
    // 달력 기간 버튼 클릭 이벤트 처리
	Mall.init.datePeriodButton();
    // 라디오박스
	Mall.init.radio();
    // 체크박스
	// Mall.init.checkbox();
	// Mall.init.checkboxAllBtn();
    // 셀렉트
	// Mall.init.select();
    // 탭
	// Mall.init.tab();
};

/**
 * 로딩 화면 처리 클래스
 */
Mall.waiting = {
    /**
     * <pre>
     * 함수명 : start
     * 설  명 : 화면 전체에 로딩 중 처리
     * 사용법 : waiting.start()
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     */
    start : function() {
        $.blockUI({
//            message : '<img src="/b2c/img/common/ajax-loader-white.gif" alt="Loading..." />'
            message :  	'<div class="loading">									'
		            +	'	<div class="loading_box">							'
		            +	'   	<div class="truck"></div>                       '
		            +	'       <div class="tire"></div>                        '
		            +	'       <span class="txt">loading</span>                '
		            +	'   </div>                                              '
		            +	'	<div class="overlay"></div>                         '
		            +	'</div>                                                 '

        });
    },

    /**
     * <pre>
     * 함수명 : stop
     * 설  명 : 화면 전체의 로딩 중 처리 해제
     * 사용법 : waiting.stop()
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     */
    stop : function() {
        $.unblockUI();
    }
};

/**
 * 포메터 클래스
 */
Mall.formatter = {
    /**
     * <pre>
     * 함수명 : tel
     * 설  명 : 전화번호 포멧 변경(0212345678 -&gt; 02-1234-5678)
     * 사용법 : formatter.tel('0212345678')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param tel
     *            전화번호
     * @return {String} 포메팅된 전화번호 또는 ""
     */
    tel : function(tel) {
        if (!Mall.validation.isNull(tel)) {
            return tel.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");
        } else {
            return "";
        }
    },

    /**
     * <pre>
     * 함수명 : fax
     * 설  명 : 팩스번호 포멧 변경(0212345678 -&gt; 02-1234-5678)
     * 사용법 : formatter.fax('0212345678')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param fax
     *            팩스번호
     * @return {String} 포메팅된 팩스번호 또는 ""
     */
    fax : function(fax) {
        if (!Mall.validation.isNull(fax)) {
            return fax.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");
        } else {
            return "";
        }
    },

    /**
     * <pre>
     * 함수명 : mobile
     * 설  명 : 휴대전화번호 포멧 변경(01012345678 -&gt; 010-1234-5678)
     * 사용법 : formatter.mobile('01012345678')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param no
     *            휴대전화번호
     * @return {String} 포메팅된 휴대전화번호 또는 ""
     */
    mobile : function(no) {
        if (!Mall.validation.isNull(no)) {
            return no.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");
        } else {
            return "";
        }
    },

    /**
     * <pre>
     * 함수명 : post
     * 설  명 : 구우편번호 포멧 변경(123456 -&gt; 123-456)
     * 사용법 : formatter.post('123456')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param no
     *            구우편번호(6자리)
     * @return {String} 포매팅된 구우편번호 또는 ""
     */
    post : function(no) {
        if (!Mall.validation.isNull(no)) {
            return no.replace(/([0-9]{3})([0-9]{3})/, "$1-$2");
        } else {
            return "";
        }
    },

    /**
     * <pre>
     * 함수명 : bizNo
     * 설  명 : 사업자번호 포멧 변경(1234567890 -&gt; 123-45-67890)
     * 사용법 : formatter.bizNo('1234567890')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param no
     *            사업자번호
     * @return {String} 포매팅된 사업자번호 또는 ""
     */
    bizNo : function(no) {
        if (!Mall.validation.isNull(no)) {
            return no.replace(/([0-9]{3})([0-9]{2})([0-9]{5})/, "$1-$2-$3");
        } else {
            return "";
        }
    },

    /**
     * <pre>
     * 함수명 : cprNo
     * 설  명 : 주민번호 포멧 변경(1234561234567 -&gt; 123456-1234567)
     * 사용법 : formatter.cprNo('1234561234567')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param no
     *            주민번호
     * @return {String} 포매팅된 주민번호 또는 ""
     */
    cprNo : function(no) {
        if (!Mall.validation.isNull(no)) {
            return no.replace(/([0-9]{6})([0-9]{7})/, "$1-$2");
        } else {
            return "";
        }
    }
};

/**
 * 검증 헬퍼 클래스
 */
Mall.validation = {

    /**
     * <pre>
     * 함수명 : date
     * 설  명 : 입력받은 문자열을 Date 형식으로 변환할 수 있는지(정상적인 날자 데이터인지) 여부 반환
     * 사용법 : validation.date('20170428')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param str
     *            yyyymmdd 또는 yymmdd 형식의 문자열
     * @return {Boolean} 정상적인 날짜 데이터 여부
     */
    date : function(str) {
        // Checks for the following valid date formats:
        // Also separates date into month, day, and year variables
        var datePat = /^(\d{2}|\d{4})(\/|-)(\d{1,2})\2(\d{1,2})$/, year, month, day;

        var matchArray = str.match(datePat); // is the format ok?
        if (matchArray == null) {
            return false;
        }
        year = matchArray[1];
        month = matchArray[3]; // parse date into variables
        day = matchArray[4];

        if (month < 1 || month > 12) { // check month range
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
            return false
        }
        if (month == 2) { // check for february 29th
            var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

            if (day > 29 || (day == 29 && !isleap)) {
                return false;
            }
        }
        return true; // date is valid
    },

    /**
     * <pre>
     * 함수명 : isUndefined
     * 설  명 : 입력받은 인자가 undefined 인지 여부 반환
     * 사용법 : validation.isUndefined(obj)
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * &lt;/pr&gt;
     * @param obj 문자열 또는 객체
     * @return {Boolean} undefined 여부
     *
     */
    isUndefined : function(obj) {
        return obj === undefined;
    },

    /**
     * <pre>
     * 함수명 : isNull
     * 설  명 : 입력받은 인자가 null 인지 여부 반환
     * 사용법 : validation.isNull(obj)
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param obj
     *            문자열 또는 객체
     * @return {Boolean} null 여부
     */
    isNull : function(obj) {
        return obj === null;
    },

    /**
     * <pre>
     * 함수명 : isEmpty
     * 설  명 : 입력받은 객체가 비었는지 여부 반환
     * 사용법 : validation.isEmpty(obj)
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param str
     *            문자열 또는 객체
     * @return {Boolean} 빈 값 여부
     */
    isEmpty : function(obj) {
        return Mall.validation.isUndefined(obj) || Mall.validation.isNull(obj) || obj === '' || obj === 'null'
                || obj.length === 0;
    }
};

/**
 * jQuery Validation Engine 을 이용한 헬퍼 클래스
 */
Mall.validate = {
    /**
     * <pre>
     * 함수명 : set
     * 설  명 : 입력받은 인자에 해당하는 폼에 검증 엔진을 세팅
     * 사용법 : validate.set('form_id_save')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param formId
     *            폼 ID
     */
    set : function(formId) {
        $("#" + formId).validationEngine('attach', {promptPosition : "centerRight", scroll: false});
    },

    /**
     * <pre>
     * 함수명 : hide
     * 설  명 : 입력받은 인자에 해당하는 폼에 출력된 에러 메시지 툴팁을 숨김
     * 사용법 : validate.hide('form_id_save')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param formId
     *            폼 ID
     */
    hide : function(formId) {
        $("#" + formId).validationEngine("hide");
    },

    /**
     * <pre>
     * 함수명 : isValid
     * 설  명 : 입력받은 인자에 해당하는 폼의 검증 결과를 반환, 실패시 에러 메시지 툴팁이 출력됨
     * 사용법 : validate.isValid('form_id_save')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param formId
     *            폼 ID
     * @return {Boolean} 폼 검증 결과
     */
    isValid : function(formId) {
        return $("#" + formId).validationEngine("validate");
    },

    /**
     * <pre>
     * 함수명 : viewExceptionMessage
     * 설  명 : 인자로 받은 서버의 검증 결과에 오류 메시지가 있으면 이를 폼에 출력
     * 사용법 : validate.viewExceptionMessage(result, 'form_id_save')
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param result
     *            Ajax 요청후 받은 JSON형태의 결과 데이터
     * @param formId
     *            폼 ID
     */
    viewExceptionMessage : function(result, formId) {
    	console.log(result);
    	if (result && result.exMsg) {
            Mall.LayerUtil.alert(result.exMsg)
            // return false;
        }
        var error_template = '<div class="formError" style="opacity: 0.87; position: absolute; top: 1px; left: 11px; margin-top: 0;"><div class="formErrorContent">* {{msg}}<br></div></div>', template, errors, $form, error, $target;

        if (result && result.exError && result.exError.length > 0) {
            errors = result.exError;
        } else {
            return;
        }

        $form = jQuery('#' + formId);
        $form.validationEngine();

        jQuery.each(errors, function(idx, error) {
            template = new Mall.Template(error_template, {
                msg : error.message
            });
            $target = $form.find('input[name="' + error.name + '"], select[name="' + error.name + '"], textarea[name="'
                    + error.name + '"]');

            if ($target.length === 0) {
                Mall.LayerUtil.alert('모델의 ' + error.name + '의 검증식이 잘못되었거나 해당 데이터가 전송시 누락되었습니다.')
                return false;
            }

            switch ($target[0].tagName) {
                case 'INPUT' :
                    switch ($target.attr('type')) {
                        case 'radio' :
                        case 'checkbox' :
                            $target = $target.parents('label:first');
                            break;
                        default :
                    }
                    break;
                default :
            }
            $target.validationEngine('showPrompt', error.message, 'error');
        });
    }
};

/**
 * 공통 클래스
 */
Mall.common = {
    /**
     * <pre>
     * 함수명 : numeric
     * 설  명 : numeric 클래스를 가진 엘리먼트에 숫자 마스크 세팅
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     */
    numeric : function() {
        $(".numeric").css("ime-mode", "disabled") // 한글입력 X
        .mask("#0", {
            reverse : true,
            maxlength : false
        });
    },

    /**
     * <pre>
     * 함수명 : decimal
     * 설  명 : decimal 클래스를 가진 엘리먼트에 정부 마스크 세팅
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     */
    decimal : function() {
        $(".decimal").css("ime-mode", "disabled") // 한글입력 X
        .autoNumeric("init", {
            aSep : ',',
            aDec : '.',
            vMax : '9999999999999.9',
            vMin : '-9999999999999.9'
        });
    },
    /**
     * <pre>
     * 함수명 : phoneNumber
     * 설  명 : phoneNumber 클래스를 가진 엘리먼트에 전화번호 마스크 세팅
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     */
    phoneNumber : function() {
        var phoneMask = function(val) {
            var mask = "000-000-00000";
            var value = val.replace(/\D/g, '');

            if (value.length > 2) {
                if (value.substring(0, 2) == "02") {
                    mask = "00-000-00000";
                    if (value.length == 10) {
                        mask = "00-0000-0000"
                    }
                } else {
                    if (value.length == 11) {
                        mask = "000-0000-0000"
                    }
                }
            }

            return mask;
        };

        var option = {
            onKeyPress : function(val, e, field, options) {
                field.mask(phoneMask.apply({}, arguments), options);
            },
            onComplete : function(val, e, field, options) {
                var mask = "000-000-00000";
                var value = val.replace(/\D/g, '');
                if (value.substring(0, 2) == "02") {
                    mask = "00-000-00000";
                    if (value.length == 10) {
                        mask = "00-0000-0000"
                    }
                } else {
                    if (value.length == 11) {
                        mask = "000-0000-0000"
                    }
                }
                field.mask(mask, options);
            }
        };

        $('.phoneNumber').mask(phoneMask, option);
    },
    /**
     * <pre>
     * 함수명 : comma
     * 설  명 : comma 클래스를 가진 엘리먼트를 숫자(콤마) 형식의 마스크를 세팅
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     */
    comma : function() {
        $('.comma').mask("#,##0", {
            reverse : true,
            maxlength : false
        });
    },

    /**
     * <pre>
     * 함수명 : removeComma
     * 설  명 : 숫자에 표기된 콤마를 제거
     * 사용법 :
     * 작성일 : 2017. 6. 22.
     * 작성자 : jhlee
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 6. 22. jhlee - 최초 생성
     * </pre>
     */
    removeComma : function(str) {
    	var n = parseInt(str.replace(/,/g, ""));
		return n;
    },

    /**
     * <pre>
     * 함수명 : onlyNum
     * 설  명 : onlyNum 클래스를 가진 엘리먼트를 숫자만 입력되도록 셋팅
     * 사용법 :
     * 작성일 : 2017. 6. 22.
     * 작성자 : jhlee
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 6. 22. jhlee - 최초 생성
     * </pre>
     */
    onlyNum : function() {
    	$(".onlyNum").keyup(function() {
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
		});
    	$(".onlyNum").blur(function() {
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
		});
    },
    /**
     * <pre>
     * 함수명 : onlyComaNum
     * 설  명 : onlyComaNum 클래스를 가진 엘리먼트를 숫자만 입력되도록 셋팅(콤마추가)
     * 사용법 :
     * 작성일 : 2017. 6. 22.
     * 작성자 : jhlee
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 9. 14. jhlee - 최초 생성
     * </pre>
     */
    onlyComaNum : function() {
    	$(".onlyNum").keyup(function() {
			$(this).val($(this).val().replace(/[^0-9,]/g, ""));
		});
    	$(".onlyNum").blur(function() {
			$(this).val($(this).val().replace(/[^0-9,]/g, ""));
		});
    },
    /**
     * <pre>
     * 함수명 : numComma
     * 설  명 : 숫자에 콤마를 표기
     * 사용법 :
     * 작성일 : 2017. 7. 27.
     * 작성자 : kclee
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 7. 27. kclee - 최초 생성
     * </pre>
     */
    numComma : function(n) {
		 var reg = /(^[+-]?\d+)(\d{3})/;
		 n = String(n);
		 while(n.match(reg)){
		     n=n.replace(reg,'$1'+','+'$2');
		 }
		 return n;
    },

    image : function(n) {
        jQuery('img').on('error', function(e) {
            var $this = jQuery(this),
                src = $this.attr('src'),
                hostname = document.location.hostname;

            if(hostname.indexOf('localhost:8090') > 0) {
                hostname = hostname.replaceAll('localhost:8090', "http://dv.www.nonghyupmall.com")
                $this.attr('src', '//'+hostname + $this.attr('src'));
                // console.log('이미지처리 1:' + src);
            } else if (src.indexOf("/") == 0){
				// $this.attr('src', 'http://dv.www.nonghyupmall.com' + src);
            	// console.log('이미지처리 2:' + src);
            }
            // console.log('이미지처리 :' + src);
            // $this.off('error');
        });
   }
};

/**
 * 이벤트 헬퍼 클래스
 */
Mall.EventUtil = {
    /**
     * <pre>
     * 함수명 : stopAnchorAction
     * 설  명 : 기본 이벤트 처리를 막고 이벤트의 처리의 전파를 중단시킴
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param e
     *            이벤트 객체
     */
    stopAnchorAction : function(e) {
        e.stopPropagation();
        e.preventDefault();
    }
};

/**
 * Ajax 요청 헬퍼 클래스
 */
Mall.AjaxUtil = {
    /**
     * 결과 객체에 메시지가 있으면 출력하고 콜백 함수를 호출한다.
     *
     * @param result
     * @param callback
     */
    viewMessage : function(result, callback) {
    	if (result && result.message) {
            Mall.LayerUtil.alert(result.message).done(function() {
                callback(result);
            });
        } else {
            callback(result);
        }
    },
    /**
     *
     * <pre>
     * 함수명 : getJSON
     * 설  명 : 서버에 post 방식으로 요청하여 JSON 데이터를 결과로 반환 받는다.
     *          메시지가 있을 경우 출력한다.
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param url
     *            요청할 서버의 URL
     * @param param
     *            요청시 전송할 파라미터
     * @param callback
     *            결과를 받아 실행할 콜백함수
     */
    getJSON : function(url, param, callback) {
        Mall.waiting.start();
        $.ajax({
            type : 'post',
            url : url,
            data : param,
            dataType : 'json'
        }).done(function(result) {
            if (result) {
                console.log('ajaxUtil.getJSON :', result);
                Mall.AjaxUtil.viewMessage(result, callback);
            } else {
                callback();
            }
            Mall.waiting.stop();
        }).fail(function(result) {
            Mall.waiting.stop();
        	// CSRF대응으로 403 반환의 경우
            if (result && result.status == 403) {
            	// 별도 처리 없을 경우, ajaxError으로 대응
            } else {
            	Mall.AjaxUtil.viewMessage(result.responseJSON, callback);
            }
        });
    },
    getJSONwoMsg : function(url, param, callback) {
        Mall.waiting.start();
        $.ajax({
            type : 'post',
            url : url,
            data : param,
            dataType : 'json'
        }).done(function(result) {
            if (result) {
                callback(result);
            } else {
                callback();
            }
            Mall.waiting.stop();
        }).fail(function(result) {
            Mall.waiting.stop();
        	// CSRF대응으로 403 반환의 경우
            if (result && result.status == 403) {
            	// 별도 처리 없을 경우, ajaxError으로 대응
            } else {
            	Mall.AjaxUtil.viewMessage(result.responseJSON, callback);
            }
        });
    },
    getCustomJSON : function(url, param, callback) {
        Mall.waiting.start();
        $.ajax({
            type : 'post',
            url : url,
            data : param,
            async : 'false',
            contentType : "text/plain;charset=UTF-8",
            dataType : 'json'
        }).done(function(result) {
            if (result) {
                console.log('ajaxUtil.getCustomeJSON :', result);
                // Mall.AjaxUtil.viewMessage(result, callback);
                callback(result);
            } else {
                callback();
            }
            Mall.waiting.stop();
        }).fail(function(result) {
            Mall.waiting.stop();
        	// CSRF대응으로 403 반환의 경우
            if (result && result.status == 403) {
            	// 별도 처리 없을 경우, ajaxError으로 대응
            } else {
            	Mall.AjaxUtil.viewMessage(result.responseJSON, callback);
            }
        });
    },
    getOrderJSON : function(url, param, callback) {
        Mall.waiting.start();
        $.ajax({
            type : 'post',
            url : url,
            data : param,
            async : 'false',
            dataType : 'json'
        }).done(function(result) {
            if (result) {
                console.log('ajaxUtil.getJSON :', result);
                Mall.AjaxUtil.viewMessage(result, callback);
            } else {
                callback();
            }
            Mall.waiting.stop();
        }).fail(function(result) {
            Mall.waiting.stop();
        	// CSRF대응으로 403 반환의 경우
            if (result && result.status == 403) {
            	// 별도 처리 없을 경우, ajaxError으로 대응
            } else {
            	Mall.AjaxUtil.viewMessage(result.responseJSON, callback);
            }
        });
    },
    post : function(url, param, callback) {

    },
    load : function(url, callback) {
        Mall.waiting.start();
        $.ajax({
            type : 'get',
            url : url,
            dataType : 'html'
        }).done(function(result) {

            if (result) {
                console.log('ajaxUtil.load :', result);
                callback(result);
            } else {
                callback();
            }
            Mall.waiting.stop();
        }).fail(function(result) {
            Mall.waiting.stop();
            Mall.AjaxUtil.viewMessage(result.responseJSON, callback);
        });
    },
    loadByPost : function(url, param, callback) {
        $.ajax({
            type : 'post',
            url : url,
            data : param,
            dataType : 'html'
        }).done(function(result) {

            if (result) {
                callback(result);
            } else {
                callback();
            }
        }).fail(function(result) {
        	// CSRF대응으로 403 반환의 경우
            if (result && result.status == 403) {
            	// 별도 처리 없을 경우, ajaxError으로 대응
            } else {
            	Mall.AjaxUtil.viewMessage(result.responseJSON, callback);
            }
        });
    }

};

/**
 * 공통코드 헬퍼 클래스
 */
Mall.CodeUtil = {
    /**
     * <pre>
     * 함수명 : getCodeList
     * 설  명 : 인자로 받은 코드 그룹의 코드 목록을 조회한다.
     * 사용법 : CodeUtil.getCodeList('AUTH_GB_CD', cbFunc)
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param cdGrp
     *            조회할 코드 그룹
     * @param callback
     *            콜백함수
     */
    getCodeList : function(cdGrp, callback) {
        var url = '/admin/code/selectCodeList.nh', param = {
            'cdGrp' : cdGrp
        }, callback = callback || function() {
            console.log('콜백 함수가 없습니다.')
        };
        Mall.AjaxUtil.getJSON(url, param, callback);
    },

    /**
     * <pre>
     * 함수명 : setCodeToOption
     * 설  명 : 입력받은 코드목록으로 option 태그를 생성하여 $select의 option을 변경한다.
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param codeList
     *            코드목록
     * @param $select
     *            option 태그를 붙일 select jQuery 객체
     */
    setCodeToOption : function(codeList, $select) {
        var option = '', template = new Mall.Template('<option value="{{dtlCd}}">{{dtlNm}}</option>');
        jQuery.each(codeList, function(i, o) {
            option += template.render(o);
        });

        $select.html(option);
    },

    /**
     * <pre>
     * 함수명 : setCodeToRadio
     * 설  명 : 입력받은 코드목록으로 radio 태그를 생성하여 $parent에 추가한다.
     *          ID는 접두어에 순번으로 생성됨
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param codeList
     *            모드목록
     * @param $parent
     *            radio 태그를 붙일 부모 jQuery 객체
     * @param name
     *            생성할 radio 태그의 이름
     * @param prefix
     *            생성할 radio 태그의 ID 접두어
     */
    setCodeToRadio : function(codeList, $parent, name, prefix) {
        var radio = '', idx = 1, template = new Mall.Template(
                '<label for="{{id}}" class="radio mr20"><span class="ico_comm"><input type="radio" name="{{name}}" id="{{id}}" value="{{dtlCd}}"></span>{{dtlNm}}</label>');
        jQuery.each(codeList, function(i, o) {
            o.name = name;
            o.id = prefix + idx++;
            radio += template.render(o);
        });

        $parent.append(radio);
    },

    /**
     * <pre>
     * 함수명 : setCodeToRadio
     * 설  명 : 입력받은 코드목록으로 checkbox 태그를 생성하여 $parent에 추가한다.
     *          ID는 접두어에 순번으로 생성됨
     * 사용법 :
     * 작성일 : 2017. 4. 28.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 28. kwt - 최초 생성
     * </pre>
     *
     * @param codeList
     *            모드목록
     * @param $parent
     *            checkbox 태그를 붙일 부모 jQuery 객체
     * @param name
     *            생성할 checkbox 태그의 이름
     * @param prefix
     *            생성할 checkbox 태그의 ID 접두어
     */
    setCodeToCheckbox : function(codeList, $parent, name, prefix) {
        var checkbox = '', idx = 1, template = new Mall.Template(
                '<label for="{{id}}" class="chack mr20"><span class="ico_comm">&nbsp;</span>{{dtlNm}}</label><input type="checkbox" name="{{name}}" id="{{id}}" class="blind" value="{{value}}">');
        jQuery.each(codeList, function(i, o) {
            o.name = name;
            o.id = prefix + idx++;
            checkbox += template.render(o);
        });

        $parent.append(checkbox);
    }
};

//그리드 관련 유틸
Mall.GridUtil = {
    /**
     * <pre>
     * 함수명 : appendPaging
     * 설  명 : 입력받은 조회 데이터와 ID들로 페이징 네이게이션을 생성한다.
     * 사용법 :
     * 작성일 : 2017. 4. 11.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 11. kwt - 최초 생성
     * </pre>
     *
     * @param formId
     *            조회조건이 속한 폼의 ID
     * @param parentId
     *            페이징이 추가될 부모 엘리먼트의 ID
     * @param resultListModel
     *            JSON으로 받은 조회 데이터
     * @param pagingId
     *            생성할 페이징의 ID
     * @param callback
     *            페이징의 페이지 클릭시 실행할 함수(조회 함수)
     */
    appendPaging : function(formId, parentId, resultListModel, pagingId, callback) {
    	// console.log('############## appendPaging :', parentId);
        jQuery('#' + parentId).html(Mall.GridUtil.paging(resultListModel, pagingId));
        if(callback) {
            jQuery('#' + parentId).grid(jQuery('#' + formId), callback);
        } else {
            jQuery('#' + parentId).grid(jQuery('#' + formId));
        }
    },

    /**
     * <pre>
     * 함수명 : paging
     * 설  명 : 입력받은 조회 데이터와 ID로 페이징 네이게이션 코드를 생성한다.
     * 사용법 :
     * 작성일 : 2017. 4. 19.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 4. 11. kwt - 최초 생성
     * </pre>
     *
     * @param resultListModel
     *            JSON으로 받은 조회 데이터
     * @param pagingId
     *            생성할 페이징의 ID
     */
    paging : function(resultListModel, id) {
        var currPageDiv = parseInt(((resultListModel.page - 1) / 10 + 1), 10),
            firstOfPage = parseInt((currPageDiv - 1) * 10 + 1, 10),
            lastPage = parseInt(Math.min(currPageDiv * 10, resultListModel.totalPages), 10),
            p = '<div class="pagination" role="navigation" aria-label="페이지 선택" id="' + id + '">';
        if (currPageDiv > 1) {
            p += '<a class="direction first" href="#" data-page="1">처음</a>';
            p += '<a class="direction prev" href="#" data-page="' + (firstOfPage - 1) + '">이전</a>';
        }

        for(var i = firstOfPage; i <= lastPage; i++) {
            p += '<a href="#none" class="' + (resultListModel.page == i ? ' thisPage' : '') + '" data-page="' + i + '">'+ i + '</a>';
        }

        if(resultListModel.totalPages > currPageDiv * 10) {
            p += '<a class="direction next" href="#" data-page="' + (lastPage + 1) + '">다음</a>';
            p += '<a class="direction end" href="#" data-page="' + resultListModel.totalPages + '">끝</a>';
        }
        // console.log('paging : ', p);
        return p;
    }
};

/**
 * 파일 업로드 클래스
 * file, image, excel 함수는 jQuery.Deferred 객체를 반환하므로 done, fail등을 사용가능한다.
 *
 * @type {{file: FileUpload.file, excel: FileUpload.excel, image: FileUpload.image, fileForm: FileUpload.fileForm, upload: FileUpload.upload, checkFileSize: FileUpload.checkFileSize}}
 */
Mall.FileUpload = {
    /**
     * <pre>
     * 함수명 : file
     * 설  명 : 간단 파일 업로드 - 업로드할 파일을 선택하면 바로 업로드
     * 사용법 :
     * 작성일 : 2017. 3. 16.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 3. 16. kwt - 최초 생성
     * </pre>
     * @returns
     */
    file : function() {
        return Mall.FileUpload.fileForm("file");
    },
    /**
     * <pre>
     * 함수명 : excel
     * 설  명 : 간단 엑셀 업로드 - 업로드할 엑셀 파일을 선택하면 바로 업로드
     * 사용법 :
     * 작성일 : 2017. 3. 16.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 3. 16. kwt - 최초 생성
     * </pre>
     * @returns
     */
    excel : function() {
        return Mall.FileUpload.fileForm("xls");
    },
    /**
     * <pre>
     * 함수명 : image
     * 설  명 : 간단 이미지 파일 업로드 - 업로드할 이미지 파일을 선택하면 바로 업로드
     * 사용법 :
     * 작성일 : 2017. 3. 16.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 3. 16. kwt - 최초 생성
     * </pre>
     * @returns
     */
    image : function(){
        return Mall.FileUpload.fileForm("image");
    },
    /**
     * <pre>
     * 함수명 : fileForm
     * 설  명 : 간단 파일 업로드를 위한 파일 업로드 폼 생성 및 업로드 처리
     *          다른 함수들에 의해 내부적으로 호출된다.
     * 사용법 :
     * 작성일 : 2017. 3. 16.
     * 작성자 : kwt
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2017. 3. 16. kwt - 최초 생성
     * </pre>
     * @returns
     */
    fileForm : function(type) {
        $("#fileUploadForm").remove();
        var html = '',
            accept = '',
            dfd = jQuery.Deferred();
        switch(type) {
            case  'xls' :
                accept = ' accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"';
                break;
            case 'image' :
                accept = ' accept="image/*"';
                break;
            default :
                break;
        }
        html += '<form name="fileUploadForm" id="fileUploadForm" method="post" enctype="multipart/form-data">';
        html += '<input type="file" name="uploadFile" id="uploadFile"/>';
        html += '</form>';
        $("body").append(html);

        $("#uploadFile").on('change', function() {
            $('#fileUploadForm').ajaxSubmit({
                url : '/front/filesave/fileUploadResult.nh'
                , dataType : 'json'
                , success : function(result){
                    $("#fileUploadForm").remove();
                    if(result.exCode != null && result.exCode != undefined && result.exCode != ""){
                        alert(result.message);
                    } else {
                        dfd.resolve(result);
                    }
                }
            });
        })
        .trigger('click');

        return dfd.promise();
    },

    upload : function(formId) {
        var dfd = jQuery.Deferred(),
            $form = $('#' + formId),
            url = $form.attr("action") || '/front/filesave/fileUploadResult.nh';

        $form.ajaxSubmit({
            url : url,
            dataType : 'json',
            success : function(result) {
                if(result.exCode != null && result.exCode != undefined && result.exCode != ""){
                    alert(result.message);
                } else {
                    dfd.resolve(result);
                }
            }
        });

        return dfd.promise();
    },
    checkFileSize : function (formId) {
        var $files = jQuery('#' + formId + ' input[type="file"]'),
            files, file;

        if(document.documentMode && document.documentMode < 10) {
            return true;
        }

        for(var i = 0, len = $files.length; i < len; i++) {
            files = $files[i].files;
            if(files&&files.length){
                for(var j = 0, l = files.length; j < l; j++) {
                    file = files[j];

                    // 파일 사이즈 수정 필요
                    if(file.size > Constant.file.maxSize) {
                        Mall.LayerUtil.alert('파일 ' + file.name + '의 파일사이즈가 2MB를 초과합니다.');
                        return false;
                    }
                }
                // console.log('file.size :' + file.size + ', Constant.file.maxSize :' + Constant.file.maxSize);
            }
        }
        return true;
    }
};

// 파일다운로드
Mall.FileDownload = {
    download : function(filePath, fileName) {
        var url = '/front/common/download.nh',
        key,
        param = {};
            for(var i = 0; i < arguments.length ; i++) {
                if(i === 0) {
                    key = "type";
                } else {
                    key = "id" + i;
                }

                param[key] = arguments[i];
            }

            Mall.FormUtil.submit(url, param, '_blank');
    }
};

Mall.Dvyaa = {
	check : function(dvyaaYn){
		var stor = "#dvyaaStorPopLayer";
		var dvycsMsg = "현재보고계신상품은 양재점 상품입니다.<br/>배송지를 설정하세요.";
		var dvycsPop = "";
		/* 
		 $.ajax({
			type : 'POST',
			url : "",
			async : false,
			success : function(result){
				rtn = true;
			}
		});
		*/
		$(".close").on("click", function(){
			$(stor).hide_modal();
			$(stor).hide();
		});	
		
		// 배송지 세션값이 미존재시
		if(dvyaaYn != 'Y'){
			Mall.LayerUtil.alert(dvycsMsg).done(function(){
				$(stor).show();
			});
			return false;
		}
		
		return true;
	}
};