package com.sgu;

import com.sgu.services.CityCountryService;
import com.sgu.entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


		/*
		System.out.println("Hello");
		CityCountryService service = new CityCountryService();

		Country country = new Country("Россия");
		service.addCountry(country);


		City city = new City("Таганрог");
		service.addCity(city,country);
		List<City> list=service.getAllCityes();

		for (City c : list){
			System.out.println(c.toString());
		}
*/



	}
}
