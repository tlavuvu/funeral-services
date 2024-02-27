package za.co.supremeworx.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Utils {

	public static <T> List<T> convertIterableToList( Iterable<T> itr) {
		return StreamSupport.stream(itr.spliterator(), false).collect(Collectors.toList());
	}
}
