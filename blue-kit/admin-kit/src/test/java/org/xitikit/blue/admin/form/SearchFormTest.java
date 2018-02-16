package org.xitikit.blue.admin.form;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
class SearchFormTest{

    @Test
    void givenName(){

        SearchForm form = new SearchForm();
        assertNull(form.getGivenName());
        form.setGivenName(" Name ");
        assertEquals(" Name ", form.getGivenName());
        form.setGivenName(" ");
        assertEquals(" ", form.getGivenName());
        form.setGivenName("");
        assertEquals("", form.getGivenName());
        form.setGivenName(null);
        assertEquals(null, form.getGivenName());
    }

    @Test
    void surName(){

        SearchForm form = new SearchForm();
        assertNull(form.getSurname());
        form.setSurname(" Name ");
        assertEquals(" Name ", form.getSurname());
        form.setSurname(" ");
        assertEquals(" ", form.getSurname());
        form.setSurname("");
        assertEquals("", form.getSurname());
        form.setSurname(null);
        assertEquals(null, form.getSurname());
    }
}