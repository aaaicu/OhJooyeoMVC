package kr.co.ohjooyeo.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainControllerTest {

	@Test
	public void testLogin() {
		MainController m = new MainController();
		assertEquals("login",m.login());
		System.out.println("test");
	}

	@Test
	public void testLoginCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testSignin() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoginInfo() {
		fail("Not yet implemented");
	}

}
