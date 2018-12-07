package com.webarity.listeners;

import java.util.Locale;
import java.util.Optional;

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

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext tempExtCtx = FacesContext.getCurrentInstance();
        UIViewRoot tempUIViewRoot = tempExtCtx.getViewRoot();
        Optional.ofNullable(tempExtCtx.getApplication().evaluateExpressionGet(tempExtCtx, "#{siteLocales.currentLocale}", Locale.class))
            .ifPresentOrElse(locale -> tempUIViewRoot.setLocale(locale), () -> tempUIViewRoot.setLocale(Locale.ROOT));
    }

    @Override
    public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
    }
    
}