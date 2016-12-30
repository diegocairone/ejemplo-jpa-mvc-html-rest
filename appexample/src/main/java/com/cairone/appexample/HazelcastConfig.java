package com.cairone.appexample;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration @EnableCaching
public class HazelcastConfig {

	@Bean
	public HazelcastInstance getHazelcastInstance() {
		
		Config config = new Config();
		
		return Hazelcast.newHazelcastInstance(config);
	}
}
