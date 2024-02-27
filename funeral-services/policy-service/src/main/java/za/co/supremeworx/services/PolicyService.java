package za.co.supremeworx.services;

import java.util.UUID;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import za.co.supremeworx.model.Member;
import za.co.supremeworx.model.Policy;
import za.co.supremeworx.model.Product;
import za.co.supremeworx.model.ProductResponse;
import za.co.supremeworx.repositories.PolicyRepository;
import za.co.supremeworx.requests.PolicyRegisterRequest;
import za.co.supremeworx.response.PolicyRegisterResponse;

@Service
@Slf4j
public class PolicyService {
	
	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private WebClient webClient;
	
	private ProductResponse product;
	
	private PolicyRegisterResponse policyRegisterResponse;
	
public PolicyRegisterResponse registerPolicy(PolicyRegisterRequest policyRegisterRequest) {
		
		String memberNumber = policyRegisterRequest.getMemberNumber();
		String productName = policyRegisterRequest.getProductName();
		
//		Call Member service to search for member
		log.info("Searching member with member number {}",memberNumber);
		Member member = webClient.get()
			.uri("http://localhost:1000/api/member/"+memberNumber)
			.retrieve()
			.bodyToMono(Member.class)
			.block();
		
		Predicate<Member> pr = t -> t.getId()!=null;
		
		if(pr.test(member)) {
			log.info("member found : {}",member);
//			Call Product service to search for product
			 product = webClient.get()
				.uri("http://localhost:1001/api/product/"+productName)
				.retrieve()
				.bodyToMono(ProductResponse.class)
				.block();
		}else {
			return new PolicyRegisterResponse();
		}

		String policyNumber = createPolicyNumber();
		String uuid = UUID.randomUUID().toString();
		Policy policy= new Policy();
		policy.setPolicyNumber(policyNumber);
		policy.setMember(member);
		policy.setUuid(uuid);
		policy.setProduct(product);
		policyRepository.save(policy);
		log.info("Policy created with policy number {}",policyNumber);
		policyRegisterResponse = new PolicyRegisterResponse();
		policyRegisterResponse.setPolicyNumber(policyNumber);
		return policyRegisterResponse;
		
	}

		public String createPolicyNumber() {
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			return Long.toString(number);
		}

}//-------------------end of class
