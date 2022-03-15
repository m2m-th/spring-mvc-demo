package kr.co.m2m.example.demo.api.vacation.service;

import java.util.List;

import kr.co.m2m.example.demo.api.vacation.model.CalendarPO;
import kr.co.m2m.example.demo.api.vacation.model.GuntaePO;
import kr.co.m2m.example.demo.api.vacation.model.GuntaeSO;
import kr.co.m2m.example.demo.api.vacation.model.GuntaeVO;
import kr.co.m2m.example.demo.api.vacation.model.MemberPO;
import kr.co.m2m.example.demo.api.vacation.model.MemberSO;
import kr.co.m2m.example.demo.api.vacation.model.ScodeVO;
import kr.co.m2m.example.demo.api.vacation.model.VacationVO;
import kr.co.m2m.example.demo.api.vacation.model.YearcntPO;
import kr.co.m2m.example.framework.web.model.ResultListModel;
import kr.co.m2m.example.framework.web.model.ResultModel;

public interface VacationService {

	public List<GuntaePO> selectCalendarElement(GuntaeSO so);

	public List<GuntaePO> selectCalendarElement_ForceDel(GuntaeSO so);

	public List<GuntaePO> selectCalendarYear();

	public ResultListModel<ScodeVO> selectScodeA005(String grCode);

	public ResultListModel<ScodeVO> selectAllDept();

	public ResultListModel<MemberPO> selectWorkerForDept(MemberSO so);

	public ResultListModel<MemberPO> selectWorkerForYear(MemberSO so);

	public ResultModel<String> insertForceApprove(VacationVO vo);

	public ResultModel<String> forceDelGuntae(GuntaeSO so);

	public ResultModel<String> insertTmpSave(VacationVO vo);

	public ResultModel<String> reqApprove(VacationVO vo);

	public ResultListModel<CalendarPO> selectHoliday();

	public ResultListModel<GuntaePO> selectAllMembersDayOffList(GuntaeSO so);

	public ResultListModel<YearcntPO> selectGuntaeYear();

	public GuntaePO selectMembersDayOffList(GuntaeSO so);

	public ResultListModel<GuntaePO> selectUsersOffDay(GuntaeSO so);

	public GuntaeVO selectMembersDayOffCount(GuntaeSO so);

	public GuntaeVO selectMembersALLDayOff(GuntaeSO so);

	public List<GuntaeVO> userList(GuntaeSO searchModel);

	public List<GuntaeVO> lookupVacationList(GuntaeSO searchModel);

	public float useVacation(GuntaeSO searchModel);

	public List<GuntaeVO> usedVacationTable(GuntaeSO searchModel);

	public ResultListModel<GuntaePO> selectDeptMonth(GuntaeSO so);

}
