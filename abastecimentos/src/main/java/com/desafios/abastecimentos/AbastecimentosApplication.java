package com.desafios.abastecimentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot para gestão de abastecimentos.
 * Implementa um sistema CRUD para gerenciar combustíveis, bombas e abastecimentos
 * em postos de combustível.
 */
@SpringBootApplication
public class AbastecimentosApplication {

	/**
	 * Método principal que inicializa a aplicação Spring Boot.
	 *
	 * @param args argumentos da linha de comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(AbastecimentosApplication.class, args);
	}

}
