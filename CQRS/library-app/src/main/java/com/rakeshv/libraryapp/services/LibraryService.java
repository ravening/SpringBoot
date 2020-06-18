package com.rakeshv.libraryapp.services;

import com.rakeshv.libraryapp.aggregates.Library;
import com.rakeshv.libraryapp.entities.LibraryBean;
import com.rakeshv.libraryapp.entities.LibraryEntity;
import com.rakeshv.libraryapp.events.LibraryCreatedEvent;
import com.rakeshv.libraryapp.queries.GetAllLibrariesQuery;
import com.rakeshv.libraryapp.queries.GetLibraryQuery;
import com.rakeshv.libraryapp.repositories.LibraryEntityRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final LibraryEntityRepository libraryRepository;

    public LibraryService(LibraryEntityRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @EventHandler
    public void addLibrary(LibraryCreatedEvent libraryCreatedEvent) {
        LibraryEntity libraryEntity = new LibraryEntity();
        libraryEntity.setId(libraryCreatedEvent.getLibraryId());
        libraryEntity.setName(libraryCreatedEvent.getName());
        libraryRepository.save(libraryEntity);
    }

    @QueryHandler
    public List<LibraryBean> getLibrary(GetLibraryQuery query) throws InterruptedException, ExecutionException {
        return libraryRepository.findById(query.getLibraryId()).stream()
                .map(toLibraryBean()).collect(Collectors.toList());
//        CompletableFuture<Library> future = new CompletableFuture<Library>();
//        libraryRepository.load("" + query.getLibraryId()).execute(future::complete);
//        return future.get();
    }

    @QueryHandler
    public List<LibraryBean> getAllLibraries(GetAllLibrariesQuery query) {
        return libraryRepository.findAll()
                .stream()
                .map(toLibraryBean())
                .collect(Collectors.toList());
    }

    private Function<LibraryEntity, LibraryBean> toLibraryBean() {
        return l -> {
            LibraryBean libraryBean = new LibraryBean();
            libraryBean.setLibraryId(l.getId());
            libraryBean.setName(l.getName());

            return libraryBean;
        };
    }
}
