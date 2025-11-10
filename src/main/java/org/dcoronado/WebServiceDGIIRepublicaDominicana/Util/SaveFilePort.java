package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util;

public interface SaveFilePort {

    String getBasePath();

    void save(String path, byte[] content);

}
