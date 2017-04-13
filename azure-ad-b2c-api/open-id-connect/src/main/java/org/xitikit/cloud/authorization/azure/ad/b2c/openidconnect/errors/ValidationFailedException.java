package org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.errors;

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
