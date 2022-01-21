package com.conigurandobeans.configurandobeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //<bean id="livro" class="com.springbeans.Livro"/>
    @Bean
    public Livro getLivro(){
        return new Livro();
    }

    @Bean
    public AutorLivro getAutorLivro(){
        return new Autor();
    }



}
