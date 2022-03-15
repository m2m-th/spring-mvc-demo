'use strict';

/**
 * 각종 초기화 및 함수 추가/재정의
 */

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.df = function(len){return "0".string(len - this.length) + this;};
Number.prototype.df = function(len){return this.toString().df(len);};

String.prototype.isEmpty = function() {
    return this.length < 1 ? !0 : !1
}, String.prototype.replaceAll = function(a, b) {
    return this.replace(new RegExp(a, "gm"), b)
}, String.prototype.getReplacestr = function(len, str) {
    return (this.isEmpty()) ? '' : (this.length > len && len > 3) ? this.substring(0, len-2) + str : this
}, String.prototype.toEscape = function() {
    return (this.isEmpty()) ? '' : (this.length > 10) ? Mall.HtmlUtil.escape(this) : this    		
}, String.prototype.getFunction = function() {
    if (this.length < 1) return null;
    for (var a = window, b = this.split("."), c = b.length, d = 0; c - 1 > d; d++)
        if (a = a[b[d]], void 0 == a) return null;
    return a[b[b.length - 1]]
}, String.prototype.isNumber = function() {
	return !isNaN(this);
}, String.prototype.getNumber = function() {
	return this.isEmpty() || !this.isNumber() ? 0 : parseInt(this, 10);
}, String.prototype.getCommaNumber = function() {
    var a = this + "";
    a = a.replace(/[^\+\-0-9]/g, "");
    var b = /(^[+-]?\d+)(\d{3})/;
    while (b.test(a)) a = a.replace(b, "$1,$2");
    return a
}, String.prototype.toDate = function() {
	if (this.isEmpty() || this.length < 8) {return this} 
	var _strDate = this
      , _year = _strDate.substring(0,4)
      , _month = _strDate.substring(4,6)-1
      , _day = _strDate.substring(6,8)
      , _dateObj = new Date(_year,_month,_day);
    return _dateObj
}, String.prototype.toDateTime = function() {
	if (this.isEmpty() || this.length < 14) {return this} 
	var _strDate = this
      , _year = _strDate.substring(0,4)
      , _month = _strDate.substring(4,6)-1
      , _day = _strDate.substring(6,8)
      , _hour = _strDate.substring(8,10)
      , _min = _strDate.substring(10,12)
      , _sec = _strDate.substring(12,14)
      , _dateObj = new Date(_year,_month,_day, _hour, _min, _sec);
    return _dateObj    
}, String.prototype.unEscape = function() {
	return this.isEmpty() || this.isNumber() ? '' : Mall.HtmlUtil.unescape(this);
}, Number.prototype.getNumber = function() {
    var a = this + "";
    return a.getNumber()    
}, Number.prototype.getCommaNumber = function() {
    var a = this + "";
    return a.getCommaNumber()
};

/**
 * <pre>
 * 함수명 : format
 * 설  명 : Date 객체에 format 함수 추가 정의
 * 사용법 : new Date().format('yyyy-mm-dd')
 * 작성일 : 2016. 4. 28.
 * 작성자 : wtKim
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------
 * 2016. 4. 28. wtKim - 최초 생성
 * </pre>
 * @param f 출력할 포멧 문자열
 * @return {String} Date 객체의 데이터가 포메팅된 문자열
 */
Date.prototype.format = function(f) {
    if (!this.valueOf()) return "";
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this,
    h;
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).df(2);
            case "MM": return (d.getMonth() + 1).df(2);
            case "dd": return d.getDate().df(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().df(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).df(2);
            case "mm": return d.getMinutes().df(2);
            case "ss": return d.getSeconds().df(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
/**
 * <pre>
 * 함수명 : trim
 * 설  명 : String 객체에 trim 함수 추가 정의
 * 사용법 : "가나다라 ".trim()
 * 작성일 : 2016. 4. 28.
 * 작성자 : wtKim
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------
 * 2016. 4. 28. wtKim - 최초 생성
 * </pre>
 * @param str
 * @returns {string} 앞뒤 공백이 제거된 문자열
 */
String.prototype.trim = function(str) {
    str = this != window ? this : str;
    return str.replace(/^\s+/g,'').replace(/\s+$/g,'');
};

/**
 * jQuery AJAX 초기 설정
 */
//jQuery.ajaxSetup({
//fail: function(xhr,st,err) {
// if (xhr.status == 403) {
//     LayerUtil.confirm('로그인 정보가 없거나 유효시간이 지났습니다.<br/>로그인페이지로 이동하시겠습니까?',
//         function() {
//             document.location.href = '/admin/login/loginView.nh';
//         });
// } else {
//     if(xhr.responseJSON && xhr.responseJSON.message) {
//         LayerUtil.alert(xhr.responseJSON.message);
//     } else {
//         LayerUtil.alert("처리중 오류가 발생했습니다. 관리자에게 문의하십시오.");
//     }
// }
// return xhr.responseJSON;
// },
// always: function() {
// waiting.stop();
// }
//});

/**
 * jQueryUI 달력 초기화
 */
$.datepicker.setDefaults({
    showOtherMonths: true,
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNamesMin: ['SUN', 'MON', 'THU', 'WED', 'THU', 'FIR', 'SAT'],
    showMonthAfterYear: true,
    changeMonth: true,
    changeYear: true,
    yearSuffix: '년'
});

jQuery(document).ready(function() {
    /**
     * CSRF 공격 방지 설정 및 jQuery ajax 초기화
     */
    Mall.init.ajax();
});

/**
 * 간단한 템플릿을 구현하기 위한 클래스
 * template 에 Map 형태인 obj의 데이터를 매핑
 * obj 의 KEY에 해당하는 템플릿의 {{KEY}} 문자열이 obj의 VALUE로 치환됨
 *
 * @param template 템플릿 문자열 ex) <span>{{name}}</span>
 * @param obj map 형태의 데이터 객체 ex) {name : 'Mall'}
 * @constructor template(필수), obj
 */
Mall.Template = function(template, obj) {
    var srcTemplate = template || '',
    map = obj || {};

    /**
     * 정의된 템플릿에 obj의 데이터를 매핑하여 반환
     * @param obj map 형태의 데이터 객체 ex) {name : 'Mall', since: 2016}
     * @returns {*|string}
     */
    this.render = function(obj) {
        var obj = obj || map,
        key,
        exp,
        temp = srcTemplate;

        for(key in obj) {
            exp = new RegExp('{{' + key + '}}', 'gi');
            temp = temp.replace(exp, obj[key] ? obj[key] : '');
        }
        return temp;
    };
};

/**
 * 디자인 체크박스 클래스
 */
Mall.CheckboxUtil = {
        /**
         * <pre>
         * 함수명 : check
         * 설  명 : 디자인 체크박스 내부의 체크박스에 값을 설정
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * </pre>
         * @param obj 이벤트가 발생한 엘리먼트
         * @param checked 체크여부
         */
        check : function(obj, checked) {
            var $this = $(obj),
            $checkbox = $this.find('input');
            if(checked) {
                $this.addClass('on');
                $checkbox.prop('checked', true);
            } else {
                $this.removeClass('on');
                $checkbox.prop('checked', false);
            }
        }
};

/**
 * 디자인 셀렉트 클래스
 */
Mall.SelectUtil = {
    /**
     * <pre>
     * 함수명 : reset
     * 설  명 : 디자인 셀렉트 이전 값으로 되돌린다.
     * 사용법 :
     * 작성일 : 2016. 6. 14.
     * 작성자 : wtKim
     * 수정내역(수정일 수정자 - 수정내용)
     * -------------------------------------
     * 2016. 6. 14. wtKim - 최초 생성
     * </pre>
     * @param obj 되돌릴 값의 SELECT 엘리먼트
     */
    reset : function(obj) {
        var $this = $(obj),
            select_name;
        $this.find('option[value="' + $this.data('prevValue') + '"]').prop('selected', true);
        select_name = $this.children('option:selected').text();
        $this.siblings('label').text(select_name);
    }
};

/**
 * 초기화에 관련된 클래스
 */
Mall.init = {
        /**
         * <pre>
         * 함수명 : jQuery ajax의 요청/오류반환/완료 시에 대한 공통 처리 초기화
         * 설  명 :
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * </pre>
         */
        ajax : function() {
            var token = $("meta[name='_csrf']").attr("content"),
            header = $("meta[name='_csrf_header']").attr("content");

            $(document).ajaxSend(function(e, xhr, options) {
                // Mall.waiting.start();
            	console.log( header + ", token : " + token)
                xhr.setRequestHeader(header, token);
            });
            
            $(document).ajaxError(function(e, xhr, options) {
            	console.log( 'xhr : ' + xhr + ", xhr.status : " + xhr.status)
                if (xhr.status == 403) {
                    Mall.LayerUtil.confirm('로그인 정보가 없거나 유효시간이 지났습니다.<br/>로그인페이지로 이동하시겠습니까?',
                            function() {
                        document.location.href = domainHttps + '/BC41000R/loginViewPage.nh';
                    });
                } else {
                    if (xhr.responseJSON && xhr.responseJSON.message) {
                        console.log(xhr.responseJSON.message);
                        Mall.LayerUtil.alert(xhr.responseJSON.message);
                    } else {
                        Mall.LayerUtil.alert("처리중 오류가 발생했습니다. 관리자에게 문의하십시오.");
                    }
                }
            });
            
            $(document).ajaxComplete(function(e, xhr, options) {
                // Mall.waiting.stop();
                return xhr.responseJSON;
            });

        },
        /**
         * <pre>
         * 함수명 : datepicker
         * 설  명 : 달력 입력란, 달력 이미지에 대한 이벤트 처리 및 jQuery UI 달력 설정 세팅
         * 사용법 :
         * 작성일 : 2017. 4. 18.
         * 작성자 : kwt
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2017. 4. 18. kwt - 최초 생성
         * </pre>
         */
        datepicker : function(inputId, btnId) {
        	   var now = new Date();
        	   var day = now.getDate();
        	   var month = now.getMonth()+1;
        	   var today = now.getFullYear()+"년 "+(month)+"월 "+(day)+"일";
        	   $('input#' + inputId).val(today);
        	   var dp1 = new datepicker('dp1', 'date', true);
        	   $('#' + btnId).click(function(e) {
        	      dp1.showDlg();
        	      Mall.EventUtil.stopAnchorAction(e);
        	      return false;
        	   });
            // 달력 INPUT
            // $(".nh_date_sc, .date_sc").nhCalandar();
        	// $(".btn_datepicker").nhCalandar();
        },

        /**
         * <pre>
         * 함수명 : datePeriodButton
         * 설  명 : 달력 옆에 달린 기간 버튼에 대한 이벤트 처리 및 설정
         * 사용법 :
         * 작성일 : 2016. 6. 1.
         * 작성자 : KMS
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 6. 1. KMS - 최초 생성
         * </pre>
         */
        datePeriodButton : function() {
            /**
             * 달력 버튼 클릭시 이벤트 처리
             */
            $('.tbl_btn button.btn_day').on('click', function(e){
                Mall.EventUtil.stopAnchorAction(e);

                $(this).addClass('on').siblings().removeClass('on');

                //TODO: 클릭시 달력 데이터 변경
                var $this = jQuery(this),
                index = $this.index(),
                $parent = $this.parents('td').data('index', index),
                $to = $parent.find('input.nh_date_sc:eq(1)'),
                $from = $parent.find('input.nh_date_sc:eq(0)'),
                date,
                from,
                to = $to.val();
                if(jQuery.trim(to) == '') {
                    date = new Date();
                    to = date.format('yyyy-MM-dd');
                } else {
                    date = new Date($to.val().replace(/-/g, '/'));
                }

                // TODO: 올바르지 않은 문자일때 오류처리

                switch (index) {
                    case 0 :
                        date = new Date();
                        from = date.format('yyyy-MM-dd');
                        to = date.format('yyyy-MM-dd');
                        break;
                    case 1 :
                        date.setDate(date.getDate() - 3);
                        break;
                    case 2 :
                        date.setDate(date.getDate() - 7);
                        break;
                    case 3 :
                        date.setMonth(date.getMonth() - 1);
                        break;
                    case 4 :
                        date.setMonth(date.getMonth() - 3);
                        break;
                    default :
                        from = '';
                    to = '';
                    break;
                }

                if(from != '') {
                    from = date.format('yyyy-MM-dd');
                }

                $from.val(from);
                $to.val(to);
                console.log()
            });
        },

        checkbox : function() {
        	$(document).on('click', 'input[type=radio], input[type=checkbox]', function(){
        		var $that = $(this);
        		if ($that.is(':checkbox')) {
        			if($that.prop('checked')) {
        				// $that.prop('checked', true);
        				$that.next().addClass('active');
        			} else {
        				// $that.prop('checked', false);
        				$that.next().removeClass('active');
        			}
        		} else if ($that.is(':radio')) {
        			$('input[name="'+ $that.attr('name') +'"]').each(function() {
        				if (this == $that[0]) $(this).next().addClass('active');
        				else $(this).next().removeClass('active');
        			});
        		}
        	})
        },

        /**
         * <pre>
         * 함수명 : select
         * 설  명 : 디자인 셀렉트에 대한 이벤트 처리
         *          디자인 셀렉트 선택시 이전값 저장 및 라벨 처리
         *          디자인 셀렉트 초기화시 체인지 트리거 발생
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * 2016. 6. 14. wtKim - 이전값 저장 기능 추가
         * </pre>
         */
        select : function() {
            jQuery(document).on('change', 'select', function () {
                var $this = $(this),
                    select_name;
                select_name = $this.children('option:selected').text();
                $this.siblings('label').text(select_name);
            }).on('focus, click', 'select', function() {
                var $this = $(this);
                $this.data('prevValue', this.value);
            });
            jQuery('select').trigger('change');
        },

        /**
         * <pre>
         * 함수명 : radio
         * 설  명 : 디자인 라디오 버튼에 대한 이벤트 처리(디자인 라디오 체크시 다른 디자인 라디오 버튼의 체크 해제)
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * </pre>
         */
        radio : function() {
            $(document).on('click', '.radio', function(e) {
                Mall.EventUtil.stopAnchorAction(e);

                var $this = $(this),
                    $input = $this.find('input');

                if(!$input.prop('disabled') && !$input.prop('readonly')) {
                    // $input.parents('label').siblings().find('input').removeProp('checked');
                    $input.prop('checked', true).trigger('change');

                    if ($input.prop('checked')) {
                        $this.addClass('on').siblings().removeClass('on');
                    }
                }
            });
        }
};

/**
 * 각종 값을 바인드하는 클래스
 *
 * @type {{setActionTypeUpdate: FormUtil.setActionTypeUpdate, jsonToForm: FormUtil.jsonToForm}}
 */
Mall.FormUtil = {

        /**
         * <pre>
         * 함수명 : getActionTypeObj
         * 설  명 : formId의 해당하는 폼의 액션 타입을 반환하는 함수
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * </pre>
         * @param formId
         * @return {Object} 액션 타입 input jQuery Object, 없으면 빈값을 가진 input jQuery Object
         */
        getActionTypeObj : function(formId) {
            var $form = jQuery('#' + formId),
            $actionType = $form.find('input[name="_action_type"]');
            if(!$actionType || $actionType.length === 0) {
                $actionType = jQuery('<input type="hidden" name="_action_type" />');
                $form.append($actionType);
            }

            return $actionType;
        },

        /**
         * <pre>
         * 함수명 : setActionTypeUpdate
         * 설  명 : formId 에 해당하는 폼의 액션 타입을 '수정'으로 변경
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * </pre>
         * @param formId 폼 ID
         */
        setActionTypeUpdate : function(formId) {
            console.log('set action type : UPDATE');
            Mall.FormUtil.getActionTypeObj(formId).val('UPDATE');
        },

        /**
         * <pre>
         * 함수명 : setActionTypeInsert
         * 설  명 : formId 에 해당하는 폼의 액션 타입을 '등록'으로 변경
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * </pre>
         * @param formId 폼 ID
         */
        setActionTypeInsert : function(formId) {
            console.log('set action type : INSERT');
            Mall.FormUtil.getActionTypeObj(formId).val('INSERT');
        },
        getActionType : function(formId) {
            var actionType = Mall.FormUtil.getActionTypeObj(formId).val();
            console.log('action type : ', actionType|| 'INSERT');
            return actionType || 'INSERT';
        },

        /**
         * <pre>
         * 함수명 : jsonToForm
         * 설  명 : JSON 형태의 데이터를 formId에 해당하는 폼에 바인드
         *          JSON 객체의 KEY의 이름과 같은 INPUT, SELECT, TEXTAREA엘리먼트의 값으로 VALUE를 세팅
         *          없다면 'bind_target_id_[KEY]'에 해당하는 엘리먼트의 'TEXT'로 VALUE를 세팅
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * </pre>
         * @param json 폼에 매핑할 JSON 객체
         * @param formId 폼 ID
         */
        jsonToForm : function(json, formId) {
            var key,
            value,
            $form = jQuery('#' + formId),
            $obj;

            for(key in json) {
                $obj = $form.find('input[name="' + key + '"], select[name="' + key + '"], textarea[name="' + key + '"]');
                value = json[key] || '';
                console.log(key, value, $obj);

                if($obj.length == 0) {
                    $obj = $form.find('#bind_target_id_' + key);
                    if($obj.length == 0) continue;
                };

                switch ($obj[0].tagName) {
                    case 'INPUT' :
                        switch ($obj.attr('type')) {
                            case 'radio' :
                                var $o = $obj.filter('input[value="' + value + '"]');
                                $o.prop('checked', true);
                                $o.parents('label').addClass('on').siblings().removeClass('on');
                                break;
                            case 'checkbox' :
                                var $o = $obj.filter('input[value="' + value + '"]');
                                $o.prop('checked', true);
                                $o.next().addClass('on').siblings().removeClass('on');
                                break;
                            default :
                                $obj.val(Mall.HtmlUtil.unescape(value));
                        }
                        break;
                    case 'SELECT' :
                        var $o = $obj.find('option[value="' + value + '"]');
                        $o.prop('selected', true);
                        $o.next().addClass('on').siblings().removeClass('on');
                        break;
                    case 'TEXTAREA' :
                    default :
                        $obj.text(Mall.HtmlUtil.unescape(value));
                }
            }

            Mall.FormUtil.setActionTypeUpdate(formId);
        },

        /**
         * <pre>
         * 함수명 : submit
         * 설  명 :
         * 사용법 :
         * 작성일 : 2016. 4. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 4. 28. wtKim - 최초 생성
         * 2016. 5. 16. wtKim - target 추가
         * </pre>
         * @param url 서버로 요청할 URL
         * @param paramMap 서버로 요청시 전달할 파라미터 객체(맵형식)
         * @param target from 태그의 target
         * @returns {Boolean} 오류시 false
         */
        submit : function (url, paramMap, target) {
            jQuery('#_form_id_comm').remove();

            var token = $("meta[name='_csrf']").attr("content"),
                $form = jQuery('<form method="post"></form>'),
                inputTemplate = '<input type="hidden" name="{{name}}" value="{{value}}" />',
                template = new Mall.Template(inputTemplate),
                key;

            $form.attr({'action' : url, 'method' : 'post', 'id' : '_form_id_comm'});

            if(target) {
                $form.attr('target', target);
            }

            if(paramMap != null && !$.isPlainObject(paramMap)) {
                Mall.LayerUtil.alert('param 변수가 Object(Map) 형식이 아닙니다.');
                return false;
            }

            $form.append(template.render({name: '_csrf', value : token}));

            for(key in paramMap) {
                $form.append(template.render({name: key, value : paramMap[key]}));
            }
            jQuery('body').append($form);
            $form.submit();
        }
        
        /**
         * <pre>
         * 함수명 : submitGet
         * 설  명 :
         * 사용법 :
         * 작성일 : 2016. 9. 28.
         * 작성자 : wtKim
         * 수정내역(수정일 수정자 - 수정내용)
         * -------------------------------------
         * 2016. 9. 28. wtKim - 최초 생성
         * </pre>
         * @param url 서버로 요청할 URL
         * @param paramMap 서버로 요청시 전달할 파라미터 객체(맵형식)
         * @param target from 태그의 target
         * @returns {Boolean} 오류시 false
         */
        , submitGet : function (url, paramMap, target) {
            jQuery('#_form_id_comm').remove();

            var token = $("meta[name='_csrf']").attr("content"),
                $form = jQuery('<form method="get"></form>'),
                inputTemplate = '<input type="hidden" name="{{name}}" value="{{value}}" />',
                template = new Mall.Template(inputTemplate),
                key;

            $form.attr({'action' : url, 'method' : 'get', 'id' : '_form_id_comm'});

            if(target) {
                $form.attr('target', target);
            }

            if(paramMap != null && !$.isPlainObject(paramMap)) {
                Mall.LayerUtil.alert('param 변수가 Object(Map) 형식이 아닙니다.');
                return false;
            }
            // $form.append(template.render({name: '_csrf', value : token}));
//            $form.append(template.render({name: 'test01', value : '[]{}'}));
//            $form.append(template.render({name: 'test02', value : '한글'}));

            for(key in paramMap) {
                $form.append(template.render({name: key, value : paramMap[key]}));
            }
            jQuery('body').append($form);
            $form.submit();
        }        
};

/**
 * jQuery 플러그인 정의
 */
(function($) {
    /**
     * jQuery 그리드 플러그인
     * 그리드의 페이징 이벤트를 처리
     *
     * @param $form 조회조건 폼 jQuery 객체
     * @param callback 콜백함수 = 조회 함수
     */
    $.fn.grid = function($form, callback) {
        callback = callback || function () {$form.submit();};
        return this.each(function() {
            var $grid = $(this),
            $page = $form.find('input[name="page"]');
            $grid.find('div.pagination a').on('click', function(e) {
                Mall.EventUtil.stopAnchorAction(e);
                $page.val(jQuery(this).data('page'));
                console.log('[custom.js] selected page :' + jQuery(this).data('page'), callback);
                callback();
            });
            $grid.find('select[name="sidx"]').on('change', function() {
                $form.find('input[name="sort"]').val(this.value);
                callback();
            });
            $grid.find('select[name="rows"]').on('change', function() {
                $form.find('input[name="rows"]').val(this.value);
                callback();
            });
        });
    };
})(jQuery),

function($) {
    "use strict";
    $.fn.DataBinder = function(data, target, area, row) {
        return this.each(function() {
            var $obj = $(this);
            // alert(f.data("bind-type"));
            if (null != data && "object" == typeof data) {
                var type = $obj.data("bind-type"), bindName = $obj.data("bind-value");

                if (null != type && ! $.isEmptyObject(type)) {
                    // alert("test 111");
                    if (null == bindName || bindName.length < 1)
                        return void $obj.html("(no bind)");

                    var value = null == data[bindName] ? "" : data[bindName];

                    switch (type.toLowerCase()) {
                        case "function":
                            var func = $obj.data("bind-function");
                            func = null == func || func.isEmpty() ? null : func.getFunction(), null != func && "function" == typeof func ? func.apply(null, [data, $obj, bindName, target, area, row]) : "" ;
                            break;
                        case "radio":
                            $obj.prop("disabled", "disabled"), $obj.val() == value && $obj.prop("checked", "checked");
                            break;
                        case "checkbox":
                            $obj.prop("disabled", "disabled"), $obj.val() == value && $obj.prop("checked", "checked");
                            break;
                        case "text":
                            $obj.val(value);
                            break;
                        case "bizno":
                            $obj.val(Mall.formatter.bizNo(value));
                            break;
                        case "tel":
                            $obj.val(Mall.formatter.tel(value));
                            break;
                        case "fax":
                            $obj.val(Mall.formatter.fax(value));
                            break;
                        case "select":
                            $("option[value='"+ value +"']", $obj).attr("selected", "true");
                            break;

                        case "password":
                            $obj.data("input-value", value).text("".padding(value.length, "*"));
                            break;

                        case "commanumber":
                            $obj.data("value", value).html(parseInt(value).getCommaNumber());
                            break;

                        case "number":
                        case "string":
                        default:
                            $obj.data("value", value).html(value);
                            break;
                    }
                }
            }
        })
    }
    
    
/*  사용예
 * 	var url = '/test/insertGoodsInfo.do'
        , param = fn_getGoodsRegistData()
        , paramContents = $('#form_goods_detail_info').serializeObject()
        , attachImages = {'attachImages' : $('#ta_goods_content').data('attachImages')}
        , deletedImages = {'deletedImages' : $('#ta_goods_content').data('deletedImages')}
    ;                          
    var paramMerge = $.extend(paramContents, param, attachImages, deletedImages);
*/
    
    , $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }    
}(jQuery);

Mall.HtmlUtil = {
    escape : function(text) {
        var entityMap = {
            "&": "&amp;",
            "<": "&lt;",
            ">": "&gt;",
            '"': '&quot;',
            "'": '&#39;',
            "/": '&#x2F;'
        };

        return String(text).replace(/[&<>"'\/]/g, function (s) {
            return entityMap[s];
        });
    },
    unescape : function(text) {
        var entityMap = {
            '&amp;': '&',
            '&lt;': '<',
            '&gt;': '>',
            '&quot;': '"',
            '&#39;': "'",
            '&#x2F;': '/'
        };
        return String(text).replace(/(&amp;)|(&lt;)|(&gt;)|(&quot;)|(&#39;)|(&#x2F;)/g, function (s) {
            return entityMap[s];
        });
    }
};

/**
 * <pre>
 * 함수명 : serializeJson
 * 설  명 : Id에 해당하는 데이터를 Json으로 serialize
 * 사용법 : $("#Id").serializeJson()
 * 작성일 : 2017. 6. 5.
 * 작성자 : jhlee
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------
 * 2016. 6. 5. jhlee - 최초 생성
 * </pre>
 */
$.fn.serializeJson = function() {
	var o = {};
	$(this).find('input[type="hidden"], input[type="text"], input[type="password"], input[type="checkbox"]:checked, input[type="radio"]:checked, textarea, select').each(function() {
		if ($(this).attr('type') == 'hidden') { //if checkbox is checked do not take the hidden field
			var $parent = $(this).parent();
			var $chb = $parent.find('input[type="checkbox"][name="' + this.name.replace(/\[/g, '\[').replace(/\]/g, '\]') + '"]');
			if ($chb != null) {
				if ($chb.prop('checked')) return;
			}
		}

		if (this.name === null || this.name === undefined || this.name === '') return;

		var elemValue = null;

		if ($(this).is('select')) elemValue = $(this).find('option:selected').val();
		else elemValue = this.value;

		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(elemValue || '');
		} else {
			o[this.name] = elemValue || '';
		}
	});
	return o;
};

Mall.fnRtn = {
	callBack : function(url, rtnYn, reDirect, param){
		if(reDirect == true){
			if(rtnYn == "Y"){
				var form = $('<form style="visibility:hidden" id="form_reDirect"></form>');
				form.attr('action', url);
				form.attr('method', 'post');
				form.appendTo('body');
				var formData = "";
				$.each(param,function(key,value){
					formData += $('<input type="hidden" id="'+ key +'" value="'+ value +'"/>');
				});
				form.append(formData);
				form.submit();
			}
		}
	}
};