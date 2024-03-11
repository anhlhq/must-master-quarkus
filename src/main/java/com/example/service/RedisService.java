package com.example.service;

public interface RedisService {
    void publish(String channel, String message);
    String subscribe(String channel, long timeout);
}
