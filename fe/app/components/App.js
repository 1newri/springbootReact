import React, {Component} from 'react';
import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class Search extends Component{
	getSearchBookByKeyword(){
		return $.ajax({
			url : 'http://localhost:8080/test',
			type : 'GET'
		}).fail((res) => {
			if(res.responseCode){
				console.error(res.responseCode);
			}
		})
	}
	render(){
		return(
			<div className="form-inline">
				<input type="text" className="form-control"/>
				<button className="btn btn-primary" onClick={() => this.getSearchBookByKeyword()}>
				Search</button>
			</div> 
		);
	}	
}


class Login extends Component{
	render(){
		return(
			<div>
				<a className="btn btn-success btn-sm" href="/login">Login</a>
				<a className="btn btn-danger btn-sm" href="/logout">Logout</a>
				<a className="btn btn-success btn-sm" href="/mypage">mypage</a>
			</div>
		);
	}
}

class App extends Component{
	render(){
		return(
			<div className="App">
				<Search></Search>		
				<Login></Login>
			</div>
		)
	}
}

export default App;