package com.prosay.controller;

import mvc.annotation.Controller;
import mvc.annotation.RequestMapping;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-9-21 上午10:31:43
 */
@Controller
@RequestMapping("/index")
public class IndexController{

    @RequestMapping("/doSomething")
    public void doSomething(){
        System.out.println("I am  doSomething now ");
    }
    @RequestMapping("/index")
    public void index(){
        System.out.println("I am index Page");
    }
}
