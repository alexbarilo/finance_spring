package org.financespring.web;

import org.financespring.model.Account;
import org.financespring.model.BankTransaction;
import org.financespring.model.Client;
import org.financespring.service.AccountService;
import org.financespring.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@SessionAttributes("account")
public class BankTransactionController {

    @Autowired
    private BankTransactionService bankTransactionService;

    @Autowired
    private AccountService accountService;

    /*
    Method is used when user needs to come back to accountpage.jsp from newaccountpage.jsp.
     */
    @RequestMapping(value = "accountdetails", method = RequestMethod.GET)
    public String showAccountDetails(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "accountpage";
    }

    /*
    Method saves/updates bank transaction-object.
     */
    @RequestMapping(value = "newtransaction", method = RequestMethod.POST)
    public String addNewBankTransaction(@ModelAttribute(value = "transaction") @Valid BankTransaction bankTransaction,
                                        BindingResult result,
                                        HttpServletRequest request, Model model) {
        //Form validation. If errors occur user redirects to the same page with notifications.
        if (result.hasErrors()) {
            return "newtransactionpage";
        }

        Account account = (Account) request.getSession().getAttribute("account");
        float transactionAmount = bankTransactionService.getTotalTransactionsAmount() + bankTransaction.getBenAmount();
        float currentAccountAmount = account.getAmount();

        //Defines if total amount of all transactions including the current one overdrafts account amount.
        if(transactionAmount > currentAccountAmount) {
            request.setAttribute("message", "The account amount is insufficient to provide current transaction");
            request.setAttribute("action", "accountdetails");
            return "error-page";
        }

        bankTransaction.setAccountId(account);
        bankTransactionService.saveEntity(bankTransaction);

        account = accountService.getEntityById(Account.class, account.getId());
        Client client = new Client();

        model.addAttribute("account", account);
        model.addAttribute("client", client);

        return "transactionpage";
    }

}
