package co.penguinLab.beerguideRepo.controller

import co.penguinLab.beerguideRepo.exception.ResourceNotFoundException
import co.penguinLab.beerguideRepo.model.Note
import co.penguinLab.beerguideRepo.model.PageModel
import co.penguinLab.beerguideRepo.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


/**
 * Created by ozanturcan on 25/01/2020.
 */
@RestController
@RequestMapping("/api")
class NoteController {
    @Autowired
    lateinit var noteRepository: NoteRepository

    @GetMapping("/notes")
    fun allNotes(pageable: Pageable): Page<Note> {
        return noteRepository.findAll(pageable)
    }

    @GetMapping("/test")
    fun allNotes2(pageable: Pageable): PageModel<Note> {
        return PageModel(noteRepository.findAll(pageable))
    }

    @PostMapping("/notes")
    fun createNote(@RequestBody note: Note): Note {
        return noteRepository.save(note)
    }

    @GetMapping("/notes/{id}")
    fun getNoteById(@PathVariable(value = "id") noteId: Long): Note {
        return noteRepository.findById(noteId)
                .orElseThrow { ResourceNotFoundException("Note", "id", noteId) }!!
    }

    @PutMapping("/notes/{id}")
    fun updateNote(@PathVariable(value = "id") noteId: Long,
                   @RequestBody noteDetails: @Valid Note): Note {
        val note: Note? = noteRepository.findById(noteId)
                .orElseThrow { ResourceNotFoundException("Note", "id", noteId) }
        note?.title = noteDetails.title
        note?.content = noteDetails.content
        return noteRepository.save(note!!)
    }

    @DeleteMapping("/notes/{id}")
    fun deleteNote(@PathVariable(value = "id") noteId: Long): ResponseEntity<*> {
        val note: Note? = noteRepository.findById(noteId)
                .orElseThrow { ResourceNotFoundException("Note", "id", noteId) }
        noteRepository.delete(note!!)
        return ResponseEntity.ok().build<Any>()
    }
}