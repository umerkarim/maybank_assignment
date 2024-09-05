import React, { useState } from 'react';
import axios from 'axios';

const InquireCustomer = () => {
    const [id, setId] = useState('');
    const [customer, setCustomer] = useState(null);

    const handleInquireCustomer = async () => {
        try {
           const response = await axios.post(
                   '/api/customers/get',
                   { id },
                   {
                     headers: {
                       'Content-Type': 'application/json'
                     }
                   }
                 );
            setCustomer(response.data);
        } catch (error) {
            console.error('Error inquiring customer:', error);
        }
    };

    return (
        <div style={{
            maxWidth: '400px',
            margin: '20px auto',
            padding: '20px',
            border: '2px solid #2196F3',
            borderRadius: '10px',
            backgroundColor: '#f9f9f9',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
            textAlign: 'center'
        }}>
            <h2 style={{ color: '#2196F3' }}>Inquire Customer</h2>
            <input
                type="number"
                value={id}
                onChange={(e) => setId(e.target.value)}
                placeholder="Customer ID"
                style={{
                    width: '80%',
                    padding: '10px',
                    margin: '10px 0',
                    border: '1px solid #ccc',
                    borderRadius: '5px',
                    fontSize: '16px',
                }}
            />
            <button
                onClick={handleInquireCustomer}
                style={{
                    backgroundColor: '#2196F3',
                    color: 'white',
                    padding: '10px 20px',
                    border: 'none',
                    borderRadius: '5px',
                    cursor: 'pointer',
                    fontSize: '16px',
                }}
            >
                Inquire
            </button>

            {customer && (
                <div style={{
                    marginTop: '20px',
                    padding: '10px',
                    border: '1px solid #ddd',
                    borderRadius: '5px',
                    backgroundColor: '#f0f0f0'
                }}>
                    <p><strong>Customer ID:</strong> {customer.id}</p>
                    <p><strong>Name:</strong> {customer.name}</p>
                </div>
            )}
        </div>
    );
};

export default InquireCustomer;
