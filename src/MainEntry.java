import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainEntry {
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainView frame = new MainView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		int frameWidth = MainView.PANEL_WIDTH + 20;
		int frameHeight = MainView.PANEL_HEIGHT + 30;
				
		MainView mainView = new MainView();
		JFrame frame = new JFrame();
		frame.setSize(frameWidth, frameHeight);
		frame.setLayout(new GridLayout(10,20));
		frame.add(mainView);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
