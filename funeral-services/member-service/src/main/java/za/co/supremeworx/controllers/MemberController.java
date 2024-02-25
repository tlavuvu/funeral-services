package za.co.supremeworx.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import za.co.supremeworx.model.Member;
import za.co.supremeworx.requests.MemberRegisterRequest;
import za.co.supremeworx.responses.MemberRegisterResponse;
import za.co.supremeworx.services.MemberService;

@RestController
@Slf4j
@RequestMapping("/api/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping
	public ResponseEntity<MemberRegisterResponse> registerMember(@RequestBody MemberRegisterRequest memberRegisterRequest) {
		return new ResponseEntity<MemberRegisterResponse>(memberService.registerMember(memberRegisterRequest),HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<List<Member>> getAllMembers(){
		log.info("Requesting all members");
		return new ResponseEntity<List<Member>>(memberService.fetchAllMembers(),HttpStatus.OK);
	}
	
	@GetMapping("/{memberNumber}")
	public ResponseEntity<Member> searchMemberByMemberNumber(@PathVariable("memberNumber") String memberNumber){
		log.info("Searching member with {} ",memberNumber);
		return new ResponseEntity<Member>(memberService.searchMemberByMemberNumber(memberNumber),HttpStatus.OK);
	}

}
