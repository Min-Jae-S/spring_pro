package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@RestController // @ResponseBody + @Controller
@RequestMapping("/board")
public class BoardRestController {
	
	@Autowired
	BoardMapper boardMapper;
	
	// @ResponseBody --> jackson-databind(객체를 JSON 데이터 형태로 변환)
	@GetMapping("/all")
	public List<Board> boardList() {
		List<Board> list = boardMapper.getLists();
		
		return list; // JSON 데이터 형식으로 변환(API)해서 리턴(응답)하겠다.
	}
	
	@PostMapping("/new")
	public void boardInsert(Board board) {
		boardMapper.boardInsert(board);
	}
	
	@DeleteMapping("/{idx}")
	public void boardDelete(@PathVariable("idx") int idx) {
		boardMapper.boardDelete(idx);
	}
	
	@PutMapping("/update")
	public void boardUpdate(@RequestBody Board board) {
		//System.out.println("boardUpdate: " + board);
		boardMapper.boardUpdate(board);
	}
	
	@GetMapping("/{idx}")
	public Board boardContent(@PathVariable("idx") int idx) {
		Board board = boardMapper.boardContent(idx);
		
		return board;
	}
	
	@PutMapping("/count/{idx}")
	public Board boardCount(@PathVariable("idx") int idx) {
		boardMapper.boardCount(idx);
		Board board = boardMapper.boardContent(idx);
		
		return board;
	}
}
