package com.nyanband.university_organizer.validation.validator;

import com.nyanband.university_organizer.repository.DisciplineRepository;
import com.nyanband.university_organizer.repository.FolderRepository;
import com.nyanband.university_organizer.validation.annotation.UniqueDisciplineNameConstraint;
import com.nyanband.university_organizer.validation.annotation.UniqueFolderNameConstraint;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFolderNameValidator
        implements ConstraintValidator<UniqueFolderNameConstraint, Object> {

    FolderRepository folderRepository;

    @Autowired
    public UniqueFolderNameValidator(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public void initialize() {
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        BeanWrapperImpl folderBeanWrapper = new BeanWrapperImpl(value);
        String folderName = (String) folderBeanWrapper.getPropertyValue("name");
        Long disciplineId = (Long) folderBeanWrapper.getPropertyValue("disciplineId");

        return folderRepository.findByNameAndDisciplineId(folderName, disciplineId).isEmpty();
    }
}
