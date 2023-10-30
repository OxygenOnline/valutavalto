import React from 'react';
import { Row, Col, Form, Container} from 'react-bootstrap';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import InputAmount from './InputAmount';
import ConvertedAmount from './ConvertedAmount';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Container className='mb-4'>
          <h1>Currency Converter</h1>
        </Container>
        <Container>
          <Row>
            <Col md>
              <InputAmount />
            </Col>
            <Col md>
              <Form.Select>
              <option key = 'blankChoice' hidden value> Target Currency </option>
              <option>USD</option>
              <option>HUF</option>
              <option>GBP</option>
              </Form.Select>
            </Col>
            <Col md>
              <ConvertedAmount />
            </Col>
          </Row>
        </Container>
      </header>
    </div>
  );
}

export default App;
