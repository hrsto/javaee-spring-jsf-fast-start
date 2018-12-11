package com.webarity.fileUpload;

import java.io.IOException;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily="webarity-renderers", rendererType="file-upload-renderer")
public class FileUploadRenderer extends Renderer {

    @Override
    public void encodeEnd(FacesContext ctx, UIComponent comp) throws IOException {
        if (ctx == null || comp == null) throw new NullPointerException();

        UIFileUpload up = (UIFileUpload)comp;

        String tagWrapper = Optional.ofNullable(up.getWrapper()).orElse("div");

        try (ResponseWriter w = ctx.getResponseWriter();) {
            w.startElement("form", up);
            w.startElement(tagWrapper, up);
            
            w.startElement("input", up);
            w.writeAttribute("type", "file", null);
            w.writeAttribute("id", "fileUpload", null);
            if (up.getMaxFiles() != null && up.getMaxFiles() > 1) {
                w.writeAttribute("multiple", "", null);
            }
            w.endElement("input");

            w.endElement(tagWrapper);
            w.endElement("form");
        } catch (IOException ex) {
            throw ex;
        }
    }

    @Override
    public void decode(FacesContext ctx, UIComponent comp) {
        comp.getAttributes();
    }
}