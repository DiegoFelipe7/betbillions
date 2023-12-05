package com.betbillions.walletservice.infrastructure.driver.consumer;

/*import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.usecase.wallet.CreateWalletUseCase;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableCommandListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import reactor.core.publisher.Mono;*/

//@Configuration
//@EnableCommandListeners
//@RequiredArgsConstructor
//@EnableRetry
public class RabbitConsumer {
   /* private static final String WALLET_SERVICE = "betbillions.wallet";
    private final CreateWalletUseCase createWalletUseCase;

    @Bean
    public HandlerRegistry commandoSubscriber() {
        return HandlerRegistry.register().handleCommand(WALLET_SERVICE, p -> {
            UserConsumer userConsumer = p.getData();
            return Mono.defer(() -> createWalletUseCase
                            .apply(new Wallet(userConsumer.getUserId())))
                    .then(Mono.empty());
        }, UserConsumer.class);
    }*/


}

