import React, { useEffect, useState } from 'react';
import { Row, Col, Form, Container } from 'react-bootstrap';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'

import currencies from './currency_list';
import axios from "axios";


function App() {

  const [fromCurrencyAmount, setFromCurrencyAmount] = useState(1);
  const [fromCurrency, setFromCurrency] = useState("EUR");

  const [toCurrencyAmount, setToCurrencyAmount] = useState(0);
  const [toCurrency, setToCurrency] = useState("HUF");

  const [theme, setTheme] = useState('default');
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
    <div className={`App ${theme}-theme`}>
      <header className="App-header">
        <Container>
          <Form.Select style={{ width: '20vh', float: 'right' }} onChange={handleThemeChange} value={theme}>
            <option value="default">Default Theme</option>
            <option value="light">Light Theme</option>
            <option value="dark">Dark Theme</option>
          </Form.Select>
        </Container>
        <Container className='mb-4'>
          <h1>Currency Converter</h1>
        </Container>
        <Container>
          <Row>
            <Col md>
              <Row>
                <Form>
                  <Col>
                    <Form.Group>
                      <Form.Control
                        name="FromCurrencyAmount"
                        type="number"
                        placeholder="Enter the amount"
                        value={fromCurrencyAmount}
                        min={1}
                        onChange={handleFromCurrencyAmountChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Select
                      name="FromCurrencyList"
                      onChange={handleFromCurrencyChange}
                      value={fromCurrency}>
                      {currencies.map((currency) => (
                        <option key={currency}>{currency}</option>
                      ))}
                    </Form.Select>
                  </Col>
                </Form>
              </Row>
            </Col>
            <Col>
              Equals
            </Col>
            <Col md>
              <Row>
                <Form>
                  <Col>
                    <Form.Group>
                      <Form.Control
                        disabled={true}
                        name="ToCurrencyAmount"
                        type="number"
                        value={toCurrencyAmount}
                        onChange={handleToCurrencyAmountChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Select
                      name="ToCurrencyList"
                      onChange={handleToCurrencyChange}
                      value={toCurrency}>
                      {currencies.map((currency) => (
                        <option key={currency}>{currency}</option>
                      ))}
                    </Form.Select>
                  </Col>
                </Form>
              </Row>
            </Col>
          </Row>
        </Container>
      </header>
    </div>
  );
}

export default App;
