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
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("room") == null)
		{
			response.sendError(400, "'room' parameter missing!");
			return;
		}
		
		RoomAccess ra = new RoomAccess();
		
		Room room = ra.getRoomById(Integer.parseInt(request.getParameter("room")));
		if(room == null)
		{
			response.sendError(404, "Room with id " + request.getParameter("room") + " not found!");
			return;
		}
		List<Chapter> chapters = room.getChapters();
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
		
		ArrayList<Snippet> resultList = new ArrayList<Snippet>();
		
		for(Chapter chapter : chapters)
		{
			List<Snippet> snippets = chapter.getSnippets();
			
			for(Snippet snippet : snippets)
			{
				boolean contains = false;
				List<Tag> snippetTags = snippet.getTags();
				
				if(!(ids.isEmpty() && tags.length == 0))
				{
					if(ids.contains(snippet.getId()))
					{
						contains = true;
					}
					
					for(Tag snippetTag : snippetTags)
					{
						if(contains)
						{
							break;
						}
						for(String tag : tags)
						{
							System.out.println("tagParam: " + tag.toLowerCase().trim());
							System.out.println("snippetTag: " + snippetTag.getName().toLowerCase());
							if(snippetTag.getName().toLowerCase().trim().equals(tag.toLowerCase().trim()))
							{
								contains = true;
								System.out.println("contains tag " + tag);
								break;
							}
						}
					}
				}
				else
				{
					contains = true;
				}
				if(contains)
				{
					resultList.add(snippet);
				}
			}
		}

		response.getWriter().write(resultList.toString());
		
	}

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
		
		SnippetAccess sa = new SnippetAccess();
		TagAccess ta = new TagAccess();
		Snippet snippet = sa.getSnippetById(Integer.parseInt(request.getParameter("id")));
		if(snippet == null)
		{
			response.sendError(404, "Snippet with id " + request.getParameter("id") + " not found!");
			return;
		}
		if(request.getParameter("tags") != null)
		{
			String[] tags;
			tags = request.getParameter("tags").split(",");
			
			ArrayList<Tag> newTags = new ArrayList<Tag>();
			for(String tagStr : tags)
			{
				if (tagStr.trim().equals("")) continue;
				Tag tag = ta.getTagByName(tagStr.trim());
				if(tag == null)
				{
					response.sendError(404, "Tag " + tagStr + " does not exist!");
					return;
				}
				else
				{
					newTags.add(tag);
				}
			}
			
			snippet.setTags(newTags);
		}

		if(request.getParameter("title") != null)
		{
			snippet.setTitle(request.getParameter("title"));
		}
		if(request.getParameter("content") != null)
		{
			snippet.setContent(request.getParameter("content"));
		}
		if(request.getParameter("chapter") != null)
		{
			ChapterAccess ca = new ChapterAccess();
			Chapter chapter = ca.getChapterById(Integer.parseInt(request.getParameter("chapter")));
			if(chapter == null)
			{
				response.sendError(404, "Chapter with id " + request.getParameter("chapter") + " not found!");
				return;
			}
			ArrayList<Chapter> chapterArg = new ArrayList<Chapter>();
			chapterArg.add(chapter);
			snippet.setChapters(chapterArg);
		}
		
		sa.saveOrUpdateSnippet(snippet);
		
		response.getWriter().write("true");
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("title") == null || request.getParameter("content") == null || request.getParameter("position") == null || request.getParameter("chapter") == null)
		{
			response.sendError(400, "One or more mandatory parameters missing!");
			return;
		}
		
		TagAccess ta = new TagAccess();
		SnippetAccess sa = new SnippetAccess();
		
		Snippet snippet = new Snippet();
		snippet.setTitle(request.getParameter("title"));
		snippet.setContent(request.getParameter("content"));
		snippet.setPosition(Integer.parseInt(request.getParameter("position")));
		
		ChapterAccess ca = new ChapterAccess();
		Chapter chapter = ca.getChapterById(Integer.parseInt(request.getParameter("chapter")));
		if(chapter == null)
		{
			response.sendError(404, "Chapter with id " + request.getParameter("chapter") + " not found!");
			return;
		}
		
		ArrayList<Chapter> chapterArg = new ArrayList<Chapter>();
		chapterArg.add(chapter);
		snippet.setChapters(chapterArg);
		
		if(request.getParameter("tags") != null)
		{
			String[] tags;
			tags = request.getParameter("tags").split(",");
			
			for(String tagStr : tags)
			{
				if (tagStr.trim().equals("")) continue;
				Tag tag = ta.getTagByName(tagStr.trim());
				if(tag != null)
				{
					snippet.addTag(tag);
				}
				else if(tag == null)
				{
					response.sendError(404, "Tag " + tagStr + " does not exist!");
					return;
				}
			}
		}
		
		String tagsStr = "[";
		if(request.getParameter("tags") != null)
		{	
			List<Tag> snippetTags = snippet.getTags();
			for(Tag tag : snippetTags)
			{
				tagsStr += "{\"id\":" + tag.getId() + ", \"name\":\"" + tag.getName() + "\"},";
			}
			
			if(tagsStr.lastIndexOf(",") != -1)
			{
				tagsStr = tagsStr.substring(0, tagsStr.lastIndexOf(","));
			}
		}
		
		tagsStr += "]";
		
		
		sa.saveOrUpdateSnippet(snippet);
		
		response.getWriter().write(snippet.toString()); //("{\"id\":" + snippet.getId() + ", \"title\":\"" + snippet.getTitle() + "\", \"content\":\"" + snippet.getContent() + "\", \"chapter\":" + snippet.getChapters().get(0).getId() + ", \"position\":" + snippet.getPosition() + ", \"tags\":" + tagsStr + "}");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) {
			response.sendError(400, "'id' parameter missing!");
			return;
		}
		
		SnippetAccess sa = new SnippetAccess();
		Snippet snippet = sa.getSnippetById(Integer.parseInt(request.getParameter("id")));
		
		if(snippet == null)
		{
			response.sendError(404, "Snippet with id " + request.getParameter("id") + " not found!");
			return;
		}
		
		response.getWriter().write(sa.deleteSnippet(snippet).toString());
	}

}
