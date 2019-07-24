import React, {Component} from 'react';

import axios from 'axios';

import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class Join extends Component{
	state = {
		userId : '',
		result : ''
	}
	constructor(props){
		super(props);
		this.state = {
			userId : '',
			result : ''
		}
		this.userJoin = this.userJoin.bind(this)
	}
	userJoin = () => {

		var userId = $("#userId").val();
		var userPw = $("#userPw").val();


		console.log(userId + "/ " +userPw);

		if(userId === ""){
			alert("Username을 입력하세요.");
			return;
		}

		if(userPw === ""){
			alert("password를 입력하세요.");
			return;
		}

		var result = this;

		const data = {
			userId : userId,
			userPw : userPw
		}

		console.log(data);

		axios.post('http://localhost:8085/user/join', data)
		.then(function (response){
			result.setState({
				result : response.data
			})
			debugger;
			console(response.data);

			if(response.data === "success"){
				alert("회원가입 성공!");
			}else{
				alert("회원가입 실패!");
			}
		})
		.catch(function(error){
			console.log(error);
		});
	}
	render(){
		return(
			<div>
				<div className="form-group">
					<label>Username:</label>
					<input type="text" className="form-control" id="userId"></input>
				</div>
				<div className="form-group">
					<label>password:</label>
					<input type="password" className="form-control" id="userPw"></input>
				</div>
				<button className="btn btn-primary"
						onClick={this.userJoin}>
				Join</button>
			</div>
		);
	}
}

export default Join;