package chlorophyll;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.image.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;

public class ChlorophyllServelet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -2410215321673656626L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("image/jpg");
		ServletOutputStream out = resp.getOutputStream();
		BufferedImage image = null;
		Connect c = new Connect();
		
			double[][] nums;
			try {
				nums = c.run();
				image = buildImage(nums, 336, 336);
				System.out.println("the color is " + image.getRGB(1, 1));
				JPEGCodec.createJPEGEncoder(out).encode(image);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
		
		out.close();
	}

	public BufferedImage buildImage(double [][] nums, int rows, int cols) throws SQLException{
		BufferedImage image = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);
		System.out.println("Building Image");
		
		
		
		int r = 1;
		for(int i = 0; i < nums.length; i++){
			for(int j = 0; j < nums[i].length; j++){
				double db = nums[i][j];
				
					
					Color c = new Color(26, 140, 255);
					image.setRGB(r, i, c.getRGB());
					
				/**
				
				else{
					int calc = (int) db * 10;
					int green = 140 + calc;
					int blue = 255 - calc;
					int red = 255 - calc * 2;
					
					if(green > 255){
						green = 255;
					}
					
					if(red < 0){
						red = 0;
					}
					
					if(blue < 0){
						blue = 0;
					}
					
					Color color = new Color(red, green, blue);
					image.setRGB(r, i, color.getRGB());
				} **/
			}
			
		}
		
		return image;
	}
}
