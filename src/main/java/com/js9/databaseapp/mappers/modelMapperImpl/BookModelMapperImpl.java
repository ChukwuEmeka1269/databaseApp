package com.js9.databaseapp.mappers.modelMapperImpl;

import com.js9.databaseapp.domain.dto.BookDto;
import com.js9.databaseapp.domain.entities.Book;
import com.js9.databaseapp.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookModelMapperImpl implements Mapper<Book, BookDto> {
    private final ModelMapper modelMapper;

    @Override
    public BookDto mapTo(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public Book mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}
