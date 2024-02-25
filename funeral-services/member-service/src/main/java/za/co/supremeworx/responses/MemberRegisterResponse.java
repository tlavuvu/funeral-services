package za.co.supremeworx.responses;

import lombok.Data;
import za.co.supremeworx.model.Address;

@Data
public class MemberRegisterResponse {
	
	private Integer id;
	private String email;
	private String mobile;
	private String firstname;
	private String surname;
	private String idnumber;
	private String gender;
	private String title;
	private String birthdate;
	private Address address;
	private String memberNumber;

}
