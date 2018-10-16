package com.javalec.board.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javalec.board.dao.BDao;
import com.javalec.board.dto.BDto;

public class BListCommand implements BCommand{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		// 역할 : 데이터베이스에 있는 데이터를 가져와서 뿌려주는 역할을 한다.
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
	}
}
