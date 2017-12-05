package dev.div0.logging;

import java.util.Map;

import dev.div0.Application;
import dev.div0.Server;
import org.json.simple.JSONObject;

import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerSession;

public class ServerInfoLogger extends BaseLogger {
	
	public ServerSession clientServerSession;
	
	@Override
	protected void log(String message){
		super.log(message); 
		sendMessage(message);
	}
	
	protected void logToClient(String message){
		super.log(message);
		sendMessage(message);
	}
	
	// Server.serverSession and Server.clientServerSession not null but messages partially not received by client
	private void sendMessage(String message)
	{
		ServerChannel channel = Server.bayeux.getChannel("/message");
		//Server.bayeux.createIfAbsent(channelName);
		
		JSONObject sendData = new JSONObject();
    	sendData.put("data", message);
		
    	if(channel!=null)
    	{
    		channel.publish(Server.serverSession, sendData.toString());
    	}
    	else
    	{
    		System.err.println("CHANNEL " + "/message" + "  IS NULL");
    	}
		
		
		/*
		if(Server.serverSession!=null && Server.clientServerSession!=null){
			Server.clientServerSession.deliver(Server.serverSession, "/message", message);
		}
		else{
			System.err.println("Server error. Server.serverSession="+Server.serverSession+"  clientServerSession="+clientServerSession);
		}
		*/
	}
}
