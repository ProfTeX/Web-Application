package pdf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class ChapterServlet
 */
@WebServlet("/chapter")
public class ChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChapterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("id") == null) 
		{
			response.sendError(400, "'id' parameter missing!");
			return;
		}
		
		ChapterAccess ca = new ChapterAccess();
		Chapter chapter = ca.getChapterById(Integer.parseInt(request.getParameter("id")));
		
		if(chapter == null)
		{
			response.sendError(404, "Chapter with id " + request.getParameter("id") + " not found!");
			return;
		}
		if(request.getParameter("name") != null)
		{
			chapter.setName(request.getParameter("name"));
		}
		if(request.getParameter("position") != null)
		{
			chapter.setPosition(Integer.parseInt(request.getParameter("position")));
		}
		
		if(request.getParameter("add_snippet") != null)
		{
			SnippetAccess sa = new SnippetAccess();
			Snippet snippet = sa.getSnippetById(Integer.parseInt(request.getParameter("add_snippet")));
			if(snippet == null)
			{
				response.sendError(404, "Snippet with id " + request.getParameter("add_snippet") + " not found!");
				return;
			}
			chapter.addSnippet(snippet);
		}
		if(request.getParameter("rem_snippet") != null)
		{
			SnippetAccess sa = new SnippetAccess();
			Snippet snippet = sa.getSnippetById(Integer.parseInt(request.getParameter("rem_snippet")));
			if(snippet == null)
			{
				response.sendError(404, "Snippet with id " + request.getParameter("rem_snippet") + " not found!");
				return;
			}
			chapter.removeSnippet(snippet);
		}
		
		response.getWriter().write(ca.saveOrUpdateChapter(chapter).toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("name") == null || request.getParameter("position") == null || request.getParameter("room") == null) 
		{
			response.sendError(400, "One or more parameters missing!" + request.getParameter("name") + request.getParameter("position")+request.getParameter("room"));
			return;
		}
		
		Room room = (new RoomAccess()).getRoomById(Integer.parseInt(request.getParameter("room")));
		if(room == null)
		{
			response.sendError(404, "Room with id " + request.getParameter("room") + " not found!");
			return;
		}
		
		ChapterAccess ca = new ChapterAccess();
		Chapter chapter = new Chapter();
		
		chapter.setName(request.getParameter("name"));
		chapter.setPosition(Integer.parseInt(request.getParameter("position")));
		chapter.setRoom(room);
		
		ca.saveOrUpdateChapter(chapter);
		
		response.getWriter().write(chapter.toString());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) {
			response.sendError(400, "'id' parameter missing!");
			return;
		}
		
		ChapterAccess ca = new ChapterAccess();
		Chapter chapter = ca.getChapterById(Integer.parseInt(request.getParameter("id")));
		
		if(chapter == null)
		{
			response.sendError(404, "Chapter with id " + request.getParameter("id") + " not found!");
			return;
		}
		
		response.getWriter().write(ca.deleteChapter(chapter).toString());
	}

}
