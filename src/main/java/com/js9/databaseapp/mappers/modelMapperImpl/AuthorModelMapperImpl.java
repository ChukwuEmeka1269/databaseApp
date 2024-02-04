package com.js9.databaseapp.mappers.modelMapperImpl;

import com.js9.databaseapp.domain.dto.AuthorDto;
import com.js9.databaseapp.domain.entities.Author;
import com.js9.databaseapp.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorModelMapperImpl implements Mapper<Author, AuthorDto> {

    private final ModelMapper modelMapper;

    @Override
    public AuthorDto mapTo(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public Author mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
