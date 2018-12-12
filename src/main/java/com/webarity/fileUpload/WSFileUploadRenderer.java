package com.webarity.fileUpload;

import java.io.IOException;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import javax.servlet.ServletContext;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

@FacesRenderer(componentFamily="webarity-renderers", rendererType="ws-file-upload-renderer")
public class WSFileUploadRenderer extends Renderer {

    private static boolean wsinit = false;

    @Override
    public void encodeBegin(FacesContext ctx, UIComponent comp) throws IOException {
        UIFileUpload up = (UIFileUpload)comp;
        if (up.getEndpoint() != null && !wsinit) {
            synchronized(WSFileUploadRenderer.class) {
                if(!wsinit) {
                    ServletContext c = (ServletContext)ctx.getExternalContext().getContext();
                    ServerContainer sc = (ServerContainer) c.getAttribute("javax.websocket.server.ServerContainer");

                    try {
                        sc.addEndpoint(
                            ServerEndpointConfig.Builder.create(WSFileUploader.class, up.getEndpoint()).build()
                        );
                        wsinit = true;
                    } catch (DeploymentException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void encodeEnd(FacesContext ctx, UIComponent comp) throws IOException {
        if (ctx == null || comp == null) throw new NullPointerException();

        UIFileUpload up = (UIFileUpload)comp;

        String tagWrapper = Optional.ofNullable(up.getWrapper()).orElse("div");

        try (ResponseWriter w = ctx.getResponseWriter();) {
            w.startElement(tagWrapper, up);

            w.writeAttribute("data:wsenpoint", up.getWsRootPrefix().concat(up.getEndpoint()), "endpoint");

            w.startElement("h1", up);
            w.writeText("WEB SOCKET", null);
            w.endElement("h1");

            w.startElement("input", up);
            w.writeAttribute("type", "file", null);
            w.writeAttribute("id", "fileUpload", null);
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
        if (ctx == null || comp == null) throw new NullPointerException();
        UIFileUpload up = (UIFileUpload)comp;
        
    }
}