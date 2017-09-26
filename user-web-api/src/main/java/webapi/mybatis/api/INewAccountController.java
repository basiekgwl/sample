package webapi.mybatis.api;

import com.viscomp.services.ErrorMsg;
import com.viscomp.services.FieldsSize;
import hello.AccountType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webapi.mybatis.dto.NewAccountDto;
import webapi.mybatis.dto.NewAccountWithUserDto;
import webapi.mybatis.dto.NewUserWithAccountsDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/api/accounts")
public interface INewAccountController {

    //GET - SELECT
    @RequestMapping(method = RequestMethod.GET, path = "/getAllAccounts")
    @ResponseBody
    List<NewAccountDto> getAllAccounts();


    @RequestMapping(method = RequestMethod.GET, path = "/getUserAccounts")
    @ResponseBody
    List<NewAccountDto> getUserAccounts(@RequestParam @NotNull String nik);


    @RequestMapping(method = RequestMethod.GET, path = "/getAccountByNrb")
    @ResponseBody
    NewAccountDto getAccountData(@RequestParam @NotNull String nrb);


    @RequestMapping(method = RequestMethod.GET, path = "/getAccountsForUser")
    @ResponseBody
    NewUserWithAccountsDto getUserEntityWithAllAccounts(@RequestParam("nik") @NotNull String nik);


    //POST - INSERT and UPDATE
    @RequestMapping(method = RequestMethod.POST, path = "/insertNrbForUser")
    @ResponseBody
    NewAccountDto insertNrbData(@RequestParam @NotNull Long id,
                                @RequestParam @NotNull String nrb,
                                @RequestParam @NotNull AccountType type,
                                @RequestParam @NotNull BigDecimal balance);


    @RequestMapping(method = RequestMethod.POST, path = "/insertNrb")
    @ResponseBody
    ModelMap insertNrb(@RequestParam @NotNull Long userId,
                       @RequestParam @NotNull AccountType type,
                       @RequestParam @NotNull String nrb,
                       @RequestParam @NotNull BigDecimal balance);

    @RequestMapping(method = RequestMethod.POST, path = "/updateAccount")
    @ResponseBody
    String updateAccount(@RequestParam @NotNull Long id,
                         @RequestParam @NotNull Long userId,
                         @RequestParam String type,
                         @RequestParam String nrb,
                         @RequestParam String balance,
                         @RequestParam String city);

    @RequestMapping(method = RequestMethod.GET, path = "/getOneAccountAndUserData")
    @ResponseBody
    NewAccountWithUserDto getOneAccountAndUserData(@Size(min = FieldsSize.ACCOUNT_NRB_SIZE, max = FieldsSize.ACCOUNT_NRB_SIZE, message = ErrorMsg.INVALID_NBR_SIZE)
                                                   @NotNull @RequestParam String accountNrb);
}
