package com.orad.Project3.session;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.orad.Project3.login.LoginManager.ClientType;
import com.orad.Project3.service.ClientService;

@Component
@Scope("prototype")
public class Session {

	public final String token;
	private long lastAccessed;
	@Value("${session.max.inactive.interval:1}")
	private long maxInactiveInterval; // milisec
	private static final int TOKEN_MAX_LENGTH = 15;
	private ClientService service;
	private ClientType clientType;

	{
		this.token = UUID.randomUUID().toString().replace("-", "").substring(0, TOKEN_MAX_LENGTH);
		resetLastAccessed();
	}

	public void resetLastAccessed() {
		this.lastAccessed = System.currentTimeMillis();
	}

	@PostConstruct
	private void init() {
		maxInactiveInterval = TimeUnit.MINUTES.toMillis(maxInactiveInterval);
	}

	public long getMaxInactiveInterval() {
		return maxInactiveInterval;
	}

	public long getLastAccessed() {
		return lastAccessed;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public ClientService getService() {
		return service;
	}

	public void setService(ClientService service) {
		this.service = service;
	}

}
