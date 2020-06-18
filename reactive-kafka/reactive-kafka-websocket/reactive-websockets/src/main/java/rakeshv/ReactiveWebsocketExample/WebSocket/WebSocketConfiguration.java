package rakeshv.ReactiveWebsocketExample.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSocketConfiguration
{

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter()
    {
        return new WebSocketHandlerAdapter();
    }
    
    @Bean
    public HandlerMapping handlerMapping()
    {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/stringConverter", webSocketHandler);
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setOrder(10);
        simpleUrlHandlerMapping.setUrlMap(map);
        return simpleUrlHandlerMapping;
    }
}
