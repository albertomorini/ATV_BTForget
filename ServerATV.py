import json
from http.server import BaseHTTPRequestHandler, HTTPServer
import sys
PORT = 9988
import os

##################################################################################################
indexMessage = 0 ## to simulate a real time scenario, we send just a single message every request

## class will handle the requests
class GetHandler(BaseHTTPRequestHandler):
    def do_GET(self): ##GET REQUESTS
        self.send_response(200)
        self.send_header('Content-type','application/json')
        self.send_header('Access-Control-Allow-Origin','*')
        self.end_headers()
        if(self.path=='/turnOffBluetooth'):
            #sudo apt install playerctl
            os.system("playerctl pause")
            os.system("{ echo 'XXX'; } | sudo -S systemctl restart bluetooth.service") # replace XXX with password

        # the response
        message = {
            "success": True
        }


## START HTTP SERVER
def startServer():
    print('Server started...')
    server_address = ('10.0.0.3', PORT)
    httpd = HTTPServer(server_address, GetHandler)
    print('Server in ready...')
    httpd.serve_forever()
        

startServer()