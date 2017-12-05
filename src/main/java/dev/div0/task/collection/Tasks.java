package dev.div0.task.collection;

import java.util.HashMap;
import java.util.Map;

import dev.div0.task.Task;

public class Tasks {
	private Map<String, Task> collection = new HashMap<String, Task>();
	
	public Task get(String key){
		return collection.get(key);
	}
	public void add(String key, Task value){
		collection.put(key, value);
	}
}
