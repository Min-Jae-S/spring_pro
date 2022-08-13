package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		return "boardList"; // /WEB-INF/view/boardList.jsp --> forward
	}
	
	@GetMapping("/boardForm.do")
	public String boardForm() {
		
		return "boardForm"; // /WEB-INF/view/boardForm.jsp --> forward
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board board) { // 파라미터 수집(Board) - title, content, writer
		mapper.boardInsert(board);
		
		return "redirect:/boardList.do"; // redirect
	}
	
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) {
		Board board = mapper.boardContent(idx);
		model.addAttribute("board", board);

		mapper.boardCount(idx);
		
		return "boardContent";
	}
	
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board board = mapper.boardContent(idx);
		model.addAttribute("board", board);
		
		return "boardUpdateForm";
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board board) { // idx, title, content
		mapper.boardUpdate(board);
		
		return "redirect:/boardList.do";
	}
}
