package org.xitikit.azureadb2c.common.errors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author Erik Jensen
 */
@Getter
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ValidationFailedException extends RuntimeException {

    private final List<ObjectError> errors;
}
