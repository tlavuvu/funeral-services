package za.co.supremeworx.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import za.co.supremeworx.model.Member;

@Component
public class ListUtils {

	public List<Member> convertIterateToList(Iterable<Member> iterable){
		List<Member> list = new ArrayList<Member>();
		iterable.forEach(list::add);
		 return list;
	}
}
