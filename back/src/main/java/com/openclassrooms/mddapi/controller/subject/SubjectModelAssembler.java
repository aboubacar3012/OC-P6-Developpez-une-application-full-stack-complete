package com.openclassrooms.mddapi.controller.subject;

import com.openclassrooms.mddapi.model.Subject;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubjectModelAssembler implements RepresentationModelAssembler<Subject, EntityModel<Subject>> {
    @Override
    public EntityModel<Subject> toModel(Subject subject) {
        return EntityModel.of(subject, //
                linkTo(methodOn(SubjectController.class).getSubjectById(subject.getId())).withSelfRel(),
                linkTo(methodOn(SubjectController.class).getSubjects()).withRel("subjects"));
    }
}