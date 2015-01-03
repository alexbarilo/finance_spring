package org.financespring.web;

import org.financespring.model.Account;
import org.financespring.model.Client;
import org.financespring.service.AccountService;
import org.financespring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes({"client", "listOfClients"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "newaccount", method = RequestMethod.POST)
    public String addNewAccount(@ModelAttribute(value = "account") Account account ,
                                HttpServletRequest request, Model model) {

        Client client = (Client) request.getSession().getAttribute("client");
        account.setClientId(client);

        if (account.getId() == 0) {
            account = accountService.saveAndRetrieveEntity(account);
        } else {
            account = accountService.updateAndRetrieveEntity(account);
        }

        List<Client> listOfClients = clientService.getListOfEntities();
        client = clientService.getEntityById(Client.class, client.getId());

        model.addAttribute("client", client);
        model.addAttribute("account", account);
        model.addAttribute("listOfClients", listOfClients);
        return "accountpage";
    }

    @RequestMapping(value = "accountselection", method = RequestMethod.POST)
    public String processAccountCRUD(HttpServletRequest request, Model model) {

        String accountAction = request.getParameter("account-buttons");
        String selectedAccount = request.getParameter("id");

        accountAction = accountAction.replaceAll("\\s", "").toLowerCase();
        String chosenAccountAction = "accountpage";

        switch(accountAction) {
            case "addaccount" :
                Account account = new Account();
                model.addAttribute("account", account);
                chosenAccountAction = "newaccountpage";
                break;
            case "delaccount" :
                int accountId = 0;
                try {
                    accountId = Integer.parseInt(selectedAccount);
                } catch (NumberFormatException e) {
                    //redirection to error-page
                }
                Account currentAccount = accountService.getEntityById(Account.class, accountId);
                accountService.deleteEntity(currentAccount);
                Client currentClient = (Client) request.getSession().getAttribute("client");
                currentClient = clientService.getEntityById(Client.class, currentClient.getId());
                account = new Account();
                model.addAttribute("client", currentClient);
                model.addAttribute("account", account);
                break;
            case "editaccount" :
                accountId = 0;
                try {
                    accountId = Integer.parseInt(selectedAccount);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                account = accountService.getEntityById(Account.class, accountId);
                model.addAttribute("account", account);
                chosenAccountAction = "newaccountpage";
                break;
            case "showaccountdetails" :

                break;
            case "addtransaction" : break;
        }

        return chosenAccountAction;
    }

}
