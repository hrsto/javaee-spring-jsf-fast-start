package com.webarity.fileUpload;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;

@ResourceDependencies({
    @ResourceDependency(name="jsf.js", library="javax.faces", target="head"),
    @ResourceDependency(name="fileUploader.js", target="head")
})

@FacesComponent("FileUploader")
public class UIFileUpload extends UIInput {

    public static enum Props {
        maxFiles, maxFileSize, wrapper;
    }

    public Integer getMaxFiles() {
        return (Integer)getStateHelper().eval(Props.maxFiles);
    }
    public void setMaxFiles(Integer maxFiles) {
        getStateHelper().put(Props.maxFiles, maxFiles);
    }
    public String getWrapper() {
        return (String)getStateHelper().eval(Props.wrapper);
    }
    public void setWrapper(String wrapper) {
        getStateHelper().put(Props.wrapper, wrapper);
    }
    public Long getMaxFileSize() {
        return (Long)getStateHelper().eval(Props.maxFileSize);
    }
    public void setMaxFileSize(Long maxFileSize) {
        getStateHelper().put(Props.maxFileSize, maxFileSize);
    }

    @Override
    public String getFamily() {
        return "webarity-renderers";
    }

}