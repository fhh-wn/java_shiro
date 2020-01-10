package com.woniuxy.realm;

import com.woniuxy.pojo.Menu;
import com.woniuxy.service.MenuSerice;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {
   @Autowired
    private MenuSerice menuSerice;
    @Override
    public void setFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection("");
        }

        System.out.println("*******************从数据库中查询***************");
        List<Menu> all= menuSerice.getAllMenus();
        if(all!=null&&!all.isEmpty()){
            for(Menu menu:all){
                String url=menu.getUrl();
                if(url!=null){
                    if(url.indexOf("/")!=-1){
                        url=url.substring(0,url.lastIndexOf("/"));
                        section.put("/"+url+"/**","perms["+url+":*]");
                    }
                }
            }
        }
        //下面这段程序主要是向控制台打印
        System.out.println("***********************************************");
        Set<Map.Entry<String,String>> es=section.entrySet();
        for(Map.Entry<String,String> entry:es){
            System.out.println(entry.getKey()+"----------->"+entry.getValue());
        }
        System.out.println("***********************************************");
        this.setFilterChainDefinitionMap(section);

    }
}
