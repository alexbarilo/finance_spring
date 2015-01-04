package org.financespring.web;

import org.financespring.model.Account;
import org.financespring.model.Client;
import org.financespring.service.AccountService;
import org.financespring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
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
    public String addNewClient(@ModelAttribute(value = "client") Client client, Model model) {
        if (client.getId() == 0) {
            clientService.saveEntity(client);
        } else {
            clientService.updateEntity(client);
        }
        List<Client> listOfClients = clientService.getListOfEntities();
        model.addAttribute("listOfClients", listOfClients);
        model.addAttribute("client", new Client()); //check if it is needed as any further client-attributes
        return "clientpage";
    }

    @RequestMapping(value = "clientselection", method = RequestMethod.POST)
    public String processClientCRUD(HttpServletRequest request, Model model) {

        String chosenClientAction = "clientpage";
        String clientAction = request.getParameter("client-action");
        String selectedClient = request.getParameter("id");

        clientAction = clientAction.replaceAll("\\s","").toLowerCase();

        switch(clientAction) {
            case "addclient" :
                chosenClientAction = "newclientpage";
                Client client = new Client();
                model.addAttribute("client", client);
                break;
            case "delclient" :
                int clientId = 0;
                try {
                    clientId = Integer.parseInt(selectedClient);
                } catch (NumberFormatException e) {
                    // redirection to error-page
                }
                Client currentClient = clientService.getEntityById(Client.class, clientId);
                clientService.deleteEntity(currentClient);
                List<Client> listOfClients = clientService.getListOfEntities();
                model.addAttribute("listOfClients", listOfClients);
                break;
            case "editclient" :
                clientId = 0;
                try {
                    clientId = Integer.parseInt(selectedClient);
                } catch (NumberFormatException e) {
                    // redirection to error-page
                }
                currentClient = clientService.getEntityById(Client.class, clientId);
                model.addAttribute("client", currentClient);
                chosenClientAction = "newclientpage";
                break;
            case "showclientdetails" :
                clientId = 0;
                try {
                    clientId = Integer.parseInt(selectedClient);
                } catch (NumberFormatException e) {
                    // redirection to error-page
                }
                currentClient = clientService.getEntityById(Client.class, clientId);
                listOfClients = clientService.getListOfEntities();
                List<Account> listOfAccounts = accountService.getListOfEntities();
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
