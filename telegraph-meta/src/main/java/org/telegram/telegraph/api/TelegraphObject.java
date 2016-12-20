package org.telegram.telegraph.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * An object from the Telegraph API received from Telegraph Servers
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface TelegraphObject extends Serializable {
}
