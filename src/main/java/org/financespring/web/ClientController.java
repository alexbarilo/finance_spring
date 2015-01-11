package org.financespring.web;

import org.financespring.model.Account;
import org.financespring.model.Client;
import org.financespring.service.AccountService;
import org.financespring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes({"client", "listOfClients"})
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initNewClientForm(ModelMap model) {
        Client client = new Client();
        List<Client> listOfClients = clientService.getListOfEntities();
        model.addAttribute("client", client);
        model.addAttribute("listOfClients", listOfClients);
        return "clientpage";
    }

    @RequestMapping(value = "newclient", method = RequestMethod.POST)
    public String addNewClient(@ModelAttribute(value = "client") @Valid Client client, BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "newclientpage";
        }

        if (client.getId() == 0) {
            clientService.saveEntity(client);
        } else {
            clientService.updateEntity(client);
        }
        List<Client> listOfClients = clientService.getListOfEntities();
        model.addAttribute("listOfClients", listOfClients);
        return "clientpage";
    }

    @RequestMapping(value = "clientselection", method = RequestMethod.POST)
    public String processClientCRUD(HttpServletRequest request, Model model) {

        String chosenClientAction = "clientpage";
        String clientAction = request.getParameter("client-action");
        String selectedClient = request.getParameter("id");

        clientAction = clientAction.replaceAll("\\s","").toLowerCase();

        int clientId = 0;

        switch(clientAction) {
            case "addclient" :
                Client client = new Client();
                model.addAttribute("client", client);

                chosenClientAction = "newclientpage";
                break;
            case "delclient" :
                try {
                    clientId = Integer.parseInt(selectedClient);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Please choose a client to delete");
                    request.setAttribute("action", "/");
                    return "error-page";
                }
                Client currentClient = clientService.getEntityById(Client.class, clientId);
                clientService.deleteEntity(currentClient);
                List<Client> listOfClients = clientService.getListOfEntities();
                model.addAttribute("listOfClients", listOfClients);
                break;
            case "editclient" :
                try {
                    clientId = Integer.parseInt(selectedClient);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Please choose a client to edit");
                    request.setAttribute("action", "/");
                    return "error-page";
                }
                currentClient = clientService.getEntityById(Client.class, clientId);
                model.addAttribute("client", currentClient);

                chosenClientAction = "newclientpage";
                break;
            case "showclientdetails" :
                try {
                    clientId = Integer.parseInt(selectedClient);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Please choose a client to show details");
                    request.setAttribute("action", "/");
                    return "error-page";
                }
                currentClient = clientService.getEntityById(Client.class, clientId);
                listOfClients = clientService.getListOfEntities();
                Account account = new Account();
                model.addAttribute("client", currentClient);
                model.addAttribute("listOfClients", listOfClients);
                model.addAttribute("account", account);

                chosenClientAction = "accountpage";
                break;
        }

        return chosenClientAction;
    }

}
