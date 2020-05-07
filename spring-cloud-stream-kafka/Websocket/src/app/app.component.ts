import { Component } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Kafka Websocket';
  description = '';

  greetings: string[] = [];
  disabled = true;
  name: string;
  private stompClient = null;

  constructor() { }

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  connect() {
    const socket = new SockJS('http://localhost:8080/kafka-stomp-endpoint');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.stompClient.subscribe('/topic/message', function (hello) {
        _this.showGreeting(JSON.parse(hello.body).contents + " - " +
        JSON.parse(hello.body).date);
      });
    });
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  sendName() {
    this.stompClient.send(
      '/jsa/hello',
      {},
      JSON.stringify({ 'name': this.name })
    );
  }

  getAllUsers() {
    this.stompClient.send(
      '/jsa/listall',
      {}
    );
  }

  showGreeting(message) {
    this.greetings.push(message);
  }
}
