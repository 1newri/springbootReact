import React, {Component} from 'react';
import axios from 'axios';
import BookList from './BookList';
import Pagination from './Pagination';

import $ from 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class Search extends Component{
	state = {
		documents : [],
		meta: {}
	}
	constructor(props){
		super(props);
		this.state = {
			documents : [],
			meta : {}
		}
		this.getSearchBookByKeyword = this.getSearchBookByKeyword.bind(this);
	}
	getSearchBookByKeyword = () => {
		var list = this;
		var keyword = $("#keyword").val();

		if(keyword === ""){
			alert("검색 키워드를 입력하세요.");
			return;
		}

		axios.get(
			'http://localhost:8085/book/search', 
			{params : {
				keyword : keyword
			}}
		)
		.then(function (response){
			list.setState({
				documents : response.data.documents,
				meta : response.data.meta
			})
		})
		.catch(function(error){
			console.log(error);
		});
	}
	render(){
		return(
			<div className="container">
				<div className="row">
					<div className="input-group mb-3">
						<input 
							type="text" 
							placeholder="Search"
							className="form-control"
							id="keyword"/>
						<div className="input-group-append">
							<button className="btn btn-primary" 
									onClick={this.getSearchBookByKeyword}>
								Search
							</button>
						</div>
					</div>
				</div>
				{this.state.documents.map((book, i) => {
					return (
						<BookList
							key={i}
							contents={book.contents}
							authors={book.authors}
							datetime={book.datetime}
							isbn={book.isbn}
							publisher={book.publisher}
							price={book.price}
							sale_price={book.sale_price}
							status={book.status}
							thumbnail={book.thumbnail}
							title={book.title}
							translators={book.translators}
							url={book.url}
						/>								
					)
				})}				
				<Pagination 
					isEnd = {this.state.meta.is_end}
					pageableCount= {this.state.meta.pageable_count}
					totalCount = {this.state.meta.total_count}
				/>
			</div>
		);
	}	
}


export default Search;