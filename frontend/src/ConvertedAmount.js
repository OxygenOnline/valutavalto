import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Row, Col } from 'react-bootstrap';

function ConvertedAmount() {
  const [numberValue, setNumberValue] = useState(0);

  useEffect(() => {
    // Fetch data from the backend
    axios.get('/backend-endpoint') //TODO
      .then(response => {
        setNumberValue(response.data); // Assuming the response contains the number value
      })
      .catch(error => console.error('Error fetching data: ', error));
  }, []);

  return (
    <Row>
        <Col>Converted amount:</Col>
        <Col><p>{numberValue}</p></Col>
    </Row>
    );
}

export default ConvertedAmount;