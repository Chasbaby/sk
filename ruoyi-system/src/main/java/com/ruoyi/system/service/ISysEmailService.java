package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysEmail;

public interface ISysEmailService {
    public void sendSimpleEmail(SysEmail email);
    public void sendComplexEmail(SysEmail email);

}
