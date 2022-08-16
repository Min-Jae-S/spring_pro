package kr.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	MemberMapper memberMapper;
	
	@RequestMapping("/memJoin.do")
	public String memJoin() {
		return "member/join";
	}
	
	@GetMapping("/memCheckId.do")
	public @ResponseBody int memCheckId(@RequestParam("memId") String memId) {
		Member member = memberMapper.memCheckId(memId);
		
		if(member != null || memId.trim().equals("")) {
			return 0; // 중복 OR 공백(으로만 구성된)입력
		}
		
		return 1;
	}
	
	@PostMapping("/memRegister.do")
	public String memRegister(Member member, RedirectAttributes rttr, HttpSession session,
							  String memPassword1, String memPassword2) {
		System.out.println("memRegister: " + member);
		
		if(member.getMemId() == null || member.getMemId().trim().equals("") ||
		   member.getMemPassword() == null || member.getMemPassword().trim().equals("") ||
		   member.getMemName() == null || member.getMemName().trim().equals("") ||
		   member.getMemAge() == 0 ||
		   member.getMemGender() == null || member.getMemGender().trim().equals("") ||
		   member.getMemEmail() == null || member.getMemEmail().trim().equals("")) {
			
			// 누락 메세지를 갖고 가기 ==> Model, HttpServletRequest에 객체바인딩X, 새로운 request객체가 생성되므로
			// RedirectAttributes로 객체바인딩을 하자
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/memJoin.do"; // ${msgType}, ${msg}
		}
		
		/*
		if(!memPassword1.equals(memPassword2)) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "비밀번호가 다릅니다.");
			return "redirect:/";
		}
		*/
		
		//member.setMemProfile("");
		// 회원정보를 DB에 저장
		int result = memberMapper.memRegister(member);
		
		if(result == 1) {
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원가입에 성공하였습니다.");
			
			// 회원가입에 성공하면 로그인 처리하기
			session.setAttribute("member", member);
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "회원가입에 실패하였습니다.");
			return "redirect:/memJoin.do";
		}
	}
	
	@GetMapping("/memLogout.do")
	public String memLogout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/memLoginForm.do")
	public String memLoginForm() {
		return "member/loginForm";
	}
	
	@PostMapping("/memLogin")
	public String memLogin(Member member, HttpSession session, RedirectAttributes rttr) {
		if(member.getMemId() == null || member.getMemId().trim().equals("") ||
		   member.getMemPassword() == null || member.getMemPassword().trim().equals("")) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력해주세요.");
			return "redirect:/memLoginForm.do";
		}
		
		Member tempMember = memberMapper.memLogin(member);
		if(tempMember != null) {
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "로그인에 성공했습니다.");
			session.setAttribute("member", tempMember);
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "로그인에 실패했습니다. 다시 로그인 해주세요.");
			return "redirect:/memLoginForm.do";
		}
	}
	
	@GetMapping("memUpdateForm.do")
	public String memUpdateForm(HttpSession session) {
		return "member/updateForm";
	}
	
	@PostMapping("memUpdate.do")
	public String memUpdate(Member member) {
		
		return "";
	}
}
