package org.example.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface _Logger<T> {
    Logger LOGGER = LogManager.getLogger(_Logger.class);
    default void logInfo(String message){
        LOGGER.info(message);
    }
    default void logError(String message)   {
        LOGGER.error(message);
    }
}
