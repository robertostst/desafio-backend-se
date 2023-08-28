package br.com.softexpert.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "br.com.softexpert.controller")
public class SwaggerConfig {

	public static final String TAG_CALCULADORA = "Rateio API";	
		
	@Value("${api.version:0.0.1}")
	private String versao;

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.softexpert.controller"))
				.build()
				.apiInfo(apiInfo())
				.tags(new Tag(TAG_CALCULADORA, "Operação para calcular o valor que cada um dos seus amigos deve pagar ao dividir um pedido pelo iFood ou similares."));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Documentação API SoftExpert")
				.version(versao)
				.build();
	}
}