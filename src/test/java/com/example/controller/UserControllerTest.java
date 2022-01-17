package com.example.controller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

class UserControllerTest {

	//DBの中身がおそらく違うので、変更しないとオールグリーンにはならないと思います
	
	
	//共通URLを設定するところ
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		RestAssured.baseURI = "http://localhost:8080";
	}

	//POSTの場合の例として　ログイン機能の成功例
	@Test
	void testLoginSuccess() {
		
		String requestBody = "{\n" +
	            "  \"email\": \"cyjo@rakus.co.jp\",\n" +
	            "  \"password\": \"jojo\" " + 
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
		assertEquals("cyjo@rakus.co.jp", response.jsonPath().getString("user.email"));
		
	}

	//GETの場合の例として
	@Test
	void testFindByIdSuccess() {
		Response response = given()
				.contentType(ContentType.JSON)
				.when()
				.get("/user/1")
				.then()
				.extract().response();
		
		assertEquals(200,response.statusCode());
		assertEquals("masayuki",response.jsonPath().getString("name"));
		assertEquals("cyjo@rakus.co.jp", response.jsonPath().getString("email"));
		
	}

}
