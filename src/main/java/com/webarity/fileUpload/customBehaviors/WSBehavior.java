package com.webarity.fileUpload.customBehaviors;

import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectOne;
import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;

@FacesBehavior("wsupld")
public class WSBehavior extends ClientBehaviorBase {

    @Override
    public String getScript(ClientBehaviorContext behaviorContext) {
        UISelectOne comp = (UISelectOne)behaviorContext.getComponent(); //will hold the new value
        behaviorContext.getEventName();
        return String.format("console.log('Triggered by event: %s; old val: %s, new val: %s, local val: %s')", 
            behaviorContext.getEventName(), 
            Optional.ofNullable(comp.getValue()).orElse(""),
            Optional.ofNullable(comp.getSubmittedValue()).orElse(""),
            Optional.ofNullable(comp.getLocalValue()).orElse(""));
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        UISelectOne comp = (UISelectOne)component;
        super.decode(context, component);
    }
}