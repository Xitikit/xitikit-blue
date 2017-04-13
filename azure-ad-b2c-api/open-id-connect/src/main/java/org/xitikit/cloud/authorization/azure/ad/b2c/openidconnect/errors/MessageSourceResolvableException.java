package org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.errors;

import org.springframework.context.MessageSourceResolvable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Erik R. Jensen
 */
public class MessageSourceResolvableException extends RuntimeException implements MessageSourceResolvable {

    private List<String> codes = new ArrayList<>();
    private List<Serializable> arguments = new ArrayList<>();
    private String defaultMessage;

    public MessageSourceResolvableException() {

    }

    public MessageSourceResolvableException(String code) {

        codes.add(code);
    }

    public MessageSourceResolvableException(String code, Throwable t) {

        super(t);
        codes.add(code);
    }

    public MessageSourceResolvableException(String code, Serializable... arguments) {

        codes.add(code);
        this.arguments.addAll(Arrays.asList(arguments));
    }

    public MessageSourceResolvableException addCode(String code) {

        codes.add(code);
        return this;
    }

    public MessageSourceResolvableException addCodes(String... codes) {

        this.codes.addAll(Arrays.asList(codes));
        return this;
    }

    @Override
    public String[] getCodes() {

        return codes.toArray(new String[codes.size()]);
    }

    public MessageSourceResolvableException addArgument(Serializable argument) {

        arguments.add(argument);
        return this;
    }

    public MessageSourceResolvableException addArguments(Serializable... arguments) {

        this.arguments.addAll(Arrays.asList(arguments));
        return this;
    }

    @Override
    public Object[] getArguments() {

        return arguments.toArray(new Object[arguments.size()]);
    }

    @Override
    public String getDefaultMessage() {

        return defaultMessage;
    }

    public MessageSourceResolvableException setDefaultMessage(String defaultMessage) {

        this.defaultMessage = defaultMessage;
        return this;
    }
}
