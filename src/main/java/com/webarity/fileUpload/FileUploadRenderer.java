package com.webarity.fileUpload;

import java.io.IOException;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@FacesRenderer(componentFamily="webarity-renderers", rendererType="file-upload-renderer")
public class FileUploadRenderer extends Renderer {

    @Override
    public void encodeEnd(FacesContext ctx, UIComponent comp) throws IOException {
        if (ctx == null || comp == null) throw new NullPointerException();

        UIFileUpload up = (UIFileUpload)comp;

        String tagWrapper = Optional.ofNullable(up.getWrapper()).orElse("div");

        try (ResponseWriter w = ctx.getResponseWriter();) {
            w.startElement(tagWrapper, up);
            
            w.startElement("input", up);
            w.writeAttribute("type", "file", null);
            w.writeAttribute("id", "fileUpload", null);
            w.writeAttribute("name", up.getClientId(), "clientId");
            if (up.getMaxFiles() != null && up.getMaxFiles() > 1) {
                w.writeAttribute("multiple", "", null);
            }
            w.endElement("input");

            w.endElement(tagWrapper);
        } catch (IOException ex) {
            throw ex;
        }
    }

    @Override
    public void decode(FacesContext ctx, UIComponent comp) {
        HttpServletRequest rq = (HttpServletRequest) ctx.getExternalContext().getRequest();
        try {
            rq.getParts().stream().forEach(part -> {
                System.out.println(String.format("name: %s; size: %d, type: %s", part.getName(), part.getSize(),
                        part.getContentType()));
            });
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
		}
        comp.getAttributes();
    }
}