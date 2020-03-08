package com.example.test.spring;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@Route
public class MainView extends AppLayout {

	private static final long serialVersionUID = 1L;

	public MainView() {

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();
		layout.setJustifyContentMode(JustifyContentMode.CENTER);
		layout.setAlignItems(Alignment.CENTER);

		H4 title = new H4("EASY TAX CALCULATOR");
		addToNavbar(new DrawerToggle(), title);
		Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		addToDrawer(tabs);
		setDrawerOpened(false);

		layout.add(new TaxForm());
		setContent(layout);
	}
}
