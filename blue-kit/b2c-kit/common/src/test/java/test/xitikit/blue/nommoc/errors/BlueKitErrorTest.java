package test.xitikit.blue.nommoc.errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.xitikit.blue.common.errors.BlueKitError;
import org.xitikit.blue.common.errors.BlueKitHttpException;
import org.xitikit.blue.common.errors.ErrorCode;
import org.xitikit.blue.common.errors.exceptions.*;

import java.io.IOException;

import static java.util.Arrays.*;
import static junit.framework.TestCase.*;
import static org.xitikit.blue.common.errors.ErrorCode.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
class BlueKitErrorTest{

    private final static String[] ARGS = new String[]{"one", "[0,1]", "{\"value\":3}"};

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void empty(){

        BlueKitError error = new BlueKitError();
        assertEquals("500, error.getCode()", INTERNAL_SERVER_ERROR.value(), error.getCode());
        assertEquals("\"answer\", error.getName()", INTERNAL_SERVER_ERROR.name(), error.getName());
        assertEquals("\"whatever\", error.getDescription()", INTERNAL_SERVER_ERROR.getDescription(), error.getDescription());
        assertNotNull("error.getArguments()", error.getArguments());
        assertEquals("0, error.getArguments().size()", 0, error.getArguments().size());
    }

    @Test
    void customArgs(){

        BlueKitError error = new BlueKitError(42, "answer", "whatever", asList(ARGS));
        assertEquals("42, error.getCode()", 42, error.getCode());
        assertEquals("\"answer\", error.getName()", "answer", error.getName());
        assertEquals("\"whatever\", error.getDescription()", "whatever", error.getDescription());
        assertEquals("3, error.getArguments().size()", 3, error.getArguments().size());
        assertNotNull("error.getArguments()", error.getArguments());
        assertEquals("ARGS[0], error.getArguments().get(0)", ARGS[0], error.getArguments().get(0));
        assertEquals("ARGS[1], error.getArguments().get(1)", ARGS[1], error.getArguments().get(1));
        assertEquals("ARGS[2], error.getArguments().get(2)", ARGS[2], error.getArguments().get(2));
    }

    @Test
    void customArgsWithSetters(){

        BlueKitError error = new BlueKitError();
        error.setCode(42);
        error.setName("answer");
        error.setDescription("whatever");
        error.setArguments(asList(ARGS));
        assertEquals("42, error.getCode()", 42, error.getCode());
        assertEquals("\"answer\", error.getName()", "answer", error.getName());
        assertEquals("\"whatever\", error.getDescription()", "whatever", error.getDescription());
        assertEquals("3, error.getArguments().size()", 3, error.getArguments().size());
        assertNotNull("error.getArguments()", error.getArguments());
        assertEquals("ARGS[0], error.getArguments().get(0)", ARGS[0], error.getArguments().get(0));
        assertEquals("ARGS[1], error.getArguments().get(1)", ARGS[1], error.getArguments().get(1));
        assertEquals("ARGS[2], error.getArguments().get(2)", ARGS[2], error.getArguments().get(2));
    }

    @Test
    void simpleForbiddenException() throws IOException{

        validate(ErrorCode.FORBIDDEN, new ForbiddenException(ARGS));
    }

    private static void validate(final ErrorCode status, final BlueKitHttpException e) throws IOException{

        BlueKitError error = new BlueKitError(e);

        validateSimpleError(error, status);
        validateSimpleErrorSerialization(error, status);
    }

    private static void validateSimpleError(final BlueKitError error, final ErrorCode errorCode){

        assertEquals("status.value(), error.getCode()", errorCode.value(), error.getCode());
        assertEquals("status.name(), error.getName()", errorCode.name(), error.getName());
        assertEquals("status.getDescription(), error.getDescription()", errorCode.getDescription(), error.getDescription());
        assertNotNull("error.getArguments()", error.getArguments());
        assertEquals("3, error.getArguments().size()", 3, error.getArguments().size());
        assertEquals("ARGS[0], error.getArguments().get(0)", ARGS[0], error.getArguments().get(0));
        assertEquals("ARGS[1], error.getArguments().get(1)", ARGS[1], error.getArguments().get(1));
        assertEquals("ARGS[2], error.getArguments().get(2)", ARGS[2], error.getArguments().get(2));
    }

    private static void validateSimpleErrorSerialization(final BlueKitError error, final ErrorCode errorCode) throws IOException{

        validateSimpleError(
            objectMapper.readValue(
                objectMapper.writeValueAsString(error),
                BlueKitError.class),
            errorCode);
    }

    @Test
    void simpleBadRequestException() throws IOException{

        validate(ErrorCode.BAD_REQUEST, new BadRequestException(ARGS));
    }

    @Test
    void simpleMethodNotAllowedException() throws IOException{

        validate(ErrorCode.METHOD_NOT_ALLOWED, new MethodNotAllowedException(ARGS));
    }

    @Test
    void simpleUnauthorizedException() throws IOException{

        validate(ErrorCode.UNAUTHORIZED, new UnauthorizedException(ARGS));
    }

    @Test
    void simpleNotFoundException() throws IOException{

        validate(ErrorCode.NOT_FOUND, new NotFoundException(ARGS));
    }
}