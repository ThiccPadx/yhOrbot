package dev.div0.robotOperations.bidding;

import dev.div0.robotOperations.OperationException;
import org.openqa.selenium.WebDriver;

public interface IBiddingStrategy {
    boolean execute(int userMoney, WebDriver WebDriver) throws OperationException;
}
