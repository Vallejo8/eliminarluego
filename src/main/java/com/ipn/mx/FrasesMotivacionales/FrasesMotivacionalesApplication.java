package com.ipn.mx.FrasesMotivacionales;

import com.ipn.mx.FrasesMotivacionales.domain.entidades.Frases;
import com.ipn.mx.FrasesMotivacionales.domain.repositorios.FrasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrasesMotivacionalesApplication implements CommandLineRunner {
	@Autowired
	private FrasesRepository dao;

	public static void main(String[] args) {
		SpringApplication.run(FrasesMotivacionalesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Frases frases = new Frases();
		//frases.setIdFrase(1L);
		//frases.setTextoFrase("La vida es lo que pasa mientras estás ocupado haciendo otros planes.");
		//frases.setAutorFrase("John Lennon");
		//Update the entity
		//frases.setIdFrase(2L);

		//Delete the entity
		//dao.deleteById(getIdFrase());

		//Find the entity
		//System.out.println(dao.findById(getIdFrase()));

		//Find all entities
		//System.out.println(dao.findAll());

		// Save the entity
		//dao.save(frases);

		//System.out.println("Frase: " + frases.getTextoFrase());
	}
}
