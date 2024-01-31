import React, { useState, useEffect } from 'react';
import './Alert.css';
const Alert = ({ message, type, onClose }) => {
  const [showAlert, setShowAlert] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setShowAlert(false);
      onClose();
    }, 3000); // Adjust the timeout as needed

    return () => clearTimeout(timer);
  }, [onClose]);

  return showAlert ? (
    <div className={`alert alert-${type}`}>
      {message}
    </div>
  ) : null;
};

export default Alert;
