package com.exam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.service.BoardService;
import com.exam.service.UserService;
import com.exam.vo.UserVO;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	UserService userservice;

	@Autowired
	BoardService boardservice;
	
	// 회원가입
	@GetMapping("/SignUp")
	public void SignUp() {
		System.out.println("게시글 작성화면");
	}

	// 회원가입 처리
	@PostMapping("/SignUp")
	public String SignUp2(UserVO userVO) throws Exception {
		System.out.println(userVO.getUser_id() + userVO.getUser_pass());
		userservice.SignUp(userVO);

		return "redirect:/user/login";
	}

	// 로그인 뷰 이동
	@GetMapping("/login")
	public void login() {
		System.out.println("로그인화면");
	}

	// 로그인 처리
	@PostMapping("/login")
	public String login2(UserVO userVO, HttpSession session,Model model) throws Exception {

		UserVO loginUser = userservice.login(userVO);

		if (loginUser != null) {

			System.out.println("로그인 성공");
			session.setAttribute("check", loginUser);
			model.addAttribute("list", boardservice.list());

			return "home";
		} else {

			System.out.println("로그인 실패");
			return "redirect:/user/login";
		}
	}
	
	//회원목록
	@GetMapping("/userlist")
	public String userlist(Model model) throws Exception {
		
		model.addAttribute("userlist", userservice.getUserlist());
		
		return "user/userlist";
	}
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model) throws Exception {
		session.invalidate();
		model.addAttribute("list", boardservice.list());
		return "home";
	}

}
