package mybatis.controller.interfaces;

import mybatis.dto.AccountDto;
import mybatis.dto.UserDto;
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

    @RequestMapping(method = RequestMethod.GET, path = "/getUserByNik")
    @ResponseBody
    UserDto getUserByNik(@RequestParam String nik);


    @RequestMapping(method = RequestMethod.GET, path = "/getUserByName")
    @ResponseBody
    List<UserDto> getUserListByName(@RequestParam String fullName);


    @RequestMapping(method = RequestMethod.GET, path = "/getOneAccountAndUserData")
    @ResponseBody
    AccountDto getOneAccountAndUserData(@Size(min = IFieldsSize.ACCOUNT_NRB_SIZE, max = IFieldsSize.ACCOUNT_NRB_SIZE, message = IErrorMsg.INVALID_NBR_SIZE)
                                                @RequestParam String accountNrb);


    @RequestMapping(method = RequestMethod.POST, path = "/insertUser")
    @ResponseBody
    String addNewUser(@Size(max = IFieldsSize.USER_NAME_MAX_SIZE, message = IErrorMsg.INVALID_FULL_NAME_SIZE) @RequestParam String fullName,
                      @Size(min = IFieldsSize.USER_NIP_SIZE, max = IFieldsSize.USER_NIP_SIZE, message = IErrorMsg.INVALID_NIP_SIZE) @RequestParam String userNip,
                      @Size(min = IFieldsSize.USER_PESEL_SIZE, max = IFieldsSize.USER_PESEL_SIZE, message = IErrorMsg.INVALID_PESEL_SIZE) @RequestParam String userPesel,
                      @Size(max = IFieldsSize.USER_ADDRESS_MAX_SIZE, message = IErrorMsg.INVALID_ADDRESS_SIZE) @RequestParam String address,
                      @Size(max = IFieldsSize.USER_CITY_MAX_SIZE, message = IErrorMsg.INVALID_CITY_SIZE) @RequestParam String city,
                      @Size(max = IFieldsSize.NIK_SIZE, message = IErrorMsg.INVALID_NIK_SIZE) @RequestParam String nik);


    @RequestMapping(method = RequestMethod.POST, path = "/updateUser")
    @ResponseBody
    String updateUser(@RequestParam String fullName,
                      @RequestParam String userNip,
                      @RequestParam String userPesel,
                      @RequestParam String address,
                      @RequestParam String city,
                      @RequestParam String nik);

    @RequestMapping(method = RequestMethod.POST, path = "/deleteUserWithAccounts")
    @ResponseBody
    String deleteUserWithAccounts(@RequestParam String nik);

}
