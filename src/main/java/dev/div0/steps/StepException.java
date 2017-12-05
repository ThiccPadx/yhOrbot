package dev.div0.steps;

public class StepException extends Exception {
	private int stepId;
	
	public StepException(String message, int stepId){
		super(message);
		this.stepId = stepId;
	}

	public int getStepId() {
		return stepId;
	}
}
