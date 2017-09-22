package mybatis.services;

import mybatis.dao.UserAccounts;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public interface ICustomJsonResponses {

    static ModelMap getJsonResponseForInsert(UserAccounts accountData) {
        if (accountData == null) {
            return null;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject(JsonRespAttrName.HTTPS_STATUS, "200");
        mav.addObject(JsonRespAttrName.MESSAGE, "SUCCESS");
        mav.addObject(JsonRespAttrName.USER_MSG, IUserMsg.INSERT_USER_SUCCESS);
        mav.addObject(JsonRespAttrName.USER_ID, accountData.getAccountId());
        return mav.getModelMap();
    }

    static ModelAndView getModelAndView() {
        ModelAndView mav = new ModelAndView();

        mav.addObject(JsonRespAttrName.ERROR_CODE, "400");
        mav.addObject(JsonRespAttrName.HTTPS_STATUS, HttpStatus.BAD_REQUEST);
        mav.addObject(JsonRespAttrName.ERROR_TYPE, "Bad Request");
        return mav;
    }

}
