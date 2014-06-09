package com.cws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class OrderController  extends AbstractController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderController.class);

	
	
	@RequestMapping(value = "/person/orderResume/{id}", method = RequestMethod.GET)
	public String getOrderResume(@PathVariable("id") Long id)
	{
		LOGGER.debug("Resuming Order: " + id);
		String resultingView = "No Page Found";
//		Order order= orderService.findById(id);
		
		return resultingView;
	}
	
	

}
