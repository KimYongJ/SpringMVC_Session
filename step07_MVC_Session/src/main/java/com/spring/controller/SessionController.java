package com.spring.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import model.domain.Customer;

@SessionAttributes({"id","age","job","customer"})//어노테이션을 추가해야 job을 @ModelAttribute라는 어노테이션으로 받을 수 있다.
@Controller
public class SessionController {

	// 쿠키와 관련된 내용
	@RequestMapping("cookieTest.do")
	public String cookieTest(@CookieValue("id") String idValue) {
		return "redirect:/cookieView.jsp";
	}
	// 세션과 관련된 내용
	@GetMapping("session/sessionTest1.do")
	public String sessionTest1(HttpSession session) {
		System.out.println(session.getAttribute("id"));
		System.out.println(session.getAttribute("age"));
		session.setAttribute("job", "programmer");
		return "redirect:/sessionView1.jsp"; 
	}
	// job 세션 데이터 삭제 
	@GetMapping("session/jobDelete.do")
	public String jobDelete(@ModelAttribute("job") String job,SessionStatus status) {
		System.out.println("job : "+job);
		status.setComplete();//세션 무효화
		return "redirect:/sessionView1.jsp";
	}
	// id, age 세션 데이터 삭제 [ 하려 했으나 setComplete함수가 모두를 삭제 해버림 ] 
	@GetMapping("session/sessionDelete.do")
	public String sessionDelete(SessionStatus status) {
		status.setComplete();//세션 무효화
		return "redirect:/sessionView1.jsp";
	}
	// DTO 객체를 세션에 저장
	@GetMapping("session/sessionTest2.do")
	public String sessionTest2DTO(Model model,Customer customer) {
		model.addAttribute("customer",customer);
		return "redirect:/sessionView2.jsp";
	}
	// customer객체 삭제 
	@GetMapping("session/customerDelete.do")
	public String customerDelete(SessionStatus status) {
		status.setComplete();//세션 무효화
		return "redirect:/sessionView2.jsp";
	}
	// 로그인으로 이동하는 함수
	@GetMapping("session/loginForm.do")
	public String loginForm() {
		return "loginForm";
	}
	// 로그인 데이터 입력 받은 후 처리하는 함수
	@RequestMapping("session/login.do")
	public String login(HttpServletRequest request, HttpSession session) {
		// 조건 : 비번 1234이면 admin 승인 , 아니면 index.jsp 화면 전환
		String pw=request.getParameter("password");
		if("1234".equals(pw)) {
			session.setAttribute("isAdmin", "true");
			return "redirect:/index.jsp";
		}else {
			return "loginForm";
		}
	}
	// 로그 아웃 클릭시 이동됨, 해당 세션 해제 후 index.jsp로 화면 전환해줌
	@RequestMapping("session/logout.do")
	public String logout(SessionStatus status, HttpSession session) {
		System.out.println(session.getAttribute("isAdmin"));
		//status.setComplete();//세션 무효화
		session.removeAttribute("isAdmin");
		return "redirect:/index.jsp";
	}
}
