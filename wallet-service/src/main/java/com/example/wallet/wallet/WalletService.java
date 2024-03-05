package com.example.wallet.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WalletRepository walletRepository;

    @KafkaListener(topics = {CommonConstant.USER_CREATION_TOPIC},groupId = "group123")
    public void createWallet(String msg)
    {
        JSONObject data = objectMapper.convertValue(msg, JSONObject.class);

        String phoneNumber = (String)data.get(CommonConstant.USER_CREATION_TOPIC_PHONE_NUMBER);
        Long userId = (Long)data.get(CommonConstant.USER_CREATION_TOPIC_USERID);
        String identifierKey = (String)data.get(CommonConstant.USER_CREATION_TOPIC_IDENTIFIER_KEY);
        String identifierValue = (String)data.get(CommonConstant.USER_CREATION_TOPIC_IDENTIFIER_KEY);

        Wallet wallet = Wallet.builder()
                .userId(userId)
                .phoneNumber(phoneNumber)
                .userIdentifier(UserIdentifier.valueOf(identifierKey))
                .identifierValue(identifierValue)
                .balance(10.0)
                .build();

        walletRepository.save(wallet);

    }

}
