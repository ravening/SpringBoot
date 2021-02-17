package com.rakeshv.tictactoe.config;

import com.rakeshv.tictactoe.models.Game;
import com.rakeshv.tictactoe.models.GameStatus;
import com.rakeshv.tictactoe.services.GameStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic/");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gameplay")
                .setAllowedOriginPatterns("*")
//                .setAllowedOrigins("*")
//                .setHandshakeHandler(new DefaultHandshakeHandler() {
//                    public boolean beforeHandshake(ServerHttpRequest request,
//                                                   ServerHttpResponse response,
//                                                   WebSocketHandler handler,
//                                                   Map<String, String> attributes) throws Exception {
//                        if (request instanceof ServletServerHttpRequest) {
//                            ServletServerHttpRequest servletServerHttpRequest =
//                                    (ServletServerHttpRequest) request;
//                            HttpSession session = servletServerHttpRequest.getServletRequest().getSession();
//                            attributes.put("sessionId", session.getId());
//                        }
//                        return true;
//                    }
//                })
                .withSockJS();

    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        String message = "Game terminated";
        log.error("session disconnet {}",  event.getSessionId());
        String gameId = GameStorage.getInstance().getGameBySessionId(event.getSessionId());
        Game game = GameStorage.getInstance().getGameById(gameId);
        game.setGameStatus(GameStatus.TERMINATED);
        this.messagingTemplate.convertAndSend("/topic/terminate-game/" + game.getPlayer1().getLogin(), message);
        this.messagingTemplate.convertAndSend("/topic/terminate-game/" + game.getPlayer2().getLogin(), message);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(websocketChannelInterceptor());
    }

    @Bean
    public WebsocketChannelInterceptor websocketChannelInterceptor() {
        return new WebsocketChannelInterceptor();
    }
}
