package kr.co.m2m.example.demo.api.commonCode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.example.demo.api.commonCode.model.CommonCodePO;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodeSO;
import kr.co.m2m.example.demo.api.commonCode.model.CommonCodeVO;
import kr.co.m2m.example.demo.api.deptMember.model.SCodeVO;

@Mapper
public interface CommonCodeMapper {

	public List<CommonCodeVO> selectCommonCodeList(CommonCodeSO so);

	public int insertCommonCode(CommonCodePO po);

	public List<CommonCodeVO> searchCommonCode(CommonCodeSO so);

	public int updateCommonCode(CommonCodePO so);

	public int updateSCode(SCodeVO vo);

	public int insertSCode(SCodeVO so);

}
