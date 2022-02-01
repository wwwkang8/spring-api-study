package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("set test data");
		String data = hello.getData();
		System.out.println(data + " 성공");

		SpringApplication.run(JpashopApplication.class, args);
	}

}
