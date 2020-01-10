package com.woniuxy.controller;

import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.MenuSerice;
import com.woniuxy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {
    @Autowired
    private MenuSerice menuSerice;
    @Autowired
    private RoleService roleService;
    @RequestMapping("goList.html")
    public String goList(@RequestParam(name ="currentPage",defaultValue ="1") Integer currentPage, @RequestParam(name = "lineSize",defaultValue = "4") Integer lineSize, HttpServletRequest request){
        if(currentPage<1){
            currentPage=1;
        }
        int count = this.roleService.getCount();
        int totalPages=this.roleService.getTotalPage(count,lineSize);
        if (currentPage>totalPages){
            currentPage=totalPages;
        }
        List<Role> userList = this.roleService.getUserByPages(currentPage,lineSize);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("count",count);
        request.setAttribute("totalPages",totalPages);
        request.setAttribute("roleList",userList);
        return "role_list";
    }
    @RequestMapping("goRolePermission.html")
    public String toAssignPerssionUi(Integer rid,HttpServletRequest request){

        request.setAttribute("roleId",rid);

        return "role_power";
    }


    @RequestMapping("upCheckMenu.html")
    @ResponseBody
    public List<Menu> indexMenu(){
        List<Menu> ms = menuSerice.getAllMenus();

        return ms;
    }
}
