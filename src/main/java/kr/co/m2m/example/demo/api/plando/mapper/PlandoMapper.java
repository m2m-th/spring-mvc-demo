package kr.co.m2m.example.demo.api.plando.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.example.demo.api.plando.model.PlandoPO;
import kr.co.m2m.example.demo.api.plando.model.PlandoSO;
import kr.co.m2m.example.demo.api.plando.model.PlandoVO;
import kr.co.m2m.example.demo.api.plando.model.TestCommonVO;

@Mapper
public interface PlandoMapper {
	public List<PlandoVO> getLastWeekPlanCopy(PlandoSO so);
	
	public List<TestCommonVO> selectCode(String groupCode);
	
	public int savePlando(PlandoPO po);
	
	public List<PlandoVO> getDepartmentInfo(PlandoSO so);
	
	public List<PlandoVO> getPlandoList(PlandoSO so);
	
	public List<PlandoVO> getDoList(PlandoSO so);
	
	public List<PlandoVO> getPlanList(PlandoSO so);

	public List<PlandoVO> getDepartmentMember(PlandoSO so);
	
	public List<PlandoVO> getDepartmentMemberPlando(PlandoSO so);
	
	public int deletePlando(PlandoPO po);
	
	public PlandoVO getMyDepartmentCode(PlandoSO so);
	
	public List<PlandoVO> thisWeekPlando(PlandoSO so);
	
	public List<PlandoVO> nextWeekPlando(PlandoSO so);
}
