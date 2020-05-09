package org.service;

import org.graalvm.compiler.code.DataSection;
import org.mapper.MailSendLogMapper;
import org.model.MailSendLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MailSendLogService {
@Autowired
    MailSendLogMapper mailSendLogMapper;
public  Integer updateMailSendLogStatus(String msgId,Integer status){
    return mailSendLogMapper.updateMailSendLogStatus(msgId, status);
}
  public Integer insert(MailSendLog mailSendLog){
    return mailSendLogMapper.insert(mailSendLog);
  }
public List<MailSendLog> getMailSendLogByStatus(){
    return mailSendLogMapper.getMailSendLogsByStatus();
}
 public Integer updateCount(String msgId, Date date){
    return mailSendLogMapper.updateCount(msgId,date);
 }
}
