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
package vn.topmedia.monitor.rabbit.server;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.topmedia.monitor.rabbit.config.AbstractRabbitConfiguration;

/**
 * Rabbit server configuration
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
@Configuration
public class RabbitServerConfiguration extends AbstractRabbitConfiguration {

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        //Chuyen noi dung sang dang JSON
        template.setMessageConverter(jsonMessageConverter());
        template.setExchange(this.monitorExchangeName);
        return template;
    }
}