package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.SQLOutput;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AppController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private UserDao userDao;
    @Autowired
    private TransferDao transferDao;
    @Autowired
    AccountDao accountDao;


    public AppController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Returns the current balance of the currently logged-in user's account")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping (path = "account", method = RequestMethod.GET)
    public BigDecimal getAccountBalance(Principal principal) {
        return accountDao.getBalance(principal.getName());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Returns a list of all users in the system")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping (path = "user", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a Transfer")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping (path = "transfer", method = RequestMethod.POST)
    public Long transferFrom (@RequestBody @Valid Transfer transfer, Principal principal) {
        transfer.setAccountToId(accountDao.getAccountIdByUserId(transfer.getAccountToId()));
        transfer.setAccountFromId(accountDao.getAccountIdByUserId(transfer.getAccountFromId()));

        long userIdFrom = userDao.findIdByUsername(principal.getName());
        long userIdTo = accountDao.getUserIdByAccountId(transfer.getAccountToId());

        transferDao.subtractFromAccount(userIdFrom,transfer.getTransferAmount());
        transferDao.addToAccount(userIdTo,transfer.getTransferAmount());

        return transferDao.createTransferFromCurrentUser(userIdTo, userIdFrom, transfer.getTransferAmount());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Returns a list of TransferDTO's based on the Current user's ID")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping (path = "transfer/user", method = RequestMethod.GET)
    public List<TransferDTO> getAllTransfers(Principal principal) {

        long userIdFrom = userDao.findIdByUsername(principal.getName());

        return transferDao.seeAllTransfers(accountDao.getAccountIdByUserId(userIdFrom), principal);
    }
    
    @ApiOperation("Returns a TransferDTO Object")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    @RequestMapping (path = "transfer/view{id}", method = RequestMethod.GET)
    public TransferDTO getATransfer(@PathVariable Long id ) {
        return transferDao.seeATransfer(id);
    }
}
