import React from 'react';
import CreateCustomer from './component/CreateCustomer';
import InquireCustomer from './component/InquireCustomer';

function App() {
  return (
    <div style={{
        padding: '20px',
        backgroundColor: '#FFF9C4', // Light yellow background
        borderRadius: '10px',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
        textAlign: 'center',
        maxWidth: '600px',
        margin: '30px auto',
        border: '2px solid #FFD54F'
    }}>
        <h1 style={{
            color: '#FFB300',
            marginBottom: '30px',
            fontSize: '2rem',
            fontWeight: 'bold'
        }}>May Bank Assignment</h1>

        <CreateCustomer />
        <InquireCustomer />
    </div>
  );
}

export default App;