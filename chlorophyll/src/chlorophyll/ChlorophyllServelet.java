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
						
					double holder = db * 100;
			
						
					
					int calc = (int)holder;
					if(calc > 0){
					System.out.println(calc);
					}
					int green = 0 + calc;
					int blue = 200 - calc;
					int red = 0;
					
					if(green > 255){
						green = 255;
					}
					
					
					if(blue < 0){
						blue = 0;
					}
					
					if(red < 0){
						red = 0;
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
