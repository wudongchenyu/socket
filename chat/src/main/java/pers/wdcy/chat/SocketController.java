package pers.wdcy.chat;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketMessage.Type;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class SocketController {
	
	public static void main(String[] args) {
		WebSocketClient client = new ReactorNettyWebSocketClient();
		URI url;
		try {
			url = new URI("ws://localhost/send");
//			DataBufferFactory factory = DefaultDataBufferFactory.sharedInstance;
//			factory.allocateBuffer("qwerasd".getBytes().length);
//			WebSocketMessage wsMessage = new WebSocketMessage(Type.TEXT, factory.wrap(ByteBuffer.wrap("qwerasd".getBytes())));
			client.execute(url, session ->{
			Flux<String> tradesFlux = session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .doOnNext(event -> log.info(event));

			return tradesFlux.then();
			}
			)
			.subscribe();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
//	private @Autowired WebSocketSession session;
//	
//	@GetMapping("send/message")
//	public void send(@RequestParam String message) {
//		DataBufferFactory factory = DefaultDataBufferFactory.sharedInstance;
//		factory.allocateBuffer(message.getBytes().length);
//		WebSocketMessage wsMessage = new WebSocketMessage(Type.TEXT, factory.wrap(ByteBuffer.wrap(message.getBytes())));
//		session.send(Mono.just(wsMessage));
//	}
//	
//	@GetMapping(path = "send", consumes = MediaType.APPLICATION_NDJSON_VALUE)
//	public Mono<Void> send(@RequestParam Mono<WebSocketMessage> message) {
//		return session.send(message);
//	}
//	
//	@GetMapping(path = "receive", produces = MediaType.APPLICATION_NDJSON_VALUE)
//	public Flux<WebSocketMessage> receive() {
//		return session.receive();
//	}

}
