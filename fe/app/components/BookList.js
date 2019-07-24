import React from 'react';
import Img from 'react-image'
import './Card.css'

class BookList extends React.Component{
    render(){
        return(
			<div className="container">
				<div className="row">
					<div calssName="">
						<div className="card">
							<Img className="card-img-top" src={this.props.thumbnail}/>
							<div className="card-body">
								<h4 className="card-title">John Doe</h4>
								<p className="card-text">r</p>
								<a href="#" className="btn btn-primary">See Profile</a>
							</div>
						</div>
					</div>
				</div>
			</div>

        )
    }
}


export default BookList;