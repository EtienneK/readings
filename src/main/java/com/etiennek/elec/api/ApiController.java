package com.etiennek.elec.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiennek.elec.Domain.ElecEntry;
import com.etiennek.elec.service.Repo;

@RestController
public class ApiController {

  @Autowired
  private Repo repo;

  @RequestMapping(value = "/api/readings")
  public Iterable<ElecEntry> list() {
    return repo.findAll();
  }

  @RequestMapping(value = "/api/readings/delete/{id}")
  public void delete(@PathVariable long id) {
    repo.delete(id);
  }

}
