package com.example.demo;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    JavaMailSender javaMailSender;

    private final  String ACCOUNT_SID="ACb3f857b15360bd8c414bfca666ea25df";
    private final  String AUTH_ID="849779a30d7c227bcd68c8371e0cfb01";

    @GetMapping(value="/findAll")
    public  @ResponseBody  List<Student> findAll(){
        List<Student> list=new ArrayList<Student>();
        studentRepo.save(new Student(101, "Akshay"));
        studentRepo.save(new Student(102, "Suraj"));
       return studentRepo.findAll();
    }

    @GetMapping(value="/findByName")
    public @ResponseBody  List<Student> findByName(@RequestParam("name") String name){
       return studentRepo.findByName(name);
    }

    @PostMapping("/sms")
    public void sms() throws Exception{
        Twilio.init(ACCOUNT_SID,AUTH_ID);
        Message.creator(new PhoneNumber("+917972282735"),
                new PhoneNumber("+16787524315"),
                "Message from Suraj Kakade's SpringBoot twilio Application").create();
    }

    @PostMapping("/call")
    public void call() throws Exception{
        Twilio.init(ACCOUNT_SID, AUTH_ID);
        Call.creator(new PhoneNumber("+917972282735"),
                new PhoneNumber("+16787524315"),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();
    }

    @PostMapping("/mail")
    public String mail(){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        simpleMailMessage.setTo("suruk3396@gmail.com");
        simpleMailMessage.setSubject("FIRST SPRING BOOT APP mail");
        simpleMailMessage.setText("Suraj Kakade has sent you this mail as a part of testing");

        javaMailSender.send(simpleMailMessage);

        return "Mail sent successfully";
    }

    @GetMapping("/token")
    public @ResponseBody Long token(){
        String pan= "4504450412341234";
        Long token=Long.parseLong(pan);
        for(int i=0;i<8;i++) {
          token = token / 10;
      }

        return token;
    }


} //class
