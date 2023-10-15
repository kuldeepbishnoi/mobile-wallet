package com.mobilewallet.user.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// import com.mobilewallet.user.entity.Transaction;
import com.mobilewallet.user.entity.Wallet;

@Component
public class KafkaListeneres{

    private Wallet updateWallet = null;

    @KafkaListener(topics = "wallet-topica", groupId = "group_id")
    public void consume(Wallet wallet) {
       System.out.println("Consumed message: " + wallet);
       updateWallet = wallet;
    }

    public Wallet getUpdateWallet() {
        return updateWallet;
    }
}
