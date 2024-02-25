package za.co.supremeworx.requests;

import java.util.List;

import lombok.Data;
import za.co.supremeworx.model.Address;
import za.co.supremeworx.model.Beneficiary;

@Data
public class MemberRegisterRequest {
	
	private String firstname;
	private String surname;
	private String idnumber;
	private String gender;
	private String title;
	private String birthdate;
	private Address address;
	private String email;
	private String mobile;
	private List<Beneficiary> beneficiaries;
	

}//----------------end of class
