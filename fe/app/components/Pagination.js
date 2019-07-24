import React from 'react';
import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class Pagination extends React.Component{
    render(){
        return(
			<div>

				<ul className="pagination">
					<li className="page-item">
						<a className="page-link" href="#">
						</a>
					</li>
				</ul>
			{this.props.isEnd}
			{this.props.pageableCount}
			{this.props.totalCount}
			</div>
        )
    }
}


export default Pagination;