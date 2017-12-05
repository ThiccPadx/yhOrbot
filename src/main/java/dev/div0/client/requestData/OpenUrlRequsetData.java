package dev.div0.client.requestData;

import org.cometd.bayeux.server.ServerSession;

public class OpenUrlRequsetData extends BaseRequestData {

	private String url;
	
	public OpenUrlRequsetData(ServerSession clientServerSession) {
		super(clientServerSession);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
