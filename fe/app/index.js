import React, { PropTypes } from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, browserHistory } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.css'

import App from './components/App';
import Login from './components/Login';
import Join from './components/Join';
import Mypage from './components/Mypage';
import Header from './components/Header';
import Book from './components/Book';


const rootElement = document.getElementById('react');
const router =  (
	<Router history = {browserHistory}>
		<Header></Header>
		<div>
			<Route exact path="/" component={App}></Route>
			<Route exact path="/login" component={Login}></Route>
			<Route exact path="/join" component={Join}></Route>
			<Route exact path="/mypage" component={Mypage}></Route>
			<Route exact path="/book/detail" component={Book}></Route>
		</div>
	</Router>
)

ReactDOM.render(router, rootElement);

if (module.hot) {
    module.hot.accept();
  }