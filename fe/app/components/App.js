import React, {Component} from 'react';
import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class Search extends Component{
	constructor(props){
		super(props);
		this.state = {
			bList : []
		};
	}
	getSearchBookByKeyword(){
		
		var keyword = $("#keyword").val();

		if(keyword === ""){
			alert("검색 키워드를 입력하세요.");
			return;
		}

		$.ajax({
			url : 'http://localhost:8080/book/search',
			type : 'GET',
			data : {
				keyword : keyword
			}
		}).then(({results}) => this.setState({ bList : results })

		).fail((res) => {
			if(res.responseCode){
				console.error(res.responseCode);
			}
		})
	}
	render(){
		return(
			<div className="form-inline">
				<input 
					type="text" 
					className="form-control"
					id="keyword"
				/>
				<button className="btn btn-primary" 
						onClick={
							() => this.getSearchBookByKeyword()
						}
				>
					Search
				</button>
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

class BookList extends Component{
	render(){
		return(
			<div>
			</div>
		);
	}
}

class App extends Component{
	render(){
		return(
			<div className="App">
			    <div className="row">
				    <div className="col-sm-6">
						<Search></Search>
					</div>
					<div className="col-sm-6">
						<Login></Login>
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