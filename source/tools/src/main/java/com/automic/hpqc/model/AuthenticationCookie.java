package com.automic.hpqc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 *
 */
/**
 * Field class is used to map the response from HPQC
 *
 */
@XmlRootElement(name = "ATCookie")
public class AuthenticationCookie {

    private String name;
    private String value;

    public String getValue() {
        return value;
    }

    @XmlElement(name = "Value")
    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

}
