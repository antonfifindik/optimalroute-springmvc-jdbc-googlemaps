package com.optimalroute.app.controllers;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.optimalroute.app.interfaces.IClientService;
import com.optimalroute.app.objects.Client;

@Controller
public class CRUDClientController {

	private static Client deleteClient;
	private static Client updateClient;

	@Autowired
	private IClientService clientService;

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		ArrayList<Client> clientsList = (ArrayList<Client>) clientService.findAllClients();

		model.addAttribute("clientsList", clientsList);

		return "clients";
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.GET)
	public String addClientPage(Model model) {
		return "addClient";
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public String addClient(@ModelAttribute("client") Client client) {
		clientService.insert(client);
		return "redirect:/clients";
	}

	@RequestMapping(value = "/deleteClient", method = RequestMethod.GET)
	public String deleteClient(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		deleteClient = clientService.selectClientById(id);
		model.addAttribute("clientForDelete", deleteClient);
		return "confirmationOfDeletionClient";
	}

	@RequestMapping(value = "/deleteClient", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, Model model) {

		try {
			clientService.delete(deleteClient);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "errorDelete";
		}

		return "redirect:/clients";
	}

	@RequestMapping(value = "/updateClient", method = RequestMethod.GET)
	public String updateClient(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		updateClient = clientService.selectClientById(id);

		model.addAttribute("updateClient", updateClient);
		return "updateClient";
	}

	@RequestMapping(value = "/updateClient", method = RequestMethod.POST)
	public String updateClient(@ModelAttribute("client") Client client) {
		client.setId(updateClient.getId());
		clientService.update(client);
		return "redirect:/clients";
	}
}
