import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.css'

import App from './components/App';


const rootElement = document.getElementById('react');    
ReactDOM.render(<App/>, rootElement);

if (module.hot) {
    module.hot.accept();
  }