package org.xitikit.blue.admin.form;

import java.io.Serializable;

public class SearchForm implements Serializable{

    private String firstName;

    private String lastName;

    public String getFirstName(){

        return firstName;
    }

    public void setFirstName(final String firstName){

        this.firstName = firstName;
    }

    public String getLastName(){

        return lastName;
    }

    public void setLastName(final String lastName){

        this.lastName = lastName;
    }
}
