package com.RestAssured.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

//Using @JsonInclude at class level will affect all the fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkspacePojo {
    private String name;
    private String type;
    private String description;

 //Using @JsonInclude at field level will only affect the field below
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

 //Using @JsonInclude at field level will only affect the field below
    //@JsonInclude(JsonInclude.Include.NON_DEFAULT)
    //private int i;

    public HashMap<String, String> gethMap() {
        return hMap;
    }

    public void sethMap(HashMap<String, String> hMap) {
        this.hMap = hMap;
    }

    //Creating empty hashmap
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private HashMap<String, String> hMap;
    public WorkspacePojo() {
    }

    public WorkspacePojo(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
