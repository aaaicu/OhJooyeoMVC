package kr.co.ohjooyeo.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.UserDAO;
import kr.co.ohjooyeo.vo.UserVO;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	public boolean loginCheck(Map<String, String> loginMap) {
		String securityPass = userDao.getSecurityPass(loginMap);
		boolean result = passEncoder.matches(loginMap.get("pw"), securityPass);
		logger.debug(result+"");
		
		
		return result;
	}
	
	public boolean regLoginInfo(Map<String, String> regLoginMap) {
		UserVO regUser = new UserVO();
		regUser.setUserId(regLoginMap.get("id"));
		regUser.setPassword(passEncoder.encode(regLoginMap.get("pw")));
		regUser.setChurchId(regLoginMap.get("churchId"));
		regUser.setUserCd(regLoginMap.get("userCd"));
		int result = userDao.setLoginInfo(regUser);
		logger.debug(Integer.toString(result));
		if (result > 0) {
			return true;
		}else {
			return false;
		}
	}

}
