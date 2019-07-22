import React, {Component} from 'react';
import Template from './Template';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component{
	constructor(props){
		super(props);
	}

	render(){
		return(
			<Template></Template>
		)
	}
}

export default App;