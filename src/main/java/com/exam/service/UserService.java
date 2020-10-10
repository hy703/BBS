package com.exam.service;

import java.util.List;


import com.exam.vo.UserVO;

public interface UserService {

	// 회원가입
	public void SignUp(UserVO userVO) throws Exception;

	// 회원가입
	public UserVO login(UserVO userVO) throws Exception;

	//유저 목록 가져오기
	public List<UserVO> getUserlist() throws Exception;
}
