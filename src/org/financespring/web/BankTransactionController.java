package org.financespring.web;

import org.financespring.model.Account;
import org.financespring.model.BankTransaction;
import org.financespring.model.Client;
import org.financespring.service.AccountService;
import org.financespring.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("account")
public class BankTransactionController {

    @Autowired
    private BankTransactionService bankTransactionService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "newtransaction", method = RequestMethod.POST)
    public String addNewBankTransaction(@ModelAttribute(value = "transaction") BankTransaction bankTransaction,
                                        HttpServletRequest request, Model model) {

        Account account = (Account) request.getSession().getAttribute("account");
        bankTransaction.setAccountId(account);
        bankTransactionService.saveEntity(bankTransaction);

        account = accountService.getEntityById(Account.class, account.getId());
        Client client = new Client();

        model.addAttribute("account", account);
        model.addAttribute("client", client);

        return "transactionpage";
    }

}
