package chlorophyll;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;

public class ChlorophyllServelet extends HttpServlet implements Servlet {

	public String picture = null;
	private static final long serialVersionUID = -2410215321673656626L;

	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {

	    // Retrieve First Name from /Demo/ text field
	    String var = request.getParameter("dates");
	    System.out.println("Choice: " + var);
	
		//
	    picture = var;
	    request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In doGet");
		String option = req.getParameter("dates");
		System.out.println("Choice in get: " + option);
		System.out.println("picture is " + picture);
		resp.setContentType("image/jpg");
		
		ServletOutputStream out = resp.getOutputStream();
		BufferedImage image = null;
		
		Connect c = new Connect();
		if(picture != null){
		c.setTable(Integer.parseInt(picture));
		}
			double[][] nums;
			
				try {
					nums = c.run();
					image = buildImage(nums, 336, 482);
					System.out.println("the color is " + image.getRGB(1, 1));
					JPEGCodec.createJPEGEncoder(out).encode(image);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		
		
		out.close();
		
	
	}

	public BufferedImage buildImage(double [][] nums, int rows, int cols) {
		BufferedImage image = new BufferedImage(cols, rows, BufferedImage.TYPE_BYTE_INDEXED);
		System.out.println("Building Image");

		Graphics2D graphics = image.createGraphics();
		
		// Set back ground of the generated image to white
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 482, 336);
		
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
		
		return image;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

}
