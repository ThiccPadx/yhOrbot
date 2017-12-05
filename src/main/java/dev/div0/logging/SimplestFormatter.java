package dev.div0.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SimplestFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuilder builder = new StringBuilder(1000);
		
		//builder.append(formatMessage(record));
		builder.append(record.getMessage());
        builder.append("\n");
        return builder.toString();
	}

}
