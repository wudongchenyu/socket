package pers.wdcy.chat;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

@Component
public class MessageHandler implements WebSocketHandler{

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session.send(session.receive()
				.map(message -> session.textMessage("推送消息：" + message.getPayloadAsText()))
				);
//		session.receive().subscribe(message -> {
//			System.out.println(message.getPayloadAsText());
//			log.info(message.getPayloadAsText());
//		});
//		
//		Mono<Void> input = session.receive()
//				.doOnNext(message -> {
//					System.out.println(message.getPayloadAsText());
//					log.info(message.getPayloadAsText());
//				})
//				.then();
//
//        Flux<String> source = Flux.just("asda");
//        Mono<Void> output = session.send(source.map(session::textMessage)); 
//		
//		return Mono.zip(input, output)
//				.then();
	}

}
