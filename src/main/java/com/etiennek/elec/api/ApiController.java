package com.etiennek.elec.api;

import org.springframework.beans.factory.annotation.Autowired;
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

}
