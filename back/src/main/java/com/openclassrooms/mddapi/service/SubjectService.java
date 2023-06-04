package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing subjects.
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Retrieves all subjects.
     *
     * @return the list of all subjects
     */
    public List<Subject> getSubjects() {
        return this.subjectRepository.findAll();
    }

    /**
     * Retrieves a subject by its ID.
     *
     * @param id the ID of the subject
     * @return an optional containing the subject if found, or an empty optional if not found
     */
    public Optional<Subject> getSubjectById(Long id) {
        return this.subjectRepository.findById(id);
    }

    /**
     * Adds a new subject.
     *
     * @param subject the subject to be added
     * @return the added subject
     */
    public Subject addSubject(Subject subject) {
        return this.subjectRepository.save(subject);
    }

    /**
     * Replaces a subject with the given ID with a new subject.
     * If the subject with the given ID exists, its fields are updated with the fields from the new subject.
     * If the subject with the given ID doesn't exist, a new subject is created with the ID and fields from the new subject.
     *
     * @param newSubject the new subject to replace the existing subject
     * @param id         the ID of the subject to be replaced
     * @return the replaced subject
     */
    public Subject replaceSubjectById(Subject newSubject, Long id) {
        return this.subjectRepository.findById(id)
                .map(subject -> {
                    if (newSubject.getName() != null) subject.setName(newSubject.getName());
                    if (newSubject.getDescription() != null) subject.setDescription(newSubject.getDescription());
                    return subjectRepository.save(subject);
                })
                .orElseGet(() -> {
                    newSubject.setId(id);
                    return subjectRepository.save(newSubject);
                });
    }

    /**
     * Deletes a subject by its ID.
     *
     * @param id the ID of the subject to be deleted
     */
    public void deleteSubjectById(Long id) {
        this.subjectRepository.deleteById(id);
    }
}
