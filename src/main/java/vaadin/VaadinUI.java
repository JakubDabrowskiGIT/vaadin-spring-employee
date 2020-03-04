package vaadin;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;

public class VaadinUI extends VerticalLayout implements View {

    public VaadinUI() {
        Button button1 = new Button("Show grid list", event -> {
            getUI().getNavigator().navigateTo("grid");
        });

        Button button2 = new Button("Show form", event -> {
            getUI().getNavigator().navigateTo("form");
        });

        addComponents(button1, button2);
    }

}
