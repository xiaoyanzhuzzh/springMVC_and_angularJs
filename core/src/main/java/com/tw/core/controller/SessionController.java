package com.tw.core.controller;

import com.tw.core.helper.EncryptionHelper;
import com.tw.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView getLoginPage() {

        return new ModelAndView("login");
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String name,
                              @RequestParam String password,
                              HttpServletRequest request){

        if(userService.verifyUserInfo(name, EncryptionHelper.md5(password))) {

            System.out.println(password);
            request.getSession().setAttribute("currentUser", name);
        }

        String url = request.getHeader("referer");

        if(url.equals("http://localhost:8080/web/login")) {

            url = "/employees/";
            return new ModelAndView("redirect:" + url);
        } else {

        return new ModelAndView("redirect:" + url);
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request){

        request.getSession().removeAttribute("currentUser");
        return new ModelAndView("redirect:/login");
    }
}
