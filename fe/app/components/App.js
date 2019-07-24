import React, {Component} from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Search from './Search'
import './Search.css'

import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class BookList extends Component{
	render(){
		return(
			<div>
				
			</div>
		);
	}
}

class App extends React.Component{
	render(){
		return(
			<div className="App">
			    <div className="row">
				    <div className="center-div">
						<Search></Search>
					</div>
				</div>
				<div className="row">
					<BookList></BookList>
				</div>
			</div>
		)
	}
}

export default App;