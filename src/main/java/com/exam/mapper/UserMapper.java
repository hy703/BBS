package com.exam.mapper;



import java.util.List;

import com.exam.vo.UserVO;

public interface UserMapper {
	
	// 회원가입
	public void SignUp(UserVO userVO);


	//로그인
	public UserVO login(UserVO userVO);
	
	//유저 목록 불러오기
	public List<UserVO> getUserlist();
}
