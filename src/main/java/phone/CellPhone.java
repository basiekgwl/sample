package phone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CellPhone implements ICellPhone {

    private int myPhoneNumber;
    private boolean isRinging;
    private boolean isOn;

    CellPhone(int phoneNumber) {
        this.myPhoneNumber = phoneNumber;
    }

    @Override
    public void powerOn() {
        isOn = true;
        log.info("Power on your cell phone");
    }

    @Override
    public void dial(int phoneNumber) {
        log.info("Your phone is ringing. Phone number is: " + phoneNumber);
    }

    @Override
    public boolean callPhone(int phoneNumber) {
        if (phoneNumber == myPhoneNumber && isOn) {
            isRinging = true;
            log.info("Phone is ringing!!!");
        } else {
            isRinging = false;
        }
        return isRinging;
    }

    @Override
    public void answer() {
        if (isRinging) {
            log.info("Please answer phone");
            isRinging = false;
        }
    }

    @Override
    public boolean isRinging() {
        return isRinging;
    }
}
