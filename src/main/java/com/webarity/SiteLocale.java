package com.webarity;

import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

public class SiteLocale {

    private Map<String, Locale> locales;
    private Locale currentLocale;

    public SiteLocale() {
        
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Map<String, Locale> getLocales() {return this.locales;}
    public void setLocales(Map<String, Locale> locales) {this.locales = locales;}

    public Locale getSupportedLocale(String language) {
        return locales.get(language);
    }

    public void handleLocaleChange(AjaxBehaviorEvent ev) throws AbortProcessingException {
        if (ev.getSource() instanceof HtmlSelectOneMenu) {
            HtmlSelectOneMenu temp = (HtmlSelectOneMenu)ev.getSource();
            setCurrentLocale((Locale)temp.getValue());
        }
    }

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (!locales.containsKey(((Locale)value).toString())) {
            throw new ValidatorException(new FacesMessage("Language not supported"));
        }
	}
}