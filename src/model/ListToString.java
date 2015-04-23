package model;
import java.util.List;


public class ListToString<E> {
		
	public String listToString(List<E> list){
		
		if (list.size() == 0){
			return "[]";
		}
		
		String returnString = "";
		
		for (E object : list){
			returnString += object.toString() + ",";
		}
		
		returnString = returnString.substring(0, returnString.lastIndexOf(","));
		
		return "[" + returnString + "]";	
	}
}