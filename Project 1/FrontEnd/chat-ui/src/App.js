import React, { useState } from 'react';
import SockJsClient from 'react-stomp';
import './App.css';
import Input from './components/Input/Input';
import LoginForm from './components/LoginForm';
import Messages from './components/Messages/Messages';
import chatAPI from './services/chatapi';


const SOCKET_URL = 'http://localhost:8080/dhakad-app/';

const App = () => {
  const [messages, setMessages] = useState([])
  const [user, setUser] = useState(null)

  let onMessageReceived = (msg) => {
    console.log('New Message Received!!');
    setMessages(messages.concat(msg));
  }

  let onSendMessage = (msgText) => {
      console.log('Sending...');
    chatAPI.sendMessage(user.username, msgText).then(res => {
      console.log('Sent...');
    }).catch(err => {
      console.log('Error Occured while sending message to api');
    })
  }

  let handleLoginSubmit = (username) => {
    console.log(username, " Logged in..");

    setUser({
      username: username
    })

  }

  return (
    <div className="App">
      {user ?
        (
          <>
            <SockJsClient
              url={SOCKET_URL}
              topics={['/topic/group']}
              onConnect={console.log("Connected!!!")}
              onDisconnect={console.log("Disconnected!!!")}
              onMessage={msg => onMessageReceived(msg)}
              debug={false}
            />
            <Messages
              messages={messages}
              currentUser={user}
            />
            <Input onSendMessage={onSendMessage} />
          </>
        ) :
        <LoginForm onSubmit={handleLoginSubmit} />
      }
    </div>
  )
}

export default App;
