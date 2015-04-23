package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//import org.hibernate.Session;

//import model.*;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
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
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 * POST image?room={ROOMID}: Array of Filenames
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		File roomDir = new File(roomPath);

	    // creates the save directory if it does not exists
        if (!roomDir.exists()) {
            roomDir.mkdir();
        }
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        LinkedList<String> fileNames = new LinkedList<String>();
        for (Part part : request.getParts()) {
            String fileName = this.randomString(30);
            try {
            	fileName += getFileEnding(part);
            } catch(IOException e) {
            	response.sendError(403, e.getMessage());
            	return;
            }
            part.write(roomPath + File.separator + fileName);
            fileNames.push("\"" + fileName + "\"");
        }
        response.getOutputStream().print(fileNames.toString());
    }
	
	protected String randomString( int len ) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                Random rnd = new Random();
		
		StringBuilder sb = new StringBuilder( len );
	    for( int i = 0; i < len; i++ ) 
	         sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	    return sb.toString();
	}
	
	protected String getFileEnding( Part part ) throws IOException {
		if (part.getContentType().equals("image/png")) {
			return ".png";
		} else if (part.getContentType().equals("image/jpeg")) {
			return ".jpg";
		}
		throw new IOException("Unsupported filetype.");
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 * DELETE image?room={ROOMID}&id={FILENAME}: Boolean
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("room") == null) {
			response.sendError(400, "'room' parameter missing!");
			return;
		}
		
		if(request.getParameter("id") == null) {
			response.sendError(400, "'id' parameter missing!");
			return;
		}
		
		File imageDir = new File(getServletContext().getRealPath("/") + "images");
		imageDir.mkdirs();

		Integer roomId = Integer.parseInt(request.getParameter("room"));
		String roomPath = imageDir.getAbsolutePath() + File.separator + roomId.toString() + File.separator;

		File image = new File(roomPath + request.getParameter("id"));
		
		if(!image.exists()) {
			response.sendError(404, "File was not found.");
			return;
		}
		
		if(!image.delete()) {
			response.sendError(500, "Could not delete file.");
		}
		
		response.getOutputStream().print("true");
	}
}
