package com.exam.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exam.service.BoardService;
import com.exam.service.ReplyService;
import com.exam.vo.BoardVO;
import com.exam.vo.ReplyVO;
import com.exam.vo.UserVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	BoardService boardservice;

	@Autowired
	ReplyService replyservice;

	ArrayList<String> list = new ArrayList<String>();
	int i = 0;

	// 게시글 작성화면
	@RequestMapping("/writeView")
	public String writeView(HttpSession session) throws Exception {

		UserVO loginUser = (UserVO) session.getAttribute("check");
		if(loginUser == null) {
			
			return "redirect:/user/login";
		}
		else {

			return "board/writeView";
		}
	}

	// 게시글 작성
	@PostMapping("/write")
	public String write(BoardVO boardVO,Model model) throws Exception {

		int result = boardservice.titleChk(boardVO);

		if (result == 1) {
			System.out.println("중복된 게시글");

			return "redirect:/board/list";
		} else if (result == 0) {

			System.out.println("사용가능한 게시글");
			boardservice.write(boardVO);
			model.addAttribute("list", boardservice.list());
		}

		return "home";
	}

	// 게시글 목록 가져오기
	@GetMapping("/list")
	public String listView(Model model) throws Exception {

		model.addAttribute("list", boardservice.list());

		return "board/list";
	}

	// 게시글 조회
	@GetMapping("/readView")
	public String read(BoardVO boardVO, Model model, HttpSession session) throws Exception {

		UserVO loginUser = (UserVO) session.getAttribute("check");

		// 클릭한 게시글의 정보를 가져옴
		model.addAttribute("read", boardservice.read(boardVO.getBno()));

		// 댓글 조회
		List<ReplyVO> replyList = replyservice.readReply(boardVO.getBno());
		model.addAttribute("replyList", replyList);

		// 일반추천 누른 유저 리스트 <<클릭한 게시글의 글번호 넘겨줌 ->? 해당글에 대한 유저리스트만 출력하기 위해서>>
		model.addAttribute("reUserList", boardservice.reUserList(boardVO.getBno()));
		// 전문가추천 누른 유저 리스트 <<클릭한 게시글의 글번호 넘겨줌>>
		model.addAttribute("maUserList", boardservice.maUserList(boardVO.getBno()));

		return "board/readView";

	}

	// 게시글 수정뷰
	@GetMapping("/updateView")
	public String updateView(BoardVO boardVO, Model model) throws Exception {

		System.out.println("boardVO : " + boardVO);
		model.addAttribute("update", boardservice.read(boardVO.getBno()));

		return "board/updateView";

	}

	// 게시글 수정
	@PostMapping("/update")
	public String update(BoardVO boardVO,Model model) throws Exception {

		boardservice.update(boardVO);

		model.addAttribute("list", boardservice.list());
		return "home";

	}

	// 게시글 삭제
	@PostMapping("/delete")
	public String delete(BoardVO boardVO,Model model) throws Exception {

		// 게시글 삭제전 댓글삭제
		boardservice.deleteReply(boardVO.getBno());

		boardservice.delete(boardVO.getBno());

		model.addAttribute("list", boardservice.list());
		
		return "home";
	}

	// 댓글 작성
	@PostMapping("/replyWrite")
	public String replyWrite(ReplyVO replyvo, RedirectAttributes rttr) throws Exception {

		replyservice.writeReply(replyvo);
		rttr.addAttribute("bno", replyvo.getBno());

		return "redirect:/board/readView";
	}

	// 댓글수정 뷰 이동
	@GetMapping("replyUpdateView")
	public String replyUpdateView(ReplyVO replyvo, Model model) throws Exception {

		// 선택한 댓글의 댓글번호를 알아냄.
		model.addAttribute("replyUpdate", replyservice.selectReply(replyvo.getRno()));

		return "board/replyUpdateView";
	}

	// 댓글 수정 처리
	@PostMapping("/replyUpdate")
	public String replyUpdate(ReplyVO replyvo, RedirectAttributes rttr) throws Exception {

		replyservice.updateReply(replyvo);

		rttr.addAttribute("bno", replyvo.getBno());

		return "redirect:/board/readView";

	}

	// 댓글 삭제 처리
	@ResponseBody
	@PostMapping("/replyDelete")
	public void replyDelete(ReplyVO replyvo, int rno, RedirectAttributes rttr) throws Exception {
		System.out.println(rno);
		replyservice.deleteReply(rno);

		rttr.addAttribute("bno", replyvo.getBno());

	}

	// 게시글 중복체크
	@ResponseBody
	@PostMapping("/titleChk")
	public int idChk(BoardVO boardVO) throws Exception {
		int result = boardservice.titleChk(boardVO);
		return result;

	}

	// 일반회원 추천
	@GetMapping("/gerecommend")
	public String gerecommend(int bno, Model model, HttpSession session) throws Exception {

		UserVO loginUser = (UserVO) session.getAttribute("check");

		// 추천수 증가
		boardservice.gerecommend(bno);

		System.out.println("로그인한 유저의 아이디 : " + loginUser.getUser_id());
		System.out.println("db 체크 결과 : " + boardservice.recommendUserChk(bno, loginUser.getUser_id()));

		// recommend테이블에 동일한 회원과 bno 존재하면 1 없으면 0 없을때 insert해줌
		if (boardservice.recommendUserChk(bno, loginUser.getUser_id()) == 1) {
			System.out.println("현재 테이블에 데이터 존재");
			model.addAttribute("list", boardservice.list());

			return "board/list";
		} else {
			System.out.println("현재 테이블에 데이터 존재하지않음");
			// 추천한 유저 추천테이블에 입력
			boardservice.insertRecommendUser(bno, loginUser.getUser_id(), loginUser.getRule());
			model.addAttribute("list", boardservice.list());

			return "board/list";
		}
	}

	// 전문가회원 추천
	@GetMapping("/marecommend")
	public String marecommend(int bno, Model model, HttpSession session) throws Exception {

		UserVO loginUser = (UserVO) session.getAttribute("check");

		// 추천수 증가
		boardservice.marecommend(bno);

		// recommend테이블에 동일한 회원과 bno 존재하면 1 없으면 0 없을때 insert해줌
		if (boardservice.recommendUserChk(bno, loginUser.getUser_id()) == 1) {
			model.addAttribute("list", boardservice.list());

			return "board/list";
		} else {
			// 추천한 유저 추천테이블에 입력
			boardservice.insertRecommendUser(bno, loginUser.getUser_id(), loginUser.getRule());
			model.addAttribute("list", boardservice.list());

			return "board/list";
		}

	}

	@PostMapping("/RPI")
	@ResponseBody
	public void RPI(HttpServletRequest httpServletRequest) throws IOException {

		String test = httpServletRequest.getParameter("result");
		System.out.println("현재 온도 : " + test);

		list.add(test);
		System.out.println(list.toString());
		System.out.println(list.size());
		System.out.println("리스트의 값 " + list.get(i));
		i++;

		if (list.size() == 3) {
			System.out.println("현재사이즈" + list.size());
			

		
			OutputStream out = null;

			
			File file = new File("C:\\Users\\USER\\Desktop\\mytext.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			
			try {
				out = new FileOutputStream("C:\\Users\\USER\\Desktop\\mytext.txt");
				OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(list.toString());
				
				
				//oout.writeObject(list.toString());
				System.out.println("list.tostring : " + list.toString());
				//oout.close();
				bw.close();
				System.out.println(bufReader.readLine());
				bufReader.close();
				
			} catch (FileNotFoundException e) { // TODOAuto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
