
package kr.co.m2m.example.demo.api.vacation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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

@Mapper
public interface VacationMapper {
	public List<GuntaePO> selectCalendarElement(GuntaeSO so);

	public List<GuntaePO> selectCalendarYear();

	public List<ScodeVO> selectScodeA005(String grCode);

	public String checkDuplVacation(VacationVO vo);

	public List<String> selectCalendar(VacationVO vo);

	public int checkDuplGuntae(VacationVO vo);

	public int insertTmpSave(VacationVO vo);

	public void updateTmpSaveReason(VacationVO vo);

	public int checkDuplApprove(String serial);

	public String selectAppStatus(String appStatus);

	public Integer reqApprove(ApprovalPO po);

	public int updateAppStatus(ApprovalPO po);

	public List<CalendarPO> selectHoliday();

	public List<GuntaePO> selectAllMembersDayOffList(GuntaeSO so);

	public List<YearcntPO> selectGuntaeYear();

	public GuntaePO checkMinusDayCapa(VacationVO vo);

	public ApprovalVO selectApprovalEmail(String serial);

	public ScodeVO selectScodeNmA005(String vcode);

	public List<ScodeVO> selectAllDept();

	public List<MemberPO> selectWorkerForDept(MemberSO so);

	public List<MemberPO> selectWorkerForYear(MemberSO so);

	public int forceInsertGuntae(VacationVO vo);

	public int forceDelGuntae(GuntaeSO so);

	public List<GuntaePO> selectCalendarElement_ForceDel(GuntaeSO so);

	public GuntaePO selectMembersDayOffList(GuntaeSO so);

	public List<GuntaePO> selectUsersOffDay(GuntaeSO so);

	public GuntaeVO selectMembersDayOffCount(GuntaeSO so);

	public GuntaeVO selectMembersALLDayOff(GuntaeSO so);

	public List<GuntaeVO> selectUsersOffDay_KHT(GuntaeSO so);

	public List<GuntaeVO> userList(GuntaeSO searchModel);

	public List<GuntaeVO> lookupVacationList(GuntaeSO searchModel);

	public float useVacation(GuntaeSO searchModel);

	public List<GuntaeVO> usedVacationTable(GuntaeSO searchModel);

	public List<GuntaePO> selectDeptMonth(GuntaeSO so);

}
