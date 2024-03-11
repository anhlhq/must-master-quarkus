package com.example;

import com.example.service.impl.RedisSerivceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/hello")
public class ExampleResource {
    @Inject
    RedisSerivceImpl redisSerivce;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        String key = UUID.randomUUID().toString();
        String msg = redisSerivce.subscribe("123", 30000);
        return Response.ok(msg).build();
    }
}
