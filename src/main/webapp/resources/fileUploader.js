document.addEventListener("DOMContentLoaded", event => {
    var host = location.host;
    var protocol = location.protocol.startsWith("http") ? (location.protocol.endsWith('s:') ? 'wss' : 'ws') : "";
    if (protocol == "") return;

    var wsaddr = Array.from(document.getElementById("someForm").children)
        .find(elem => elem.hasAttribute("data:wsenpoint"))
        .getAttribute("data:wsenpoint");
    var address = protocol.concat('://').concat(host).concat(wsaddr);
    
    var ws = new WebSocket(address);
    
    ws.onmessage = function(e) {
        console.log("New message received.");
        console.log(e.data);  
    };
    ws.onopen = function(e) {
        ws.send("some message, a JSON string"); //send to server
        console.log("Connection opened.");
    };
    ws.onerror = function(e) {
        console.error("Connection lost due to error.");
    };
    ws.onclose = function(e) {
        if (e.wasClean) {
            console.log("Connection closed.");
        } else {
            console.error("Connection abnormally closed.");//e.code; e.reason
        }
    };
});
