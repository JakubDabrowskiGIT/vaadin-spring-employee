package vaadin.form;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vaadin.user.User;
import vaadin.user.UserData;

public class UserFormView extends VerticalLayout implements View {

    private User user;

    public UserFormView() {
        Button returnButton = new Button("Menu", event -> {
            getUI().getNavigator().navigateTo("");
        });

        addComponent(returnButton);
        setComponentAlignment(returnButton, Alignment.TOP_CENTER);

        setWidth("100%");
        setHeightUndefined();
        setSpacing(true);

        setDefaultComponentAlignment(Alignment.TOP_CENTER);

        setCaption("User Form");

        addComponent(createFromLayout());

    }

    public FormLayout createFromLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.setSizeUndefined();

        TextField tf1 = new TextField("Login");
        tf1.setSizeUndefined();
        tf1.setIcon(VaadinIcons.USER);
        tf1.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tf1);

        PasswordField pf = new PasswordField("Password");
        pf.setSizeUndefined();
        pf.setIcon(VaadinIcons.PASSWORD);
        pf.setRequiredIndicatorVisible(true);
        formLayout.addComponent(pf);

        TextField tf2 = new TextField("Email");
        tf2.setSizeUndefined();
        tf2.setIcon(VaadinIcons.ENVELOPE);
        formLayout.addComponent(tf2);

        DateField dateField = new DateField();
        dateField.setSizeUndefined();
        dateField.setIcon(VaadinIcons.DATE_INPUT);
        formLayout.addComponent(dateField);

        if(user != null) {
            tf1.setValue(user.getLogin());
            pf.setValue(user.getPassword());
            tf2.setValue(user.getEmail());
            dateField.setValue(user.getRegistrationDate());
        }

        Button save = new Button();
        save.setSizeUndefined();
        save.setIcon(VaadinIcons.CHECK);
        formLayout.addComponent(save);

        UserData userData = new UserData();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        save.addClickListener(event -> {

            if(user == null) {
                User user = new User(userData.getUsers().size() + 1, tf1.getValue(), bCryptPasswordEncoder.encode(pf.getValue()), tf2.getValue(), dateField.getValue());
                userData.addUser(user);
                Notification.show("Success! You add a new user: " + user);
            } else {
                user.setLogin(tf1.getValue());
                user.setPassword(bCryptPasswordEncoder.encode(pf.getValue()));
                user.setEmail(tf2.getValue());
                user.setRegistrationDate(dateField.getValue());
                userData.updateUser(user);
                Notification.show("Success! You done update date of user: " + user);
            }
        });

        return formLayout;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
