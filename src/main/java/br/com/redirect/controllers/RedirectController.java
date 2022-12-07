package br.com.redirect.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.redirect.entity.Customer;
import br.com.redirect.entity.RedirectForm;
import br.com.redirect.entity.Users;
import br.com.redirect.repository.ICustomerRepository;
import br.com.redirect.repository.IFormRepository;
import br.com.redirect.service.RedirectService;

@Controller
@RequestMapping("/redirect")
public class RedirectController {
	
	@Autowired
	private RedirectService redirectService;
	
	@Autowired
	private IFormRepository formRepository;
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	@GetMapping("/{name:.+}")
	public String doRedirect(@PathVariable String name, HttpServletResponse request) throws Exception{
	    RedirectForm url = formRepository.findDsUrlByDsName(name);
		if(url != null) {
    	    String redirecionador = url.getDsUrl();
			request.sendRedirect(redirecionador);
		} else {
			System.out.print("URL NAO ENCONTRADA PARA O ID: " + name);
			return "/404";
		}
		return null;
	}
	
	@GetMapping("/list")
	public String listar(ModelMap model) {
		model.addAttribute("forms", formRepository.findAll());
		return "/redirect/list";
	}
	
	@GetMapping("/listcustomer")
	public String listCustomer(ModelMap model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "/index";
	} 
	
	@PostMapping("/searchcustomer")
	public String listSingleCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		model.addAttribute("forms", customerRepository.findAll());
		return "/redirect/list";
	} 
	
	@GetMapping("/cadastrar")
	public String cadastrar(RedirectForm form, ModelMap model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "/redirect/cadastro";
	}
	
	@PostMapping("/salvarurl")
	public String saveForm(RedirectForm form, Authentication auth) throws Exception {
		try {
			Users user = (Users) auth.getPrincipal();
			Optional<Customer> customerId = customerRepository.findById(form.getId());
			redirectService.save(form, user.getId(), customerId);
		} catch (Exception error) {
			error.printStackTrace();
			return "redirect/cadastro";
		}
		return "redirect:/redirect/list";
	}
	
	@PostMapping("/edit")
	public String editar(RedirectForm form, Authentication auth, Model model) {
		try {
			Users user = (Users) auth.getPrincipal();
			model.addAttribute("customers", customerRepository.findById(form.getId()));
			redirectService.save(form, user.getId(), user.getCustomerId());
		} catch (Exception error) {
			error.printStackTrace();
		}
		return "redirect/cadastro";
	}
	
	@GetMapping("/delete")
	public String excluir(String name) {
		try {
		    RedirectForm form = formRepository.findByName(name);
		    formRepository.delete(form);
		} catch (Exception error) {
			error.printStackTrace();
			return "redirect/cadastro";
		}
		return "redirect:/redirect/list";
	}
}