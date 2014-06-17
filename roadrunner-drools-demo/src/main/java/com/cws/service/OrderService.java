package com.cws.service;

import java.util.List;

import com.cws.dto.OrderDTO;
import com.cws.model.Order;

/**
 * Declares methods used to obtain and modify Order information.
 * 
 * @author
 */
public interface OrderService {

	/**
	 * Creates a new Order.
	 * 
	 * @param created
	 *            The information of the created Order.
	 * @return The created Order.
	 */
	public Order create(OrderDTO created);

	/**
	 * Deletes a Order.
	 * 
	 * @param OrderId
	 *            The id of the deleted Order.
	 * @return The deleted Order.
	 * @throws OrderNotFoundException
	 *             if no Order is found with the given id.
	 */
	public Order delete(Long OrderId) throws OrderNotFoundException;

	/**
	 * Finds all Orders.
	 * 
	 * @return A list of Orders.
	 */
	public List<Order> findAll();

	/**
	 * Finds Order by id.
	 * 
	 * @param id
	 *            The id of the wanted Order.
	 * @return The found Order. If no Order is found, this method returns null.
	 */
	public Order findById(Long id);

	/**
	 * Updates the information of a Order.
	 * 
	 * @param updated
	 *            The information of the updated Order.
	 * @return The updated Order.
	 * @throws OrderNotFoundException
	 *             if no Order is found with given id.
	 */
	// public Order update(OrderDTO updated) throws OrderNotFoundException;
}
