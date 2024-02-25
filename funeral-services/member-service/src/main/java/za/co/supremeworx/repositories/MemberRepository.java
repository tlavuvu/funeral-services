package za.co.supremeworx.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.supremeworx.model.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
	
	public Optional<Member> findByMemberNumber(String memberNumber);
	
	

}
