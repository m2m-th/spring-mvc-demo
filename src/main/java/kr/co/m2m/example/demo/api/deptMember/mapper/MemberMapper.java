package kr.co.m2m.example.demo.api.deptMember.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.example.demo.api.deptMember.model.MemberPO;
import kr.co.m2m.example.demo.api.deptMember.model.SCodeVO;

@Mapper
public interface MemberMapper {

	public int insertMember(MemberPO po);

	public String selectYear(MemberPO po);

	public int insertYearCnt(MemberPO po);

	public List<SCodeVO> selectDept(SCodeVO vo);

	public List<SCodeVO> selectDeptMemList(SCodeVO vo);

	public List<SCodeVO> selectJikgubList(SCodeVO vo);

	public int updateMember(SCodeVO vo);

	public List<MemberPO> memberIdList();

	public List<SCodeVO> checkChiefYn(SCodeVO vo);

	public List<MemberPO> checkId(MemberPO checkId);

	public int insertAcnt(MemberPO acnt);
}
