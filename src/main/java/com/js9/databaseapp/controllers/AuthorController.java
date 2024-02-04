package com.js9.databaseapp.controllers;

import com.js9.databaseapp.domain.dto.AuthorDto;
import com.js9.databaseapp.domain.entities.Author;
import com.js9.databaseapp.mappers.Mapper;
import com.js9.databaseapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final Mapper<Author, AuthorDto> authorMapper;



    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto){
        Author author = authorMapper.mapFrom(authorDto);
        var createdAuthor = authorService.create(author);

        return new ResponseEntity<>(authorMapper.mapTo(createdAuthor), HttpStatus.CREATED );
    }




}
