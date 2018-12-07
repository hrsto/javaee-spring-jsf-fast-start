package com.webarity.listeners;

import java.util.Locale;
import java.util.Optional;

import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * <p>Probably not the ideal way. Hooks into the JSF lifecycle and for each render page adjusts the locale according to a {@link javax.servlet.http.HttpSession HttpSession} attribute. This attibute is set elsewhere - {@link com.webarity.SiteLocale SiteLocale}</p>
 */
public class LocalePhaseListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {

    }

    /**
     * <p>Change locale for requests across the app. Takes into consideration HttpSession. Also takes the http request header and checks if it the requested Locale is in the supported list. If it is, and if the the user has not chosen a specific Locale ({@link com.webarity.SiteLocale#setCurrentLocale(Locale) SiteLocale's currentLocale}), it will use that Locale. Otherwise, fallback to the default locale, set via {@literal faces-config.xml}.</p>
     * FIXME: check if the HttpRequest returns a null from the call to getRequestLocale()
     */
    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext tempExtCtx = FacesContext.getCurrentInstance();
        UIViewRoot tempUIViewRoot = tempExtCtx.getViewRoot();
        Application tempApp = tempExtCtx.getApplication();
        tempUIViewRoot.setLocale(
            Optional.ofNullable(tempApp.evaluateExpressionGet(tempExtCtx, "#{siteLocales.currentLocale}", Locale.class))
                .orElseGet(
                    () -> Optional.ofNullable(tempApp.evaluateExpressionGet(tempExtCtx, String.format("#{siteLocales.getSupportedLocale('%s')}", tempExtCtx.getExternalContext().getRequestLocale().getLanguage()), Locale.class))
                    .orElseGet(()-> tempExtCtx.getApplication().getDefaultLocale())
                )
        );
    }

    @Override
    public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
    }
    
}