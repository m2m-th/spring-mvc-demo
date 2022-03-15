package kr.co.m2m.example.demo.api.vacation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.example.demo.api.vacation.model.CalendarPO;
import kr.co.m2m.example.demo.api.vacation.model.GuntaePO;
import kr.co.m2m.example.demo.api.vacation.model.GuntaeSO;
import kr.co.m2m.example.demo.api.vacation.model.GuntaeVO;
import kr.co.m2m.example.demo.api.vacation.model.MemberPO;
import kr.co.m2m.example.demo.api.vacation.model.MemberSO;
import kr.co.m2m.example.demo.api.vacation.model.ScodeVO;
import kr.co.m2m.example.demo.api.vacation.model.VacationVO;
import kr.co.m2m.example.demo.api.vacation.model.YearcntPO;
import kr.co.m2m.example.demo.api.vacation.service.VacationService;
import kr.co.m2m.example.demo.common.AuthDetailHelper;
import kr.co.m2m.example.framework.auth.BEAuthDetailModel;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/vacation")
public class VacationController {

	@Autowired
	VacationService vacationService;

	// ***************************************************************** 월별 근태 조회
	// start ******************************************************************

	/**
	 * @메소드명 : selectCalendarElement
	 * @작성자 : wbm
	 * @작성일 : 2020. 6. 18
	 * @설명 : 월별 근태 조회
	 * @return
	 */
	@GetMapping("selectCalendarElement")
	public List<GuntaePO> selectCalendarElement(GuntaeSO so) {
		BEAuthDetailModel vo = AuthDetailHelper.getAuthDetail();
//		if (so.getId().equals("") || so.getId() == null) {
//			so.setId(vo.getId());
//		}
		so.setId(vo.getId());
		List<GuntaePO> list = vacationService.selectCalendarElement(so);
		return list;
	}

	/**
	 * @메소드명 : selectCalendarElement_ForceDel
	 * @작성자 : bsj
	 * @작성일 : 2020. 7. 06
	 * @설명 : 월별 근태 조회(근태 강제삭제 페이지용)
	 * @return
	 */
	@GetMapping("selectCalendarElement_ForceDel")
	public List<GuntaePO> selectCalendarElement_ForceDel(GuntaeSO so) {
		log.info("selectCalendarElement_ForceDel controller test");
		List<GuntaePO> list = vacationService.selectCalendarElement_ForceDel(so);
		return list;
	}

	@GetMapping("selectCalendarYear")
	public List<GuntaePO> selectCalendarYear() {
		List<GuntaePO> list = vacationService.selectCalendarYear();
		return list;
	}

	// ***************************************************************** 월별 근태 조회
	// end ******************************************************************

	// ***************************************************************** 근태 등록 화면
	// start ******************************************************************

	/**
	 * @메소드명 : selectScodeA005
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 13.
	 * @설명 : 근태 종류 전체목록
	 * @return
	 */
	@GetMapping("selectScodeA005")
	public ResultListModel<ScodeVO> selectScodeA005() {
		log.info("selectScodeA005 controller test");
		return vacationService.selectScodeA005("A005");
	}

	/**
	 * @메소드명 : selectAllDept
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 30.
	 * @설명 : 부서 리스트
	 * @return
	 */
	@GetMapping("selectAllDept")
	public ResultListModel<ScodeVO> selectAllDept() {
		log.info("selectAllDept controller test");
		return vacationService.selectAllDept();
	}

	/**
	 * @메소드명 : selectWorkerForDept
	 * @작성자 : bsj
	 * @작성일 : 2020. 7. 1.
	 * @설명 : 부서별 사원목록
	 * @param so
	 * @return
	 */
	@PostMapping("selectWorkerForDept")
	public ResultListModel<MemberPO> selectWorkerForDept(@RequestBody MemberSO so) {
		log.info("selectWorkerForDept controller test");
		return vacationService.selectWorkerForDept(so);
	}

	/**
	 * @메소드명 : selectWorkerForYear
	 * @작성자 : bsj
	 * @작성일 : 2020. 7. 1.
	 * @설명 : 연도별 사원목록
	 * @param so
	 * @return
	 */
	@PostMapping("selectWorkerForYear")
	public ResultListModel<MemberPO> selectWorkerForYear(@RequestBody MemberSO so) {
		log.info("selectWorkerForYear controller test");
		return vacationService.selectWorkerForYear(so);
	}

	/**
	 * @메소드명 : insertForceApprove
	 * @작성자 : bsj
	 * @작성일 : 2020. 7. 1.
	 * @설명 : 근태 강제등록
	 * @param vo
	 * @return
	 */
	@PostMapping("insertForceApprove")
	public ResultModel<String> insertForceApprove(@RequestBody VacationVO vo) {
		log.info("insertForceApprove controller test");
		log.info("parameter == " + vo.toString());
		// 로그인 유저 정보
		return vacationService.insertForceApprove(vo);
	}

	/**
	 * @메소드명 : forceDelGuntae
	 * @작성자 : bsj
	 * @작성일 : 2020. 7. 1.
	 * @설명 : 근태 강제삭제
	 * @param so
	 * @return
	 */
	@PostMapping("forceDelGuntae")
	public ResultModel<String> forceDelGuntae(@RequestBody GuntaeSO so) {
		log.info("forceDelGuntae controller test");
		System.out.println("파라미터 == " + so.toString());
		return vacationService.forceDelGuntae(so);
	}

	/**
	 * @메소드명 : selectHoliday
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 17.
	 * @설명 : 공휴일 전체목록
	 * @return
	 */
	@GetMapping("selectHoliday")
	public ResultListModel<CalendarPO> selectHoliday() {
		log.info("selectHoliday controller test");
		return vacationService.selectHoliday();
	}

	/**
	 * @메소드명 : insertTmpSave
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 13.
	 * @설명 : 근태등록 임시저장
	 * @param vo
	 * @return
	 */
	@PostMapping("insertTmpSave")
	public ResultModel<String> insertTmpSave(@RequestBody VacationVO vo) {
		log.info("insertTmpSave controller test");
		BEAuthDetailModel my = AuthDetailHelper.getAuthDetail();
		if (vo.getId() == null || vo.getId().equals("")) {
			vo.setId(my.getId());
		}
		return vacationService.insertTmpSave(vo);
	}

	/**
	 * @메소드명 : reqApprove
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 15.
	 * @설명 : 근태등록 결제상신
	 * @param vo
	 * @return
	 */
	@PostMapping("reqApprove")
	public ResultModel<String> reqApprove(@RequestBody VacationVO vo) {
		log.info("reqApprove controller test");
		log.info("parameter == " + vo.toString());
		// 로그인 유저 정보
		BEAuthDetailModel loginUser = AuthDetailHelper.getAuthDetail();
		System.out.println("로그인 유저 id : " + loginUser.getId());
		vo.setId(loginUser.getId());
		return vacationService.reqApprove(vo);
	}

	// ***************************************************************** 근태 등록 화면
	// end ******************************************************************

	// *********************************************************** 년도별 사용자별 휴가사용현황
	// 화면 start **********************************************************

	/**
	 * @메소드명 : loadAllMembersDayOffList
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 년도별 사용자별 휴가 사용 현황
	 * @return
	 */
	@PostMapping("selectAllMembersDayOffList")
	public ResultListModel<GuntaePO> selectAllMembersDayOffList(@RequestBody GuntaeSO so) {
		log.info("selectAllMembersDayOffList controller test");
		System.out.println("검색할 연도 : " + so.getGDate());
		return vacationService.selectAllMembersDayOffList(so);
	}

	@PostMapping("selectMembersDayOffList")
	public GuntaePO selectMembersDayOffList(@RequestBody GuntaeSO so) {
		log.info("selectMembersDayOffList controller test");
		System.out.println("검색할 id : " + so.getId());
		System.out.println("검색할 연도 : " + so.getGDate());
		return vacationService.selectMembersDayOffList(so);
	}

	@PostMapping("selectUsersOffDay")
	public ResultListModel<GuntaePO> selectUsersOffDay(@RequestBody GuntaeSO so) {
		log.info("selectUsersOffDay controller test");
		System.out.println("검색할 id : " + so.getId());
		System.out.println("검색할 연도 : " + so.getGDate());
		return vacationService.selectUsersOffDay(so);
	}

	@GetMapping("selectGuntaeYear")
	public ResultListModel<YearcntPO> selectGuntaeYear() {
		log.info("selectGuntaeYear controller test");
		return vacationService.selectGuntaeYear();
	}

	// *********************************************************** 년도별 사용자별 휴가사용현황
	// 화면 end **********************************************************

	// *********************************************************** 월별 개인 휴가 사용 현황
	// 화면 start **********************************************************

	/**
	 * @메소드명 : loginUser
	 * @작성자 : kht
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 현재 로그인 유저 검색
	 * @param vo
	 * @return
	 */
	@GetMapping("loginUser")
	public BEAuthDetailModel loginUser() {
		log.info("loginUser controller test");
		BEAuthDetailModel vo = AuthDetailHelper.getAuthDetail();
		return vo;
	}

	/**
	 * @메소드명 : userList
	 * @작성자 : kht
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 해당 이름 조회
	 * @param vo
	 * @return
	 */
	@GetMapping("userList")
	public List<GuntaeVO> userList() {
		log.info("userList controller test");
		GuntaeSO so = new GuntaeSO();
		BEAuthDetailModel vo = AuthDetailHelper.getAuthDetail();
		so.setId(vo.getId());
		so.setChiefYn(vo.getChiefYn());
		so.setDept(vo.getDept());
		List<GuntaeVO> list = vacationService.userList(so);
		return list;
	}

	/**
	 * @메소드명 : useVacation
	 * @작성자 : kht
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 전체 사용한 휴가사용일수 가져오기 : 콤보 박스에서 이름
	 * @param vo
	 * @return
	 */
	@GetMapping("useVacation")
	public float useVacation(GuntaeSO searchModel) {
		log.info("useVacation controller test");
		float list = vacationService.useVacation(searchModel);
		return list;
	}

	/**
	 * @메소드명 : lookupVacationList
	 * @작성자 : kht
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 해당하는 년도 부르기 : 콤보 박스에서 이름
	 * @param vo
	 * @return
	 */
	@GetMapping("lookupVacationList")
	public List<GuntaeVO> lookupVacationList(GuntaeSO searchModel) {
		log.info("lookupVacationList controller test");
		List<GuntaeVO> list = vacationService.lookupVacationList(searchModel);
		return list;
	}

	/**
	 * @메소드명 : usedVacationTable
	 * @작성자 : kht
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 사용한 휴가 날짜 정보 가져오기 : 콤보 박스에서 이름
	 * @param vo
	 * @return
	 */
	@GetMapping("usedVacationTable")
	public List<GuntaeVO> usedVacationTable(GuntaeSO searchModel) {
		log.info("usedVacationTable controller test");
		return vacationService.usedVacationTable(searchModel);
	}

	// *********************************************************** 월별 개인 휴가 사용 현황
	// 화면 end **********************************************************

	@GetMapping("selectDeptMonth")
	public ResultListModel<GuntaePO> selectDeptMonth(GuntaeSO so) {
		log.info("selectUsersOffDay controller test");
		System.out.println("검색할 id : " + so.getId());
		return vacationService.selectDeptMonth(so);
	}

}
