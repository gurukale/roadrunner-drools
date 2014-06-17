package com.cws.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cws.dto.OrderDTO;
import com.cws.model.Order;
import com.cws.repository.OrderRepository;

/**
 * This implementation of the OrderService interface communicates with the
 * database by using a Spring Data JPA repository.
 * 
 * @author
 */
@Service
public class RepositoryOrderService implements OrderService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RepositoryOrderService.class);

	@Resource
	private OrderRepository OrderRepository;

	@Transactional
	@Override
	public Order create(OrderDTO created) {
		LOGGER.debug("Creating a new Order with information: " + created);

		Order order = Order.getBuilder(created.getFirstName(),
				created.getLastName(), created.getLastScreen(),
				created.isPropositionCheck(), created.isExtraCheck(),
				created.isGiftCheck(), created.isInsurance(),
				created.isProofsValid(), created.isCreditCheckValid(),
				created.isFraudCheckValid()

		).build();

		return OrderRepository.save(order);
	}

	@Transactional(rollbackFor = OrderNotFoundException.class)
	@Override
	public Order delete(Long OrderId) throws OrderNotFoundException {
		LOGGER.debug("Deleting Order with id: " + OrderId);

		Order deleted = OrderRepository.findOne(OrderId);

		if (deleted == null) {
			LOGGER.debug("No Order found with id: " + OrderId);
			throw new OrderNotFoundException();
		}

		OrderRepository.delete(deleted);
		return deleted;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Order> findAll() {
		LOGGER.debug("Finding all Orders");
		return OrderRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Order findById(Long id) {
		LOGGER.debug("Finding Order by id: " + id);
		return OrderRepository.findOne(id);
	}

//	@Transactional(rollbackFor = OrderNotFoundException.class)
//	@Override
//	public Order update(OrderDTO updated) throws OrderNotFoundException {
//		LOGGER.debug("Updating Order with information: " + updated);
//
//		Order Order = OrderRepository.findOne(updated.getId());
//
//		if (Order == null) {
//			LOGGER.debug("No Order found with id: " + updated.getId());
//			throw new OrderNotFoundException();
//		}
//
//		Order.update(updated.getFirstName(), updated.getLastName(),
//				updated.getLastScreen());
//
//		return Order;
//	}

	/**
	 * This setter method should be used only by unit tests.
	 * 
	 * @param OrderRepository
	 */
	protected void setOrderRepository(OrderRepository OrderRepository) {
		this.OrderRepository = OrderRepository;
	}
}
