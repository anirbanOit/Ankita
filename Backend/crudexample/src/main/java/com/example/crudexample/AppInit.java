package com.example.crudexample;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.crudexample.utils.constant.AppConstant;

@Component
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		AppConstant.setHttpStatusCode();
	}
}
