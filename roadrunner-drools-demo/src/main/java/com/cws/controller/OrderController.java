package com.cws.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cws.dto.MobileNumberDTO;
import com.cws.dto.OrderDTO;
import com.cws.model.Order;
import com.cws.rules.service.RuleProcessor;
import com.cws.rules.service.RuleService;
import com.cws.service.OrderNotFoundException;
import com.cws.service.OrderService;

/**
 * @author
 */
@Controller
@SessionAttributes("order")
public class OrderController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderController.class);

	protected static final String ERROR_MESSAGE_KEY_DELETED_PERSON_WAS_NOT_FOUND = "error.message.deleted.not.found";
	protected static final String ERROR_MESSAGE_KEY_EDITED_PERSON_WAS_NOT_FOUND = "error.message.edited.not.found";

	protected static final String FEEDBACK_MESSAGE_KEY_PERSON_CREATED = "feedback.message.person.created";
	protected static final String FEEDBACK_MESSAGE_KEY_PERSON_DELETED = "feedback.message.person.deleted";
	protected static final String FEEDBACK_MESSAGE_KEY_PERSON_EDITED = "feedback.message.person.edited";

	protected static final String MODEL_ATTIRUTE_PERSON = "order";
	protected static final String MODEL_ATTIRUTE_MOBILENUMBER = "mobilenumber";
	protected static final String MODEL_ATTRIBUTE_PERSONS = "orders";
	protected static final String MODEL_ATTIRUTE_PAGE = "page";

	protected static final String PERSON_ADD_FORM_VIEW = "order/create";
	protected static final String PERSON_EDIT_FORM_VIEW = "order/edit";
	protected static final String PERSON_RESUME_FORM_VIEW = "order/resume";
	protected static final String PERSON_LIST_VIEW = "order/list";
	protected static final String REDIRECT_PAGE = "order/redirect/";
	protected static final String REQUEST_MAPPING_LIST = "/";

	@Resource
	private OrderService orderService;

	/**
	 * Processes delete person requests.
	 * 
	 * @param id
	 *            The id of the deleted person.
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/order/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes attributes) {
		LOGGER.debug("Deleting order with id: " + id);

		try {
			Order deleted = orderService.delete(id);
			addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_PERSON_DELETED,
					deleted.getName());
		} catch (OrderNotFoundException e) {
			LOGGER.debug("No person found with id: " + id);
			addErrorMessage(attributes,
					ERROR_MESSAGE_KEY_DELETED_PERSON_WAS_NOT_FOUND);
		}

		return createRedirectViewPath(REQUEST_MAPPING_LIST);
	}

	@RequestMapping(value = "/order/resume/{id}", method = RequestMethod.GET)
	public String resumeOrder(@PathVariable("id") Long id, Model model) {
		LOGGER.debug("Resuming Order: " + id);

		Order order = orderService.findById(id);
		OrderDTO orderDTO = OrderDTO.getBuilder(order.getFirstName(),
				order.getLastName(), order.getLastScreen(),
				order.getPropositionCheck(), order.getExtraCheck(),
				order.getGiftCheck(), order.getInsurance(),
				order.getProofsValid(), order.getCreditCheckValid(),
				order.getFraudCheckValid()).build();
		String returnURL = "redirect:/order/resumeTo/";
		String resumeOrderToPage = "Not yet decided by Rules";
		KieContainer kContainer = null;
		/*
		 * Calling the web based drools workbench
		 */
		// try {
		// kContainer = RuleService.getKieContainer();
		/*
		 * Calling the stand alone RuleProcessor
		 */
		kContainer = RuleProcessor.getKieContainer();
		// } catch (IOException e) {
		// LOGGER.error("Error in reading the rools jar " + e.getMessage());
		// e.printStackTrace();
		// return returnURL + "Unable to read Rules";
		// }
		// Object msg1 = RuleService.createResuemOrderTemplate
		// (kContainer, order.getPropositionCheck(), order.getExtraCheck(),
		// order.getGiftCheck(), order.getInsurance(), order.getProofsValid(),
		// order.getCreditCheckValid(), order.getFraudCheckValid(),
		// order.getLastScreen());
//		Object msg1 = RuleProcessor.createResuemOrderTemplate(kContainer,
//				order.getPropositionCheck(), order.getExtraCheck(),
//				order.getGiftCheck(), order.getInsurance(),
//				order.getProofsValid(), order.getCreditCheckValid(),
//				order.getFraudCheckValid(), order.getLastScreen());
		try {
			// resumeOrderToPage = (String) RuleService.fireRule(kContainer,
			// "getNextScreen", msg1);
			resumeOrderToPage = (String) RuleProcessor.fireRule(kContainer,
					"getNextScreen", orderDTO);

		} catch (NoSuchMethodException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!";
		} catch (SecurityException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!!";
		} catch (IllegalAccessException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!!";
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!!";
		} catch (InvocationTargetException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!!";
		}
		// kSession.dispose();
		if (StringUtils.isEmpty(resumeOrderToPage)) {
			resumeOrderToPage = "No Rules Defined, Go to next screen";
		}
		return returnURL + resumeOrderToPage;
	}

	@RequestMapping(value = "/order/resumeTo/{page}", method = RequestMethod.GET)
	public String resumeOrderTo(@PathVariable("page") String page, Model model) {
		LOGGER.debug("Rendering the page after order resume: " + page);

		model.addAttribute(MODEL_ATTIRUTE_PAGE, page);

		return PERSON_RESUME_FORM_VIEW;
	}

	/**
	 * Processes create person requests.
	 * 
	 * @param model
	 * @return The name of the create person form view.
	 */
	@RequestMapping(value = "/order/create", method = RequestMethod.GET)
	public String showCreatePersonForm(Model model) {
		LOGGER.debug("Rendering create person form");

		model.addAttribute(MODEL_ATTIRUTE_PERSON, new OrderDTO());
		return PERSON_ADD_FORM_VIEW;
	}

	/**
	 * Processes the submissions of create person form.
	 * 
	 * @param created
	 *            The information of the created persons.
	 * @param bindingResult
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/order/create", method = RequestMethod.POST)
	public String submitCreatePersonForm(
			@Valid @ModelAttribute(MODEL_ATTIRUTE_PERSON) OrderDTO created,
			BindingResult bindingResult, RedirectAttributes attributes) {
		LOGGER.debug("Create person form was submitted with information: "
				+ created);

		if (bindingResult.hasErrors()) {
			return PERSON_ADD_FORM_VIEW;
		}

		Order order = orderService.create(created);

		addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_PERSON_CREATED,
				order.getName());

		return createRedirectViewPath(REQUEST_MAPPING_LIST);
	}

	/**
	 */
	@RequestMapping(value = "/order/validate", method = RequestMethod.GET)
	public String showValidateSIM(Model model) {
		LOGGER.debug("Rendering Validate Sim Page: ");

		model.addAttribute(MODEL_ATTIRUTE_MOBILENUMBER, new MobileNumberDTO());

		return PERSON_EDIT_FORM_VIEW;
	}

	/**
	 */
	@RequestMapping(value = "/order/validate", method = RequestMethod.POST)
	public String validateSIM(
			@Valid @ModelAttribute(MODEL_ATTIRUTE_MOBILENUMBER) MobileNumberDTO updated,
			BindingResult bindingResult) {
		LOGGER.debug("Validate Mobile number submitted with information: "
				+ updated);
		if (bindingResult.hasErrors()) {
			LOGGER.debug("Validate Mobile number  form contains validation errors. Rendering form view.");
			return PERSON_EDIT_FORM_VIEW;
		}
		String returnURL = "redirect:/order/resumeTo/";
		String resumeOrderToPage = "Sim Validation  - ";

		KieContainer kContainer = null;
//		try {
//			kContainer = RuleService.getKieContainer();
			kContainer = RuleProcessor.getKieContainer();
//		} catch (IOException e) {
//			LOGGER.error("Error in reading the rools jar " + e.getMessage());
//			e.printStackTrace();
//			return returnURL + "Unable to read Rules";
//		}

//		Object msg1 = RuleService.createSIMValidationTemplate(kContainer,
//				updated.getNetwork(), updated.getImei(),
//				updated.isLuhnCheckDone());
		try {
//			resumeOrderToPage += (Boolean) RuleService.fireRule(kContainer,
//					"getSimValid", msg1);
			resumeOrderToPage += (Boolean) RuleProcessor.fireRule(kContainer,
					"isSimValid", updated);
		} catch (NoSuchMethodException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!";
		} catch (SecurityException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!!";
		} catch (IllegalAccessException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!!";
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!!";
		} catch (InvocationTargetException e) {
			LOGGER.error("Error in Rule retrival " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Error in Rule retrival!!!";
		}
		// kSession.dispose();
		if (StringUtils.isEmpty(resumeOrderToPage)) {
			resumeOrderToPage = "No Rules Defined";
		}
		return returnURL + resumeOrderToPage;
	}

	/**
	 * Processes requests to home page which lists all available persons.
	 * 
	 * @param model
	 * @return The name of the person list view.
	 */
	@RequestMapping(value = REQUEST_MAPPING_LIST, method = RequestMethod.GET)
	public String showList(Model model) {
		LOGGER.debug("Rendering person list page");

		List<Order> orders = orderService.findAll();
		model.addAttribute(MODEL_ATTRIBUTE_PERSONS, orders);

		return PERSON_LIST_VIEW;
	}

	/**
	 * This setter method should only be used by unit tests
	 * 
	 * @param orderService
	 */
	protected void setorderService(OrderService orderService) {
		this.orderService = orderService;
	}

}