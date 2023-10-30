import React, { Component } from 'react';
import { Form, Button, Row, Col } from 'react-bootstrap';

class InputAmount extends Component {
  constructor() {
    super();
    this.state = {
      numberValue: 0,
    };
  }

  handleNumberChange = (event) => {
    // Update the state when the number input changes
    this.setState({ numberValue: event.target.value });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    // Use this.state.numberValue to access the entered number
    console.log(this.state.numberValue);
  }

  render() {
    return (
        <Row>
            <Form onSubmit={this.handleSubmit}>
                <Col>
                
                    <Form.Group>
                    <Form.Control
                        type="number"
                        placeholder="Enter the amount"
                        value={this.state.numberValue}
                        onChange={this.handleNumberChange}
                    />
                    </Form.Group>
                </Col>
                <Col>
                    <Button variant="primary" type="submit">
                        Convert
                    </Button>
                </Col>
            </Form>
        </Row>
      
    );
  }
}

export default InputAmount;