package com.webarity;

import java.util.Locale;
import java.util.Map;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.AjaxBehaviorEvent;

public class SiteLocale implements ActionListener {

    private static Map<String, Locale> locales;
    private String someRandomTest;
    private Locale currentLocale;

    public SiteLocale() {
        this.setCurrentLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public Locale getCurrentLocale() {return currentLocale;}
    public void setCurrentLocale(Locale currentLocale) {this.currentLocale = currentLocale;}

    public String getSomeRandomTest() {
        return someRandomTest;
    }
    public void setSomeRandomTest(String someRandomTest) {this.someRandomTest = someRandomTest;}
    public Map<String, Locale> getLocales() {return SiteLocale.locales;}
    public void setLocales(Map<String, Locale> locales) {SiteLocale.locales = locales;}

    public void handleLocaleChange(AjaxBehaviorEvent ev) {
        System.out.println("SITE LOCALE CHANGE");
        if (ev.getSource() instanceof HtmlSelectOneMenu) {
            HtmlSelectOneMenu temp = (HtmlSelectOneMenu)ev.getSource();
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locales.get(temp.getValue()));
        }
    }

    @Override
    public void processAction(ActionEvent arg0) throws AbortProcessingException {

        System.out.println("SITE LOCALE CHANGE2");
	}
}