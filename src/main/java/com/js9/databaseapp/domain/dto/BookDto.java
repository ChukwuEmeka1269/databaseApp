package com.js9.databaseapp.domain.dto;

import com.js9.databaseapp.domain.entities.Author;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String isbn;
    private String title;
    private AuthorDto authorDto;
}
