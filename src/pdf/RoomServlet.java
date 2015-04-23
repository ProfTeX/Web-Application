package pdf;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet(urlPatterns={"/room"}, initParams={@WebInitParam(name="readonly", value="false")})
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null)
		{
			response.sendError(400, "'id' parameter missing!");
			return;
		}
		
		Integer roomId = Integer.parseInt(request.getParameter("id"));
		
		RoomAccess ra = new RoomAccess();
		//ChapterAccess ca = new ChapterAccess();
		
		Room room = ra.getRoomById(roomId);
		
		if(room == null)
		{
			response.sendError(400, "Room with ID " + roomId + " not found!");
			return;
		}
		
		/*String chaptersStr = "[";
		
		List<Chapter> chapters = room.getChapters();
		
		System.out.println(chapters.size());
		
		for(Chapter chapter : chapters)
		{
			List<Snippet> snippets = chapter.getSnippets();
			String snippetsStr = "[";
			
			for(Snippet snippet : snippets)
			{
				String tagsStr = "[";
				List<Tag> tags = snippet.getTags();
				
				for(Tag tag : tags)
				{
					tagsStr += "{\"id\":\"" + tag.getId() + "\", \"name\":\"" + tag.getName() + "\"},";
				}
				
				if(tagsStr.lastIndexOf(",") != -1)
				{
					tagsStr = tagsStr.substring(0, tagsStr.lastIndexOf(","));
				}
				
				tagsStr += "]";
				
				snippetsStr += "{\"id\":\"" + snippet.getId() + "\", \"title\":\"" + snippet.getTitle() + "\", \"content\":\"" + snippet.getContent() + "\", \"tags\":" + tagsStr + ", \"position\": " + snippet.getPosition() + "},";
			}
			
			if(snippetsStr.lastIndexOf(",") != -1)
			{
				snippetsStr = snippetsStr.substring(0, snippetsStr.lastIndexOf(","));
			}
			
			snippetsStr += "]";
			
			chaptersStr += "{\"id\":\"" + chapter.getId() + "\", \"name\":\"" + chapter.getName() + "\", \"room\":\"" + chapter.getRoom().getId() + "\", \"snippets\":" + snippetsStr + ", \"position\": " + chapter.getPosition() + "},";
		}
		
		if(chaptersStr.lastIndexOf(",") != -1)
		{
			chaptersStr = chaptersStr.substring(0, chaptersStr.lastIndexOf(","));
		}
		
		chaptersStr += "]";
		
		String returnStr = "[{\"id\":\"" + room.getId() + "\", \"name\":\"" + room.getName() + "\", \"course\":\"" 
					+ room.getCourse() + "\", \"description\":\"" + room.getDescription() + "\", \"chapters\":" 
					+ chaptersStr + "}]";
		*/
		response.getWriter().write(room.toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("name") == null) {
			response.sendError(400, "'name' parameter missing!");
			return;
		}
		Room room = new Room();
		room.setName(request.getParameter("name"));
		room.setCourse(request.getParameter("course"));
		room.setDescription(request.getParameter("description"));
				
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("test");
		if (user == null) {
			response.sendError(400, "User not found!");
			return;
		}
		user.addRoom(room);
		ua.saveOrUpdateUser(user);
		
		response.getWriter().write(room.toString()); //("{\"id\":\"" + room.getId() + "\", \"name\":\"" + room.getName() + "\", \"course\":" + room.getCourse() + "\", \"description\":\"" + room.getDescription() + "\", \"chapters\":[]}");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) 
		{
			response.sendError(400, "'id' parameter missing!");
			return;
		}
		
		RoomAccess ra = new RoomAccess();
		Room room = ra.getRoomById(Integer.parseInt(request.getParameter("id")));
		response.getWriter().write(ra.deleteRoom(room).toString());
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) 
		{
			response.sendError(400, "'id' parameter missing!");
			return;
		}
		RoomAccess ra = new RoomAccess();
		Room room = ra.getRoomById(Integer.parseInt(request.getParameter("id")));
		
		if(room == null)
		{
			response.sendError(404, "Room with id " + request.getParameter("id") + " not found!");
			return;
		}
		
		if(request.getParameter("name") != null)
		{
			room.setName(request.getParameter("name"));
		}
		if(request.getParameter("course") != null)
		{
			room.setCourse(request.getParameter("course"));
		}
		if(request.getParameter("description") != null)
		{
			room.setDescription(request.getParameter("description"));
		}
		
		if(request.getParameter("add_user") != null)
		{
			UserAccess ua = new UserAccess();
			User user = ua.getUserById(Integer.parseInt(request.getParameter("add_user")));
			if(user == null)
			{
				response.sendError(404, "User with id " + request.getParameter("add_user") + " not found!");
				return;
			}
			room.addUser(user);
		}
		if(request.getParameter("rem_user") != null)
		{
			UserAccess ua = new UserAccess();
			User user = ua.getUserById(Integer.parseInt(request.getParameter("rem_user")));
			if(user == null)
			{
				response.sendError(404, "User with id " + request.getParameter("id") + " not found!");
				return;
			}
			room.removeUser(user);
		}

		ra.saveOrUpdateRoom(room);
		
		response.getWriter().write("true");

	}

}
