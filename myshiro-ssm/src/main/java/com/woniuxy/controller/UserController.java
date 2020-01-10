package com.woniuxy.controller;

import com.woniuxy.bean.RoleBean;
import com.woniuxy.pojo.User;
import com.woniuxy.service.RoleService;
import com.woniuxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("goList.html")
    public String goList(@RequestParam(name ="currentPage",defaultValue ="1") Integer currentPage, @RequestParam(name = "lineSize",defaultValue = "4") Integer lineSize, HttpServletRequest request){
        if(currentPage<1){
            currentPage=1;
        }
        int count = this.userService.getCount();
        int totalPages=this.userService.getTotalPage(count,lineSize);
        if (currentPage>totalPages){
            currentPage=totalPages;
        }
        List<User> userList = this.userService.getUserByPages(currentPage,lineSize);

        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("count",count);
        request.setAttribute("totalPages",totalPages);
        request.setAttribute("userList",userList);
        return "user_list";
    }

    @RequestMapping("goUserRoles.html")
    public String goUserRoles(Integer uid,HttpServletRequest request){
        List<RoleBean> allRoleBeans = roleService.getAllRoleBeans(uid);
        request.setAttribute("roleBeans",allRoleBeans);
        request.setAttribute("userId",uid);

        return "user_role";
    }

    @RequestMapping("assignRole.html")
    @ResponseBody
    public Map<String,String> assignRole(Integer userId,String roleArray){

        Map<String,String> mas = new HashMap<String, String>();
        boolean bl = this.roleService.assignRoleByUid(userId,roleArray);
        if(bl){
            mas.put("msg","success");
        }else {
            mas.put("msg","fail");
        }
        return mas;
    }
}
