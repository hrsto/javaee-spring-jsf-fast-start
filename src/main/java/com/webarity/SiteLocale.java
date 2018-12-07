package com.webarity;

import java.util.Locale;
import java.util.Map;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

public class SiteLocale {

    private Map<String, Locale> locales;
    private String someRandomTest;
    private Locale currentLocale;

    public SiteLocale() {
        
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getSomeRandomTest() {
        return someRandomTest;
    }
    public void setSomeRandomTest(String someRandomTest) {this.someRandomTest = someRandomTest;}
    public Map<String, Locale> getLocales() {return this.locales;}
    public void setLocales(Map<String, Locale> locales) {this.locales = locales;}

    public Locale getSupportedLocale(String language) {
        return locales.get(language);
    }

    public void handleLocaleChange(AjaxBehaviorEvent ev) {
        if (ev.getSource() instanceof HtmlSelectOneMenu) {
            HtmlSelectOneMenu temp = (HtmlSelectOneMenu)ev.getSource();
            if (temp.getValue() != null && temp.getId().compareTo("languageSelector") == 0) {
                setCurrentLocale(locales.get(temp.getValue()));
            }
        }
    }
}