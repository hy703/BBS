package com.exam.service;

import java.util.List;

import com.exam.vo.BoardVO;
import com.exam.vo.UserVO;

public interface BoardService {

	//게시글 작성
	public void write(BoardVO boardVO) throws Exception;
	
	//게시글 목록 가져오기
	public List<BoardVO> list() throws Exception;
	
	//게시글 상세 조회
	public BoardVO read(int bno) throws Exception;
	
	//게시글 수정
	public void update(BoardVO boardVO) throws Exception;
	
	//게시글 삭제
	public void delete(int bno) throws Exception;
	
	//게시글 댓글 전체 삭제
	public void deleteReply(int bno) throws Exception;
	
	//게시글 중복체크
	public int titleChk(BoardVO boardVO) throws Exception;
	
	//일반회원 추천
	public void gerecommend(int bno) throws Exception;
	
	//전문가회원 추천
	public void marecommend(int bno) throws Exception;
	
	
	//추천테이블에 추천한 회원데이터 입력
	public void insertRecommendUser(int bno,String user_id,int rule) throws Exception;

	//추천테이블에 회원이있는지 체크
	public int recommendUserChk(int bno,String user_id) throws Exception;

	//일반추천 누른 유저 리스트
	public List<UserVO> reUserList(int bno) throws Exception;
	
	//전문가추천 누른 유저 리스트
	public List<UserVO> maUserList(int bno) throws Exception;
	
}
