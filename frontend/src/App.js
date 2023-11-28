import React, { useEffect, useState } from 'react';
import { Row, Col, Form, Container } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';

import currencies from './currency_list';
import axios from "axios";


function App() {

  const [fromCurrencyAmount, setFromCurrencyAmount] = useState(1);
  const [fromCurrency, setFromCurrency] = useState("EUR");

  const [toCurrencyAmount, setToCurrencyAmount] = useState(0);
  const [toCurrency, setToCurrency] = useState("HUF");

  const [theme, setTheme] = useState('blue');
  const handleThemeChange = (event) => {
    setTheme(event.target.value);
  };

  useEffect(() => {
    axios.defaults.baseURL = "http://localhost:9090";
    console.log(`Conversion from: ${fromCurrency} to: ${toCurrency} with amount: ${fromCurrencyAmount}`)
    axios.get(`/exchangerates/latest/convert?from=${fromCurrency}&to=${toCurrency}&amount=${fromCurrencyAmount}`)
      .then(response => {
        setToCurrencyAmount(response.data); // Assuming the response contains the number value
      })
      .catch(error => console.error('Error fetching data: ', error));
  }, [toCurrency, fromCurrencyAmount, fromCurrency]);

  function handleFromCurrencyAmountChange(event) {
    event.preventDefault();
    setFromCurrencyAmount(event.target.value);
  }

  function handleFromCurrencyChange(event) {
    event.preventDefault();
    setFromCurrency(event.target.value);
  }

  function handleToCurrencyAmountChange(event) {
    event.preventDefault();
    setToCurrencyAmount(event.target.value);
  }

  function handleToCurrencyChange(event) {
    event.preventDefault();
    setToCurrency(event.target.value);
  }

  return (
    <div id="MainWrapper" className={`App ${theme}-theme`}>
      <Form.Select className="theme-selector" style={{ width: '20vh', float: 'left' ,margin: '1em'}} onChange={handleThemeChange} value={theme}>
        <option value="purple">Purple</option>
        <option value="blue">Blue</option>
      </Form.Select>
      <div id="Wrapper" >
        <div className="wrapperTitle" >Currency converter</div>
        <div className="horizontalBreak"></div>
        <Form.Control
            className="inputAmount fromInput"
            size="lg"
            name="FromCurrencyAmount"
            type="number"
            placeholder="Enter the amount"
            value={fromCurrencyAmount}
            onChange={handleFromCurrencyAmountChange}
        />
        <Form.Select
            className="SelectCurrency"
            size="lg"
            name="FromCurrencyList"
            onChange={handleFromCurrencyChange}
            value={fromCurrency}>
          {currencies.map((currency) => (
              <option key={currency}>{currency}</option>
          ))}
        </Form.Select>
        <div>Equals</div>
        <div>{Number(toCurrencyAmount).toFixed(2)}</div>
        <Form.Select
            className="SelectCurrency"
            size="lg"
            name="ToCurrencyList"
            onChange={handleToCurrencyChange}
            value={toCurrency}>
          {currencies.map((currency) => (
              <option key={currency}>{currency}</option>
          ))}
        </Form.Select>
      </div>
    </div>
  );
}

export default App;
