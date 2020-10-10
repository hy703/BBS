package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exam.mapper.replyMapper;
import com.exam.vo.ReplyVO;

@Repository
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	replyMapper replymapper;
	

	//댓글조회
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		
		return replymapper.readReply(bno);
	}


	//댓글 작성
	@Override
	public void writeReply(ReplyVO replyvo) throws Exception {


		replymapper.writeReply(replyvo);
		
	}


	//댓글 수정
	@Override
	public void updateReply(ReplyVO replyvo) throws Exception {
		
		replymapper.updateReply(replyvo);
		
	}

	//댓글 삭제
	@Override
	public void deleteReply(int rno) throws Exception {
		replymapper.deleteReply(rno);
		
	}


	//선택된 댓글 조회
	@Override
	public ReplyVO selectReply(int rno) throws Exception {
		
		return replymapper.selectReply(rno);
	}



}
