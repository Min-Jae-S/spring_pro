package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	
	// 만들어져있는 mapper를 주입받고 mapper를 통해 구현체의 구현메서드를 동작시킨다.
	@Autowired
	private BoardMapper mapper;

	// 스프링 내부에 존재하는 HandlerMapping이 갖고있는 매핑정보를 참고하여 
	// HandlerAdapter가 url과 메서드를 매핑시킨다.
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		List<Board> list = mapper.getLists();
		
		model.addAttribute("list", list);
		
		return "boardList"; // ViewResolver, /WEB-INF/view/boardList.jsp --> forward
	}
}
