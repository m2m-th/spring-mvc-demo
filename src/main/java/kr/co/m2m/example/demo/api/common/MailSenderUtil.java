package kr.co.m2m.example.demo.api.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MailSenderUtil {

	@Autowired
	private JavaMailSender mailSender;

	private final static String siteUrl = "http://192.168.0.91/";

	/**
	 * @메소드명 : mailSend
	 * @작성자 : ihKim
	 * @작성일 : 2020. 6. 30.
	 * @설명 :
	 * @param to        받는사람
	 * @param from      보내는사람
	 * @param name      상신자명
	 * @param appGubun  결재구분명
	 * @param appStatus 결재상태명
	 * @param msg       결재사유
	 * @param sdate     시작일자
	 * @param edate     종료일자
	 */
	public void mailSend(String to, String from, String name, String appGubun, String appStatus, String msg, String sdate, String edate) {

		SimpleMailMessage message = new SimpleMailMessage();
		StringBuffer sb = new StringBuffer();

		String subject = name + " 님의 " + appGubun + " 결재건이 " + appStatus + " 되었습니다.";

		sb.append("일자 : " + sdate + " ~ " + edate);
		sb.append("\n사유 : " + msg);
		sb.append("\n\n위의 사유로 인한 " + appGubun + " 결재건이 " + appStatus + " 되었습니다.");
		sb.append("\n사이트로 이동 후 확인하여 주시기 바랍니다. (" + siteUrl + ")");

		message.setTo(to);
		message.setFrom(from);
		message.setSubject(subject);
		message.setText(sb.toString());

		mailSender.send(message);
	}
}
