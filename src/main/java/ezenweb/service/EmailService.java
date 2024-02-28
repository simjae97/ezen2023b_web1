package ezenweb.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    //SMTP : 간이 우편 전송 프로토콜 (메일 전송)
        // 자바에서 메일 보내기 (네이버계정+2차보안없는 계정만)
        //1.SMTP 라이브러리 필요
        //2.그래들에 빌드
        // 3.이메일 전송
            //1. 이메일 내용을 구성 -> 구성 포멧: MIME (SMTP 사용할때 사용되는 포맷)
        // 4. 보내는 사람의 이메일 인증
            //application properties
    @Autowired
    private JavaMailSender javaMailSender; // 다른함수에서도 사용하기위해 필드로
    public void send(){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            //1.메세지 구성
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true,"utf-8");
            //2.메세지 보내는사람
            mimeMessageHelper.setFrom("tlawo33@naver.com"); //관리자 이메일
            //3.메세지 받는 사람
            mimeMessageHelper.setTo("tlawo2232@gmail.com"); //클라이언트 이메일(매개변수
            //4.메일 제목
            mimeMessageHelper.setSubject("자바에서 보내온 메시지"); //제목(매개변수)
            //5.메일 내용
            mimeMessageHelper.setText("안녕하세요 내용입니다"); //내용(매개변수)
            javaMailSender.send(message);
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
    }
}
