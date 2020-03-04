package vaadin;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.Getter;
import vaadin.form.UserFormView;
import vaadin.grid.UserGridView;

@SpringUI
public class NavigatorUI extends UI {

    @Getter
    private Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);
        setNavigator(navigator);
        navigator.addView("" , new VaadinUI());
        navigator.addView("grid", new UserGridView());
        navigator.addView("form", new UserFormView());
    }
}
