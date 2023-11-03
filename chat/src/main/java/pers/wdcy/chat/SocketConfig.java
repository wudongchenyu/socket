package pers.wdcy.chat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

@Configuration
public class SocketConfig {
	
    @Bean
    WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
    
    @Bean
    HandlerMapping handlerMapping(MessageHandler messageHandler) {
        Map<String, WebSocketHandler> map = new HashMap<>(1);
        map.put("/send", messageHandler);
        int order = -1;
        return new SimpleUrlHandlerMapping(map, order);
    }
    
//    @Bean
//    WebSocketService webSocketService() {
//        TomcatRequestUpgradeStrategy strategy = new TomcatRequestUpgradeStrategy();
//        strategy.setMaxSessionIdleTimeout(0L);
//        return new HandshakeWebSocketService(strategy);
//    }
    
//    @Bean	
//    WebSocketService webSocketService() {
//    	new TomcatS
//        return new HandshakeWebSocketService(new ReactorNetty2RequestUpgradeStrategy());
//    }
}
