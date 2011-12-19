package utility;

import java.util.List;

public class Helper {

	//println
	public static void pl(Object o){
		System.out.println(o);
	}

	public static <T> void pl(List<T> list){
		for(T elem : list){
			pl(elem);
		}
	}
	
	//print
	public static void p(Object o){
		System.out.print(o);
	}
}
