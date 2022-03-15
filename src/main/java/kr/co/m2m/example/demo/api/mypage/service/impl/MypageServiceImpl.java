package kr.co.m2m.example.demo.api.mypage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.example.demo.api.common.MailSenderUtil;
import kr.co.m2m.example.demo.api.mypage.mapper.MypageMapper;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalPO;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalSO;
import kr.co.m2m.example.demo.api.mypage.model.ApprovalVO;
import kr.co.m2m.example.demo.api.mypage.model.CodeSO;
import kr.co.m2m.example.demo.api.mypage.model.CodeVO;
import kr.co.m2m.example.demo.api.mypage.service.MypageService;
import kr.co.m2m.example.demo.common.AuthDetailHelper;
import kr.co.m2m.example.demo.exception.BEMessageException;
import kr.co.m2m.example.framework.auth.BEAuthDetailModel;
import kr.co.m2m.example.framework.util.MessageUtils;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @패키지명 : kr.co.m2m.example.demo.api.mypage.service.impl
 * @파일명 : MypageServiceImpl.java
 * @작성자 : ihKim
 * @생성일자 : 2020. 6. 18.
 * @설명 : 마이페이지 Service
 */

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageMapper mypageMapper;

	@Autowired
	private MailSenderUtil mailSenderUtil;

	/**
	 * @메소드명 : selectCodeList
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 공통코드 조회
	 * @param so
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<CodeVO> selectCodeList(CodeSO so) {

		ResultListModel<CodeVO> rv = new ResultListModel<>();

		// 코드 목록 조회
		rv.setResultList(this.mypageMapper.selectCodeList(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));

		return rv;
	}

	/**
	 * @메소드명 : selectApprovalRecList
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 결재수신함 목록
	 * @param so
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<ApprovalVO> selectApprovalRecList(ApprovalSO so) {

		ResultListModel<ApprovalVO> rv = new ResultListModel<>();
		BEAuthDetailModel vo = AuthDetailHelper.getAuthDetail();

		String name = vo.getName();
		String appNum = "";
		String manageYn = "N";

		// 대표이사
		if ("1".equals(vo.getJikgub())) {
			// 대표이사 순번
			appNum = this.mypageMapper.selectAppNum(name);

			if ("1".equals(appNum)) {
				so.setApp1(vo.getId());
			} else {
				so.setApp2(vo.getId());
			}
		} else if ("2".equals(vo.getJikgub())) {
			so.setApp3(vo.getId());
		} else {
			so.setAppId(vo.getId());

			// 경원지원팀 여부
			manageYn = this.mypageMapper.selectManageYn(name);
		}

		// 결재상태 전체일 경우 null로 변경
		if ("0".equals(so.getAppCode())) {
			so.setAppCode(null);
		}

		// 결재 목록 조회
		if ("N".equals(manageYn)) {
			rv.setResultList(this.mypageMapper.selectApprovalRecList(so));
		} else {
			if ("Y".equals(vo.getChiefYn())) {
				so.setChiefYn(vo.getChiefYn());
				rv.setResultList(this.mypageMapper.selectApprovalRecList(so));
			} else {
				rv.setResultList(this.mypageMapper.selectManageList(so));
			}
		}
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));

		return rv;
	}

	/**
	 * @메소드명 : selectApprovalRecDetail
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 결재수신함 상세
	 * @param so
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultModel<ApprovalVO> selectApprovalRecDetail(ApprovalSO so) {

		ResultModel<ApprovalVO> rv = new ResultModel<>();

		// 결재 상세 조회
		rv.setData(this.mypageMapper.selectApprovalRecDetail(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));

		return rv;
	}

	/**
	 * @메소드명 : updateApprovalRec
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 18.
	 * @설명 : 결재수신함 수정
	 * @param po
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public ResultModel<String> updateApprovalRec(ApprovalPO po) {

		ResultModel<String> rv = new ResultModel<>();
		BEAuthDetailModel vo = AuthDetailHelper.getAuthDetail();
		List<String> calList = null;

		String name = vo.getName();
		String appNum = "";
		String appStatus = "N";

		// 대표이사
		if ("1".equals(vo.getJikgub())) {
			// 대표이사 순번
			appNum = this.mypageMapper.selectAppNum(name);

			if ("1".equals(appNum)) {
				po.setApp1(vo.getId());
			} else {
				po.setApp2(vo.getId());
			}
		} else if ("2".equals(vo.getJikgub())) {
			po.setApp3(vo.getId());
		} else {
			po.setAppId(vo.getId());
		}

		try {
			// 결재자 상태 수정
			this.mypageMapper.updateApprovalRec(po);

			// 대표이사1,2 결재 상태
			appStatus = this.mypageMapper.selectAppStatus(po);

			// 결재건 상태 수정
			if ("Y".equals(appStatus) || "3".equals(po.getAppCode())) {
				this.mypageMapper.updateAppStatus(po);

				// 결재 완료
				if ("2".equals(po.getAppCode())) {
					po.setSDate(po.getSDate().replace(".", ""));
					po.setEDate(po.getEDate().replace(".", ""));

					// 캘린더 조회
					calList = this.mypageMapper.selectCalendar(po);

					if (calList != null && calList.size() > 0) {
						for (int i = 0; i < calList.size(); i++) {
							po.setVDate(calList.get(i));

							// 근태 등록
							this.mypageMapper.insertCalendar(po);
						}
					}
				} else if ("3".equals(po.getAppCode())) {
					// 반려 시 종결 처리
					this.mypageMapper.updateAppStatusEnd(po);
				}
			}

			List<CodeVO> codeList = null;
			CodeSO so = new CodeSO();
			String memberEmail = this.mypageMapper.selectMemberEmail(po);
			String cc = "";

			// 대표이사1,2,3 결재 시
			if ("1".equals(vo.getJikgub()) || "2".equals(vo.getJikgub())) {
				// 결재
				if ("Y".equals(appStatus) && "2".equals(po.getAppCode())) {
					// 상신자, 경영지원팀 메일 발송
					mailSenderUtil.mailSend(memberEmail, vo.getEmail(), po.getName(), po.getAppGubunNm(), po.getAppStatus(), po.getAppContent(), po.getSDate(),
							po.getEDate());

					so.setGrcode("A008");
					codeList = this.mypageMapper.selectCodeList(so);

					if (codeList != null && codeList.size() > 0) {
						for (int i = 0; i < codeList.size(); i++) {

							mailSenderUtil.mailSend(codeList.get(i).getBigo1(), memberEmail, po.getName(), po.getAppGubunNm(), po.getAppStatus(),
									po.getVReason(), po.getSDate(), po.getEDate());
						}
					}
				} else if ("3".equals(po.getAppCode())) {
					// 반려
					// 상신자 메일 발송
					mailSenderUtil.mailSend(memberEmail, vo.getEmail(), po.getName(), po.getAppGubunNm(), po.getAppStatus(), po.getAppContent(), po.getSDate(),
							po.getEDate());
				}
			} else {
				// 부서장 결재 시
				// 결재
				if ("2".equals(po.getAppCode())) {
					// 대표이사1,2 메일 발송
					so.setGrcode("A011");
					codeList = this.mypageMapper.selectCodeList(so);

					if (codeList != null && codeList.size() > 0) {
						for (int i = 0; i < codeList.size(); i++) {

							mailSenderUtil.mailSend(codeList.get(i).getBigo1(), memberEmail, po.getName(), po.getAppGubunNm(), po.getAppStatus(),
									po.getVReason(), po.getSDate(), po.getEDate());
						}
					}
				} else {
					// 반려
					// 상신자 메일 발송
					mailSenderUtil.mailSend(memberEmail, vo.getEmail(), po.getName(), po.getAppGubunNm(), po.getAppStatus(), po.getAppContent(), po.getSDate(),
							po.getEDate());
				}
			}

			rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));

		return rv;
	}

	/**
	 * @메소드명 : selectApprovalSenList
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 결재상신함 목록
	 * @param so
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultListModel<ApprovalVO> selectApprovalSenList(ApprovalSO so) {

		ResultListModel<ApprovalVO> rv = new ResultListModel<>();
		BEAuthDetailModel vo = AuthDetailHelper.getAuthDetail();

		so.setSubmitId(vo.getId());

		// 결재상태 전체일 경우 null로 변경
		if ("0".equals(so.getAppCode())) {
			so.setAppCode(null);
		}

		// 결재상신 목록 조회
		rv.setResultList(this.mypageMapper.selectApprovalSenList(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));

		return rv;
	}

	/**
	 * @메소드명 : selectApprovalSenDetail
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 19.
	 * @설명 : 결재상신함 상세
	 * @param so
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultModel<ApprovalVO> selectApprovalSenDetail(ApprovalSO so) {

		ResultModel<ApprovalVO> rv = new ResultModel<>();

		// 결재상신 상세 조회
		rv.setData(this.mypageMapper.selectApprovalSenDetail(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));

		return rv;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * @메소드명 : passwordChange
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 30.
	 * @설명 : 패스워드 변경
	 * @param po
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public ResultModel<String> passwordChange(ApprovalPO po) {

		ResultModel<String> rv = new ResultModel<>();
		BEAuthDetailModel vo = AuthDetailHelper.getAuthDetail();

		String pwd1 = passwordEncoder.encode(po.getPwd1());

		if (po.getId() == null || po.getId() == "") {
			po.setId(vo.getId());
		}
		po.setPwd1(pwd1);

		try {
			this.mypageMapper.updatePassword(po);

			rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));

		return rv;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ResultModel<ApprovalVO> selectTemplateDetail(ApprovalSO so) {

		ResultModel<ApprovalVO> rv = new ResultModel<>();

		// 템플릿 상세 조회
		rv.setData(this.mypageMapper.selectTemplateDetail(so));
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));

		return rv;
	}

	/**
	 * @메소드명 : templateUpdate
	 * @작성자 : ihKim
	 * @작성일 : 2020. 7. 6.
	 * @설명 : 템플릿 관리
	 * @param po
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public ResultModel<String> updateTemplate(ApprovalPO po) {

		ResultModel<String> rv = new ResultModel<>();

		try {
			this.mypageMapper.updateTemplate(po);

			rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));
		} catch (Exception e) {
			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
		}
		rv.setData(MessageUtils.getMessage("server.common.process.success"));

		return rv;
	}

	/**
	 * @메소드명 : memberList
	 * @작성자 : ihKim
	 * @작성일 : 2020. 7. 13.
	 * @설명 : 회원 목록
	 * @return
	 */
	@Override
	public ResultListModel<ApprovalVO> selectMemberList() {

		ResultListModel<ApprovalVO> rv = new ResultListModel<>();

		// 코드 목록 조회
		rv.setResultList(this.mypageMapper.selectMemberList());
		rv.setMessage(MessageUtils.getMessage("server.common.message.select.success"));

		return rv;
	}
}
