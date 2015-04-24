package rooms;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class ShowRoomsServlet
 */
@WebServlet("/Rooms")
public class ShowRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowRoomsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserAccess ua = new UserAccess();
		User currUser = ua.getUserByEmail(request.getUserPrincipal().getName());
		
		System.out.println(currUser.getId());
		
		request.setAttribute("user", currUser);
		getServletContext().getRequestDispatcher("/protected/room_overview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().print("Nono posterino!");
	}
}
