package pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class SnippetServlet
 */
@WebServlet("/snippet")
public class SnippetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnippetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("room") == null)
		{
			response.sendError(400, "'room' parameter missing!");
			return;
		}
		
		RoomAccess ra = new RoomAccess();
		ChapterAccess ca = new ChapterAccess();
		
		//Room room = ra.getRoomById(Integer.parseInt(request.getParameter("room")));
		List<Chapter> chapters = ca.getChaptersByRoomId(Integer.parseInt(request.getParameter("room")));
		System.out.println("chapter " + chapters.size());
		String[] tags, idsStr;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		if(request.getParameter("tags") != null)
		{
			tags = request.getParameter("tags").split(",");		
		} 
		else
		{
			tags = new String[0];
		}
		if(request.getParameter("ids") != null)
		{
			idsStr = request.getParameter("ids").split(",");		
		} 
		else
		{
			idsStr = new String[0];
		}
		
		if(idsStr != null)
		{
			for(int i = 0; i < idsStr.length; i++)
			{
				ids.add(Integer.parseInt(idsStr[i]));
			}
		}
		
		String result = "[";
		
		for(Chapter chapter : chapters)
		{
			List<Snippet> snippets = chapter.getSnippets();
			
			for(Snippet snippet : snippets)
			{
				boolean contains = false;
				
				if(ids.contains(snippet.getId()))
				{
					contains = true;
					System.out.println("contains id");
				}
				
				List<Tag> snippetTags = snippet.getTags();
				for(Tag snippetTag : snippetTags)
				{
					if(contains)
					{
						break;
					}
					for(String tag : tags)
					{
						if(snippetTag.getName().toLowerCase() == tag.toLowerCase().trim())
						{
							contains = true;
							System.out.println("contains tag " + tag);
							break;
						}
					}
				}
				
				if(contains)
				{
					String tagsStr = "[";
					
					for(Tag tag : snippetTags)
					{
						tagsStr += "{\"id\":\"" + tag.getId() + "\", \"name\":\"" + tag.getName() + "\"},";
					}
					
					if(tagsStr.lastIndexOf(",") != -1)
					{
						tagsStr = tagsStr.substring(0, tagsStr.lastIndexOf(","));
					}
					
					tagsStr += "]";
					
					result += "{\"id\":\"" + snippet.getId() + "\", \"title\":\"" + snippet.getTitle() + "\", \"content\":\"" + snippet.getContent() + "\", \"tags\":" + tagsStr + "},";
				}
			}
			
			
		}
		
		if(result.lastIndexOf(",") != -1)
		{
			result = result.substring(0, result.lastIndexOf(","));
		}
		
		result += "]";
		
		response.getWriter().write(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("test");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
