package com.orad.Project3.controllers;

import com.orad.Project3.login.LoginManager.ClientType;
import com.orad.Project3.session.Session;
import com.orad.Project3.session.SessionContext;

public class ClientController {

	public boolean verifyToken(String token, SessionContext sessionContext, ClientType clientType) {
		// get session by token, check it exists and it's the correct clientType
		// if so then all good+reset last access
		Session session = sessionContext.getSession(token);
		if (session != null && session.getClientType().ordinal() == clientType.ordinal()) {
			session.resetLastAccessed();
			return true;
		} else {
			return false;
		}

	}

}
