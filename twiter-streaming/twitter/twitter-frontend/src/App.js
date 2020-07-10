import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";
import Websocket from 'react-websocket';


class Table extends Component {
  render() {
    const { messages, addMessage } = this.props;

    return (
      <div>
      <table className="App-messageTable">
        <TableHeader />
        <TableBody messages={messages} addMessage={addMessage} />
      </table>
      </div>
    );
  }
}

class TableHeader extends Component {
  render() {
    return (
      <thead>
        <tr>
          <th>ID</th>
          <th>Message</th>
        </tr>
      </thead>
    );
  }
}

const TableBody = props => {
  const rows = props.messages.map((row, index) => {
    return (
      <tr key={index}>
        <td>{row.name}</td>
        <td>{row.job}</td>
      </tr>
    )
  })

  return <tbody>{rows}</tbody>
}


class App extends Component {
  state = {
    messages: [],
  }
  addMessage = (message) => {
    this.setState({
      // messages: [...this.state.messages, message]});
      messages: [message, ...this.state.messages]});
  }

  handleData(data) {
    let result = JSON.parse(data);
    this.addMessage({"name": result.status.user.screenName, "job": result.status.text})
  }


  render() {
    const { messages } = this.state  

    return (
      <div className="App">
        <header className="App-header">
          <Table messages={messages} addMessage={this.addMessage}/>
        </header>

        <Websocket url='ws://localhost:8080/twitter'
              onMessage={this.handleData.bind(this)}/>
      </div>
    );
  }
}

export default App;
