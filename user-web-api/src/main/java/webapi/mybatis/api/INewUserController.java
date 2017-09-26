package webapi.mybatis.api;

import webapi.mybatis.dto.NewUserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webapi.mybatis.dto.NewUserWithAccountsDto;

import javax.validation.constraints.NotNull;

@RestController
@Validated // This means that this class is a Controller
@RequestMapping(path = "/api/user") // This means URL's start with /demo (after Application path)
public interface INewUserController {


    @RequestMapping(method = RequestMethod.GET, path = "/getUserByNik")
    @ResponseBody
    NewUserDto getUserByNik(@NotNull @RequestParam String nik);

    @RequestMapping(method = RequestMethod.POST, path = "/deleteUserWithAccounts")
    @ResponseBody
    String deleteUserWithAccounts(@RequestParam @NotNull String nik);

    @RequestMapping(method = RequestMethod.POST, path = "/updateUser")
    @ResponseBody
    String updateUser(@RequestParam String fullName,
                      @RequestParam String userNip,
                      @RequestParam String userPesel,
                      @RequestParam String address,
                      @RequestParam String city,
                      @RequestParam String nik);


    @RequestMapping(method = RequestMethod.GET, path = "/getUserWithAccounts")
    @ResponseBody
    NewUserWithAccountsDto getUserEntityWithAllAccounts(@RequestParam("nik") @NotNull String nik);

}
