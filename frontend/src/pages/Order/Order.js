import React, {Component} from 'react';
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheckCircle, faShoppingBag} from "@fortawesome/free-solid-svg-icons";

import {IMG_URL} from "../../utils/constants/url";
import {fetchOrder, addOrder} from "../../actions/order-actions";
import {validateEmail} from "../../utils/input-validators";
import Spinner from "../../component/Spinner/Spinner";

class Order extends Component {
    state = {
        firstName: "",
        lastName: "",
        city: "",
        address: "",
        postIndex: "",
        phoneNumber: "",
        email: "",
        validateEmailError: "",
        loading:false
    };

    componentDidMount() {
        this.props.fetchOrder();
    }

    onFormSubmit = (event) => {
        event.preventDefault();

        let totalPrice = 0;
        this.props.perfumes.map((perfume) => totalPrice = totalPrice + perfume.price);

        const perfumeList = this.props.perfumes;
        const {firstName, lastName, city, address, postIndex, phoneNumber, email} = this.state;
        const validateEmailError = validateEmail(email);

        if (validateEmailError) {
            this.setState({
                validateEmailError
            });
        } else {
            const order = {firstName, lastName, city, address, postIndex, phoneNumber, email, perfumeList, totalPrice};

            this.setState({loading: true})


            this.props.addOrder(order, this.props.history);
        }
    };

    handleInputChange = (event) => {
        const {name, value} = event.target;

        this.setState({
            [name]: value
        });
    };

    render() {
        const {perfumes} = this.props;
        const {firstName, lastName, city, address, postIndex, phoneNumber, email, validateEmailError} = this.state;
        const {firstNameError, lastNameError, cityError, addressError, postIndexError, phoneNumberError, emailError} = this.props.errors;

        let totalPrice = 0;
        perfumes.map((perfume) => totalPrice = totalPrice + perfume.price);

        return (

            <div className="container mt-5 pb-5">
                <h4 className="mb-4 text-center">
                    <FontAwesomeIcon className="mr-2" icon={faShoppingBag}/> ĐẶT HÀNG
                </h4>
                <br/>
                {this.state.loading?<Spinner/>:<form onSubmit={this.onFormSubmit}>
                    <div className="row">
                        <div className="col-lg-6">
                            <div className="form-group row">
                                <label className="col-sm-2 col-form-label">Họ:</label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={lastNameError ? "form-control is-invalid" : "form-control"}
                                        name="lastName"
                                        value={lastName}
                                        placeholder="Nhập họ của bạn"
                                        onChange={this.handleInputChange}/>
                                    <div className="invalid-feedback">{lastNameError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-2 col-form-label">Tên:</label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={firstNameError ? "form-control is-invalid" : "form-control"}
                                        name="firstName"
                                        value={firstName}
                                        placeholder="Nhập tên của bạn"
                                        onChange={this.handleInputChange}/>
                                    <div className="invalid-feedback">{firstNameError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-2 col-form-label">Thành phố:</label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={cityError ? "form-control is-invalid" : "form-control"}
                                        name="city"
                                        value={city}
                                        placeholder="Thành phố"
                                        onChange={this.handleInputChange}/>
                                    <div className="invalid-feedback">{cityError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-2 col-form-label">Địa chỉ:</label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={addressError ? "form-control is-invalid" : "form-control"}
                                        name="address"
                                        value={address}
                                        placeholder="Địa chỉ"
                                        onChange={this.handleInputChange}/>
                                    <div className="invalid-feedback">{addressError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-2 col-form-label">Số nhà:</label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={postIndexError ? "form-control is-invalid" : "form-control"}
                                        name="postIndex"
                                        value={postIndex}
                                        placeholder="Số nhà"
                                        onChange={this.handleInputChange}/>
                                    <div className="invalid-feedback">{postIndexError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-2 col-form-label">Số điện thoại:</label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={phoneNumberError ? "form-control is-invalid" : "form-control"}
                                        name="phoneNumber"
                                        value={phoneNumber}
                                        placeholder="(0xxxxxxxxx)"
                                        onChange={this.handleInputChange}/>
                                    <div className="invalid-feedback">{phoneNumberError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-2 col-form-label">Email:</label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={emailError || validateEmailError ? "form-control is-invalid" : "form-control"}
                                        name="email"
                                        value={email}
                                        placeholder="example@gmail.com"
                                        onChange={this.handleInputChange}/>
                                    <div className="invalid-feedback">{emailError || validateEmailError}</div>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6">
                            <div className="container-fluid">
                                <div className="row">
                                    {perfumes.map((perfume) => {
                                        return (
                                            <div key={perfume.id} className="col-lg-6 d-flex align-items-stretch">
                                                <div className="card mb-5">
                                                    <img src={IMG_URL + `${perfume.filename}`}
                                                         className="rounded mx-auto w-50"/>
                                                    <div className="card-body text-center">
                                                        <h5>{perfume.perfumeTitle}</h5>
                                                        <h6>{perfume.brandName}</h6>
                                                        <h6><span>$ {perfume.price}</span>.00</h6>
                                                    </div>
                                                </div>
                                            </div>
                                        )
                                    })}
                                </div>
                            </div>
                            <button type="submit" className="btn btn-primary btn-lg btn-success px-5 float-right">
                                <FontAwesomeIcon icon={faCheckCircle}/> Xác nhận
                            </button>
                            <div className="row">
                                <h4>Tổng : $ <span>{totalPrice}</span>.00</h4>
                            </div>
                        </div>
                    </div>
                </form>}


            </div>
        );
    }
}

Order.propTypes = {
    fetchOrder: PropTypes.func.isRequired,
    addOrder: PropTypes.func.isRequired,
    perfumes: PropTypes.array.isRequired,
    errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    perfumes: state.order.perfumes,
    errors: state.order.errors
});

export default connect(mapStateToProps, {fetchOrder, addOrder})(Order);
