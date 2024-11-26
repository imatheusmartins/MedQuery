package br.edu.fesa.MedQuery.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.edu.fesa.MedQuery.model.User;

@Component
@SessionScope
public class UserContext {

    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void clearUser() {
        currentUser = null;
    }

}
