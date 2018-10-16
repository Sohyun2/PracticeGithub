package com.javalec.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.board.command.BCommand;
import com.javalec.board.command.BContentCommand;
import com.javalec.board.command.BDeleteCommand;
import com.javalec.board.command.BListCommand;
import com.javalec.board.command.BModifyCommand;
import com.javalec.board.command.BReplyCommand;
import com.javalec.board.command.BReplyViewCommand;
import com.javalec.board.command.BWriteCommand;

@Controller
public class BController {
	
	BCommand command;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		
		command = new BListCommand();
		command.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/write_view") //작성화면
	public String write_view(Model model) {
		System.out.println("write()");
		// 단순한 작성 화면이므로 데이터베이스를 끌어오거나 할 필요가 없으므로..
		// 바로 뷰페이지로 넘겨준다.
		return "write";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) { 
		//HttpServletRequest 사용 이유 : write_view에서 작성한 글을 받아야 하므로..
		System.out.println("write()");
		
		model.addAttribute("request", request); // 이것의 하는 역할은 무엇...?
		command = new BWriteCommand();
		command.execute(model);
		
		// 작성완료 후 리스트 페이지가 보이게..
		// controller에서 바로 list페이지로 가게 하는법
		// redirect사용..
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model); // 보고싶은 컨텐츠의 데이터 DAO작업이 실행되는 곳.
		
		return "content_view";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		return "modify";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		//답변 보는 곳
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
}
