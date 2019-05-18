package kr.co.ohjooyeo.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.UserDAO;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserDAO userDao;
	
	public boolean loginCheck(Map<String, String> loginMap) {
		boolean result = userDao.loginCheck(loginMap);
		logger.debug(result+"");
		
		return result;
	}

}
