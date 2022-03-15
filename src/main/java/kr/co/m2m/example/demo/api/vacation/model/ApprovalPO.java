package kr.co.m2m.example.demo.api.vacation.model;


import kr.co.m2m.example.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ApprovalPO extends BaseModel<ApprovalPO> {
	private String serial; // 결재serial
	private String submitId; // 결재상신ID
	private String submitDt; // 결재상신일
	private String appCode; // 결재코드(A005) -- 1:월차, 2:오전반차, 3:오후반차, 4:출장, 5:외근, 6:병가
	private String appStatus; // 결재상태(A009) -- 1:상신중, 2:결재완료, 3:반려, 4:임시저장
	private String app_1_code = "1"; // 결재 1 코드(A010) -- 1: 상신(기본), 2: 결재, 3: 반려
	private String app_2_code = "1"; // 결재 2 코드(A010) -- 1: 상신(기본), 2: 결재, 3: 반려
}
