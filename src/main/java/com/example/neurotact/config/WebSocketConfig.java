package com.example.neurotact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author: lyz
 * @date: 2023-05-2023/5/3
 * @description:
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter exporter() {
        return new ServerEndpointExporter();
    }

}
