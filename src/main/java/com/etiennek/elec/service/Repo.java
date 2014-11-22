package com.etiennek.elec.service;

import org.springframework.data.repository.CrudRepository;

import com.etiennek.elec.Domain.ElecEntry;

public interface Repo extends CrudRepository<ElecEntry, Long> {

}
