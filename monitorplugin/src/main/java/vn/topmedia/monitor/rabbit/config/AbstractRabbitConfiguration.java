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
package vn.topmedia.monitor.rabbit.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
@Configuration
public abstract class AbstractRabbitConfiguration {

    @Value("${rabbit-server}")
    private String server = "192.168.1.55";
    @Value("${rabbit-username}")
    private String username = "monitor";
    @Value("${rabbit-password}")
    private String password = "monitor";
    @Value("${rabbit-virtualhost}")
    private String virtualhost = "/monitor";
    @Value("${rabbit-port}")
    private int port = 5672;

    protected final String monitorExchangeName = "monitor.exchange.logs";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(server);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualhost);
        connectionFactory.setPort(port);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        AmqpAdmin admin = new RabbitAdmin(connectionFactory());
        admin.declareExchange(marketDataExchange());
        return admin;
    }

    @Bean
    public FanoutExchange marketDataExchange() {
        return new FanoutExchange(monitorExchangeName);
    }
}