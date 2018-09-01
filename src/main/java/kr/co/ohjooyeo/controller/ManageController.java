package kr.co.ohjooyeo.controller;

import kr.co.ohjooyeo.vo.UserVO;

public interface ManageController {
	public void addUser(UserVO userVo);

	public void updateUser(UserVO userVo);

	public void deleteUser(String userId);

	// 업데이트 요청시 해당 유저의 data가 없으면 추가,존재하면 기존데이터 수정 후 추가
	public void updateLaunch_phrase(String phrase);

	// 삭제 요청시 해당 유저의 data가 있으면 end_date 수정으로 삭제
	public void deleteLaunch_phrase();
}
