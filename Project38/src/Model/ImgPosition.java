package Model;


public class ImgPosition{
	public static ImgPosition position_Yourimg;
	public static ImgPosition position_Yourcard1;
	public static ImgPosition position_Yourcard2;
	public static ImgPosition position_Myimg;
	public static ImgPosition position_Mycard1;
	public static ImgPosition position_Mycard2;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public ImgPosition() {
		// TODO Auto-generated constructor stub
			position_Yourimg = new ImgPosition(0,0,100,120);
			position_Yourcard1 = new ImgPosition(815,10, 80, 115);
			position_Yourcard2 = new ImgPosition(905, 10, 80, 115);
			position_Myimg = new ImgPosition(450,0,100,120);
			position_Mycard1 = new ImgPosition(815,10, 80, 115);
			position_Mycard2 = new ImgPosition(905, 10, 80, 115);
	}
	public ImgPosition(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}