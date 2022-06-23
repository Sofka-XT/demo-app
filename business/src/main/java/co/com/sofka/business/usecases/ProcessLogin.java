package co.com.sofka.business.usecases;

import java.util.Map;

public interface ProcessLogin {
    void login();
    Map<String, String> cookies();
    void logout();
}
