package phone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeskPhone implements ICellPhone {

    private int myNumber;
    private boolean isRinging;

    DeskPhone(int phoneNumber) {
        this.myNumber = phoneNumber;
    }

    @Override
    public boolean isRinging() {
        return isRinging;
    }

    @Override
    public void powerOn() {
        log.info("Phone is OK");
    }

    @Override
    public void dial(int phoneNumber) {
        log.info("Dial: " + phoneNumber);
    }

    @Override
    public void answer() {
        log.info("Please answer the phone");
        isRinging = false;
    }

    @Override
    public boolean callPhone(int phoneNumber) {
        if (myNumber == phoneNumber) {
            isRinging = true;
        } else {
            isRinging = false;
        }
        return isRinging;
    }
}
