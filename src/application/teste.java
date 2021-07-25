package application;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class teste {

	public static void main(String[] args) {

		
		 // Cria um Objeto LocalDate com a data atual.
        LocalDate hoje = LocalDate.now();
        
        // Cria um Objeto LocalDate com a data 26/09/2020.
        LocalDate outraData = LocalDate.of(2022, Month.JULY, 25);
        
        // Calcula a diferença de dias entre as duas datas
        long diferencaEmDias = ChronoUnit.DAYS.between(hoje, outraData);
        
        System.out.println(diferencaEmDias);

	}

}
