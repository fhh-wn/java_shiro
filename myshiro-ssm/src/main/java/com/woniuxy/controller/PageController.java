package com.woniuxy.controller;

import com.woniuxy.pojo.Menu;
import com.woniuxy.service.MenuSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class PageController {
    @Autowired
    private MenuSerice menuSerice;
    @RequestMapping("index.html")
    public String index(){
        return "index";
    }
    @RequestMapping("error.html")
    public String error(){
        return "error";
    }

    @RequestMapping("leftMenu.html")
    @ResponseBody
    public List<Menu> indexMenu(){
        List<Menu> ms = menuSerice.getAllMenus();

        return ms;
    }


}
