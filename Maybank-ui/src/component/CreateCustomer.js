import React, { useState } from 'react';
import axios from 'axios';

const CreateCustomer = () => {
    const [name, setName] = useState('');
    const [customer, setCustomer] = useState(null);

    const handleCreateCustomer = async () => {
        try {
        //    const response = await axios.post('/api/customers', null, { params: { name } });
            const response = await axios.post(
                               '/api/customers',
                               { name },
                               {
                                 headers: {
                                   'Content-Type': 'application/json'
                                 }
                               }
                             );
            setCustomer(response.data);
        } catch (error) {
            console.error('Error creating customer:', error);
        }
    };

    return (
        <div style={{
            maxWidth: '400px',
            margin: '0 auto',
            padding: '20px',
            border: '2px solid #4CAF50',
            borderRadius: '10px',
            backgroundColor: '#f9f9f9',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
            textAlign: 'center'
        }}>
            <h2 style={{ color: '#4CAF50' }}>Create Customers UI</h2>
            <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Name"
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
                onClick={handleCreateCustomer}
                style={{
                    backgroundColor: '#4CAF50',
                    color: 'white',
                    padding: '10px 20px',
                    border: 'none',
                    borderRadius: '5px',
                    cursor: 'pointer',
                    fontSize: '16px',
                }}
            >
                Create
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

export default CreateCustomer;
