package com.webarity.fileUpload.customBehaviors;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectOne;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class WsUploadTag extends TagHandler {

    public WsUploadTag(TagConfig conf) {
        super(conf);
    }

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        if (!(parent instanceof UISelectOne)) return;
        UISelectOne comp = (UISelectOne)parent;
        comp.addClientBehavior("click", new WSBehavior());
        comp.addClientBehavior("mouseover", new WSBehavior());
	}

}