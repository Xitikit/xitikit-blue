package org.xitikit.blue.admin.controller;

import org.xitikit.blue.admin.form.SearchForm;
import org.xitikit.blue.admin.view.ReassignResultView;
import org.xitikit.blue.admin.view.SearchResultView;
import org.xitikit.blue.admin.view.WebAccountView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public interface IMainController{

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    List<SearchResultView> search(@RequestBody SearchForm searchForm);

    @RequestMapping(value = "/clientLookup", method = RequestMethod.POST)
    @ResponseBody
    WebAccountView clientLookup(@RequestBody String emailAddress);

    @RequestMapping(value = "/client/{webAccountId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    void deleteClient(@PathVariable String webAccountId);

    @RequestMapping(value = "/client/{webAccountId}/disconnect", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    void disconnectClient(@PathVariable String webAccountId);

    @RequestMapping(value = "/client/{webAccountId}/reassign", method = RequestMethod.POST)
    @ResponseBody
    ReassignResultView reassignClient(@PathVariable String webAccountId, @RequestBody SearchResultView searchResult) throws ParseException;
}
