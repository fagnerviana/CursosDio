package com.superheroi.heroesapi.controller;


import com.superheroi.heroesapi.document.Heroes;
import com.superheroi.heroesapi.repository.HeroesRepository;
import com.superheroi.heroesapi.service.HeroeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.superheroi.heroesapi.constains.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
//@Slf4j
public class HeroesController {

    HeroeService heroeService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger log=
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    
    public HeroesController(HeroeService heroeService, HeroesRepository heroesRepository){
        this.heroesRepository=heroesRepository;
        this.heroeService=heroeService;

    }
    
    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems(){
    log.info("requesting the list off all heroes");
    return heroeService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/id")
    public Mono<ResponseEntity<Heroes>> findByIdHere(@PathVariable String id){
    log.info("request the hero with id {}",id);
    return heroeService.findByIdHero(id)
            .map((item)-> new ResponseEntity<>(item, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
      public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info("a new hero was created");
        return heroeService.save(heroes);
    }
    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/id")
    @ResponseStatus(code = HttpStatus.CONTINUE)
    public Mono<HttpStatus> deletebyIDHero(@PathVariable String id){
        heroeService.deleteByIdHero(id);
        log.info("deleting a hero with id {}",id);
        return Mono.just(HttpStatus.CONTINUE);
    }
}
