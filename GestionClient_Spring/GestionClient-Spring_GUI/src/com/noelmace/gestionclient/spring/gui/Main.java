package com.noelmace.gestionclient.spring.gui;

import java.awt.EventQueue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.noelmace.gestionclient.spring.gui.frames.MainFrame;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("guiSpringContext.xml");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = (MainFrame) context.getBean("mainFrame");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
