package kr.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	MemberMapper memberMapper;
	
	@RequestMapping("/memRegisterForm.do")
	public String memRegisterForm() {
		return "member/registerForm";
	}
	
	@GetMapping("/memCheckId.do")
	public @ResponseBody int memCheckId(@RequestParam("memId") String memId) {
		//Member member = memberMapper.memCheckId(memId);
		String memName = memberMapper.memCheckId(memId);
		
		//if(member != null || memId.trim().equals("")) {
		if(memName != null || memId.trim().equals("")) {
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
		   member.getMemEmail() == null || member.getMemEmail().trim().equals("") || 
		   memPassword1 == null || memPassword1.trim().equals("") || 
		   memPassword2 == null || memPassword2.trim().equals("")) {
			
			// 누락 메세지를 갖고 가기 ==> Model, HttpServletRequest에 객체바인딩X, 새로운 request객체가 생성되므로
			// RedirectAttributes로 객체바인딩을 하자
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			
			return "redirect:/memRegisterForm.do"; // ${msgType}, ${msg}
		}
		
		if(!memPassword1.equals(memPassword2)) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "비밀번호가 다릅니다.");
			
			return "redirect:/memRegisterForm.do";
		}
		
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
			
			return "redirect:/memRegisterForm.do";
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
	
	@PostMapping("/memLogin.do")
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
	
	@GetMapping("/memUpdateForm.do")
	public String memUpdateForm(HttpSession session) {
		return "member/updateForm";
	}
	
	@PostMapping("/memUpdate.do")
	public String memUpdate(Member member, RedirectAttributes rttr,
							String memPassword1, String memPassword2, HttpSession session) {
		System.out.println("memUpdate: " + member);
		if(member.getMemId() == null || member.getMemId().trim().equals("") ||
		   member.getMemPassword() == null || member.getMemPassword().trim().equals("") ||
		   member.getMemName() == null || member.getMemName().trim().equals("") ||
		   member.getMemAge() == 0 ||
		   member.getMemGender() == null || member.getMemGender().trim().equals("") ||
		   member.getMemEmail() == null || member.getMemEmail().trim().equals("") || 
		   memPassword1 == null || memPassword1.trim().equals("") || 
		   memPassword2 == null || memPassword2.trim().equals("")) {
					
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			
			return "redirect:/memUpdateForm.do"; // ${msgType}, ${msg}
		}
		
		if(!memPassword1.equals(memPassword2)) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "비밀번호가 다릅니다.");
			
			return "redirect:/memUpdateForm.do";
		}
		
		// 회원정보 수정
		int result = memberMapper.memUpdate(member);
		
		if(result == 1) {
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원정보가 정상적으로 수정되었습니다.");
			
			Member DBmember = memberMapper.getMember(member.getMemId());
			session.setAttribute("member", DBmember);
			
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "회원정보 수정에 실패하였습니다.");
			
			return "redirect:/memUpdateForm.do";
		}
	}
	
	@GetMapping("/memImageForm.do")
	public String memImageForm() {
		return "member/imageForm";
	}
	
	// 회원사진 업로드(1.upload 폴더에 파일 저장,  2.DB에 파일 이름 저장)
	@PostMapping("/memImageUpload.do")
	public String memImageUpload(HttpServletRequest request, RedirectAttributes rttr,
								 HttpSession session) {
		MultipartRequest multi = null;
		
		// C:\\Users\\User\\git\\spring_pro\\SpringMVC04\\src\\main\\webapp\\resources\\upload
		// C:\\eGovFrame-4.0.0\\workspace.edu\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SpringMVC04\\resources\\upload
		String savePath = request.getRealPath("resources/upload");
		int fileMaxSize = 10*1024*1024; // 10MB
	
		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "파일의 크기는 10MB를 초과할 수 없습니다.");
			
			return "redirect:/memImageForm.do";
		}
		
		String memId = multi.getParameter("memId");
		File file = multi.getFile("memProfile");

		if(file != null) { // 업로드가 된 상태
			// 이미지 파일(png, jpg, gif) 체크
			String newProfile = file.getName();
			String ext = newProfile.substring(newProfile.lastIndexOf(".") + 1).toUpperCase();
			
			if(ext.equals("PNG") || ext.equals("JPG") || ext.equals("GIF")) {
				String oldProfile = memberMapper.getMember(memId).getMemProfile();
				File oldFile = new File(savePath + File.separator + oldProfile);
				
				if(oldFile.exists()) {
					oldFile.delete();
				}
			} else {
				if(file.exists()) {
					file.delete();
				}
				rttr.addFlashAttribute("msgType", "실패 메세지");
				rttr.addFlashAttribute("msg", "이미지 파일(PNG, JPG, GIF)만 업로드 가능합니다.");
				
				return "redirect:/memImageForm.do";
			}
			
			// 새로운 이미지를 DB에 업데이트
			memberMapper.memProfileUpdate(memId, newProfile);
			
			Member member = memberMapper.getMember(memId);
			session.setAttribute("member", member);
		}
		
		rttr.addFlashAttribute("msgType", "성공 메세지");
		rttr.addFlashAttribute("msg", "저장되었습니다.");
		
		return "redirect:/";
	}
}
