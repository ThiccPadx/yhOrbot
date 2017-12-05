package dev.div0.logging;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.cometd.bayeux.ChannelId;
import org.cometd.bayeux.server.ServerMessage;

public class BaseServerMessage implements ServerMessage {

	@Override
	public Map<String, Object> getAdvice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelId getChannelId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClientId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMeta() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPublishReply() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getDataAsMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getExt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object put(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mutable getAssociated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLazy() {
		// TODO Auto-generated method stub
		return false;
	}

}
