package phone;

public interface ICellPhone {

    void powerOn();

    boolean isRinging();

    void dial(int phoneNumber);

    boolean callPhone(int phoneNumber);

    void answer();

}
