package com.automic.hpqc.report;

import org.apache.fop.events.Event;
import org.apache.fop.events.EventFormatter;
import org.apache.fop.events.EventListener;
import org.apache.fop.events.model.EventSeverity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** A logging event listener that writes the events to log file. */
public class LoggingEventListener implements EventListener {

    private static final Logger LOGGER = LogManager.getLogger(LoggingEventListener.class);

    public void processEvent(Event event) {
        String msg = EventFormatter.format(event);
        EventSeverity severity = event.getSeverity();
        if (severity == EventSeverity.INFO) {
            LOGGER.info(msg);
        } else if (severity == EventSeverity.WARN) {
            LOGGER.warn(msg);
        } else if (severity == EventSeverity.ERROR) {
            LOGGER.error(msg);
        } else if (severity == EventSeverity.FATAL) {
            LOGGER.fatal(msg);
        } else {
            LOGGER.error(msg);
        }
    }
}
