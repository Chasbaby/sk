package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.system.domain.SysEmail;
import com.ruoyi.system.service.ISysEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.File;

@Service
public class SysEmailServiceImpl implements ISysEmailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Override
    public void sendSimpleEmail(SysEmail email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getEmailSubject());
        message.setText(email.getEmailText());
        mailSender.send(message);
    }
    public void sendComplexEmail(SysEmail email){
        //定制复杂邮件信息MimeMessage
        MimeMessage message = mailSender.createMimeMessage();
        // 使用帮助类，并设置multipart 多部件使用为 true
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true, Constants.UTF8);
            helper.setFrom(email.getEmailFrom());
           // helper.setSentDate(date);
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getEmailSubject());
            helper.setText(email.getEmailText(),true);
            //设置邮件静态资源
//          //FileSystemResource resource = new FileSystemResource(new File(rscPath));
//          //helper.addInline(rscId,resource);
            //设置附件地址
            for (int i = 0; i <email.getMultipart().length ; i++) {
                FileSystemResource fileSystemResource = new FileSystemResource(new File(email.getMultipart()[i]));
                String fileName = email.getMultipart()[i].substring(email.getMultipart()[0].lastIndexOf(File.separator));
                helper.addAttachment(fileName,fileSystemResource);
            }
            //发送邮件
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
