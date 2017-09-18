package phone;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class CellPhoneTest {

    @Test
    public void myPhoneTestFirst() {

        int myNumber = 513654321;
        int invalidNumber = 513073344;

        ICellPhone myPhone = new CellPhone(myNumber);

        myPhone.powerOn();
        myPhone.dial(myNumber);
        boolean result = myPhone.callPhone(myNumber);

        Assert.assertEquals(result, true);

        if (myPhone.isRinging()) {
            myPhone.answer();
        }
        Assert.assertEquals(myPhone.isRinging(), false);

        result = myPhone.callPhone(invalidNumber);
        Assert.assertEquals(result, false);
    }

    @Test
    public void myDeskPhoneTest() {

        int myDeskNumber = 123456;

        ICellPhone deskPhone = new DeskPhone(myDeskNumber);
        deskPhone.powerOn();
        deskPhone.dial(myDeskNumber);
        deskPhone.callPhone(myDeskNumber);
        Assert.assertEquals(deskPhone.isRinging(), true);

        deskPhone.answer();
        Assert.assertEquals(deskPhone.isRinging(), false);
    }
}
