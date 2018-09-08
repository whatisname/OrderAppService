package com.xxy.ordersystem.constantConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author X
 * @package com.xxy.ordersystem.constantConfig
 * @date 9/6/2018 5:53 PM
 */
@Component
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
