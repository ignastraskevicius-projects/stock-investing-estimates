package org.ignast.stockinvesting.api.controller.errorhandler.annotations;

import lombok.val;
import org.ignast.stockinvesting.domain.MarketIdentifierCode;
import org.ignast.stockinvesting.mockito.MockitoUtils;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DomainClassConstraintValidatorTest {

    private DomainClassConstraintValidator validator = new DomainClassConstraintValidator();

    @Test
    public void shouldBeValidForNullFieldToGivePreferenceToJavaxNonNullAnnotation() {
        validator.initialize(constrainedBy(MarketIdentifierCode.class));

        assertThat(validator.isValid(null, null)).isTrue();
    }

    @Test
    public void shouldInvalidateInvalidMIC() {
        validator.initialize(constrainedBy(MarketIdentifierCode.class));
        val builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        val context = MockitoUtils.mock(ConstraintValidatorContext.class,
                c -> when(c.buildConstraintViolationWithTemplate(any())).thenReturn(builder));

        assertThat(validator.isValid("nont4Characters", context)).isFalse();

        verify(context).buildConstraintViolationWithTemplate(
                "Market Identifier is not 4 characters long (ISO 10383 standard)");
        verify(builder).addConstraintViolation();
    }

    @Test
    public void shouldValidateValidMIC() {
        validator.initialize(constrainedBy(MarketIdentifierCode.class));

        assertThat(validator.isValid("XNYS", null)).isTrue();
    }

    private DomainClassConstraint constrainedBy(Class<?> domainClass) {
        val constraint = new DomainClassConstraint() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String message() {
                return null;
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<?>[] payload() {
                return new Class[0];
            }

            @Override
            public Class<?> domainClass() {
                return domainClass;
            }
        };
        assertThat(constraint.domainClass()).isEqualTo(domainClass);
        return constraint;
    }
}