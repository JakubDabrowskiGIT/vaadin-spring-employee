package vaadin.grid;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import vaadin.form.UserFormView;
import vaadin.user.User;
import vaadin.user.UserData;

import java.util.List;

public class UserGridView extends GridLayout implements View {

    public UserGridView() {
        Button returnButton = new Button("Menu", event -> {
            getUI().getNavigator().navigateTo("");
        });

        addComponent(returnButton);
        setComponentAlignment(returnButton, Alignment.TOP_CENTER);

        setWidth("100%");
        setHeightUndefined();
        setSpacing(true);

        List<User> userList = getUserList();

        if (userList == null) {
            addComponent(new Label("No data"));
            return;
        }

        Grid<User> grid = new Grid();

        grid.setSizeFull();
        grid.setItems(userList);

        grid.addColumn(User::getId);
        grid.addColumn(User::getLogin).setCaption("Login");
        grid.addColumn(User::getEmail);
        grid.addColumn(User::getRegistrationDate);
        grid.addColumn(User::getPassword);

        grid.addItemClickListener(item -> {
            Window window = new Window();
            window.setPosition(item.getMouseEventDetails().getClientX(),item.getMouseEventDetails().getClientY());
            window.setDraggable(true);
            UserFormView userFormView = new UserFormView();
            userFormView.setUser(item.getItem());
            FormLayout formLayout = userFormView.createFromLayout();
            formLayout.setMargin(true);
            window.setContent(formLayout);
            UI.getCurrent().addWindow(window);
            grid.setItems(userList);
        });

        addComponent(grid);
    }

    private List<User> getUserList() {
        UserData userData = new UserData();
        List<User> userList = userData.getUsers();
        return userList;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the User List");
    }
}
