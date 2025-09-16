package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption;


public class RepositoryException extends DomainException {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
