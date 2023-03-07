package com.ruoyi.common.exception.home;

import com.ruoyi.common.exception.base.BaseException;

public class HomeAdException extends HomeException {
    private static final long serialVersionUID = 1L;

    public HomeAdException(){
        super("home.configAdNum.not.true",null);
    }

}
