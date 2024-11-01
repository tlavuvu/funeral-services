package za.co.supremeworx.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.supremeworx.model.Member;
import za.co.supremeworx.repositories.MemberRepository;
import za.co.supremeworx.requests.MemberRegisterRequest;
import za.co.supremeworx.responses.MemberRegisterResponse;
import za.co.supremeworx.utils.ListUtils;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ListUtils listUtils;
	
	public MemberRegisterResponse registerMember(MemberRegisterRequest memberRegisterRequest) {
		String memberNumber = createMemberNumber(memberRegisterRequest);
		log.info("Member number generated for {} is {} ",memberRegisterRequest.getSurname(),memberNumber);
		
		Member member = mapMemberRegisterRequestToMember(memberRegisterRequest);
		
		Member registeredMember = memberRepository.save(member);
		return mapMemberToMemberRegisterResponse(registeredMember);
		
	}
	
	public List<Member> fetchAllMembers(){
		return listUtils.convertIterateToList(memberRepository.findAll());
	}
	
	public Member searchMemberByMemberNumber(String memberNumber) {
		Optional<Member> opt = memberRepository.findByMemberNumber(memberNumber);
		
		if(opt.isPresent()) {
			log.info("Member with member number {} found ", memberNumber);
			return opt.get();
		}else {
			log.info("Member with member number {} not found ", memberNumber);
			return new Member();
		}
	}
	
	public String createMemberNumber(MemberRegisterRequest memberRegisterRequest) {
		String key = memberRegisterRequest.getSurname().substring(0, Math.min(memberRegisterRequest.getSurname().length(), 2));
		String id = memberRegisterRequest.getIdnumber().substring(0, Math.min(memberRegisterRequest.getIdnumber().length(), 5));
		String memberNumber = key.concat(id);
		return memberNumber;
	}
	
	public Member mapMemberRegisterRequestToMember(MemberRegisterRequest memberRegisterRequest) {
		Member member = new Member();
		member.setFirstname(memberRegisterRequest.getFirstname());
		member.setSurname(memberRegisterRequest.getSurname());
		member.setIdnumber(memberRegisterRequest.getIdnumber());
		member.setGender(memberRegisterRequest.getGender());
		member.setTitle(memberRegisterRequest.getTitle());
		member.setBirthdate(memberRegisterRequest.getBirthdate());
		member.setEmail(memberRegisterRequest.getEmail());
		member.setMobile(memberRegisterRequest.getMobile());
		member.setBeneficiaries(memberRegisterRequest.getBeneficiaries());
		return member;
		
	}
	
	public MemberRegisterResponse mapMemberToMemberRegisterResponse(Member member) {
		MemberRegisterResponse memberRegisterResponse = new MemberRegisterResponse();
		memberRegisterResponse.setId(member.getId());
		memberRegisterResponse.setEmail(member.getEmail());
		memberRegisterResponse.setFirstname(member.getFirstname());
		memberRegisterResponse.setSurname(member.getSurname());
		memberRegisterResponse.setIdnumber(member.getIdnumber());
		memberRegisterResponse.setGender(member.getGender());
		memberRegisterResponse.setTitle(member.getTitle());
		memberRegisterResponse.setBirthdate(member.getBirthdate());
		memberRegisterResponse.setAddress(member.getAddress());
		memberRegisterResponse.setMemberNumber(member.getMemberNumber());
		return memberRegisterResponse;
	}

}//-----------------end of class
