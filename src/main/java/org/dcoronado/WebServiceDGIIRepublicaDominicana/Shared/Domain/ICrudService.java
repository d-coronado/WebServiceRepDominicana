package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.RepositoryException;

public interface ICrudService<T, U> {
    U findById(Long id) throws RepositoryException;
    U save(T t) throws RepositoryException;
    U update(T t, Long id) throws RepositoryException;
    void deleteById(Long id) throws RepositoryException;
}

