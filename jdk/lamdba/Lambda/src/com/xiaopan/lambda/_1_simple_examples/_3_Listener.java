package com.xiaopan.lambda._1_simple_examples;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @Package: com.xiaopan.lambda._1_simple_examples
 * @ClassName: _3_Listener
 * @author: panhuaxiong
 * @date: 2017��8��17�� ����10:16:09
 * @Description: lambda�Ż�Listener
 */
public class _3_Listener {

	public static void main(String[] args) {
		
		//ͨ�������ڲ�����Ӽ����¼�
		JButton testButton = new JButton("Test Button");
//		testButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Click Detected by Anon Class");
//			}
//		});
		
		//ͨ��lambda��Ӽ����¼�
		testButton.addActionListener(e -> System.out.println("Clieck Detected by lambda Listener"));
		
		JFrame frame = new JFrame("Listener Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(testButton, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
