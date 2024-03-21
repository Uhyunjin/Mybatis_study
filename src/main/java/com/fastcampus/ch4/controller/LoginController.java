package com.fastcampus.ch4.controller;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastcampus.ch4.dao.*;
import com.fastcampus.ch4.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

  @Autowired
  UserDao userDao;

  @GetMapping("/login")
  public String loginForm() {
    return "loginForm";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

  @PostMapping("/login")
  public String login(String id, String pwd, String toURL, boolean rememberId,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {

    if(!loginCheck(id, pwd)){
      String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "uft-8");
      return "redirect:/login/login?msg="+msg;
    }
    // 로그인 체크로 id와 pwd가 일치하는지 체크

    // 로그인 체크를 통과하면 세션 객체에 id저장하기
    HttpSession session = request.getSession();
    session.setAttribute("id", id);

    //쿠키에 아이디 저장하기(체크박스)
    if(rememberId) {
      // 체크박스에 체크가 되어있다면
      Cookie cookie = new Cookie("id", id);
      // 쿠키 객체를 생성해서 id 담기
      response.addCookie(cookie); // 생성한 쿠키 객체를 응답에 담아준다
    } else {
      // id기억 체크박스에 체크가 되어있지 않으면
      Cookie cookie = new Cookie("id", id);
      cookie.setMaxAge(0); // 시간을 0으로 설정하면 쿠키가 없어지는 것
      response.addCookie(cookie); //0으로 설정한 쿠키를 응담에 담아준다
    }

    //로그인 전의 화면으로 연결
    toURL = toURL == null || toURL.equals("") ? "/" : toURL;
    // toURL -> login form에서 hidden으로 받아옴
    return "redirect:"+toURL;
  }

  private boolean loginCheck(String id, String pwd){
    User user = null;
    try{
      user = userDao.selectUser(id);
    } catch (Exception e){
      e.printStackTrace();
      return false;
    }

    return user !=null && user.getPwd().equals(pwd);
  }
}
