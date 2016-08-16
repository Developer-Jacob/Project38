package Model;

import java.awt.Font;
import java.awt.Label;

public class MyLabel extends Label {
	public MyLabel(int size) {
		// TODO Auto-generated constructor stub
		Font f = new Font("Serif", Font.BOLD, size);
		this.setFont(f);
	}
}
