package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * エラーに関するサービスです
 * 
 * @author ootomokenji
 *
 */
@Service
@Transactional
public class ErrorService {

	public List<String> errorMessage(BindingResult result) {
		
		List<ObjectError>errorList =  result.getAllErrors();
		List<String> errorMessageList = new ArrayList<>();
		
		for (ObjectError objectError : errorList) {
			errorMessageList.add(objectError.getDefaultMessage());
			}
		
		return errorMessageList;
	}
}
