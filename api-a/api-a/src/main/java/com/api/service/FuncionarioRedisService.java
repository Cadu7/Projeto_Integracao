package com.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.api.model.Funcionario;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;

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
			List<String> result = new ArrayList<String>();
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
