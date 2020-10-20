package com.automic.hpqc.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents java binding for HPQC Fields mapping.
 * 
 */
@XmlRootElement(name = "Fields")
public class Fields {

    private List<Field> fieldList;

    public Fields() {
        super();
    }

    public Fields(List<Field> fieldList) {
        super();
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Fields [fieldList=");
        builder.append(fieldList);
        builder.append("]");
        return builder.toString();
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    @XmlElement(name = "Field")
    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public Map<String, String> nameValueMap() {
        Map<String, String> nameValueMap = new HashMap<String, String>();
        for (Field field : fieldList) {
            String value = (field.getValue() != null) ? field.getValue().trim() : "";
            nameValueMap.put(field.getName(), value);
        }
        return nameValueMap;
    }

    public Map<String, Field> nameFieldMap() {
        Map<String, Field> nameFieldMap = new HashMap<String, Field>();
        for (Field field : fieldList) {
            nameFieldMap.put(field.getName(), field);
        }
        return nameFieldMap;
    }

}
