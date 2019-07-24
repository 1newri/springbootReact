import React from 'react';
import { Link } from 'react-router-dom';

import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';


const Login = () => {
	return(
		<div>
			<div className="form-group">
				<label>Username:</label>
				<input type="text" className="form-control" id="name"></input>
			</div>
			<div className="form-group">
				<label>password:</label>
				<input type="text" className="form-control" id="password"></input>
			</div>
			<button className="btn btn-primary">Login</button>
			<Link to={'/join'} className="btn btn-primary">Join</Link>
		</div>
	);
};

export default Login;