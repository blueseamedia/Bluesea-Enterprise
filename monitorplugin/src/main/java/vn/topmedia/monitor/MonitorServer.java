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

import org.apache.log4j.DefaultThrowableRenderer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
    
    public RabbitTemplate getRabbitTemplate() {
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
     * Log level
     *
     * @param level
     * @param info
     */
    private void log(Level level, MonitorBean info) {
        logger = Logger.getLogger(info.getClazz());
        if (level.equals(Level.INFO)) {
            info.setLevel(MonitorLevel.INFO);
            logger.info(info);
        } else if (level.equals(Level.DEBUG)) {
            info.setLevel(MonitorLevel.DEBUG);
            logger.debug(info);
        } else if (level.equals(Level.ERROR)) {
            info.setLevel(MonitorLevel.ERROR);
            logger.error(info);
        }
        sendQueue(info);
    }

    /**
     *
     * @param level
     * @param type
     * @param info
     * @param clazz
     */
    private void log(Level level, String type, String serviceId, String info) {
        MonitorBean bean = new MonitorBean();
        bean.setType(type);
        bean.setClazz(clazz.getName());
        bean.setInfo(info);
        bean.setServiceId(serviceId);
        log(level, bean);
    }

    /**
     * Log level
     *
     * @param info
     * @param throwable
     * @param clazz
     */
    private void log(Level level, String type, String serviceId, Throwable throwable) {
        log(level, type, serviceId, throwableToString(throwable));
    }

    /**
     * Log level info
     *
     * @param info
     * @param throwable
     * @param clazz
     */
    public void info(String serviceId, String type, String info) {
        log(Level.INFO, type, serviceId, info);
    }

    /**
     * Log level debug
     *
     * @param type
     * @param info
     * @param clazz
     */
    public void debug(String serviceId, String type, String info) {
        log(Level.DEBUG, type, serviceId, info);
    }

    /**
     * Log level error
     *
     * @param type
     * @param throwable
     * @param clazz
     */
    public void error(String serviceId, String type, Throwable throwable) {
        log(Level.ERROR, MonitorType.CONSOLE, serviceId, throwable);
        log(Level.ERROR, type, serviceId, throwable);
    }

    /**
     * Log level error
     *
     * @param type
     * @param info
     * @param clazz
     */
    public void error(String serviceId, String type, String info) {
        log(Level.ERROR, MonitorType.CONSOLE, serviceId, info);
        log(Level.ERROR, type, serviceId, info);
    }

    /**
     * Log level error
     *
     * @param type
     * @param info
     * @param clazz
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
        String[] infos = DefaultThrowableRenderer.render(throwable);
        StringBuilder sb = new StringBuilder("");
        for (String info : infos) {
            sb.append(info);
        }
        return sb.toString();
    }
}
