package com.cws.rules.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleProcessor {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RuleProcessor.class);

	public RuleProcessor() {
	}

	public static KieContainer getKieContainer() {
		KieServices ks = KieServices.Factory.get();
		return ks.getKieClasspathContainer();
	}
	
	public static KieSession init() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		return kSession;

	}

	public static Object fireRule(KieContainer kContainer, String methodName,
			Object template) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		KieSession kSession = init();
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

	public static Object processOrderResumption() {

		return null;
	}

}
