package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getSubjects() {
        return this.subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return this.subjectRepository.findById(id);
    }

    public Subject addSubject(Subject subject) {
        return this.subjectRepository.save(subject);
    }

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

    public void deleteSubjectById(Long id) {
        this.subjectRepository.deleteById(id);
    }
}
