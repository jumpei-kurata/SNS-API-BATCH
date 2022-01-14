package com.example.controller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

class UserControllerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		RestAssured.baseURI = "http://localhost:8080";
	}

	@Test
	void testLoginSuccess() {
		
		String requestBody = "{\n" +
	            "  \"email\": \"abab@rakus.co.jp\",\n" +
	            "  \"password\": \"aaaabbbb\" " + 
	            "\n}" ;
		
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.post("/login")
				.then()
				.extract().response();
		
		assertEquals(200, response.statusCode());
		assertEquals("ログインに成功しました", response.jsonPath().getString("message"));
		assertEquals("success", response.jsonPath().getString("status"));
		assertEquals("abab@rakus.co.jp", response.jsonPath().getString("user.email"));
		
	}

	

}
