package mybatis.controller;

import mybatis.dao.UserAccounts;
import mybatis.dao.UserEntity;
import mybatis.services.IErrorMsg;
import mybatis.services.IFieldsSize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated // This means that this class is a Controller
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
public interface IUserController {

    @GetMapping(path = "/find")
    @ResponseBody
    UserEntity getOneUser(@RequestParam Long userId);


    @GetMapping(path = "/getUserByName")
    @ResponseBody
    List<UserEntity> getUserByName(@RequestParam String fullName);

    @GetMapping(path = "/insertUser")
    @ResponseBody
    String addNewUser(@Size(max = IFieldsSize.USER_NAME_MAX_SIZE, message = IErrorMsg.INVALID_FULL_NAME_SIZE) @RequestParam String fullName,
                      @Size(min = IFieldsSize.USER_NIP_SIZE, max = IFieldsSize.USER_NIP_SIZE, message = IErrorMsg.INVALID_NIP_SIZE) @RequestParam String userNip,
                      @Size(min = IFieldsSize.USER_PESEL_SIZE, max = IFieldsSize.USER_PESEL_SIZE, message = IErrorMsg.INVALID_PESEL_SIZE) @RequestParam String userPesel,
                      @Size(max = IFieldsSize.USER_ADDRESS_MAX_SIZE, message = IErrorMsg.INVALID_ADDRESS_SIZE) @RequestParam String address,
                      @Size(max = IFieldsSize.USER_CITY_MAX_SIZE, message = IErrorMsg.INVALID_CITY_SIZE) @RequestParam String city);


    @GetMapping(path = "/updateUser")
    @ResponseBody
    String updateUserData(@RequestParam Long userId,
                          @RequestParam String fullName,
                          @RequestParam String userNip,
                          @RequestParam String userPesel,
                          @RequestParam String address,
                          @RequestParam String city);



    @GetMapping(path = "/getOneAccountAndUserData")
    @ResponseBody
    UserAccounts getOneAccountAndUserData(@Size(min = IFieldsSize.ACCOUNT_NRB_SIZE, max = IFieldsSize.ACCOUNT_NRB_SIZE, message = IErrorMsg.INVALID_NBR_SIZE)
                                          @RequestParam String accountNrb);



}
