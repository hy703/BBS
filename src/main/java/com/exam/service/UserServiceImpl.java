package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exam.mapper.UserMapper;
import com.exam.vo.UserVO;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public void SignUp(UserVO userVO) throws Exception {
		
		mapper.SignUp(userVO);
	}

	@Override
	public UserVO login(UserVO userVO) throws Exception {
		
		return mapper.login(userVO);
	}

	@Override
	public List<UserVO> getUserlist() throws Exception {
		// TODO Auto-generated method stub
		return mapper.getUserlist();
	}
	
	


}
