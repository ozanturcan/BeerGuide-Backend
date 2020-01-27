package co.penguinLab.beerguideRepo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.domain.Page

data class PageModel<T>(@JsonIgnore private val content: Page<Note>) {
    val result = content.content
    val totalPage = content.totalPages
    val totalElements = content.totalElements
}

