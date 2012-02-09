/*
 * Copyright (c) 2011 Topmedia Company
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.topmedia.monitor;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ThrowableInformation;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vn.topmedia.monitor.common.MonitorLevel;
import vn.topmedia.monitor.common.MonitorType;

/**
 * Monitor info. Log info to Rabbit queue
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class MonitorServer {

    private static AbstractApplicationContext context;
    private Class clazz;
    /**
     * The logger.
     */
    private static Logger logger = null;
    /**
     * Rabbit template send message to queue
     */
    private RabbitTemplate rabbitTemplate;

    public MonitorServer(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Config to RabbitMQ by Spring AMQP.
     *
     * @return RabbitTemplate
     */
    private RabbitTemplate getRabbitTemplate() {
        if (rabbitTemplate == null) {
            try {
                if (context == null) {
                    context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/monitor-rabbit-server.xml");
                    context.registerShutdownHook();
                }
                rabbitTemplate = context.getBean(RabbitTemplate.class);
            } catch (Exception ex) {
                logger.error("Error create RabbitTemplate monitor plugin. Dont alert info to monitor client.", ex);
            }
        }
        return rabbitTemplate;
    }

    public void resetRabbitTemplate() {
        context = null;
        rabbitTemplate = null;
    }

    /**
     * Log message to RabbitMQ and log4j.
     *
     * @param level Level
     * @param info Message
     * @param isLogger Use log4j or not.
     */
    private void log(Level level, MonitorBean info, boolean isLogger) {
        if (isLogger) {
            logger = Logger.getLogger(info.getClazz());
        }
        if (level.equals(Level.INFO)) {
            info.setLevel(MonitorLevel.INFO);
            if (isLogger) {
                logger.info(info);
            }
        } else if (level.equals(Level.DEBUG)) {
            info.setLevel(MonitorLevel.DEBUG);
            if (isLogger) {
                logger.debug(info);
            }
        } else if (level.equals(Level.ERROR)) {
            info.setLevel(MonitorLevel.ERROR);
            if (isLogger) {
                logger.error(info);
            }
        }
        sendQueue(info);
    }

    /**
     * Log message to RabbitMQ and log4j.
     *
     * @param level
     * @param type
     * @param serviceId
     * @param info
     * @param isLogger Use log4j or not
     */
    private void log(Level level, String type, String serviceId, String info, boolean isLogger) {
        MonitorBean bean = new MonitorBean();
        bean.setType(type);
        bean.setClazz(clazz.getName());
        bean.setInfo(info);
        bean.setServiceId(serviceId);
        log(level, bean, isLogger);
    }

    /**
     * Log message to RabbitMQ and log4j.
     *
     * @param level
     * @param type
     * @param serviceId
     * @param throwable
     * @param isLogger Use log4j or not
     */
    private void log(Level level, String type, String serviceId, Throwable throwable, boolean isLogger) {
        log(level, type, serviceId, throwableToString(throwable), isLogger);
    }

    /**
     * Log level info to RabbitMQ and log4j.
     *
     * @param serviceId
     * @param type
     * @param info
     */
    public void info(String serviceId, String type, String info) {
        log(Level.INFO, type, serviceId, info, true);
    }

    /**
     * Log level debug to RabbitMQ and log4j.
     *
     * @param serviceId
     * @param type
     * @param info
     */
    public void debug(String serviceId, String type, String info) {
        log(Level.DEBUG, type, serviceId, info, true);
    }

    /**
     * Log level error to RabbitMQ and log4j.
     *
     * @param serviceId
     * @param type
     * @param throwable
     */
    public void error(String serviceId, String type, Throwable throwable) {
        log(Level.ERROR, MonitorType.CONSOLE, serviceId, throwable, false);
        log(Level.ERROR, type, serviceId, throwable, true);
    }

    /**
     * Log level error to RabbitMQ and log4j.
     *
     * @param serviceId
     * @param type
     * @param info
     */
    public void error(String serviceId, String type, String info) {
        log(Level.ERROR, MonitorType.CONSOLE, serviceId, info, false);
        log(Level.ERROR, type, serviceId, info, true);
    }

    /**
     * Log level error to RabbitMQ and log4j.
     *
     * @param serviceId
     * @param type
     * @param info
     * @param throwable
     */
    public void error(String serviceId, String type, String info, Throwable throwable) {
        error(serviceId, type, info);
        error(serviceId, type, throwable);
    }

    /**
     * Send message to queue in Rabbit server.
     *
     * @param info
     */
    private void sendQueue(MonitorBean info) {
        if (getRabbitTemplate() != null) {
            try {
                getRabbitTemplate().convertAndSend(info);
            } catch (AmqpException amqp) {
                //Khi khong gui duoc message den Rabbit Server 
                resetRabbitTemplate();
                logger.error(amqp);
            }
        } else {
            logger.error("Error send message to queue in Rabbit server. RabbitTemplate not create");
            resetRabbitTemplate();
        }
    }

    /**
     * Throw able to String
     *
     * @param throwable
     * @return
     */
    private static String throwableToString(Throwable throwable) {
        StringBuilder msgBody = new StringBuilder();
        ThrowableInformation tinfo = new ThrowableInformation(throwable);
        for (String line : tinfo.getThrowableStrRep()) {
            msgBody.append(String.format("%s%n", line));
        }
        return msgBody.toString();
    }
}
