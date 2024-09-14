package com.english.battle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"models"})
public class BattleApplication {
	public static void main(String[] args) {
		SpringApplication.run(BattleApplication.class, args);
	}
}
