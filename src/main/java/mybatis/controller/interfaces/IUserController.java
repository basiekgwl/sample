package mybatis.controller.interfaces;

import mybatis.dto.AccountsWithUserDto;
import mybatis.dto.UserDto;
import mybatis.services.ErrorMsg;
import mybatis.services.FieldsSize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated // This means that this class is a Controller
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
public interface IUserController extends IAbstractController {

    @RequestMapping(method = RequestMethod.GET, path = "/getUserByNik")
    @ResponseBody
    UserDto getUserByNik(@NotNull @RequestParam String nik);


    @RequestMapping(method = RequestMethod.GET, path = "/getUserByName")
    @ResponseBody
    List<UserDto> getUserListByName(@Size(min = 1) @NotNull @RequestParam String fullName);


    @RequestMapping(method = RequestMethod.GET, path = "/getOneAccountAndUserData")
    @ResponseBody
    AccountsWithUserDto getOneAccountAndUserData(@Size(min = FieldsSize.ACCOUNT_NRB_SIZE, max = FieldsSize.ACCOUNT_NRB_SIZE, message = ErrorMsg.INVALID_NBR_SIZE)
                                                 @NotNull @RequestParam String accountNrb);


    @RequestMapping(method = RequestMethod.POST, path = "/insertUser")
    @ResponseBody
    String addNewUser(@Size(max = FieldsSize.USER_NAME_MAX_SIZE, message = ErrorMsg.INVALID_FULL_NAME_SIZE) @RequestParam String fullName,
                      @Size(min = FieldsSize.USER_NIP_SIZE, max = FieldsSize.USER_NIP_SIZE, message = ErrorMsg.INVALID_NIP_SIZE) @RequestParam String userNip,
                      @Size(min = FieldsSize.USER_PESEL_SIZE, max = FieldsSize.USER_PESEL_SIZE, message = ErrorMsg.INVALID_PESEL_SIZE) @RequestParam String userPesel,
                      @Size(max = FieldsSize.USER_ADDRESS_MAX_SIZE, message = ErrorMsg.INVALID_ADDRESS_SIZE) @RequestParam String address,
                      @Size(max = FieldsSize.USER_CITY_MAX_SIZE, message = ErrorMsg.INVALID_CITY_SIZE) @RequestParam String city,
                      @Size(max = FieldsSize.NIK_SIZE, message = ErrorMsg.INVALID_NIK_SIZE) @RequestParam String nik);


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
    String deleteUserWithAccounts(@RequestParam @NotNull String nik);

}
