package pdf;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
//import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.hibernate.Session;

//import model.*;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		File imageDir = new File(getServletContext().getRealPath("/") + "images");
		imageDir.mkdirs();
		
		if(request.getParameter("room") == null) {
			response.sendError(400, "'room' parameter missing!");
			return;
		}
		
		Integer roomId = Integer.parseInt(request.getParameter("room"));
		
		
		//Room room =  (new RoomAccess()).getRoomById(roomId);
		
		//System.out.println(room.getId());
		
		//ServletConfig sc = //getServletContext();
		//getServletConfig();
		
		//String cn = sc.getInitParameter("companyName");
		
		//while(cn.hasMoreElements())
		//response.getWriter().write(cn.nextElement());
		
		/*if(cn != null)
		{
			response.getWriter().write(cn);
		}
		else
		{
			response.getWriter().write("null");
		}*/
		
		String roomPath = imageDir.getAbsolutePath() + "/" + roomId.toString() + "/";
		
		File roomDir = new File(roomPath);
		
		roomDir.mkdirs();
		
		File[] images = roomDir.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".bmp") || name.toLowerCase().endsWith(".gif");
		    }
		});
		
		String resultStr = "[";
		
		for(File img : images)
		{
			String id = img.getName().substring(0, img.getName().lastIndexOf("."));
			String url = img.getAbsolutePath().replace(getServletContext().getRealPath("/"), "");
			
			resultStr += "{\"id\":\"" + id + "\", \"url\":\"" + url + "\"},"; 
		}
		
		if(resultStr.lastIndexOf(",") != -1)
		{
			resultStr = resultStr.substring(0, resultStr.lastIndexOf(","));
		}
		
		resultStr += "]";
		
		response.getWriter().write(resultStr);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		
		File imageDir = new File(getServletContext().getRealPath("/") + "images");
		imageDir.mkdirs();
		
		// check for room get variable
		if(request.getParameter("room") == null) {
			response.sendError(400, "'room' parameter missing!");
			return;
		}
		Integer roomId = Integer.parseInt(request.getParameter("room"));
		String roomPath = imageDir.getAbsolutePath() + File.separator + roomId.toString() + File.separator;
		out.print(roomPath + "\n");
		File roomDir = new File(roomPath);

	    // creates the save directory if it does not exists
        if (!roomDir.exists()) {
            roomDir.mkdir();
        }
        for (Part part : request.getParts()) {
        	out.print("Kacke is am dampfen");
            String fileName = this.randomString(30);
            part.write(roomDir + File.separator + fileName);
        }
        
    }
	
	protected String randomString( int len ) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		
		StringBuilder sb = new StringBuilder( len );
	    for( int i = 0; i < len; i++ ) 
	         sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	    return sb.toString();
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
