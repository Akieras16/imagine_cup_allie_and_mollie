package chlorophyll;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;

/** this is the servlet class which controls the logic on the webpage
 * 
 * @author Allie
 *
 */
public class ChlorophyllServelet extends HttpServlet implements Servlet {

	//creates some basic string variables to be used later
	public String picture = null;
	private static final long serialVersionUID = -2410215321673656626L;

	/** a post request which, when the "Submit" button on the form is clicked,
	 * recieves the selected month and translates that number into an integer value.
	 */
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {

	    // Retrieve the dates parameter from the controls field
	    String var = request.getParameter("dates");
	    System.out.println("Choice: " + var);
	
		//saves the retrieved variable in the picture string and reloads the page
	    picture = var;
	    request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	//uses the doGet method to retrieve the selected data and returns a response in the form of an image
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In doGet");
		String option = req.getParameter("dates");
		System.out.println("Choice in get: " + option);
		System.out.println("picture is " + picture);
		resp.setContentType("image/jpg");
		
	
		ServletOutputStream out = resp.getOutputStream();
		
		//starts building the image
		BufferedImage image = null;
		
		//creates a Connect object to access the database
		Connect c = new Connect();
		
		/** if picture, which is storing the selected value, is not null, start building an image
		 * 
		 */
		if(picture != null){
		c.setTable(Integer.parseInt(picture));
		}
			double[][] nums;
			
				try {
					
					//fill the double array nums with the retrieved data
					nums = c.run();
					
					//calls buildImage to make the image
					image = buildImage(nums, 336, 482);
					
					//encodes the image and sends it to the url being accessed in home.jsp
					JPEGCodec.createJPEGEncoder(out).encode(image);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		
		
		out.close();
		
	
	}

	//builds the image from a double array
	public BufferedImage buildImage(double [][] nums, int rows, int cols) throws IOException {
		
		//a pre-generated background map of the Bay of Bengal
		File imageFile = new File("puremap.jpg");
		BufferedImage image;
		
			//fills the BufferedImage with the backround map
			image = ImageIO.read(getClass().getResource("/puremap.jpg"));
			if(image != null){
				System.out.println("map exists");
		
			}
		
		System.out.println("Building Image");
		
		//creates a graphics object to draw on the map
		Graphics2D graphics = image.createGraphics();
	
			/** draws a point on the map at every value where the clorophyll appears.
			 * The color of the point is based on the intensity of the pigment, creating a
			 * heat map of chlorophyll in the bay
			 */
			for(int i = 0; i < nums.length; i++){
				for(int j = 0; j < nums[i].length; j++){
					double db = nums[i][j];
					
					if(db != -1){
						int red = 0;
						int green = 0; 
						int blue = 0;
						
						if(db > .1 && db < .3){
							red = 122;
							green = 5;
							blue = 247;
						}
						
						if(db > .3 && db < .5){
							red = 89;
							green = 9;
							blue = 219;
						}
						
						if(db > .5 && db < .7){
							red = 54;
							green = 5;
							blue = 232;
						}
						
						if(db > .7 && db < .9){
							red = 27;
							green = 62;
							blue = 222;
						}
						
						if( db > .9 && db < 1.1){
							red = 7;
							green = 183;
							blue = 237;
						}
						
						if(db > 1.1 && db < 1.3){
							red = 7;
							green = 237;
							blue = 237;
						}
						
						if(db > 1.3 && db < 1.5){
							red = 7;
							green = 237;
							blue = 210;
						}
						
						if(db > 1.5 && db < 1.7){
							red = 7;
							green = 237;
							blue = 95;
						}
						
						if(db > 1.7 && db < 1.9){
							red = 7; 
							green = 237;
							blue = 26;
						}
						
						if(db > 1.9 && db < 2.1){
							red = 80;
							green = 237;
							blue = 7;
						}
						
						if(db > 2.1 && db < 2.3){
							red = 149;
							green = 237;
							blue = 7;
						}
						
						if(db > 2.3 && db < 2.5){
							red = 214;
							green = 237;
							blue = 7;
						}
						
						if(db > 2.5 && db < 2.7){
							red = 237;
							green = 233;
							blue = 7;
						}
						
						if(db > 2.7 && db < 2.9){
							red = 237;
							green = 180;
							blue = 7;
						}
						
						if(db > 2.9 && db < 3.1){
							red = 237;
							green = 153;
							blue = 7;
						}
						
						if(db > 3.1 && db < 3.3){
							red = 237;
							green = 88;
							blue = 7;
						}
						
						if(db > 3.3 && db < 3.5){
							red = 237;
							green = 57;
							blue = 7;
						}
						
						if(db > 3.5){
							red = 237;
							green = 38;
							blue = 7;
						}
					Color color = new Color(red, green, blue);
					graphics.setColor(color);
					graphics.drawLine(j, i, j, i);
				} 
				}
			}
					//returns the created image
					return image;
		
		
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	}

