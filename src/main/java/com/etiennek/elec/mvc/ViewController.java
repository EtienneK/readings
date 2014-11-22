package com.etiennek.elec.mvc;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.etiennek.elec.service.Repo;

@Controller
@RequestMapping("/")
public class ViewController {

  @Autowired
  private Repo repo;

  @RequestMapping
  public String index(@ModelAttribute("entry") ElecEntry entry) {
    return "index";
  }

  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView capture(@Valid @ModelAttribute("entry") ElecEntry entry, BindingResult result,
      RedirectAttributes redirect) {
    if (result.hasErrors()) {
      return new ModelAndView("index", "formErrors", result.getAllErrors());
    }
    this.repo.save(new com.etiennek.elec.Domain.ElecEntry(new BigDecimal(entry.getReading())));
    redirect.addFlashAttribute("globalMessage", "Reading was successfully captured.");
    // return new ModelAndView("redirect:/{message.id}", "message.id", message.getId());
    return new ModelAndView("redirect:/");
  }

}
