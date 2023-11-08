package com.javatechie.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoFluxTest {


    @Test
    public void testMono(){
        Mono<?> monoString = Mono.just("javatechie")
                .then(Mono.error(new RuntimeException("Exception occured")))
                .log();
        monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Hibernate", "microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occured in Flux")))
                .concatWithValues("cloud")
                .log();

        fluxString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }
    @Test
    public void whenRequestingChunks10_thenMessagesAreReceived() {
        Flux<Integer> request = Flux.range(1, 50);

        request.subscribe(
                System.out::println,
                err -> ((Error)err).printStackTrace(),
                () -> System.out.println("All 50 items have been successfully processed!!!"),
                subscription -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Requesting the next 10 elements!!!");
                        subscription.request(10);
                    }
                }
        );

        StepVerifier.create(request)
                .expectSubscription()
                .thenRequest(10)
                .expectNext(1, 2 ,3 ,4 ,5 ,6 ,7,8 ,9, 10)
                .thenRequest(10)
                .verifyComplete();

    }

    @Test
    public void whenLimitRateSet_thenSplitIntoChunks() throws InterruptedException {
        Flux<Integer> limit = Flux.range(1, 25);

        limit.limitRate(10);
        limit.subscribe(
                value -> System.out.println(value),
                err -> err.printStackTrace(),
                () -> System.out.println("Finished!!"),
                subscription -> subscription.request(15)
        );

        StepVerifier.create(limit)
                .expectSubscription()
                .thenRequest(15)
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .expectNext(11, 12, 13, 14, 15)
                .thenRequest(10)
                .expectNext(16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
                .verifyComplete();
    }

    @Test
    public void whenCancel_thenSubscriptionFinished() {
        Flux<Integer> cancel = Flux.range(1, 10).log();

        cancel.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnNext(Integer value) {
                request(3);
                System.out.println(value);
                cancel();
            }
        });

        StepVerifier.create(cancel)
                .expectNext(1, 2, 3)
                .thenCancel()
                .verify();
    }
}
