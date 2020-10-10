package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.exam.mapper.boardMapper;
import com.exam.vo.BoardVO;
import com.exam.vo.UserVO;

@Repository
public class BoardServiceImpl implements BoardService {

	@Autowired
	boardMapper boardmapper;

	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		boardmapper.write(boardVO);

	}

	// 게시글 목록 가져오기
	@Override
	public List<BoardVO> list() throws Exception {

		return boardmapper.list();
	}

	// 게시글 상세 조회
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(int bno) throws Exception {

		// 조회수
		boardmapper.boardHit(bno);

		return boardmapper.read(bno);
	}

	// 게시글 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {

		boardmapper.update(boardVO);

	}

	// 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {

		boardmapper.delete(bno);

	}

	// 댓글 전체삭제
	@Override
	public void deleteReply(int bno) throws Exception {
		boardmapper.deleteReply(bno);

	}

	// 게시글 중복체크
	@Override
	public int titleChk(BoardVO boardVO) throws Exception {

		return boardmapper.titleChk(boardVO);
	}

	// 일반회원 추천
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void gerecommend(int bno) throws Exception {

		boardmapper.gerecommend(bno);

	}

	// 전문가회원 추천
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void marecommend(int bno) throws Exception {
		boardmapper.marecommend(bno);

	}

	// 추천테이블에 추천한 회원데이터 입력
	@Override
	public void insertRecommendUser(int bno, String user_id, int rule) throws Exception {

		boardmapper.insertRecommendUser(bno, user_id, rule);

	}

	// 추천테이블에 해당 유저와 게시글번호 가 있는지 확인 (데이터 중복 방지)
	@Override
	public int recommendUserChk(int bno, String user_id) throws Exception {

		return boardmapper.recommendUserChk(bno, user_id);
	}

	// 일반추천 누른 유저 리스트
	@Override
	public List<UserVO> reUserList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return boardmapper.reUserList(bno);
	}

	// 전문가추천 누른 유저 리스트
	@Override
	public List<UserVO> maUserList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return boardmapper.maUserList(bno);
	}

}
