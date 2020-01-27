package co.penguinLab.beerguideRepo.repository

import co.penguinLab.beerguideRepo.model.Note
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


/**
 * Created by ozanturcan on 25/01/2020.
 */
@Repository
interface NoteRepository : CrudRepository<Note, Long> {

    fun findAll(pageable: Pageable?): Page<Note>
}