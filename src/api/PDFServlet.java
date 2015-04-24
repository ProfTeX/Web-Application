package api;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class PDFServlet
 */
@WebServlet("/pdf")
public class PDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PDFServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("ids") == null)
		{
			response.sendError(400, "'ids' parameter missing!");
			return;		
		} 
		
		File pdftemp = new File(getServletContext().getRealPath("/") + "pdf_temp");
		pdftemp.mkdirs();
		
		PDFCreator pdf = new PDFCreator();
		pdf.setOutputDir(pdftemp.getAbsolutePath());
		
		String[] ids;
		ArrayList<Snippet> snippets = new ArrayList<Snippet>();
		ArrayList<Chapter> chapters = new ArrayList<Chapter>();
		SnippetAccess sa = new SnippetAccess();
		ids = request.getParameter("ids").split(",");
		for(int i = 0; i < ids.length; i++)
		{
			Snippet snippet = sa.getSnippetById(Integer.parseInt(ids[i]));
			if(snippet == null)
			{
				response.sendError(404, "Snippet with id " + ids[i] + " not found!");
				return;
			}
			if(!snippets.contains(snippet))
			{
				snippets.add(snippet);
				Chapter chapter = snippet.getChapters().get(0);
				if(!chapters.contains(chapter))
				{
					chapters.add(chapter);
				}
			}
		}
		
		//Sort snippets and chapters based on position
		Collections.sort(snippets, new Comparator<Snippet>() {
	        @Override
	        public int compare(Snippet  snip1, Snippet  snip2)
	        {
	        	if(snip1.getPosition() == snip2.getPosition())
	        	{
	        		return 0;
	        	}
	        	else if(snip1.getPosition() < snip2.getPosition())
	        	{
	        		return -1;
	        	}
	        	else
	        	{
	        		return 1;
	        	}
	        }
	    });
		Collections.sort(chapters, new Comparator<Chapter>() {
	        @Override
	        public int compare(Chapter  chap1, Chapter  chap2)
	        {
	        	if(chap1.getPosition() == chap2.getPosition())
	        	{
	        		return 0;
	        	}
	        	else if(chap1.getPosition() < chap2.getPosition())
	        	{
	        		return -1;
	        	}
	        	else
	        	{
	        		return 1;
	        	}
	        }
	    });
		
		String content = "";
		
		for(Chapter chapter : chapters)
		{
			content += "\\section{" + chapter.getName() + "}\n";
			
			List<Snippet> chapterSnippets = chapter.getSnippets();
			for(Snippet snippet : chapterSnippets)
			{
				if(snippets.contains(snippet))
				{
					content += "\\subsection{" + snippet.getTitle() + "}\n";
					content += snippet.getContent().replace("\\\\", "\\") + "\n";
				}
			}
		}
		
		//System.out.println(request.getParameter("texstring"));

		String template =
				"\n" + 
				"\\documentclass{scrartcl}\n" + 
				"\\usepackage[utf8]{inputenc}\n" + 
				"\\usepackage[T1]{fontenc}\n" + 
				"\\usepackage[ngerman]{babel}\n" +
				"\\usepackage{graphicx}\n" + 
				"\\usepackage{tabularx}\n" + 
				"\n" + 
				"\\begin{document}\n" + 
				"\n" + 
				"%\\tableofcontents\n" + 
				"%CONTENT%" +
				"\\end{document}";
		
		System.out.println(snippets);
		
		System.out.println(template.replace("%CONTENT%", content));
		
		String output = pdf.compileString(template.replace("%CONTENT%", content));
		
		System.out.println(output);
		
		//delete files older than 1h
		File[] listFiles = pdftemp.listFiles();
	    long purgeTime = System.currentTimeMillis() - (60 * 60 * 1000);
	    for(File listFile : listFiles) 
	    {
	        if(listFile.lastModified() < purgeTime) 
	        {
	            if(!listFile.delete()) 
	            {
	                System.err.println("Unable to delete file: " + listFile);
	            }
	         }
	      }
		
		request.setAttribute("pdf_path", "pdf_temp/"+pdf.getUUID() + ".pdf");
		
		File outFile = new File(pdf.getOutputDir() + "/" + pdf.getUUID() + ".pdf");
		if(!outFile.exists())
		{
			response.sendError(500, "The Document could not be compiled! There probably is an error in the LaTex-Code.");
			return;
		}
		
		RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("protected/pdf.jsp");
		RequetsDispatcherObj.forward(request, response);
		
	}

}
