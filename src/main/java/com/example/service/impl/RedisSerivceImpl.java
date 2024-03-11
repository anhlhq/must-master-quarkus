package com.example.service.impl;

import com.example.service.RedisService;
import com.example.util.AppLogger;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


@ApplicationScoped
public class RedisSerivceImpl implements RedisService {
    private final ValueCommands<String, String> commands;
    private final PubSubCommands<String> pubSubCommands;

    public RedisSerivceImpl(RedisDataSource redisDataSource) {
        this.commands = redisDataSource.value(String.class);
        this.pubSubCommands = redisDataSource.pubsub(String.class);
    }


    @Override
    public void publish(String channel, String message) {
        try {
            AppLogger.info("Publish message to channel \"{}\" with value: {}", channel, message);
            pubSubCommands.publish(channel, message);
        } catch (Exception e) {
            AppLogger.error("Error when publish message to channel: \"{}\". Message: {}", channel, e.getMessage());
            new RuntimeException(e);
        }
    }

    @Override
    public String subscribe(String channel, long timeout) {
        AppLogger.info("Subscribe to channel: {}", channel);
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<String> message = new AtomicReference<>();
        try {
            pubSubCommands.subscribe(channel, (notification) -> {
                AppLogger.info("Received message from channel \"{}\" with value: {}", channel, notification);
                message.set(notification);
                latch.countDown();
            });
            if (!latch.await(timeout, TimeUnit.MILLISECONDS)) {
                AppLogger.warn("Timeout when subscribe to channel: {}", channel);
            }
        } catch (Exception e) {
            AppLogger.error("Error when subscribe to channel: \"{}\". Message: {}", channel, e);
            e.printStackTrace();
        }
        return message.get();
    }
}
