package com.ruoyi.system.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮件实体类
 *
 * @author Chas
 */

public class SysEmail extends BaseEntity {
    /**邮件主键 */
    private Long emailId;

    /**邮件主机号*/
    private String emailHost;

    /**邮件端口号*/
    private Long emailPort;

    /**邮件发件人*/
   // @Value("${spring.mail.username}")
    private String emailFrom;

    /**邮件收件人*/
    private String[] emailTo;

    /**邮件主题*/
    private String emailSubject;

    /**邮件内容*/
    private String emailText;

    /**附件*/
    private String[] multipart;

    public SysEmail() {}

    public SysEmail(String emailTo, String emailSubject, String emailText, String multipart) {
        this.emailFrom="3436919768@qq.com";
        this.emailTo = emailTo.split(";");
        this.emailSubject = emailSubject;
        this.emailText = emailText;
        this.multipart = multipart.split(";");
    }

    public SysEmail(String emailHost, Long emailPort, String emailFrom, String emailTo, String emailSubject, String emailText, String multipart) {
        this.emailHost = emailHost;
        this.emailPort = emailPort;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo.split(";");
        this.emailSubject = emailSubject;
        this.emailText = emailText;
        this.multipart = multipart.split(";");
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public Long getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(Long emailPort) {
        this.emailPort = emailPort;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public String[] getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String[] emailTo) {
        this.emailTo = emailTo;
    }

    public String[] getMultipart() {
        return multipart;
    }

    public void setMultipart(String[] multipart) {
        this.multipart = multipart;
    }
}
