package mybatis.controller;

import hello.AccountType;
import mybatis.dao.UserAccounts;
import mybatis.dao.UserEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated // This means that this class is a Controller
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
public interface IAccountController {


    @GetMapping(path = "/getAllAccounts")
    @ResponseBody
    List<UserAccounts> getAllAccounts();


    @GetMapping(path = "/userAccounts")
    @ResponseBody
    List<UserAccounts> getUserAccounts(@RequestParam Long userId);


    @GetMapping(path = "/getAccountByNrb")
    @ResponseBody
    UserAccounts getAccountData(@RequestParam String nrb);


    @GetMapping(path = "/insertNrbForUser")
    @ResponseBody
    long insertNrbData(@RequestParam int id,
                       @RequestParam String nrb,
                       @RequestParam AccountType type,
                       @RequestParam BigDecimal balance);


    @GetMapping(path = "/insertNrb")
    @ResponseBody
    ModelMap insertNrb(@RequestParam int userId,
                       @RequestParam AccountType type,
                       @RequestParam String nrb,
                       @RequestParam BigDecimal balance);


    @RequestMapping(method = RequestMethod.GET, path = "/getAccountsForUser")
    @ResponseBody
    UserEntity getAccountsForUserById(@RequestParam("userId") @NotNull Long userId);


    @GetMapping(path = "/getAccountsForUser2")
    @ResponseBody
    UserEntity getMyAllUsers(@RequestParam long userId);


}
