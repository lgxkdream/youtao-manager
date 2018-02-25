package com.youtao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gang.li
 * @version 1.0.0
 * @title: PageController
 * @description: 通用页面跳转
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @since 2018年2月25日 上午10:49:29
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("{pageName}")
    public String toPage(@PathVariable("pageName") String pageName) {
        return pageName;
    }

}
