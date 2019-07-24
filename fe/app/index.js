import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.css'

import App from './components/App';
import Login from './components/Login';
import Join from './components/Join';
import Mypage from './components/Mypage';
import Header from './components/Header';


const rootElement = document.getElementById('react');    
ReactDOM.render(
	<Router>
		<Header></Header>
		<div>
			<Route exact path="/" component={App}></Route>
			<Route path="/login" component={Login}></Route>
			<Route path="/join" component={Join}></Route>
			<Route path="/mypage" component={Mypage}></Route>
		</div>
	</Router>
	, rootElement
);

if (module.hot) {
    module.hot.accept();
  }