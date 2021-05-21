import React from 'react'

const Messages = ({ messages, currentUser }) => {

    let renderMessage = (message) => {
        const { sender, content,timestamp } = message;
        const messageFromMe = currentUser.username === message.sender;
        const className = messageFromMe ? "Messages-message currentUser" : "Messages-message";
        return (
            <li className={className} style={messageFromMe ? {marginRight:"0.5rem"}:{marginLeft:"0.5rem"}}>
                <div className="Message-content">
                    <div className="username">
                        {messageFromMe ? "You": sender}
                    </div>
                    <div className="text">
                        <div className="text-content">{content}</div>
                        <div className="text-time">{timestamp.substring(11,16)}</div>
                    </div>
                </div>
            </li>
        );
    };

    return (
        <ul className="messages-list">
            {messages.map((msg,i) => 
                <div key={i}>{renderMessage(msg)}</div>
            )}
        </ul>
    )
}


export default Messages