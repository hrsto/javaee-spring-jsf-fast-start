package com.webarity.fileUpload;

import java.util.Optional;

import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;

@ResourceDependency(name="fileUploader.js", target="head")
@FacesComponent("FileUploader")
public class UIFileUpload extends UIInput {

    public static enum Props {
        maxFiles, maxFileSize, wrapper, type, endpoint, wsRootPrefix;
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
    public String getEndpoint() {
        String e = (String)getStateHelper().eval(Props.endpoint);
        if (e != null) {
            if (!e.startsWith("/")) e = "/".concat(e);
            if (e.endsWith("/")) e = e.substring(0, e.length() - 1);
            return e;
        }
        return e;
    }
    public void setEndpoint(String endpoint) {
        getStateHelper().put(Props.endpoint, endpoint);
    }
    public String getWsRootPrefix() {
        String prefix = (String)getStateHelper().eval(Props.wsRootPrefix);
        if (prefix != null) {
            if (!prefix.startsWith("/")) prefix = "/".concat(prefix);
            if (prefix.endsWith("/")) prefix = prefix.substring(prefix.length());
        } else {
            prefix = "/";
        }
        return prefix;
    }
    public void setWsRootPrefix(String wsRootPrefix) {
        getStateHelper().put(Props.wsRootPrefix, wsRootPrefix);
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

    @Override
    public String getRendererType() {
        return Optional.ofNullable((String)getStateHelper().eval(Props.type)).orElse(super.getRendererType());
    }

}