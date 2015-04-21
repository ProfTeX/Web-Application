package pdf;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
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
		ChapterAccess ca = new ChapterAccess();
		
		Room room = ra.getRoomById(roomId);
		
		if(room == null)
		{
			response.sendError(400, "Room with ID " + roomId + " not found!");
			return;
		}
		
		String chaptersStr = "[";
		
		List<Chapter> chapters = ca.getChaptersByRoomId(room.getId());
		
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
				
				snippetsStr += "{\"id\":\"" + snippet.getId() + "\", \"title\":\"" + snippet.getTitle() + "\", \"content\":\"" + snippet.getContent() + "\", \"tags\":" + tagsStr + "},";
			}
			
			if(snippetsStr.lastIndexOf(",") != -1)
			{
				snippetsStr = snippetsStr.substring(0, snippetsStr.lastIndexOf(","));
			}
			
			snippetsStr += "]";
			
			chaptersStr += "{\"id\":\"" + chapter.getId() + "\", \"name\":\"" + chapter.getName() + "\", \"room\":\"" + chapter.getRoomId() + "\", \"snippets\":" + snippetsStr + "},";
		}
		
		if(chaptersStr.lastIndexOf(",") != -1)
		{
			chaptersStr = chaptersStr.substring(0, chaptersStr.lastIndexOf(","));
		}
		
		chaptersStr += "]";
		
		String returnStr = "[{\"id\":\"" + room.getId() + "\", \"name\":\"" + room.getName() + "\", \"course\":\"" 
					+ room.getCourse() + "\", \"description\":\"" + room.getDescription() + "\", \"chapters\":" 
					+ chaptersStr + "}]";
		
		response.getWriter().write(returnStr);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("name") == null) {
			response.sendError(400, "'name' parameter missing!");
			return;
		}
		/*else
		{
			name = request.getParameter("name");
		}
		if(request.getParameter("course") != null) {
			course = request.getParameter("course");
		}
		else
		{
			course = "";
		}
		if(request.getParameter("description") != null) {
			description = request.getParameter("description");	
		}
		else
		{
			description = "";
		}*/
		Room room = new Room();
		room.setName(request.getParameter("name"));
		room.setCourse(request.getParameter("course"));
		room.setCreatedAt(new Date());
		room.setDescription(request.getParameter("description"));
				
		UserAccess ua = new UserAccess();
		User user = ua.getUserByName("test");
		if (user == null) {
			response.sendError(400, "User not found!");
			return;
		}
		user.addRoom(room);
		ua.saveOrUpdateUser(user);
		
		response.getWriter().write("true");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
