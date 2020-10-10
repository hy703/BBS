package com.exam.service;

import java.util.List;

import com.exam.vo.ReplyVO;

public interface ReplyService {

	// 댓글조회
	public List<ReplyVO> readReply(int bno) throws Exception;

	// 댓글 작성
	public void writeReply(ReplyVO replyvo) throws Exception;

	// 댓글 수정
	public void updateReply(ReplyVO replyvo) throws Exception;

	// 댓글 삭제
	public void deleteReply(int rno) throws Exception;

	// 선택한 댓글조회
	public ReplyVO selectReply(int rno) throws Exception;

}
