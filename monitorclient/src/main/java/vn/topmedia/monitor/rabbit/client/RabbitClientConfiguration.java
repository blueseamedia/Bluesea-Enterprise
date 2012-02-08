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
package vn.topmedia.monitor.rabbit.client;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.topmedia.monitor.commons.Constants;
import vn.topmedia.monitor.rabbit.MonitorHandler;
import vn.topmedia.monitor.rabbit.config.AbstractRabbitConfiguration;

/**
 * Configure to Rabbit Server
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
@Configuration
public class RabbitClientConfiguration extends AbstractRabbitConfiguration {

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(Constants.RABBIT_USER);
        connectionFactory.setPassword(Constants.RABBIT_PASSWORD);
        connectionFactory.setHost(Constants.RABBIT_SERVER);
        connectionFactory.setVirtualHost(Constants.RABBIT_VITUALHOST);
        connectionFactory.setPort(Constants.RABBIT_PORT);
        return connectionFactory;
    }

    @Bean
    public Queue marketDataQueue() {
        //!Chu y
        //Moi client se tao ra queue bat ky dung de chua message duoc gui toi
        return new AnonymousQueue();
    }

    /**
     * Binds to the market data exchange. Interested in messages log.
     */
    @Bean
    public Binding marketDataBinding() {
        return BindingBuilder.bind(marketDataQueue()).to(marketDataExchange());
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(marketDataQueue());
        container.setMessageListener(messageListenerAdapter());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new MonitorHandler(), jsonMessageConverter());
    }
}