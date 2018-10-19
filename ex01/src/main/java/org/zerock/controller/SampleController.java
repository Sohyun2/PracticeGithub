package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller // SampleController가 Controller인 것을 알려주는 어노테이션
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	@RequestMapping(value="", method=RequestMethod.GET) //반드시 GET방식 일 때만 사용가능한 메서드
	public void basic() {
		log.info("basic......................");
	}
	
	@GetMapping("/ex01") //method = get방식
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		
		return "ex02"; // 현재는 view페이지가 없으므로 404 에러 뜨는게 6정상...
	}
}
