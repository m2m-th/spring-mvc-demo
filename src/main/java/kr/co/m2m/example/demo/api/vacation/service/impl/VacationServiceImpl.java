
package kr.co.m2m.example.demo.api.vacation.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.example.demo.api.common.MailSenderUtil;
import kr.co.m2m.example.demo.api.vacation.mapper.VacationMapper;
import kr.co.m2m.example.demo.api.vacation.model.ApprovalPO;
import kr.co.m2m.example.demo.api.vacation.model.ApprovalVO;
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
import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.framework.auth.BEAuthDetailModel;
import kr.co.m2m.example.framework.util.MessageUtils;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class VacationServiceImpl implements VacationService {

	@Autowired
	private VacationMapper vacationMapper;

	@Autowired
	private MailSenderUtil mailSenderUtil;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<GuntaePO> selectCalendarElement(GuntaeSO so) {
		String getStrDate = so.getStrDate();
		String[] tempStrDate = getStrDate.split("월");

		String setStrDate = "";

		if (Integer.parseInt(tempStrDate[0]) < 10) {
			setStrDate = tempStrDate[1] + "0" + tempStrDate[0];
		} else {
			setStrDate = tempStrDate[1] + tempStrDate[0];
		}

		so.setStrDate(setStrDate);

		List<GuntaePO> list = vacationMapper.selectCalendarElement(so);
		return list;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<GuntaePO> selectCalendarElement_ForceDel(GuntaeSO so) {
		String getStrDate = so.getStrDate();
		String[] tempStrDate = getStrDate.split("월");

		String setStrDate = "";

		if (Integer.parseInt(tempStrDate[0]) < 10) {
			setStrDate = tempStrDate[1] + "0" + tempStrDate[0];
		} else {
			setStrDate = tempStrDate[1] + tempStrDate[0];
		}

		so.setStrDate(setStrDate);

		List<GuntaePO> list = vacationMapper.selectCalendarElement_ForceDel(so);
		return list;
	}

	@Override
	public List<GuntaePO> selectCalendarYear() {
		return vacationMapper.selectCalendarYear();
	}

	/**
	 * @메소드명 : selectScodeA005
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 22.
	 * @설명 : 근태 종류
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<ScodeVO> selectScodeA005(String grCode) {
		ResultListModel<ScodeVO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectScodeA005(grCode));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	/**
	 * @메소드명 : selectAllDept
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 22.
	 * @설명 : 부서 리스트
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<ScodeVO> selectAllDept() {
		ResultListModel<ScodeVO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectAllDept());
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<MemberPO> selectWorkerForDept(MemberSO so) {
		ResultListModel<MemberPO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectWorkerForDept(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<MemberPO> selectWorkerForYear(MemberSO so) {
		ResultListModel<MemberPO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectWorkerForYear(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> insertForceApprove(VacationVO vo) {
		ResultModel<String> rv = new ResultModel<>();
		List<String> calList = null;
		try {
			int duplCount = this.vacationMapper.checkDuplGuntae(vo);
			if (duplCount > 0) {
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.alreadyApproval"));
			} else {
				// [1] vacation 등록
				this.vacationMapper.insertTmpSave(vo);
				// 시작일 ~ 종료일 사이 평일목록 캘린더 조회
				calList = this.vacationMapper.selectCalendar(vo);

				if (calList != null && calList.size() > 0) {
					for (int i = 0; i < calList.size(); i++) {
						vo.setV_date(calList.get(i));
						// [2] 근태 강제등록
						this.vacationMapper.forceInsertGuntae(vo);
					}
				}
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
				rv.setData(MessageUtils.getMessage("server.common.process.success"));
			}

		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> forceDelGuntae(GuntaeSO so) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			this.vacationMapper.forceDelGuntae(so);
			rv.setMessage(MessageUtils.getMessage("server.common.message.delete.success"));
			rv.setData(MessageUtils.getMessage("server.common.process.success"));
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	};

	/**
	 * @메소드명 : selectHoliday
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 22.
	 * @설명 : 법정공휴일 목록
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<CalendarPO> selectHoliday() {
		ResultListModel<CalendarPO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectHoliday());
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	/**
	 * @메소드명 : insertTmpSave
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 22.
	 * @설명 : 근태등록 - 임시저장
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> insertTmpSave(VacationVO vo) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			String prevSerial = this.vacationMapper.checkDuplVacation(vo);
			String prevAppStatus = null;
			if (prevSerial != null) {
				prevAppStatus = this.vacationMapper.selectAppStatus(prevSerial);
			}
			// 기데이터가 존재하는데, 임시저장 상태가 아니라면(상신중, 결재완료, 반려) 경고창만 리턴.
			if (prevAppStatus != null && !prevAppStatus.equals("4")) {
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.alreadyApproval"));
			} else {
				// *********** vacation tb에 인서트 ****** ****
				String serial = insertVacation(vo);

				String app_status = "임시저장";

				// *********** approval tb에 인서트 **********
				rv = insertApproval(serial, app_status, vo);

				if (prevSerial != null && !prevSerial.equals("")) {
					System.out.println("임시저장 업데이트 완료");
					rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));
					rv.setData(MessageUtils.getMessage("server.common.process.success"));
				} else {
					System.out.println("임시저장 인서트 완료");
					rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
					rv.setData(MessageUtils.getMessage("server.common.process.success"));
				}
			}

		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	}

	/**
	 * @메소드명 : insertTmpSave
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 22.
	 * @설명 : 근태등록 - 결재상신
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ResultModel<String> reqApprove(VacationVO vo) {
		ResultModel<String> rv = new ResultModel<>();
		try {
			GuntaePO po = vacationMapper.checkMinusDayCapa(vo);

			// 쓸 수 있는 월차여유분보다 현재 필요한 근태차감일수가 더 많으면, 경고창만 리턴(모든 테이블 insert 안함)
			if (((po.getMyAcnt() - po.getMyAminus()) < po.getReqMinus()) && po.getReqMinus() != 0) {
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.approvalCntLack"));
			}
			// 이미 해당일자 내에 근태일자가 차감되는 결재건이 상신되었거나, 이미 결재완료된것이 있으면 경고창만 리턴.(모든 테이블 insert 안함)
			else if (po.getPrevMinus() > 0 && po.getReqMinus() != 0) {
				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.approvalDupl"));
			} else {
				// *********** vacation tb에 인서트 **********
				String serial = insertVacation(vo);

				String app_status = "상신중";
				// *********** approval tb에 인서트 **********
				rv = insertApproval(serial, app_status, vo);

				// *********** 결재상신 이메일 전송 **********
				// 이메일 발송 대상(부서장)
				ApprovalVO appVo = this.vacationMapper.selectApprovalEmail(serial);
				// session 로그인 유저 정보
				BEAuthDetailModel myVo = AuthDetailHelper.getAuthDetail();
				// 근태종류 이름
				ScodeVO sVo = this.vacationMapper.selectScodeNmA005(vo.getV_code());
				// 나에게 메일전송
				mailSenderUtil.mailSend(myVo.getEmail(), myVo.getEmail(), myVo.getName(), sVo.getScodeNm(), "상신", vo.getV_reason(), vo.getS_date(),
						vo.getE_date());
				// 부서장에게 메일전송
				mailSenderUtil.mailSend(appVo.getAppEmail(), myVo.getEmail(), myVo.getName(), sVo.getScodeNm(), "상신", vo.getV_reason(), vo.getS_date(),
						vo.getE_date());
			}
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		return rv;
	}

	/**
	 * @메소드명 : insertVacation
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 25.
	 * @설명 : 근태 테이블 등록/수정
	 * @param vo
	 * @return
	 */
	private String insertVacation(VacationVO vo) {
		// [1] 근태TB 중복 데이터 기존재시 해당 serial 리턴
		String serial = this.vacationMapper.checkDuplVacation(vo);
		System.out.println("기존재 serial : " + serial);
		if (serial == null) {
			// [2] 새로운 값이라면, 근태TB 새로 insert
			System.out.println("근태 tt 새로 insert 시작");
			int seq = this.vacationMapper.insertTmpSave(vo);
			System.out.println("방금 insert 된  seq : " + seq);
			serial = seq + "";
		} else {
			// [3] 기존에 임시저장된 건이 있다면 근태사유만 업데이트
			System.out.println("근태 tt 기존건 update 시작");
			vo.setSerial(serial);
			this.vacationMapper.updateTmpSaveReason(vo);
		}
		return serial;
	}

	/**
	 * @메소드명 : insertApproval
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 25.
	 * @설명 : 결재 테이블 등록/수정
	 * @param serial
	 * @param app_status
	 * @param vo
	 * @return
	 */
	private ResultModel<String> insertApproval(String serial, String app_status, VacationVO vo) {
		ResultModel<String> rv = new ResultModel<>();
		ApprovalPO po = new ApprovalPO();
		// [1] 결재TB 중복 데이터 기존재시 건수 COUNT 리턴
		int chkDuplApp = this.vacationMapper.checkDuplApprove(serial);
		po.setSerial(serial);
		po.setSubmitId(vo.getId());
		po.setSubmitDt(todayFormatYYYYmmDD());
		po.setAppCode(vo.getV_code());
		po.setAppStatus(app_status);
		if (chkDuplApp <= 0) {
			// [2] 결재TB 새로 insert
			this.vacationMapper.reqApprove(po);
		} else {
			// [3] 결재TB 결재상태만 update
			this.vacationMapper.updateAppStatus(po);
		}
		rv.setMessage(MessageUtils.getMessage("server.common.message.insert.approvalSuccess"));
		rv.setData(MessageUtils.getMessage("server.common.process.success"));
		return rv;
	}

	/**
	 * @메소드명 : todayFormatYYYYmmDD
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 16.
	 * @설명 : 오늘 날짜 (yyyyMMdd 형식)
	 * @return
	 */
	public String todayFormatYYYYmmDD() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String todayYYYYmmDD = sdf.format(cal.getTime());
		return todayYYYYmmDD;
	}

	/**
	 * @메소드명 : selectAllMembersDayOffList
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 16.
	 * @설명 : 연도별 사용자별 휴가 사용 현황 리스트
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<GuntaePO> selectAllMembersDayOffList(GuntaeSO so) {
		ResultListModel<GuntaePO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectAllMembersDayOffList(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	/**
	 * @메소드명 : selectMembersDayOffList
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 16.
	 * @설명 : 특정 사용자의 휴가 사용 현황(사용일수/발생일수)
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public GuntaePO selectMembersDayOffList(GuntaeSO so) {
		GuntaePO po = new GuntaePO();
		po = this.vacationMapper.selectMembersDayOffList(so);
		return po;
	}

	/**
	 * @메소드명 : selectUsersOffDay
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 25.
	 * @설명 : 특정 사용자의 월별 휴가사용 일자 리스트
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<GuntaePO> selectUsersOffDay(GuntaeSO so) {
		ResultListModel<GuntaePO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectUsersOffDay(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	/**
	 * @메소드명 : selectGuntaeYear
	 * @작성자 : bsj
	 * @작성일 : 2020. 6. 25.
	 * @설명 : 근태현황 조회 연도
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<YearcntPO> selectGuntaeYear() {
		ResultListModel<YearcntPO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectGuntaeYear());
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	@Override
	public List<GuntaeVO> userList(GuntaeSO searchModel) {
		List<GuntaeVO> list = vacationMapper.userList(searchModel);
		return list;
	}

	@Override
	public List<GuntaeVO> lookupVacationList(GuntaeSO searchModel) {
		List<GuntaeVO> list = vacationMapper.lookupVacationList(searchModel);
		return list;
	}

	@Override
	public float useVacation(GuntaeSO searchModel) {
		float list = vacationMapper.useVacation(searchModel);
		return list;
	}

	@Override
	public List<GuntaeVO> usedVacationTable(GuntaeSO searchModel) {
		List<GuntaeVO> list = vacationMapper.selectUsersOffDay_KHT(searchModel);
		return list;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<GuntaePO> selectDeptMonth(GuntaeSO so) {
		String getStrDate = so.getStrDate();
		String[] tempStrDate = getStrDate.split("월");

		String setStrDate = "";

		if (Integer.parseInt(tempStrDate[0]) < 10) {
			setStrDate = tempStrDate[1] + "0" + tempStrDate[0];
		} else {
			setStrDate = tempStrDate[1] + tempStrDate[0];
		}

		so.setStrDate(setStrDate);
		ResultListModel<GuntaePO> rv = new ResultListModel<>();
		rv.setResultList(vacationMapper.selectDeptMonth(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));
		return rv;
	}

	@Override
	public GuntaeVO selectMembersDayOffCount(GuntaeSO so) {
		GuntaeVO vo = new GuntaeVO();
		vo = this.vacationMapper.selectMembersDayOffCount(so);
		return vo;
	}

	@Override
	public GuntaeVO selectMembersALLDayOff(GuntaeSO so) {
		GuntaeVO vo = new GuntaeVO();
		vo = this.vacationMapper.selectMembersALLDayOff(so);
		return vo;
	}
}
