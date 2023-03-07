package com.ruoyi.common.exception.home;

import com.ruoyi.common.exception.base.BaseException;

public class HomeException extends BaseException {
    private static final long serialVersionUID = 1L;

    public HomeException(String code, Object[] args){
        super("home",code,args,null);
    }
}
