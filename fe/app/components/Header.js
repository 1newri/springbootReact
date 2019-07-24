import React from 'react';

import './Header.css'
import { Link } from 'react-router-dom';
 
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const Header = () => {
	return(
		<div className="header">
			<Link to={'/'} className="item">Home</Link>
			<Link to={'/login'} className="item">Login</Link>
			<Link to={'/logout'} className="item">Logout</Link>
			<Link to={'/mypage'} className="item">Mypage</Link>
		</div>
	);
}

export default Header;