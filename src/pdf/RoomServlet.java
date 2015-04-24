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
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("id") == null)
		{
			response.sendError(400, "'id' parameter missing!");
			return;
		}
		
		Integer roomId = Integer.parseInt(request.getParameter("id"));
		
		RoomAccess ra = new RoomAccess();
		
		Room room = ra.getRoomById(roomId);
		
		if(room == null)
		{
			response.sendError(400, "Room with ID " + roomId + " not found!");
			return;
		}
		response.getWriter().write(room.toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("name") == null) {
			response.sendError(400, "'name' parameter missing!");
			return;
		}
		RoomAccess ra = new RoomAccess();
		Room room = new Room();
		room.setName(request.getParameter("name"));
		if(request.getParameter("course") != null)
		{
			room.setCourse(request.getParameter("course"));
		}
		if(request.getParameter("description") != null)
		{
			room.setDescription(request.getParameter("description"));
		}
		
		ra.saveOrUpdateRoom(room);
				
		//current user needs permission to access new room
		UserAccess ua = new UserAccess();
		//response.getWriter().write(request.getUserPrincipal().getName());
		User user = ua.getUserByEmail(request.getUserPrincipal().getName());
		if (user == null) {
			response.sendError(404, "User not found!");
			return;
		}
		user.addRoom(room);
		ua.saveOrUpdateUser(user);
		
		response.getWriter().write(room.toString());
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
		response.setCharacterEncoding("UTF-8");
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
		
		response.getWriter().write(ra.saveOrUpdateRoom(room).toString());

	}

}
