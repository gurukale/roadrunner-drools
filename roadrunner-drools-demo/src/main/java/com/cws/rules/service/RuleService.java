package com.cws.rules.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RuleService.class);

//	private static final String RULE_ULR = "http://localhost:9090/kie-drools-wb-distribution-wars-6.0.1.Final-tomcat7.0/maven2/com/order/resumption/ResumeOrder/1.0-RELEASE/ResumeOrder-1.0-RELEASE.jar";
	private static final String RULE_ULR = "http://localhost:9090/kie-drools-wb-distribution-wars-6.0.1.Final-tomcat7.0/maven2/com/cpw/drools/DroolsDemo/1.0.0/DroolsDemo-1.0.0.jar";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	private static final String BASICAUTHENTICATION = "enabled";
	private static final String KEISESSION = "defaultKieSession";

	public static KieContainer getKieContainer() throws IOException {
		KieServices ks = KieServices.Factory.get();
		KieRepository kr = ks.getRepository();
		UrlResource urlResource = (UrlResource) ks.getResources()
				.newUrlResource(RULE_ULR);
		urlResource.setBasicAuthentication(BASICAUTHENTICATION);
		urlResource.setUsername(USERNAME);
		urlResource.setPassword(PASSWORD);
		InputStream is = null;
		is = urlResource.getInputStream();
		KieModule kModule = kr.addKieModule(ks.getResources()
				.newInputStreamResource(is));
		KieContainer kContainer = ks.newKieContainer(kModule.getReleaseId());

		return kContainer;
	}

	public static Object fireRule(KieContainer kContainer, String methodName,
			Object template) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		KieSession kSession = kContainer.newKieSession(KEISESSION);
		kSession.insert(template);
		kSession.fireAllRules();
		Method method = null;
		method = template.getClass().getDeclaredMethod(methodName);
		return method.invoke(template);
//		kSession.dispose();		 
	}

	public static Object createResuemOrderTemplate(KieContainer kContainer,
			boolean propostionCheck, 
			boolean extraCheck, 
			boolean giftCheck, 
			boolean insuranceCheck, 
			boolean proofsValid, 
			boolean creditCheckValid, 
			boolean fraudCheckValid, 
			String lastScreen
) {

		Object o = null;
		String templateObject = "Order";
		LOGGER.debug("Running rule on template " + templateObject
				+ " \n-values - propostionCheck " + propostionCheck
				+ " extraCheck " + extraCheck
				+ " giftCheck " + giftCheck
				+ " insuranceCheck " + insuranceCheck
				+ " proofsValid " + proofsValid
				+ " creditCheckValid " + creditCheckValid
				+ " fraudCheckValid " + fraudCheckValid
				+ " lastScreen " + lastScreen);
		// String ruleTemplateClass="com.order.resumption."+templateObject;
		String ruleTemplateClass = "com.cpw.order."
				+ templateObject;
		try {
			Class cl = kContainer.getClassLoader().loadClass(ruleTemplateClass);
			// o = cl.getConstructor(new Class[]{
			// Boolean.class,String.class,String.class}).newInstance(validityCheckScenario,lastScreen,"");
			o = cl.getConstructor(
					new Class[] { 
							Boolean.class, 
							Boolean.class,
							Boolean.class,
							Boolean.class,
							Boolean.class,
							Boolean.class,
							Boolean.class,
							String.class,
							String.class 
							}).
							newInstance(
									 propostionCheck, 
									 extraCheck, 
									 giftCheck, 
									 insuranceCheck, 
									 proofsValid, 
									 creditCheckValid, 
									 fraudCheckValid, 
									 lastScreen, "");

		} catch (Exception e) {
			LOGGER.error(" Error in creating Template " + e.getMessage());
			e.printStackTrace();
		}
		return o;
	}

	
	public static Object createTemplateObject(KieContainer kContainer,
			String validityCheck, boolean validityCheckScenario,
			String lastScreen) {

		Object o = null;
		String templateObject = "ResumeOrder";
		LOGGER.debug("Running rule on template " + templateObject
				+ " \n-values - validityCheck " + validityCheck
				+ " validityCheckScenario " + validityCheckScenario
				+ " lastScreen " + lastScreen);
		// String ruleTemplateClass="com.order.resumption."+templateObject;
		String ruleTemplateClass = "com.order.resumption.resumeorder."
				+ templateObject;
		try {
			Class cl = kContainer.getClassLoader().loadClass(ruleTemplateClass);
			// o = cl.getConstructor(new Class[]{
			// Boolean.class,String.class,String.class}).newInstance(validityCheckScenario,lastScreen,"");
			o = cl.getConstructor(
					new Class[] { Boolean.class, String.class, String.class,
							String.class }).newInstance(validityCheckScenario,
					validityCheck, lastScreen, "");

		} catch (Exception e) {
			LOGGER.error(" Error in creating Template " + e.getMessage());
			e.printStackTrace();
		}
		return o;
	}
	public static Object createSIMValidationTemplate(
			KieContainer kContainer, String network, String imei,
			boolean luhnCheckDone) {
		Object o = null;
		String templateObject = "Sim";
		LOGGER.debug("Running rule on template " + templateObject
				+ " \n-values - network " + network + " imei " + imei
				+ " PREFIX " + imei.subSequence(0, 6) + " length "
				+ imei.length() + " luhnCheckDone " + luhnCheckDone);
		// String ruleTemplateClass="com.order.resumption."+templateObject;
		String ruleTemplateClass = "com.cpw.sim." + templateObject;
		try {
			Class cl = kContainer.getClassLoader().loadClass(ruleTemplateClass);
//			public Sim(java.lang.String simNumber, java.lang.String network, java.lang.Short length, 
//					java.lang.String startsWith, 
//					java.lang.Boolean luhnCheck, java.lang.Boolean simValid)
			o = cl.getConstructor(
					new Class[] { String.class, String.class, Short.class, String.class,
							 Boolean.class, Boolean.class })
					.newInstance(imei,network, Short.parseShort("" + imei.length()),
							imei.subSequence(0, 6),
							Boolean.valueOf(luhnCheckDone),
							Boolean.FALSE );

		} catch (Exception e) {
			LOGGER.error(" Error in creating Template " + e.getMessage());
			e.printStackTrace();
		}
		return o;
	}

	
	public static Object createTemplateObjectSIMValidation(
			KieContainer kContainer, String network, String imei,
			boolean luhnCheckDone) {

		Object o = null;
		String templateObject = "mobileNumber";
		LOGGER.debug("Running rule on template " + templateObject
				+ " \n-values - network " + network + " imei " + imei
				+ " PREFIX " + imei.subSequence(0, 6) + " length "
				+ imei.length() + " luhnCheckDone " + luhnCheckDone);
		// String ruleTemplateClass="com.order.resumption."+templateObject;
		String ruleTemplateClass = "com.order.imei." + templateObject;
		try {
			Class cl = kContainer.getClassLoader().loadClass(ruleTemplateClass);
			// String network, String imei, String startsWith, Short length,
			// Boolean simValid, Boolean luhnCheck)
//			public Sim(java.lang.String simNumber, java.lang.String network, java.lang.Short length, 
//					java.lang.String startsWith, 
//					java.lang.Boolean luhnCheck, java.lang.Boolean simValid)
			o = cl.getConstructor(
					new Class[] { String.class, String.class, String.class,
							Short.class, Boolean.class, Boolean.class })
					.newInstance(network, imei, imei.subSequence(0, 6),
							Short.parseShort("" + imei.length()),
							Boolean.FALSE, Boolean.valueOf(luhnCheckDone));

		} catch (Exception e) {
			LOGGER.error(" Error in creating Template " + e.getMessage());
			e.printStackTrace();
		}
		return o;
	}

}
