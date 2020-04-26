package com.abeldevelop.petclinic.library.common.component;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class MessageFormatter {

	public String format(String message, List<Object> arguments) {
		if(CollectionUtils.isEmpty(arguments)) {
			return message;
		}
		return org.slf4j.helpers.MessageFormatter.arrayFormat(message, arguments.toArray()).getMessage();
	}
}
