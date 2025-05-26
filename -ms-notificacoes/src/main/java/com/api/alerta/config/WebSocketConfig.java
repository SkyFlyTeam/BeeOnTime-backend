package com.api.alerta.config;

import com.api.alerta.websocket.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public MyWebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/ws")
                .setAllowedOrigins("*");
    }
}
