package com.bnpparibas.itg.mylibraries.libraries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LibraryService {

    @Autowired
    private LibraryDAO libraryDAO;

    public Long create(Library newLibrary) {
        Library library = this.libraryDAO.save(newLibrary);
        return library.getId();
    }

    public Library obtain(Long id) {
        return this.libraryDAO.findById(id).orElseThrow(() -> new RuntimeException("LIBRARY NOT FOUND"));
    }

    public List<Library> listAll() {
        return this.libraryDAO.findAll();
    }

    public void update(Long id, Library libraryWithNewInformations) {
        Library library = obtain(id);
        library.update(libraryWithNewInformations);
        this.libraryDAO.save(library);
    }

    public void remove(Long id) {
        Library library = obtain(id);
        this.libraryDAO.delete(library);
    }

    public List<Library> listAllByType(Type type) {
        return this.libraryDAO.findByType(type);
    }

    public List<Library> listAllByDirectorName(String surname) {
        return this.libraryDAO.searchByDirectorNameQuery(surname);
    }
}