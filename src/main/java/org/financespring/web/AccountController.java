package org.financespring.web;

import org.financespring.model.Account;
import org.financespring.model.BankTransaction;
import org.financespring.model.Client;
import org.financespring.service.AccountService;
import org.financespring.service.BankTransactionService;
import org.financespring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes({"client", "listOfClients", "account"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "clientdetails", method = RequestMethod.GET)
    public String showClientDetails(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "accountpage";
    }

    @RequestMapping(value = "newaccount", method = RequestMethod.POST)
    public String addNewAccount(@ModelAttribute(value = "account") @Valid Account account ,
                                BindingResult result,
                                HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            return "newaccountpage";
        }

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

        int accountId = 0;

        switch(accountAction) {
            case "addaccount" :
                Account account = new Account();
                model.addAttribute("account", account);

                chosenAccountAction = "newaccountpage";
                break;
            case "delaccount" :
                try {
                    accountId = Integer.parseInt(selectedAccount);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "There is no account to delete");
                    request.setAttribute("action", "clientdetails");
                    return "error-page";
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
                try {
                    accountId = Integer.parseInt(selectedAccount);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "There is no account to edit");
                    request.setAttribute("action", "clientdetails");
                    return "error-page";
                }
                account = accountService.getEntityById(Account.class, accountId);
                model.addAttribute("account", account);

                chosenAccountAction = "newaccountpage";
                break;
            case "showaccountdetails" :
                try {
                    accountId = Integer.parseInt(selectedAccount);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "The current client has no accounts");
                    request.setAttribute("action", "clientdetails");
                    return "error-page";
                }
                account = accountService.getEntityById(Account.class, accountId);
                model.addAttribute("account", account);

                chosenAccountAction = "transactionpage";
                break;
            case "addtransaction" :
                try {
                    accountId = Integer.parseInt(selectedAccount);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "There are no accounts to make bank transaction");
                    request.setAttribute("action", "clientdetails");
                    return "error-page";
                }
                currentAccount = accountService.getEntityById(Account.class, accountId);
                BankTransaction bankTransaction = new BankTransaction();

                model.addAttribute("account", currentAccount);
                model.addAttribute("transaction", bankTransaction);

                chosenAccountAction = "newtransactionpage";
                break;
        }

        return chosenAccountAction;
    }

}
