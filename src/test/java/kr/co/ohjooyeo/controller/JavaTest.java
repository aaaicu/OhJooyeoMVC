package kr.co.ohjooyeo.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.ohjooyeo.service.OrderService;

public class JavaTest {

	@Autowired
	OrderService orderService;
	
	@Test
	public void testJava() {
		Map<String,String> map = new HashMap<>();
		map.put("churchId","1");
		map.put("worshipId","19-001");
		orderService.deleteWorshipOrder(map);
	}
	
}
