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
import com.cws.dto.PersonDTO;
import com.cws.model.Person;
import com.cws.rules.service.RuleService;
import com.cws.service.PersonNotFoundException;
import com.cws.service.PersonService;

/**
 * @author
 */
@Controller
@SessionAttributes("person")
public class PersonController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonController.class);

	protected static final String ERROR_MESSAGE_KEY_DELETED_PERSON_WAS_NOT_FOUND = "error.message.deleted.not.found";
	protected static final String ERROR_MESSAGE_KEY_EDITED_PERSON_WAS_NOT_FOUND = "error.message.edited.not.found";

	protected static final String FEEDBACK_MESSAGE_KEY_PERSON_CREATED = "feedback.message.person.created";
	protected static final String FEEDBACK_MESSAGE_KEY_PERSON_DELETED = "feedback.message.person.deleted";
	protected static final String FEEDBACK_MESSAGE_KEY_PERSON_EDITED = "feedback.message.person.edited";

	protected static final String MODEL_ATTIRUTE_PERSON = "person";
	protected static final String MODEL_ATTIRUTE_MOBILENUMBER = "mobilenumber";
	protected static final String MODEL_ATTRIBUTE_PERSONS = "persons";
	protected static final String MODEL_ATTIRUTE_PAGE = "page";

	protected static final String PERSON_ADD_FORM_VIEW = "person/create";
	protected static final String PERSON_EDIT_FORM_VIEW = "person/edit";
	protected static final String PERSON_RESUME_FORM_VIEW = "person/resume";
	protected static final String PERSON_LIST_VIEW = "person/list";
	protected static final String REDIRECT_PAGE = "person/redirect/";
	protected static final String REQUEST_MAPPING_LIST = "/";

	@Resource
	private PersonService personService;

	/**
	 * Processes delete person requests.
	 * 
	 * @param id
	 *            The id of the deleted person.
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/person/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes attributes) {
		LOGGER.debug("Deleting person with id: " + id);

		try {
			Person deleted = personService.delete(id);
			addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_PERSON_DELETED,
					deleted.getName());
		} catch (PersonNotFoundException e) {
			LOGGER.debug("No person found with id: " + id);
			addErrorMessage(attributes,
					ERROR_MESSAGE_KEY_DELETED_PERSON_WAS_NOT_FOUND);
		}

		return createRedirectViewPath(REQUEST_MAPPING_LIST);
	}

	@RequestMapping(value = "/person/resume/{id}", method = RequestMethod.GET)
	public String resumeOrder(@PathVariable("id") Long id, Model model) {
		LOGGER.debug("Resuming Order: " + id);

		Person person = personService.findById(id);
		String returnURL = "redirect:/person/resumeorder/";
		String resumeOrderToPage = "Not yet decided by Rules";
		KieContainer kContainer = null;
		try {
			kContainer = RuleService.getKieContainer();
		} catch (IOException e) {
			LOGGER.error("Error in reading the rools jar " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Unable to read Rules";
		}
		Object msg1 = RuleService.createTemplateObject(kContainer,
				person.getValidityCheck(),
				Boolean.parseBoolean(person.getValidityCheckValue()),
				person.getLastScreen());
		try {
			resumeOrderToPage = (String) RuleService.fireRule(kContainer,
					"getNextScreen", msg1);

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

	@RequestMapping(value = "/person/resumeorder/{page}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/person/create", method = RequestMethod.GET)
	public String showCreatePersonForm(Model model) {
		LOGGER.debug("Rendering create person form");

		model.addAttribute(MODEL_ATTIRUTE_PERSON, new PersonDTO());
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
	@RequestMapping(value = "/person/create", method = RequestMethod.POST)
	public String submitCreatePersonForm(
			@Valid @ModelAttribute(MODEL_ATTIRUTE_PERSON) PersonDTO created,
			BindingResult bindingResult, RedirectAttributes attributes) {
		LOGGER.debug("Create person form was submitted with information: "
				+ created);

		if (bindingResult.hasErrors()) {
			return PERSON_ADD_FORM_VIEW;
		}

		Person person = personService.create(created);

		addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_PERSON_CREATED,
				person.getName());

		return createRedirectViewPath(REQUEST_MAPPING_LIST);
	}

	/**
	 */
	@RequestMapping(value = "/person/validate", method = RequestMethod.GET)
	public String showValidateSIM(Model model) {
		LOGGER.debug("Rendering Validate Sim Page: ");

		model.addAttribute(MODEL_ATTIRUTE_MOBILENUMBER, new MobileNumberDTO());

		return PERSON_EDIT_FORM_VIEW;
	}

	/**
	 */
	@RequestMapping(value = "/person/validate", method = RequestMethod.POST)
	public String validateSIM(
			@Valid @ModelAttribute(MODEL_ATTIRUTE_MOBILENUMBER) MobileNumberDTO updated,
			BindingResult bindingResult) {
		LOGGER.debug("Validate Mobile number submitted with information: "
				+ updated);
		if (bindingResult.hasErrors()) {
			LOGGER.debug("Validate Mobile number  form contains validation errors. Rendering form view.");
			return PERSON_EDIT_FORM_VIEW;
		}
		String returnURL = "redirect:/person/resumeorder/";
		String resumeOrderToPage = "Sim Validation  - ";

		KieContainer kContainer = null;
		try {
			kContainer = RuleService.getKieContainer();
		} catch (IOException e) {
			LOGGER.error("Error in reading the rools jar " + e.getMessage());
			e.printStackTrace();
			return returnURL + "Unable to read Rules";
		}

		Object msg1 = RuleService.createTemplateObjectSIMValidation(kContainer,
				updated.getNetwork(), updated.getImei(),
				updated.isLuhnCheckDone());
		try {
			resumeOrderToPage += (Boolean) RuleService.fireRule(kContainer,
					"getSimValid", msg1);
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
	 * Processes the submissions of edit person form.
	 * 
	 * @param updated
	 *            The information of the edited person.
	 * @param bindingResult
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/person/edit", method = RequestMethod.POST)
	public String submitEditPersonForm(
			@Valid @ModelAttribute(MODEL_ATTIRUTE_PERSON) PersonDTO updated,
			BindingResult bindingResult, RedirectAttributes attributes) {
		LOGGER.debug("Edit person form was submitted with information: "
				+ updated);

		if (bindingResult.hasErrors()) {
			LOGGER.debug("Edit person form contains validation errors. Rendering form view.");
			return PERSON_EDIT_FORM_VIEW;
		}

		try {
			Person person = personService.update(updated);
			addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_PERSON_EDITED,
					person.getName());
		} catch (PersonNotFoundException e) {
			LOGGER.debug("No person was found with id: " + updated.getId());
			addErrorMessage(attributes,
					ERROR_MESSAGE_KEY_EDITED_PERSON_WAS_NOT_FOUND);
		}

		return createRedirectViewPath(REQUEST_MAPPING_LIST);
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

		List<Person> persons = personService.findAll();
		model.addAttribute(MODEL_ATTRIBUTE_PERSONS, persons);

		return PERSON_LIST_VIEW;
	}

	/**
	 * This setter method should only be used by unit tests
	 * 
	 * @param personService
	 */
	protected void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}