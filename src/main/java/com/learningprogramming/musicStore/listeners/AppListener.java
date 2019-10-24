package com.learningprogramming.musicStore.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

@WebListener
public class AppListener implements ServletContextListener, ServletContextAttributeListener{

	private static final Logger logger = LoggerFactory.getLogger(AppListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Context...start");
		System.out.println("Context...start");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Context...Destroyed");
		System.out.println("Context...Destroyed");
	}
	
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		logger.info("Attribute {0} has been added, with value {1}", 
				new Object[] {
						event.getName(), event.getValue()
				});
		
		System.out.printf("Attribute {0} has been added, with value {1}", 
				new Object[] {
						event.getName(), event.getValue()
				});
	}
	
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		logger.info("Attribute {0} has been removed", event.getName());
		System.out.printf("Attribute {0} has been removed", event.getName());
	}
	
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		logger.info("Attribute {0} has been replaced, with value: {1}", 
				new Object[] {
						event.getName(), event.getValue()
				});
		
		System.out.printf("Attribute {0} has been replaced, with value: {1}", 
				new Object[] {
						event.getName(), event.getValue()
				});
	}
}
