package com.antoniocmoura.cloudparking.domain.exceptions;

import com.antoniocmoura.cloudparking.domain.AggregateRoot;
import com.antoniocmoura.cloudparking.domain.Identifier;

public class NotFoundException extends DomainException {

    protected NotFoundException(final int aStatusCode, final String aStatusText, final String aMessage) {
        super(aStatusCode, aStatusText, aMessage);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id

    ) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(404, "error", anError);
    }
}
