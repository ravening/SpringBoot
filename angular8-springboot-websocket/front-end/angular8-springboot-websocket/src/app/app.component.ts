import { Component } from '@angular/core';
import { WebSocketAPI } from './WebSocketAPI';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular8-springboot-websocket';

  webSocketAPI: WebSocketAPI;
  greeting: string;
  name: string;
  another: string;
  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(new AppComponent());

  }

  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  sendMessage() {
    this.another = 'venkatesh rakesh';
    this.webSocketAPI._send(this.name);
  }

  handleMessage(message) {
    this.another = 'rakesh venaktesh';
    this.greeting = JSON.parse(message.body).content;
    this.name = message;
    console.log(this.another);
    console.log('message is ' + this.greeting);
  }
}
