import React, {useState, useEffect} from "react";
import logo from './logo.svg';
import './App.css';

function App() {
  const [message, setMessage] = useState([]);
  useEffect(() => {
    fetch("api/test")
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          setMessage(data);
        })
  }, []);
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <ul>
          {message.map((v, idx) => <li key={`${idx}-${v}`}>{v}</li>)}
        </ul>
      </header>
    </div>
  );
}

export default App;
