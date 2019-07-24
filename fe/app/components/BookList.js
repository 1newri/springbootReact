import React from 'react';
import Img from 'react-image'
import { Link } from 'react-router-dom';
import './Card.css'
import Moment from 'moment';

class BookList extends React.Component{
    render(){
		const authors = this.props.authors.map((author,p) =>
			<li className="list-inline-item" key={p}>{author}(작가)</li>
		);
		const translators = this.props.translators.map((translator,p) =>
			<li className="list-inline-item" key={p}>{translator}</li>
		);
        return(
			<div className="row">
				<div className="col-sm-2">
					<Link to={'/book/detail'}>
						<Img className="img-thumbnail" src={this.props.thumbnail}/>
					</Link>
				</div>
				<div className="col-sm-10">			
					<p>
						<a>
							<strong>{this.props.title}</strong>
						</a>
					</p>
					<p>
						{authors} 저 | {translators} 역 | {this.props.publisher} | {Moment(this.props.datetime).format('YYYY-MM-DD')}
					</p>
					<p>
						{this.props.price} 원 -> {this.props.sale_price}원
					</p>
					<p>
						{this.props.contents}
					</p>
				</div>
			</div>
        )
    }
}


export default BookList;