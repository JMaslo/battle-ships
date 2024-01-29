package me.devik.battleships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BattleShipsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleShipsApplication.class, args);
	}



}
