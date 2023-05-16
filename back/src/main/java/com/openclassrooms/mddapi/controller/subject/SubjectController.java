package com.openclassrooms.mddapi.controller.subject;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private  SubjectModelAssembler assembler;

    @GetMapping()
    public CollectionModel<EntityModel<Subject>> getSubjects(){
        List<EntityModel<Subject>> subjects =  this.subjectService.getSubjects().stream()
                .map(subject -> assembler.toModel(subject))
                .collect(Collectors.toList());

        return CollectionModel.of(subjects, linkTo(methodOn(SubjectController.class).getSubjects()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Subject> getSubjectById(@PathVariable Long id) {
        Subject subject = this.subjectService.getSubjectById(id).orElseThrow(() -> new NotFoundException(id));
        return assembler.toModel(subject);
    }

    @PostMapping()
    public ResponseEntity<EntityModel<Subject>> addSubject(@RequestBody Subject newSubject){
        EntityModel<Subject> entityModel = assembler.toModel(this.subjectService.addSubject(newSubject));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Subject>> updateSubject(@RequestBody Subject newSubject, @PathVariable Long id){
        EntityModel<Subject> entityModel = assembler.toModel(this.subjectService.replaceSubjectById(newSubject,id));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        this.subjectService.deleteSubjectById(id);
        return ResponseEntity.noContent().build();
    }
}
