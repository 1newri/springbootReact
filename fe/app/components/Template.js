import React from 'react';

const Template = ({form, children}) => {
	return (
		<main className="list-template">
			<div className="container">
				<div>
					<button className="btn btn-primary"> Login </button>
					<button className="btn btn-primary"> Logout </button>
					<button className="btn btn-primary"> Mypage </button>
				</div>
				<div class="form-inline"> 
					<input type="text" className="form-control"/>
					<button className="btn btn-danger"> Search </button>
				</div>
				<section className="form-wrapper">
					<h4> 책 목록 </h4>
					<div>
						{form}
					</div>
				</section>
				<section className="form-wrapper">
					<h4> 인기 키워드  </h4>
					<div>
						{children}
					</div>
				</section>
			</div>
		</main>
	)
}

export default Template;