package dev.div0.robotOperations.screen;

import dev.div0.Application;
import dev.div0.task.Task;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public class TakePageScreenshot extends TakeScreenshot{

    @Override
    protected boolean takeScreenshot() throws Exception{
        log("creating screenshot");

        String dirname = Application.screenshotsFolder+"task_"+ Task.id+"\\";
        File dir = new File(dirname);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            long millis = System.currentTimeMillis();
            String filename = "screenshot_"+millis+".png";
            //FileUtils.copyFile(scrFile, new File("c:\\consultant\\screenshots\\"+filename));
            FileUtils.copyFile(scrFile, new File(dirname+filename));
            log("screenshot "+dirname+filename+"  saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
