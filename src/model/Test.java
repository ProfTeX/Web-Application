package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Test {
	
	public static void main(String[] args) throws Exception{

//USER		
		/*How to create a User
		User user = new User();
		user.setEmail("cre@e.test");
		user.setName("create");
		user.setPassword("create");
		UserAccess ua = new UserAccess();
		ua.saveOrUpdateUser(user);*/
		
		/*How to load a User by his Name
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");*/

		/*How to load a User by his Email
		UserAccess ua = new UserAccess();
		User user = ua.getUserByEmail("cre@e.test");*/
		
		/*How to load a User by his Whatever
		UserAccess ua = new UserAccess();
		User user = ua.getUserByWhatEver("password", "create");*/
		
		/*How to update a User
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("update");
		user.setName("update");
		ua.saveOrUpdateUser(user);*/

//ROOM		
		/*How to create a Room
		Room room = new Room();
		room.setName("create");
		room.setCourse("create");
		room.setCreatedAt(new Date(2015, 4, 17));
		room.setDescription("create");
				
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		if (user == null) throw new Exception();
		user.addRoom(room);
		ua.saveOrUpdateUser(user);
		System.out.println(room.getUsers());*/
		
		/*How to load the Rooms of a User
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		System.out.println(user.getRooms());*/
		
		/*How to update a Room
		RoomAccess ra = new RoomAccess();
		Room room = ra.getRoomById(24);
		room.setName("update");
		ra.saveOrUpdateRoom(room);*/

//Chapter
		/*How to create a Chapter*/
		
		/*How to load a Chapter*/
		
		/*How to update a Chapter*/
		
//Snippet
		/*How to create a Snippet*/
		
		/*How to load a Snippet*/
		
		/*How to update a Snippet*/
		
//Tag
		/*How to create a Tag*/
				
		/*How to load a Tag*/
				
		/*How to update a Tag*/
		
		
		
		
		
		
		
		
		
		
		
		
		/*How to create a Snippet
		Snippet snippet = new Snippet();
		snippet.setContent("Create");
		snippet.setCreatedAt(new Date(2015, 4, 17));
		snippet.setTitle("BigTest");
		snippet.setUpdatedAt(new Date(2015, 4, 17));
		
		SnippetAccess sa = new SnippetAccess();
		sa.saveOrUpdateSnippet(snippet);*/
		
		
		
		/*How to handle Room and Chapters
		Room room = new Room();
		
		room.setName("BigTest");
		room.setCourse("BigTest");
		room.setCreatedAt(new Date(2015, 4, 17));
		room.setDescription("BigTest");
		
		RoomAccess ra = new RoomAccess();
		ra.saveOrUpdateRoom(room);
        
		Chapter chapter = new Chapter();                          
		chapter.setName("BigTest");                               
		chapter.setCreatedAt(new Date(2015, 4, 17));              
		chapter.setUpdatedAt(new Date(2015, 4, 17));   
		chapter.setRoomId(room.getId());
		
		Chapter chapter2 = new Chapter();                          
		chapter2.setName("BigTest2");                               
		chapter2.setCreatedAt(new Date(2015, 4, 17));              
		chapter2.setUpdatedAt(new Date(2015, 4, 17));  
		chapter2.setRoomId(room.getId());
		
		ChapterAccess ca = new ChapterAccess();
		ca.saveOrUpdateChapter(chapter);
		ca.saveOrUpdateChapter(chapter2);*/

		/*How to handel Snippet and Chanpter
		Snippet snippet = new Snippet();
		snippet.setContent("BigTest");
		snippet.setCreatedAt(new Date(2015, 4, 17));
		snippet.setTitle("BigTest");
		snippet.setUpdatedAt(new Date(2015, 4, 17));
		
		Snippet snippet2 = new Snippet();
		snippet2.setContent("BigTest2");
		snippet2.setCreatedAt(new Date(2015, 4, 17));
		snippet2.setTitle("BigTest2");
		snippet2.setUpdatedAt(new Date(2015, 4, 17));
		
		Chapter chapter = new Chapter();
		chapter.setId(44);
		chapter.setName("BigTest");
		chapter.setCreatedAt(new Date(2015, 4, 17));
		chapter.setUpdatedAt(new Date(2015, 4, 17));
		chapter.setRoomId(9);        
		chapter.addSnippet(snippet);
		chapter.addSnippet(snippet2);
		
		ChapterAccess ca = new ChapterAccess();
		ca.saveOrUpdateChapter(chapter);*/
		
		//Tag tag = new Tag();
		//tag.setCreatedAt(new Date(2015, 4, 17));
		//tag.setName("BigTest");
		//tag.setUpdatedAt(new Date(2015, 4, 17));
		//
		//TagAccess ta = new TagAccess();
		//ta.saveOrUpdateTag(tag);
		//
		
	}
	
}