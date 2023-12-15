package com.ruoyi.common.exception;


/**
 * 用户手机号不正确或不符合规范异常类
 * 
 * @author ruoyi
 */
public class PhoneNotMatchException extends Exception
{
    private static final long serialVersionUID = 1L;

    public PhoneNotMatchException()
    {
        super("phone.not.match", null);
    }
}
