package dev.div0.robotOperations.google;

import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;

public class GetGoogleSearchResultsTotalOperation extends BaseOperation {
	
	@Override
	public boolean execute() throws OperationException{
		
		log("GetGoogleSearchResultsTotalOperation Payload: "+operationData.getPayload());
		return true;
	}
}
