package dev.div0.client.requestData;

import org.cometd.bayeux.server.ServerSession;

public class BaseRequestData {
	private ServerSession clientServerSession;
	
	public BaseRequestData(ServerSession clientServerSession){
		this.clientServerSession = clientServerSession;
	}
	
	public ServerSession getClientServerSession() {
		return clientServerSession;
	}

	public void setClientServerSessionId(ServerSession clientServerSession) {
		this.clientServerSession = clientServerSession;
	}
}
