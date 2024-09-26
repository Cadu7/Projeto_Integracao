package com.api.service;

import com.api.model.Funcionario;
import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Singleton
public class FuncionarioRedisService {

    @Inject
    private RedisClient redisClient;

    public void del(String key) {
        List<String> a = Arrays.asList(key);
        redisClient.del(a);
    }

    public String get(String key) {
        return redisClient.get(key).toString();
    }

    public void set_update(String key, Funcionario func) {
        redisClient.set(Arrays.asList(key, func.toString()));
    }

    public Stream<Object> keys() {
        return redisClient.keys("*").stream().map(response -> {
            List<String> result = new ArrayList<>();
            if (response != null) {
                for (Response r : response) {
                    result.add(r.toString());
                }
                return result;
            } else {
                return "fala galela";
            }
        });

    }

}
