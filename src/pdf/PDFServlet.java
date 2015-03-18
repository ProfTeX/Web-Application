package pdf;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PDFServlet
 */
@WebServlet("/PDFServlet")
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
		
		File pdftemp = new File(getServletContext().getRealPath("/") + "pdf_temp");
		pdftemp.mkdirs();
		
		PDFCreator pdf = new PDFCreator();
		pdf.setOutputDir(pdftemp.getAbsolutePath());
		//System.out.println(request.getParameter("texstring"));
		//TODO: Daten von DB holen, Parameter übergeben
		String output = pdf.compileString("\\documentclass{scrartcl}\n" + 
				"\n" + 
				"\\usepackage[utf8]{inputenc}\n" + 
				"\\usepackage[T1]{fontenc}\n" + 
				"\\usepackage[ngerman]{babel}\n" + 
				"%\\usepackage{lipsum}\n" + 
				"\\usepackage{graphicx}\n" + 
				"\\usepackage{tabularx}\n" + 
				"\n" + 
				"\\begin{document}\n" + 
				"\n" + 
				"\\tableofcontents\n" + 
				"\n" + 
				"\n" + 
				"\\section{Hello World}\n" + 
				"Hello World!\n" + 
				"\n" + 
				"\n" + 
				"\\section{Befehle}\n" + 
				"Befehle: \\\\<befehlsnahme>[..optionen..]\\{..argument..(zwingend)\\}\n" + 
				"\n" + 
				"\\section{Umlaute}\n" + 
				"äöü+ßasökfepokfsa\n" + 
				"\\subsubsection{Umlaute subsub}\n" + 
				"subsubsub\n" + 
				"\n" + 
				"\\begin{figure}[t]\n" + 
				"  %\\includegraphics{\\textwidth}{IMG_0118.jpg}\n" + 
				"  \\centering\n" + 
				"  \\caption{Das ist ein Bild}\n" + 
				"  \\label{fig:testbild}\n" + 
				"\\end{figure}\n" + 
				"\n" + 
				"\\begin{table}\n" + 
				"  \\centering\n" + 
				"  \\caption{Überschrift}\n" + 
				"\\begin{tabularx}{\\textwidth}{l|r|X}\n" + 
				"\n" + 
				"  \n" + 
				"  links & rechts & center \\\\ \\hline\n" + 
				"  links & rechts & center \\\\\n" + 
				"  asdf & asdf & asfd \\\\\n" + 
				"  \n" + 
				"\\end{tabularx}\n" + 
				"\\end{table}\n" + 
				"Noch mehr Text. :)\n" + 
				"\n" + 
				"\\end{document}\n" + 
				"");
		
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
		
		RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/pdf.jsp");
		RequetsDispatcherObj.forward(request, response);
		
	}

}
