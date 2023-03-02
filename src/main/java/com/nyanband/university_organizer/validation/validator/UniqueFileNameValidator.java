package com.nyanband.university_organizer.validation.validator;

import com.nyanband.university_organizer.repository.DisciplineRepository;
import com.nyanband.university_organizer.repository.FileRepository;
import com.nyanband.university_organizer.validation.annotation.UniqueDisciplineNameConstraint;
import com.nyanband.university_organizer.validation.annotation.UniqueFileNameConstraint;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFileNameValidator
        implements ConstraintValidator<UniqueFileNameConstraint, Object> {

    FileRepository fileRepository;

    @Autowired
    public UniqueFileNameValidator(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void initialize() {
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        BeanWrapperImpl fileBeanWrapper = new BeanWrapperImpl(value);
        String fileName = (String) fileBeanWrapper.getPropertyValue("name");
        Long folderId = (Long) fileBeanWrapper.getPropertyValue("folderId");

        return fileRepository.findByNameAndFolderId(fileName, folderId).isEmpty();
    }
}
