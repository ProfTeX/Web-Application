package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Beispiel {
	
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
		
		/*How to delete a User
		UserAccess ua = new UserAccess();
		User user = ua.getUserByEmail("cre@e.test");
		ua.deleteUser(user);*/
		
//ROOM		
		/*How to create a Room
		Room room = new Room();
		room.setName("create");
		room.setCourse("create");
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
		/*How to create a Chapter
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		Chapter chapter = new Chapter();                          
		chapter.setName("create");
		chapter.setCreatedAt(new Date(2015, 4, 17));              
		chapter.setUpdatedAt(new Date(2015, 4, 17));  	
		chapter.setRoomId(user.getRooms().get(0).getId());
		ChapterAccess ca = new ChapterAccess();
		ca.saveOrUpdateChapter(chapter);*/
		
		/*How to load a Chapter from a Room
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		ChapterAccess ca = new ChapterAccess();
		List<Chapter> chapters = ca.getChaptersByRoomId(user.getRooms().get(0).getId());
		System.out.println(chapters);*/
		
		/*How to update a Chapter
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		ChapterAccess ca = new ChapterAccess();
		List<Chapter> chapters = ca.getChaptersByRoomId(user.getRooms().get(0).getId());
		chapters.get(0).setName("update");
		ca.saveOrUpdateChapter(chapters.get(0));*/
		
		/*How to delete a Chapter
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		ChapterAccess ca = new ChapterAccess();
		List<Chapter> chapters = ca.getChaptersByRoomId(user.getRooms().get(0).getId());
		ca.deleteChapter(chapters.get(0));*/
		
//Snippet
		/*How to create a Snippet
		Snippet snippet = new Snippet();
		snippet.setContent("create");
		snippet.setCreatedAt(new Date(2015, 4, 17));
		snippet.setTitle("create");
		snippet.setUpdatedAt(new Date(2015, 4, 17));
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		ChapterAccess ca = new ChapterAccess();
		List<Chapter> chapters = ca.getChaptersByRoomId(user.getRooms().get(0).getId());
		chapters.get(0).addSnippet(snippet);
		ca.saveOrUpdateChapter(chapters.get(0));*/
		
		/*How to load a Snippet
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		ChapterAccess ca = new ChapterAccess();
		List<Chapter> chapters = ca.getChaptersByRoomId(user.getRooms().get(0).getId());
		System.out.println(chapters.get(0).getSnippets());*/

		/*How to update a Snippet
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		ChapterAccess ca = new ChapterAccess();
		List<Chapter> chapters = ca.getChaptersByRoomId(user.getRooms().get(0).getId());
		Snippet snippet = chapters.get(0).getSnippets().get(0);
		snippet.setTitle("update");
		ca.saveOrUpdateChapter(chapters.get(0));
		//Alternative: ua.saveOrUpdateUser(user);*/
		
//Tag
		/*How to create a Tag
		Tag tag = new Tag();
		tag.setCreatedAt(new Date(2015, 4, 17));
		tag.setName("create");
		tag.setUpdatedAt(new Date(2015, 4, 17));
		TagAccess ta = new TagAccess();
		ta.saveOrUpdateTag(tag);*/
		
		/*How to load a Tag
		TagAccess ta = new TagAccess();
		Tag tag = ta.getTagByName("create");*/
		
		/*How to add a Tag to a Snippet
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		ChapterAccess ca = new ChapterAccess();
		List<Chapter> chapters = ca.getChaptersByRoomId(user.getRooms().get(0).getId());
		TagAccess ta = new TagAccess();
		Tag tag = ta.getTagByName("create");
		chapters.get(0).getSnippets().get(0).addTag(tag);
		ca.saveOrUpdateChapter(chapters.get(0));*/	
			
		/*How to load the Tags of a Snippet
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("create");
		ChapterAccess ca = new ChapterAccess();
		List<Chapter> chapters = ca.getChaptersByRoomId(user.getRooms().get(0).getId());
		SnippetAccess sa = new SnippetAccess();
		Snippet snippet = sa.getSnippetById(chapters.get(0).getSnippets().get(0).getId());
		System.out.println(snippet.getTags());*/
		
		/*How to update a Tag
		TagAccess ta = new TagAccess();
		Tag tag = ta.getTagByName("create");
		tag.setName("update");
		ta.saveOrUpdateTag(tag);*/
	}
	
}