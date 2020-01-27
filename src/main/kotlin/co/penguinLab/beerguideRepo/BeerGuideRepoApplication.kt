package co.penguinLab.beerguideRepo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class BeerGuideRepoApplication

fun main(args: Array<String>) {

    runApplication<BeerGuideRepoApplication>(*args)
}