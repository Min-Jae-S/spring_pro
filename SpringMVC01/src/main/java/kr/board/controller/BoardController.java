package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;

@Controller
public class BoardController {

	// 스프링 내부에 존재하는 Handler Mapping에 의해 url과 메서드를 매핑시킨다.
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		Board board = new Board();
		board.setIdx(1);
		board.setTitle("게시판 실습");
		board.setContent("게시판 실습");
		board.setWriter("서민재");
		board.setIndate("2022-08-10");
		board.setCount(0);
		
		List<Board> list = new ArrayList<>();
		list.add(board);
		list.add(board);
		list.add(board);
		
		model.addAttribute("list", list);
		
		return "boardList"; // ViewResolver, /WEB-INF/view/boardList.jsp --> forward
	}
}
