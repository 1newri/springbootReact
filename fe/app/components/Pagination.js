import React from 'react';
import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class Pagination extends React.Component{
	constructor(props){
		super(props);
		this.state = {

		}
	}
    render(){
        return(
			<div>

				{/* <ul className="pagination">
					<li className="page-item">
						<a className="page-link" href="#">
						</a>
					</li>
				</ul> */}
				a : {this.props.isEnd}
				b : {this.props.pageableCount}
				c : {this.props.totalCount}
			</div>
        )
    }
}


export default Pagination;
