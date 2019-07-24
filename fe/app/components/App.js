import React, {Component} from 'react';
import Search from './Search'
import './Search.css'

import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';


class App extends React.Component{
	render(){
		return(
			<div className="App">
			    <div className="row">
				    <div className="center-div">
						<Search></Search>
					</div>
				</div>
			</div>
		)
	}
}

export default App;